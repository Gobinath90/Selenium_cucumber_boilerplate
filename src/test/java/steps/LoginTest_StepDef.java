package steps;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.LoginPage;
import pages.RegistrationPage;
import support.Driver;

public class LoginTest_StepDef {
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String name = "testing.qa7890";
	static WebDriver driver = null;
	LoginPage loginpage;
	RegistrationPage registrationPage;

	
	//Reusable Common Methods
	
	@Given("User navigate to Login Page")
	public void userNavigateToLoginPage() {
		driver = Driver.getDriver();
	}
	
	@And("user clicks on login button")
	public void userClicksOnLoginButton() {
		new LoginPage(driver).getLoginbutton();
	}
	@Then("user is logged in successully")
	public void user_is_logged_in_successully() {
		Assert.assertEquals("ETANA CUSTODY", driver.findElement(By.xpath("//span[text()='ETANA CUSTODY']")).getText());
	}
	@Then("There should be no broken links")
	public void there_should_be_no_broken_links() {
		System.out.println("This is to Print all Href Links");
	}
	@Then("user logouts from the application")
	public void user_logouts_from_the_application() {
		driver.findElement(By.xpath("//mat-icon[@matbadgecolor='warn']")).click();
		driver.findElement(By.xpath("//button[text()='LOGOUT']")).click();
		BasePage.holdScript(2);
	}
	@Then("user should see an error message")
	public void userShouldSeeAnErrorMessage() {
		Assert.assertEquals("Invalid username or password.",
				driver.findElement(By.xpath("//span[text()='Invalid username or password.']")).getText());
	}

	//TC_1
	
	@Given("user enters username {string} and password {string} of individual profile")
	public void user_enters_username_and_password_of_individual_profile(String email, String password) {
		new LoginPage(driver).getEmail(email);
		new LoginPage(driver).getPassword(password);
	}

	//TC_2
	
	@Given("user enters username {string} and password {string} of organization profile")
	public void user_enters_username_and_password_of_organization_profile(String email, String password) {
		new LoginPage(driver).getEmail(email);
		new LoginPage(driver).getPassword(password);
	}
	//TC_3
	@Given("user enters username {string} and password {string}")
	public void user_enters_username_and_password(String email, String password) {
		new LoginPage(driver).getEmail(email);
		new LoginPage(driver).getPassword(password);
	}

	@Then("Password field is visible as asterisk")
	public void password_field_is_visible_as_asterisk() {
		Assert.assertTrue(driver.findElement(By.xpath("//input[@type='password']")).isDisplayed());
	}
	//TC_4
	@Given("user enters invalid username {string} and password {string}")
	public void user_enters_invalid_username_and_password(String email, String password) {
		new LoginPage(driver).getEmail(email);
		new LoginPage(driver).getPassword(password);
	}

	//TC_6
	@Given("user clicks on register button")
	public void user_clicks_on_register_button() {
		new LoginPage(driver).getregister();
	}

	@When("user enters registration details")
	public void user_enters_registration_details() {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(sdf3.format(timestamp)); 
		System.out.println(name+"+"+sdf3.format(timestamp)+"@gmail.com");
		new RegistrationPage(driver).getEmailfield(name+"+"+sdf3.format(timestamp)+"@gmail.com");
		new RegistrationPage(driver).getPassword(name+"+"+sdf3.format(timestamp)+"@gmail.com");
		new RegistrationPage(driver).getPasswordconfirm(name+"+"+sdf3.format(timestamp)+"@gmail.com");
		new RegistrationPage(driver).getregisterButton();
//		String code = MongoDBConn.getConfirmationCodeFromDB(name+"+"+sdf3.format(timestamp)+"@gmail.com");
//		System.out.println("code "+code);
	}

}
