package com.floatinvoice.test.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {

	public static void main (String... strings) throws ParserConfigurationException, SAXException, IOException{
		
		File fXmlFile = new File("C:\\Work\\Projects\\AllRegion.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("Company");
		
		List<RowObject> list = new LinkedList<>();
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);

			System.out.println("\n");
			RowObject ro = new RowObject();
			list.add(ro);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				ro.setCompanyName(eElement.getAttribute("name"));
				ro.setPostalAddress(formatString(eElement.getElementsByTagName("postaladdress").item(0).getTextContent()));
				
				ro.setPhone(formatString(eElement.getElementsByTagName("phone").item(0).getTextContent()));
				ro.setFax(formatString(eElement.getElementsByTagName("fax").item(0).getTextContent()));
				ro.setEmail(formatString(eElement.getElementsByTagName("email").item(0).getTextContent()));
				ro.setPlantAddress(formatString(eElement.getElementsByTagName("plantaddress").item(0).getTextContent()));
				ro.setPlantPhone(formatString(eElement.getElementsByTagName("plphone").item(0).getTextContent()));
				ro.setPlantEmail(formatString(eElement.getElementsByTagName("plemail").item(0).getTextContent()));
				ro.setWebsite(formatString(eElement.getElementsByTagName("website").item(0).getTextContent()));
				ro.setMd(formatString(eElement.getElementsByTagName("md").item(0).getTextContent()));
				ro.setCp(formatString(eElement.getElementsByTagName("cp").item(0).getTextContent()));
				ro.setCpNumber(formatString(eElement.getElementsByTagName("cpnumber").item(0).getTextContent()));
				ro.setType(formatString(eElement.getElementsByTagName("type").item(0).getTextContent()));
				ro.setCons(formatString(eElement.getElementsByTagName("cons").item(0).getTextContent()));
				ro.setYear(formatString(eElement.getElementsByTagName("year").item(0).getTextContent()));
				ro.setProducts(formatString(eElement.getElementsByTagName("products").item(0).getTextContent()));
				ro.setAp(formatString(eElement.getElementsByTagName("ap").item(0).getTextContent()));
				
				System.out.println(eElement.getAttribute("name"));
				System.out.println(formatString(eElement.getElementsByTagName("postaladdress").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("phone").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("fax").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("email").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("plantaddress").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("plphone").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("plemail").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("website").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("md").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("cp").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("cpnumber").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("type").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("cons").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("year").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("products").item(0).getTextContent()));
				System.out.println(formatString(eElement.getElementsByTagName("ap").item(0).getTextContent()));
				

			}

		}
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Prospects");
        int rowCount = 0;
       
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        
        Row header = sheet.createRow(rowCount++);
        createHeaderRow(header, cellStyle);
       // header.setRowStyle(cellStyle);
        
		for(RowObject tmp: list){
			Row row = sheet.createRow(rowCount++);
			fillRow(row, tmp);
		}
		try (FileOutputStream outputStream = new FileOutputStream("C:\\Work\\Projects\\ProspectList.xlsx")) {
            workbook.write(outputStream);
        }
	}
	public static void createHeaderRow( Row row, CellStyle cellStyle ){
		int columnCount = 0;
		Cell cell1 = row.createCell(columnCount);
		cell1.setCellValue("Company Name");
		cell1.setCellStyle(cellStyle);
		Cell cell2 = row.createCell(++columnCount);
		cell2.setCellValue("Postal Addr");
		cell2.setCellStyle(cellStyle);

		Cell cell3 = row.createCell(++columnCount);
		cell3.setCellValue("Phone");
		cell3.setCellStyle(cellStyle);

		
		Cell cell4 = row.createCell(++columnCount);
		cell4.setCellValue("Fax");
		cell4.setCellStyle(cellStyle);

		
		Cell cell5 = row.createCell(++columnCount);
		cell5.setCellValue("Email");
		cell5.setCellStyle(cellStyle);

		
		Cell cell6 = row.createCell(++columnCount);
		cell6.setCellValue("Plant Address");
		cell6.setCellStyle(cellStyle);

		Cell cell7 = row.createCell(++columnCount);
		cell7.setCellValue("Plant Phone");
		cell7.setCellStyle(cellStyle);

		
		Cell cell8 = row.createCell(++columnCount);
		cell8.setCellValue("Plant Email");
		cell8.setCellStyle(cellStyle);

		
		Cell cell9 = row.createCell(++columnCount);
		cell9.setCellValue("Website");
		cell9.setCellStyle(cellStyle);

		Cell cell10 = row.createCell(++columnCount);
		cell10.setCellValue("Managing Director");
		cell10.setCellStyle(cellStyle);

		Cell cell11 = row.createCell(++columnCount);
		cell11.setCellValue("Cell Phone Name");
		cell11.setCellStyle(cellStyle);

		
		Cell cell12 = row.createCell(++columnCount);
		cell12.setCellValue("Cell Phone #");
		cell12.setCellStyle(cellStyle);

		
		Cell cell13 = row.createCell(++columnCount);
		cell13.setCellValue("Industry Type");
		cell13.setCellStyle(cellStyle);

		Cell cell14 = row.createCell(++columnCount);
		cell14.setCellValue("Constitution");
		cell14.setCellStyle(cellStyle);

		
		Cell cell15 = row.createCell(++columnCount);
		cell15.setCellValue("Year");
		cell15.setCellStyle(cellStyle);

		
		Cell cell16 = row.createCell(++columnCount);
		cell16.setCellValue("Products");
		cell16.setCellStyle(cellStyle);

		Cell cell17 = row.createCell(++columnCount);
		cell17.setCellValue("Authorized Person");
		cell17.setCellStyle(cellStyle);

	}
	
	public static void fillRow(Row row, RowObject tmp){
		int columnCount = 0;
		Cell cell1 = row.createCell(columnCount);
		cell1.setCellValue((String)tmp.getCompanyName());
		
		Cell cell2 = row.createCell(++columnCount);
		cell2.setCellValue((String)tmp.getPostalAddress());
		
		Cell cell3 = row.createCell(++columnCount);
		cell3.setCellValue((String)tmp.getPhone());
		Cell cell4 = row.createCell(++columnCount);
		cell4.setCellValue((String)tmp.getFax());
		
		Cell cell5 = row.createCell(++columnCount);
		cell5.setCellValue((String)tmp.getEmail());
		
		Cell cell6 = row.createCell(++columnCount);
		cell6.setCellValue((String)tmp.getPlantAddress());

		Cell cell7 = row.createCell(++columnCount);
		cell7.setCellValue((String)tmp.getPlantPhone());
		
		Cell cell8 = row.createCell(++columnCount);
		cell8.setCellValue((String)tmp.getPlantEmail());

		Cell cell9 = row.createCell(++columnCount);
		cell9.setCellValue((String)tmp.getWebsite());

		Cell cell10 = row.createCell(++columnCount);
		cell10.setCellValue((String)tmp.getMd());

		Cell cell11 = row.createCell(++columnCount);
		cell11.setCellValue((String)tmp.getCp());
		
		Cell cell12 = row.createCell(++columnCount);
		cell12.setCellValue((String)tmp.getCpNumber());

		Cell cell13 = row.createCell(++columnCount);
		cell13.setCellValue((String)tmp.getType());

		Cell cell14 = row.createCell(++columnCount);
		cell14.setCellValue((String)tmp.getCons());
		
		Cell cell15 = row.createCell(++columnCount);
		cell15.setCellValue((String)tmp.getYear());

		Cell cell16 = row.createCell(++columnCount);
		cell16.setCellValue((String)tmp.getProducts());

		Cell cell17 = row.createCell(++columnCount);
		cell17.setCellValue((String)tmp.getAp());
		
	}
	
	public static String formatString( String s){
		if(s.contains(":")){
			int index = s.indexOf(":");
			StringBuffer sBuf = new StringBuffer(s);
			return sBuf.substring(index+1);
		}else{
			return s;
		}	

	}
}
