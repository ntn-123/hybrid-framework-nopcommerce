package factoryBrowser;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("useAutomationExtension", false);
//		options.setExperimentalOption("includeSwitches", Collections.singletonList("enable-automation"));
//		options.addArguments("--disable-notifications");
//		options.addArguments("--disable-geolocations");
//		
//		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE);
//		options.setExperimentalOption("prefs", chromePrefs);
		//File file = new File(GlobalConstants.BROWSER_EXTENSIONS_FILE + "to_google_translate-4.2.0.xpi");
//		ChromeOptions options = new ChromeOptions();
//		options.addExtensions(file);
//		driver = new ChromeDriver(options);
		
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
		return new ChromeDriver(options);
		
	}

}
