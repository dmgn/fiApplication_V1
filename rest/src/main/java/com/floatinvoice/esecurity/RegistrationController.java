package com.floatinvoice.esecurity;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.floatinvoice.business.ProfileService;
import com.floatinvoice.business.RegistrationService;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.RegistrationStep1SignInDtlsMsg;
import com.floatinvoice.messages.RegistrationStep2CorpDtlsMsg;
import com.floatinvoice.messages.RegistrationStep3UserPersonalDtlsMsg;
import com.floatinvoice.messages.SupportDocDtls;
import com.floatinvoice.messages.UploadMessage;
import com.floatinvoice.messages.UserProfile;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	ProfileService profileService;
	
	@Autowired
	RegistrationService registrationSvc;

	
	@RequestMapping(value = { "/delete/usrInfo"}, method = RequestMethod.DELETE)
    public ResponseEntity<BaseMsg> delete(@RequestParam(value="usr", required=true) String usr) {
		BaseMsg baseMsg = profileService.deleteUser(usr);
        return new ResponseEntity<>(baseMsg, HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/usrInfo"}, method = RequestMethod.GET)
    public UserProfile userInfo(@RequestParam(value="usr", required=true) String usr) {
		UserProfile userProfile = profileService.fetchUserProfile(usr);
        return userProfile;
    }
 
	@RequestMapping(value = { "/docs"}, method = RequestMethod.GET)// Normal registration to return the document upload page
    public ModelAndView docUpload() {
		UserProfile userProfile = profileService.fetchUserProfile(UserContext.getUserName());
        ModelAndView model = new ModelAndView();
        model.addObject("acronym", userProfile.getOrgAcronym());
        model.setViewName("registerDocsPage");
        return model;
    }
	
	@RequestMapping(value = { "/signInInfo"}, method = RequestMethod.POST)
    public ResponseEntity<BaseMsg> signInInfo(@RequestBody RegistrationStep1SignInDtlsMsg regStep1,
    		 HttpSession session, Model model) {
		String email = regStep1.getEmail();
		model.addAttribute("email", email);		
		initializeSession(email, session);
		if(profileService.verifyTempUserProfileExists(email)){			
			return new ResponseEntity<>(registrationSvc.updateRegisteredEmail(regStep1) , HttpStatus.OK);
		}else{
			return new ResponseEntity<>(registrationSvc.registerSignInInfo(regStep1), HttpStatus.OK);
		}
        
    }
	
	@RequestMapping(value = { "/corpInfo"}, method = RequestMethod.POST)
    public ResponseEntity<BaseMsg> regCorpInfo(@RequestBody RegistrationStep2CorpDtlsMsg regStep2 ) {
       // ModelAndView model = new ModelAndView();
       // model.setViewName("homePage");
        return new ResponseEntity<>(registrationSvc.registerOrgInfo(regStep2), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/findCorpInfo"}, method = RequestMethod.POST)
    public ResponseEntity<RegistrationStep2CorpDtlsMsg> findCorpInfo(@RequestParam(value="acro", required=true) String acro) {
        return new ResponseEntity<>(registrationSvc.fetchOrgInfo(acro), HttpStatus.OK);
    }
	
	
	@RequestMapping(value = { "/usrInfo"}, method = RequestMethod.POST)
    public ResponseEntity<BaseMsg> regUserInfo(@RequestBody RegistrationStep3UserPersonalDtlsMsg regStep3 ) {
       // ModelAndView model = new ModelAndView();
       // model.setViewName("homePage");
        return new ResponseEntity<>(registrationSvc.registerUserBankInfo(regStep3), HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/findUsrInfo"}, method = RequestMethod.POST)
    public ResponseEntity<RegistrationStep3UserPersonalDtlsMsg> findUsrInfo(@RequestParam(value="acro", required=true) String acro) {
        return new ResponseEntity<>(registrationSvc.fetchUserBankInfo(acro), HttpStatus.OK);
    }
	
	@RequestMapping(value = { "/upload"}, method = RequestMethod.POST)						// Upload supporting docs on registration page
    public  ResponseEntity<BaseMsg> uploadFile(/*@RequestParam(value="acro", required=true) String acro,*/
    		@RequestParam(value="filename", required=false) String fileName,
    		@RequestParam(value="category", required=true) String category,
    		@RequestParam(value="file", required=true) MultipartFile file) throws Exception {    
		UploadMessage uploadMsg = new UploadMessage(file);
    	uploadMsg.setFileName(file.getOriginalFilename());
    	//uploadMsg.setAcronym(acro);
    	uploadMsg.setCategory(category);
    	return new ResponseEntity<>(registrationSvc.uploadSupportDocs(uploadMsg), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = { "/docs/summary"}, method = RequestMethod.GET)
    public  ResponseEntity<ListMsg<SupportDocDtls>> summary(@RequestParam(value="acro", required=false) String acro) throws Exception {    
		
    	return new ResponseEntity<>(registrationSvc.summary(acro), HttpStatus.OK);
	}
	
	 protected void initializeSession(String email, HttpSession session){	    	
    	if(session != null){
    		session.setAttribute("remote-user", email);
    		UserContext.addContextData(email, null, null, 0);
    	}
    	
	  }
}
