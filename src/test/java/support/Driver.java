package support;

import static constants.FrameworkConstants.ACCESS_KEY;
import static constants.FrameworkConstants.URL_BROWSERSTACK;
import static constants.FrameworkConstants.USERNAME;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.Scenario;

public class Driver {
	public static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		return driver;
	}

	public static WebDriver createBrowserStack() throws MalformedURLException {

		if (driver == null) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "115.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			// INIT CHROME OPTIONS
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			Map<String, Object> profile = new HashMap<String, Object>();
			Map<String, Object> contentSettings = new HashMap<String, Object>();
			// SET CHROME OPTIONS
			// 0 - Default, 1 - Allow, 2 - Block
			contentSettings.put("notifications", 1);
			profile.put("managed_default_content_settings", contentSettings);
			prefs.put("profile", profile);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("incognito");
			// SET CAPABILITY
			caps.setCapability(ChromeOptions.CAPABILITY, options);

			caps.setCapability("projectName", "Etana Custody");
			caps.setCapability("buildName", "alpha_0.1.7");
			caps.setCapability("sessionName", "Home page must have a title");
			caps.setCapability("buildTag", "sanity");

			driver = new RemoteWebDriver(new URL("https://" + USERNAME + ":" + ACCESS_KEY + URL_BROWSERSTACK), caps);
			Dimension dimension = new Dimension(1920, 1080);
			driver.manage().window().setSize(dimension);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		return driver;
	}

	public static void endSession() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void AddScreenshot(Scenario scenario) {
		boolean failed = scenario.isFailed();
		System.out.println("is Failed? " + failed);
		if (!failed) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			final byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
    		scenario.attach(screenshot, "image/png", scenario.getName());
			

		}

	}

}
