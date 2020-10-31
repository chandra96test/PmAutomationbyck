package com.blizz.common.portal;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blizz.common.BasePage;
import com.blizz.objectrepository.portal.CommonLocators;
import com.blizz.utils.CommonFunctionLibrary;

public class PortalHomePage extends LoginBlizzPage {
	public static Object obj;
	private static Logger LOGGER = LoggerFactory.getLogger(PortalHomePage.class);

	public PortalHomePage(WebDriver driver) {
		super(driver);
		homePage();
	}

	public WebDriver homePage() {
		functionLibrary = new CommonFunctionLibrary(driver);
		if (isElementPresent(CommonLocators.searchBar, 15)) {
			LOGGER.info("User is navigated to Home page");

			wellcomePopup();
			enterCredentials();

		} else {
			LOGGER.warn("User is not navigated to Home page");
			throw new ElementNotVisibleException("User is not logged in");
		}
		return driver;
	}

}
