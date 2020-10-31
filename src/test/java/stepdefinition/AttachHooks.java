package stepdefinition;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blizz.utils.CommonFunctionLibrary;
import com.blizz.utils.ConfigManager;
import com.blizz.utils.ReusableStaticMethods;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AttachHooks {

	private Scenario scenario;
	public static WebDriver driver;
	DesiredCapabilities capabilities;
	private static Logger LOGGER = LoggerFactory.getLogger(AttachHooks.class);
	CommonFunctionLibrary functionLibrary;

//	public static ExtentReports extentReports;
//	public ExtentTest LOGGER;

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Before
	public void setUp(Scenario scenario) throws IOException, InterruptedException {

		ConfigManager.loadConfig();
		this.scenario = scenario;
		System.out.println(scenario.getName());
		/**
		 * This method Is responsible for executing Api test cases.
		 */
		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("PC")) {
			System.out.println("Web Execution Start");
			// driver connection
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--start-maximized");
			driver = new ChromeDriver(chromeoptions);
		}
	}



	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws InstantiationException, IllegalAccessException, IOException {

		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("PC")) {
			System.out.println("Closing Browser Instance");

			// functionLibrary.embedScreenshot(scenario);
			// driver.quit();
		} else if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Api")) {
			System.out.println(" Execution Stop :) ");
		} else if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Mobile")) {
			

			driver.close();
			if (scenario.isFailed()) {
				int incrementedcount = Integer.parseInt(ReusableStaticMethods.readServerFalureCountFromFile()) + 1;
				ReusableStaticMethods.writeServerFalureCountToFile(String.valueOf(incrementedcount));
			} else {
				ReusableStaticMethods.writeServerFalureCountToFile("0");
			}
			LOGGER.info("Execution Stop for scenario : " + scenario.getName());
			Date date = new Date();
			LOGGER.info(date.toString());
			LOGGER.info(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
			driver.quit();

			// DriverFactory.appiumStop();
		}
	}
}
