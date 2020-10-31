package com.blizz.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blizz.utils.CommonFunctionLibrary;

public class BasePage {

	protected static WebDriver driver;
	static WebDriverWait wait;
	public static CommonFunctionLibrary functionLibrary;
	private static Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
		functionLibrary = new CommonFunctionLibrary(this.driver);

	}

	public boolean isElementPresent(String locator, int timeoutInSeconds) {
		try {
			wait = new WebDriverWait(driver, timeoutInSeconds);
			WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			if (elem != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public WebElement findElement(String locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		if (elem != null) {
			return elem;
		} else {
			return null;
		}
	}

	public WebElement findElement(By locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (elem != null) {
			return elem;
		} else {
			return null;
		}
	}

	public List<WebElement> findAllWebElements(By locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		List<WebElement> elem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return elem;
	}

	public boolean buttonClick(String locator, int timeoutSeconds) {
		try {
			WebElement element = findElement(locator, timeoutSeconds);
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void sendKeys(String locator, String str, int timeoutInSeconds) {
		findElement(locator, timeoutInSeconds).clear();
		findElement(locator, timeoutInSeconds).sendKeys(str);
	}

	public void sendKeys(By locator, String str, int timeoutInSeconds) {
		findElement(locator, timeoutInSeconds).clear();
		findElement(locator, timeoutInSeconds).sendKeys(str);
	}

	public boolean jsButtonClick(By locator) {
		try {
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = findElement(locator, 10);
			js.executeScript("arguments[0].click();", element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean jsButtonClick(String locator) {
		try {
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = findElement(locator, 10);
			js.executeScript("arguments[0].click();", element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean jsFunction(String function) {
		try {
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("" + function + "");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void switchToAlertOk() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void switchToAlertCancel() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void mouseHover(By elem) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(elem);
		action.moveToElement(element).build().perform();
	}

	public boolean mouseHover(WebElement Mhover) {
		try {
			Actions Hover = new Actions(driver);
			Hover.moveToElement(Mhover).build().perform();
			Thread.sleep(2000);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	

	public boolean switchFrame(String frameId) {
		try {
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
			// driver.switchTo().frame(frameId);
			System.out.println(driver.getWindowHandle());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}