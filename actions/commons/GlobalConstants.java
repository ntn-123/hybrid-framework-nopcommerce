package commons;

import java.io.File;

public class GlobalConstants {
	// dev
	public static final String USER_DEV_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_DEV_URL = "https://admin-demo.nopcommerce.com/login";
	
	// test
	public static final String USER_TESTING_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_TESTING_URL = "https://admin-demo.nopcommerce.com/login";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String BROWSER_EXTENSIONS_FILE = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	public static final String RESOURCES_SOURCE_FOLDER = PROJECT_PATH + File.separator + "resources" + File.separator;
	
	public static final String DB_DEV_URL = "32.18.252.185:9860";
	public static final String DB_DEV_USER = "admin";
	public static final String DB_DEV_PASS = "admin123";
	
	public static final String DB_TEST_URL = "32.18.195.23:9860";
	public static final String DB_TEST_USER = "admin";
	public static final String DB_TEST_PASS = "admin123";
	
	public static final long SHOTR_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final long RETRY_TEST_FAIL = 3;
	
	public static final String TECH_PANDA_USER_TESTING_URL = "http://live.techpanda.org/";
	public static final String TECH_PANDA_ADMIN_TESTING_URL = "http://live.techpanda.org/index.php/backendlogin/customer/";
	
	public static final String BROWSER_USERNAME = "automationfc1";
	public static final String BROWSER_AUTOMATE_KEY = "";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
}
