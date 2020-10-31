package com.blizz.portal.ui;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import com.blizz.common.BasePage;
import com.blizz.common.portal.PortalHomePage;
import com.blizz.objectrepository.portal.CommonLocators;
import com.blizz.utils.CommonFunctionLibrary;
import com.blizz.utils.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortalLoginPage extends BasePage {
	private static Logger LOGGER = LoggerFactory.getLogger(PortalLoginPage.class);
	CommonFunctionLibrary common;
	public static PortalLoginPage obj;

	public PortalLoginPage(WebDriver driver) {
		super(driver);
		LOGGER.info("" + driver);
		welcomePopup();
		enterCredentials();
		clickLogin();
		acceptCookies();
		checkProduct();
		
}

	public void welcomePopup() {
		try {
			driver.get(ConfigManager.getProperty("URL"));
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void enterCredentials() {
		try {
			if (isElementPresent(CommonLocators.searchBar, 15)) {
				sendKeys(CommonLocators.searchBar, ConfigManager.getProperty("searchKeyword"), 10);
				Thread.sleep(3000);
				LOGGER.info("User able to enter username &  password in placeholder");
			} else {

				LOGGER.warn("User not able to enter username & password");
				throw new ElementNotVisibleException("Username & password placeholder not visible");
			}
		} catch (Exception e) {
			LOGGER.warn("Error Found in Enter Credential");
			System.out.println(e);
		}
	}

	public PortalHomePage clickLogin() {
		try {
			if (isElementPresent(CommonLocators.searchDownbutton, 15))
				jsButtonClick(CommonLocators.searchDownbutton);
			else {

				LOGGER.warn("login button is not available");
				throw new ElementNotVisibleException("User not able to login on login button");
			}
			return new PortalHomePage(driver);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public void acceptCookies()
	{
		try {
			if (isElementPresent(CommonLocators.acceptCookies, 15))
				jsButtonClick(CommonLocators.acceptCookies);
			else {

				LOGGER.warn("accepted cookies");
				throw new ElementNotVisibleException("not accepted cookies");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	public void checkProduct()
	{
		try {
			if (isElementPresent(CommonLocators.productd, 15))
				jsButtonClick(CommonLocators.productd);
			else {

				LOGGER.warn("product detail is visible");
				throw new ElementNotVisibleException("User not able to see product detail");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
		
	}
	
	
	

