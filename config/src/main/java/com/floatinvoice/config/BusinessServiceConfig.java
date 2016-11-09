package com.floatinvoice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.floatinvoice.business.BankService;
import com.floatinvoice.business.BankServiceImpl;
import com.floatinvoice.business.EmailProperties;
import com.floatinvoice.business.EmailService;
import com.floatinvoice.business.EmailServiceImpl;
import com.floatinvoice.business.EnquiryService;
import com.floatinvoice.business.EnquiryServiceImpl;
import com.floatinvoice.business.FIApplicationService;
import com.floatinvoice.business.FIApplicationServiceImpl;
import com.floatinvoice.business.FileService;
import com.floatinvoice.business.FileServiceImpl;
import com.floatinvoice.business.FraudInvoiceService;
import com.floatinvoice.business.FraudInvoiceServiceImpl;
import com.floatinvoice.business.InvoiceService;
import com.floatinvoice.business.InvoiceServiceImpl;
import com.floatinvoice.business.ProductService;
import com.floatinvoice.business.ProductServiceImpl;
import com.floatinvoice.business.ProfileService;
import com.floatinvoice.business.ProfileServiceImpl;
import com.floatinvoice.business.RegistrationService;
import com.floatinvoice.business.RegistrationServiceImpl;
import com.floatinvoice.business.dao.EnquiryDao;
import com.floatinvoice.business.dao.FIApplicationDao;
import com.floatinvoice.business.dao.FIProductDao;
import com.floatinvoice.business.dao.OrgReadDao;


@Configuration
public class BusinessServiceConfig {

	@Autowired
	ReadServicesConfig readServicesConfig;
	
	@Autowired
	Environment environment;
	
	@Bean
	public FIApplicationService fiAppService(){
		return new FIApplicationServiceImpl(readServicesConfig.enquiryDao(), readServicesConfig.orgReadDao(), readServicesConfig.fiApplicationDao(),
				readServicesConfig.productDao(), registrationService(), bankService(), enquiryService());
	}
	
	@Bean
	public ProductService productService(){
		return new ProductServiceImpl(readServicesConfig.productDao());
	}
	
	@Bean
	public EnquiryService enquiryService(){
		return new EnquiryServiceImpl(readServicesConfig.enquiryDao(), emailService(), registrationService(), readServicesConfig.orgReadDao() );
	}
	
	@Bean
	public 	InvoiceService invoiceService(){
		return new InvoiceServiceImpl(readServicesConfig.invoiceInfoReadDao(), readServicesConfig.invoiceFileUploadDao(),
				readServicesConfig.orgReadDao());
	}
	
	@Bean
	public 	ProfileService profileService(){
		return new ProfileServiceImpl(readServicesConfig.profileDao());
	}
	

	@Bean
	public 	RegistrationService registrationService(){
		return new RegistrationServiceImpl(readServicesConfig.registrationDao());
	}

	@Bean
	public 	BankService bankService(){
		return new BankServiceImpl(readServicesConfig.bankInfoDao(), readServicesConfig.orgReadDao());
	}
	
	@Bean
	public FraudInvoiceService fraudInvoiceService(){
		return new FraudInvoiceServiceImpl(readServicesConfig.fraudInvoiceInfoDao(), 
				readServicesConfig.orgReadDao());
	}
	
	@Bean
	public FileService fileService(){
		return new FileServiceImpl(readServicesConfig.fileServiceDao());
	}
	
	@Bean
	public EmailService emailService(){
		EmailProperties emailProps = new EmailProperties();
		emailProps.setFromEmailAddress(environment.getRequiredProperty("email.from"));
		emailProps.setPassword(environment.getRequiredProperty("smtp.password"));
		emailProps.setSmtpServer(environment.getRequiredProperty("smtp.server"));
		emailProps.setUser(environment.getRequiredProperty("smtp.user"));
		return new EmailServiceImpl(emailProps);
	}
}
