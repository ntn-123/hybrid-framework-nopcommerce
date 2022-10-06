package commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	private String osName = System.getProperty("os.name");
	
//	@BeforeSuite
//	public void initBeforeSuite() {
//		deleteAllureReport();
//	}
//	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		if(browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
//			FirefoxProfile profile = new FirefoxProfile();
//			File file = new File(GlobalConstants.BROWSER_EXTENSIONS_FILE + "extension_2_0_12_0.crx");
//			profile.addExtension(file);
//			FirefoxOptions options = new FirefoxOptions();
//			options.setProfile(profile);
//			driver = new FirefoxDriver(options);
			
			// Disable log cua driver trong tab Console
			//System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			//System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.BROWSER_LOG + "FirefoxLog.log");
			
			driver = new FirefoxDriver();
		} else if(browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if(browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
//			File file = new File(GlobalConstants.BROWSER_EXTENSIONS_FILE + "to_google_translate-4.2.0.xpi");
//			ChromeOptions options = new ChromeOptions();
//			options.addExtensions(file);
//			driver = new ChromeDriver(options);
			
			// Disable log cua driver trong tab Console
			//System.setProperty("webdriver.chrome.args", "--disable-logging");
			//System.setProperty("webdriver.chrome.silentOutput", "true");
			
			//ChromeOptions options = new ChromeOptions();
			
			//Map<String, Object> prefs = new HashMap<String, Object>();

			// Auto save file download at folder downloadFiles
			//prefs.put("profile.default_content_settings.popups", 0);
			//prefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE);
			
			// Disable notify save password on Chrome browser
			//prefs.put("credentials_enable_service", false);
			//prefs.put("profile.password_manager_enable", false);
			
			// Disable notify automation on Chrom browser
			//options.setExperimentalOption("useAutomationExtension", false);
			//options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			
			driver = new ChromeDriver();
		} else if(browserList == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if(browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if(browserList == BrowserList.SAFARI) {
			driver = new SafariDriver();
		} else if(browserList == BrowserList.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else if(browserList == BrowserList.OPERA) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if(browserList == BrowserList.COCCOC) {
			// Coc Coc browser tru di 5-6 version ra chromedriver
			if(osName.toLowerCase().contains("win")) {
				WebDriverManager.chromedriver().driverVersion("101.0.4951.41").setup();
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
				driver = new ChromeDriver(options);
			} else {
				WebDriverManager.chromedriver().driverVersion("101.0.4951.41").setup();
				ChromeOptions options = new ChromeOptions();
				options.setBinary("/Applications/CocCoc.app");
				driver = new ChromeDriver(options);
			}
			
		} else if(browserList == BrowserList.BRAVE) {
			/// Brave browser version nao dung chromedriver version do
			if(osName.toLowerCase().contains("win")) {
				WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
				driver = new ChromeDriver(options);
			} else {
				WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
				ChromeOptions options = new ChromeOptions();
				options.setBinary("/Applications/Brave Browser.app");
				driver = new ChromeDriver(options);
			}
			
		} else {
			throw new RuntimeException("Browser name invalid"); 
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

		driver.get(GlobalConstants.USER_DEV_URL);
		//driver.get("http://live.techpanda.org/");
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//FirefoxOptions options = new FirefoxOptions();
			//options.setAcceptInsecureCerts(false);
			driver = new FirefoxDriver();
		} else if(browserName.equals("h_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);
		} else if(browserName.equals("h_chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		} else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if(browserName.equals("safari")) {
			driver = new SafariDriver();
		} else if(browserName.equals("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else if(browserName.equals("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if(browserName.equals("coccoc")) {
			// Coc Coc browser tru di 5-6 version ra chromedriver
			WebDriverManager.chromedriver().driverVersion("101.0.4951.41").setup();
			ChromeOptions options = new ChromeOptions();
			if(GlobalConstants.OS_NAME.toLowerCase().contains("win")) {
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				options.setBinary("");
			}
			driver = new ChromeDriver(options);
		} else if(browserName.equals("brave")) {
			/// Brave browser version nao dung chromedriver version do
			WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
			ChromeOptions options = new ChromeOptions();
			if(GlobalConstants.OS_NAME.toLowerCase().contains("win")) {
				options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			} else {
				options.setBinary("");
			}
			driver = new ChromeDriver(options);
			
		} else {
			throw new RuntimeException("Browser name invalid"); 
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		
		//driver.get(GlobalConstants.USER_DEV_URL);
		driver.get(appUrl);
		//driver.get("http://live.techpanda.org/");
		//driver.get(getEnvironmentUrl(environmentName));
		return driver;
	}
	
	protected WebDriver getBrowserDriverBrowserstack(String browserName, String appUrl, String osName, String osVersion) {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("os", osName);
		capability.setCapability("os_version", osVersion);
		capability.setCapability("browser", browserName);
		capability.setCapability("browser_version", "latest");
		capability.setCapability("browserstack.debug", "true");
		capability.setCapability("project", "Nopcommerce");
		capability.setCapability("resolution", "1920x1080");
		capability.setCapability("name", "Run on" + osName + " | " + osVersion + " | " + browserName);
		
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

		//driver.get(GlobalConstants.USER_DEV_URL);
		driver.get(appUrl);
		//driver.get("http://live.techpanda.org/");
		//driver.get(getEnvironmentUrl(environmentName));
		return driver;
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	protected String getEnvironmentUrl(String environmentName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
		if(environment == EnvironmentList.DEV) {
			envUrl = GlobalConstants.USER_DEV_URL;
		} else if (environment == EnvironmentList.TESTING) {
			envUrl = GlobalConstants.USER_TESTING_URL;
		} else {
			throw new RuntimeException("Server name invalid");
		}
		return envUrl;
	}

	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add l盻擁 vﾃ�o ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

//	public void deleteAllureReport() {
//		try {
//			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
//			File file = new File(pathFolderDownload);
//			File[] listOfFiles = file.listFiles();
//			for (int i = 0; i < listOfFiles.length; i++) {
//				if(listOfFiles[i].isFile()) {
//					new File(listOfFiles[i].toString()).delete();
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internet explorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected String getCurrentDate() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if(day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if(month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}
	
	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}
	
	protected String getCurrentDay() {
		return getCurrentDate() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}
}
