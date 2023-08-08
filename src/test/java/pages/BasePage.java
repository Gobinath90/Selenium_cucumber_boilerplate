package pages;

import static constants.FrameworkConstants.EXPLICIT_WAIT;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	static final Logger logger = Logger.getLogger(BasePage.class.getName());
	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
	}

	protected void locator(By by, String elementName) {
		waitUntilPresenceOfElementLocated(by).isDisplayed();
		 logger.info("-- clicking on the element " + by);
	}
	
	protected void click(By by, String elementName) {
		 waitUntilElementToBeClickable(by).click();
		 logger.info("-- clicking on the element " + by);
	}

	protected void clearAndSendKeys(By by, String value, String elementName) {
		WebElement element = waitUntilElementToBeClickable(by);
		element.clear();
		element.sendKeys(value);
		 logger.info("-- writing to the element " + by);
	}

	protected WebElement waitUntilElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	protected WebElement waitUntilPresenceOfElementLocated(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	protected String getElementText(By by) {
		WebElement element = waitUntilPresenceOfElementLocated(by);
		 logger.info("-- getting the element's text " + by);
		return element.getText();
	}

	public static void holdScript(int seconds){
			try {
				Thread.sleep(1000 * seconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	

}
