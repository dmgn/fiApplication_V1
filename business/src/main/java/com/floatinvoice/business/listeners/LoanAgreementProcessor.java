package com.floatinvoice.business.listeners;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.usermodel.PositionInParagraph;
import org.apache.poi.xwpf.usermodel.TextSegement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ReflectionUtils;

import com.floatinvoice.business.dao.LoanAgreementDao;
import com.floatinvoice.messages.LoanAgreementTemplateDtls;

public class LoanAgreementProcessor {

	private LoanAgreementDao loanAgreementDao;
	
	public LoanAgreementProcessor(){
		
	}
	
	public LoanAgreementProcessor(LoanAgreementDao loanAgreementDao){
		this.loanAgreementDao = loanAgreementDao;
	}
	
	@Scheduled(fixedRate=5000)
	public void processAgreement() throws Exception{
		// 1. Fetch the agreements ready for loan disbursement
		// 2. Populate all details
		// 3. Save the agreement details
		
		List<LoanAgreementTemplateDtls> list = loanAgreementDao.fetchPendingAgreementsForApprovedLoans();
		for(LoanAgreementTemplateDtls template : list){
			Date dt = template.getLoanDispatchDt();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			
			template.setDay(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "th ");
			template.setMonth( cal.getDisplayName( Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) );
			template.setYear(String.valueOf(cal.get(Calendar.YEAR)));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
			Date date = template.getLoanCloseDt();
			String s = sdf.format(date);
			template.setLoanCloseDtDisplay(s);
			populateAgreementTemplate(template);
		}
	}
	
	public void populateAgreementTemplate(LoanAgreementTemplateDtls template) throws SQLException, InvalidFormatException, IOException, NoSuchFieldException, SecurityException{
		// Query to find the agreement params
		//byte[] blob = template.getLoanAgreementTemplate();
		long count = 0;
		List<String> params = loanAgreementDao.fetchTemplateParams(template.getAgreementRefId());
		Map<String, String> replacements = new LinkedHashMap<>();
		XWPFDocument doc = new XWPFDocument(OPCPackage.open(template.getLoanAgreementTemplate()));
		for(String param : params){
			Field field = template.getClass().getDeclaredField(JdbcUtils.convertUnderscoreNameToPropertyName(param));
			ReflectionUtils.makeAccessible(field);
			 if( field.getName().equalsIgnoreCase("companyDirectors")){
				 replacements.put("%"+param+"%", template.getCompanyDirectors().get(0));
			 }else{
				 replacements.put("%"+param+"%", ReflectionUtils.getField(field, template) == null ? "" :
					 (String) ReflectionUtils.getField(field, template));
			 }
		}		
		for (XWPFParagraph paragraph : doc.getParagraphs()) {
			List<XWPFRun> runs = paragraph.getRuns();			
			 for (Map.Entry<String, String> replPair : replacements.entrySet()) {    
			        String find = replPair.getKey();
			        String repl = replPair.getValue();
			        TextSegement found = paragraph.searchText(find, new PositionInParagraph());
			        if ( found != null ) {
			            count++;
			            if ( found.getBeginRun() == found.getEndRun() ) {
			              // whole search string is in one Run
			              XWPFRun run = runs.get(found.getBeginRun());
			              String runText = run.getText(run.getTextPosition());
			              String replaced = runText.replace(find, repl);
			              run.setText(replaced, 0);
			            } else {
			              // The search string spans over more than one Run
			              // Put the Strings together
			              StringBuilder b = new StringBuilder();
			              for (int runPos = found.getBeginRun(); runPos <= found.getEndRun(); runPos++) {
			                XWPFRun run = runs.get(runPos);
			                b.append(run.getText(run.getTextPosition()));
			              }                       
			              String connectedRuns = b.toString();
			              String replaced = connectedRuns.replace(find, repl);

			              // The first Run receives the replaced String of all connected Runs
			              XWPFRun partOne = runs.get(found.getBeginRun());
			              partOne.setText(replaced, 0);
			              // Removing the text in the other Runs.
			              for (int runPos = found.getBeginRun()+1; runPos <= found.getEndRun(); runPos++) {
			                XWPFRun partNext = runs.get(runPos);
			                partNext.setText("", 0);
			              }                          
			            }
			          }
			 }
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfConverter.getInstance().convert(doc, baos, null);
		byte [] byteArrayOS = baos.toByteArray();
		loanAgreementDao.updateAgreement(byteArrayOS, template.getLoanId());
		doc.close();
	}
}
