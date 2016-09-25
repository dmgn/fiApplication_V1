package com.floatinvoice.business;

import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.UserProfile;

public interface ProfileService {

	UserProfile fetchUserProfile( String userEmail );
	
	/*boolean isCompanyRegistered( String userEmail );
	
	boolean isPersonalInfoRegistered( String userEmail );*/
	
	int findUserRegistrationStatus(String userEmail);
	
	boolean verifyTempUserProfileExists(String userEmail);

	BaseMsg deleteUser(String userEmail);
}
