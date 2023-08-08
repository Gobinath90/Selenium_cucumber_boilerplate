package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	private static final By emailField = By.id("email");
	private static final By password = By.id("password");
	private static final By passwordconfirm = By.id("password-confirm");
	private static final By registerButton = By.xpath("//input[@type='submit']");

	public RegistrationPage getEmailfield(String txt) {
		clearAndSendKeys(emailField, txt, "Enter Email");
		return this;
	}
	public RegistrationPage getPassword(String txt) {
		clearAndSendKeys(password, txt, "Enter Password");
		return this;
	}
	public RegistrationPage getPasswordconfirm(String txt) {
		clearAndSendKeys(passwordconfirm, txt, "Enter Confirm Password");
		return this;
	}

	public RegistrationPage getregisterButton() {
		holdScript(2);
		click(registerButton, "Click on Login Button");
		return this;
	}
}
