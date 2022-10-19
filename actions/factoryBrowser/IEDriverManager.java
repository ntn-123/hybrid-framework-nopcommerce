package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import commons.GlobalConstants;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		if(!IS_OS_WINDOWS) {
			throw new BrowserNotSupportedException("IE not supported on " + GlobalConstants.OS_NAME);
		}
		WebDriverManager.iedriver().arch32().setup();
		InternetExplorerOptions options = new InternetExplorerOptions();
		return new InternetExplorerDriver(options);
		
	}

}
