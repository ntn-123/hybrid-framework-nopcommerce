package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.GlobalConstants;

public class ExtentManagerV5 {
	public static final ExtentReports extentReports = new ExtentReports();
	
	public synchronized static ExtentReports creatExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.PROJECT_PATH + "/extentV5/ExtentReport.html");
		reporter.config().setReportName("Nopcommerce HTML Report");
		reporter.config().setDocumentTitle("Nopcommerce HTML Report");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "Sa");
		extentReports.setSystemInfo("Project", "Nopcommerce");
		extentReports.setSystemInfo("Author", "NT");
		extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);
		return extentReports;
	}
}
