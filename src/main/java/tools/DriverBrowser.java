package tools;

import org.openqa.selenium.WebDriver;

public class DriverBrowser {
	static WebDriver driver;
	public static WebDriver Driver() { return driver; }
	public static void setDriver(WebDriver setDriver) { driver = setDriver; }
}
