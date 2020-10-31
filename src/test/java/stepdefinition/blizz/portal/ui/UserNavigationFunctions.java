package stepdefinition.blizz.portal.ui;

import org.apache.log4j.Logger;
import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;

import com.blizz.common.portal.PortalHomePage;
import com.blizz.utils.CommonFunctionLibrary;
import com.blizz.portal.ui.PortalLoginPage;

import cucumber.api.java.en.Given;
import stepdefinition.AttachHooks;

public class UserNavigationFunctions {
	PortalLoginPage portalLoginPage;
	PortalHomePage portalHomePage;
	CommonFunctionLibrary common;
	private static Logger LOGGER = Logger.getLogger(UserNavigationFunctions.class);


	@Given("^User navigates to Home page$")
	public void User_navigates_to_Home_page() throws Throwable {
		try {
			portalLoginPage = new PortalLoginPage(AttachHooks.driver);
		} catch (Exception e) {

			LOGGER.warn("Exception Occured:" + e);
			LOGGER.error("User is not navigated to Home Page");
			Assert.fail();
		}
		PortalLoginPage.obj = portalLoginPage;
	}
	
}

      