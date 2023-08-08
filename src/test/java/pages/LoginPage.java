package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	 public LoginPage loginPage;

	private static final By email = By.name("username");
	private static final By password = By.name("password");
	private static final By loginButton = By.name("login");
	private static final By register = By.xpath("//a[contains(text(),'Register')]");
	//private static final By homePageLogo = By.xpath("//span[text()='ETANA CUSTODY']");
	

	public LoginPage getEmail(String txt) {
		holdScript(2);
		clearAndSendKeys(email, txt, "Enter Username");
		return this;
	}
	public LoginPage getPassword(String txt) {
		holdScript(2);
		clearAndSendKeys(password, txt, "Enter Password");
		return this;
	}
	public LoginPage getLoginbutton() {
		holdScript(2);
		click(loginButton, "Click on Login Button");
		return this;
	}
	public LoginPage getregister() {
		holdScript(2);
		click(register, "Click on Register Button");
		return this;
	}

}
