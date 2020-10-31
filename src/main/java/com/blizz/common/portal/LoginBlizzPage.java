
package com.blizz.common.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blizz.common.BasePage;
import com.blizz.objectrepository.portal.CommonLocators;
import com.blizz.utils.ConfigManager;

public class LoginBlizzPage extends BasePage {

	private static Logger logger = LoggerFactory.getLogger(LoginBlizzPage.class);
	public static LoginBlizzPage obj;
	public WebDriver driver;

	public LoginBlizzPage(WebDriver driver) {
		super(driver);
		logger.info("" + driver);

	}

	public void wellcomePopup() {
		try {
			if (isElementPresent(CommonLocators.searchBar, 15)) {
				WebElement searchBar = driver.findElement(By.xpath(CommonLocators.searchBar));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", searchBar);
			} else {

				logger.warn("User is not able to see welcome pop up");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void enterCredentials() {
		try {
			if (isElementPresent(CommonLocators.searchBar, 15)) {
				sendKeys(CommonLocators.searchBar, ConfigManager.getProperty("searchKeyword"), 20);

				logger.info("User able to enter keyword in placeholder");
			} else {
				logger.warn("User not able to enter keyword");
				throw new ElementNotVisibleException("placeholder not visible");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void searchBotton() {
		try {
			if (isElementPresent(CommonLocators.searchButton, 15)) {
				WebElement searchBotton = driver.findElement(By.xpath(CommonLocators.searchButton));

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", searchBotton);
			} else {
				logger.warn("search button is not available");
				throw new ElementNotVisibleException("User not able to login on login button");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
