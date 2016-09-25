package com.floatinvoice.business;

import java.util.List;
import java.util.Map;

import com.floatinvoice.business.dao.EnquiryDao;
import com.floatinvoice.business.dao.FIApplicationDao;
import com.floatinvoice.business.dao.FIProductDao;
import com.floatinvoice.business.dao.OrgReadDao;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.messages.AppDtlsMsg;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ProductDtls;

public class FIApplicationServiceImpl implements FIApplicationService {

	EnquiryDao enquiryDao;
	OrgReadDao orgReadDao;
	FIApplicationDao fiApplicationDao;
	FIProductDao fiProductDao;
	
	public FIApplicationServiceImpl(){
		
	}
	
	public FIApplicationServiceImpl( EnquiryDao enquiryDao, OrgReadDao orgReadDao, FIApplicationDao fiApplicationDao,
			FIProductDao fiProductDao){
		this.enquiryDao = enquiryDao;
		this.orgReadDao = orgReadDao;
		this.fiApplicationDao = fiApplicationDao;
		this.fiProductDao = fiProductDao;
	}
	
	@Override
	public List<AppDtlsMsg> viewAllApplications(String acro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppDtlsMsg viewOneApplication(String refId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMsg saveApplication(AppDtlsMsg appDetails) {
		Map<String, Object> orgInfo = orgReadDao.findOrgAndUserId(UserContext.getUserName());
		ProductDtls productDtls = fiProductDao.findProductByRefId(appDetails.getRefId());
		return fiApplicationDao.saveApplication(appDetails, (Integer)orgInfo.get("COMPANY_ID"), 
				(Integer)orgInfo.get("USER_ID"), productDtls.getProductId());
		

	}

	@Override
	public BaseMsg editApplication(AppDtlsMsg appDetails) {
		return null;
	}

}
