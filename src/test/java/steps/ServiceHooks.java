package steps;


import static constants.FrameworkConstants.MAIN_URL;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import support.Driver;
import utils.Filters;

public class ServiceHooks {
	   Filters filter = new Filters();
	   WebDriver driver;


    @Before
    public void beforeScenario(Scenario scenario) throws InterruptedException, NoSuchFieldException, IllegalAccessException, MalformedURLException {
    	
    	System.out.println("BEFORE: THREAD ID : " + Thread.currentThread().getId() + "," + "SCENARIO NAME: " + scenario.getName());
    	
    	filter.removeDirectoryEvidence();
//         if (ENVIRONMENT.equals("browserstack")) {
//             Driver.createBrowserStack().get(MAIN_URL);
//         }
//         else {
//             Driver.getDriver().get(MAIN_URL);
//         }
         Driver.getDriver().get(MAIN_URL);
    }


    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
    	   	
    	System.out.println(
				"AFTER: THREAD ID : " + Thread.currentThread().getId() + "," + "SCENARIO NAME: " + scenario.getName());
    	Driver.AddScreenshot(scenario);
        Driver.endSession();
    }

    
//    @AfterStep
//    public void afterStep() {
//        Screenshot.takeScreenShot();
//    }
    
    
}

