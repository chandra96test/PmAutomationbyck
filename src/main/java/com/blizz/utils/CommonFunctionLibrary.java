package com.blizz.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;

public class CommonFunctionLibrary {

	static WebDriver driver;
	WebDriverWait wait;
	public Dimension size;

	public CommonFunctionLibrary(WebDriver driver) {
		this.driver = driver;
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


	public List<String> dropdownList(By listid, String tagelem) {
		List<String> optionlist = new ArrayList<String>();
		WebElement items = driver.findElement(listid);
		List<WebElement> options = items.findElements(By.tagName(tagelem));
		System.out.println(options.size());

		for (WebElement data : options) {
			if (!data.getText().contains("Please Select")) {
				optionlist.add(data.getText());
			}

		}

		return optionlist;

	}

	public void selectdropdownByIndex(By listitem, int Index) {
		Select dropdownitem = new Select(findElement(listitem, 5));
		dropdownitem.selectByIndex(Index);

	}

	public void selectdropdownByValue(By listvalue, String Value) {
		Select dropdownitem = new Select(findElement(listvalue, 5));
		dropdownitem.selectByValue(Value);
	}

	public void selectdropdownByvisibleTxt(By listvalue, String Value) {
		Select dropdownitem = new Select(findElement(listvalue, 5));
		dropdownitem.selectByVisibleText(Value);
	}

	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}

	}

	public static void FullScreenCaptureExample() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "/target/Reports/screenshot.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void writeLog(ExtentTest LOGGER, String message, Status status, ECategory category, Exception e) {
		LOGGER.assignAuthor("Chandra Kiran");
		LOGGER.assignCategory(category.toString());
		MediaEntityModelProvider mediaModel = null;
		try {
			mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (status.equals(Status.FAIL) || status.equals(Status.ERROR))
			LOGGER.log(status, message, mediaModel);
		else
			LOGGER.log(status, message);
	}
}
