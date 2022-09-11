package commons;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
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
			driver = new FirefoxDriver();
		} else if(browserList == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if(browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
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
			driver = new FirefoxDriver();
		} else if(browserName.equals("h_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
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
		return driver;
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	protected String getEnvironmentUrl(String serverName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(serverName.toUpperCase());
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

			// Add lỗi vào ReportNG
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
			} else if (driverInstanceName.contains("internetexplorer")) {
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
}
