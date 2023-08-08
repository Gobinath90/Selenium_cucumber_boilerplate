package runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features = "src/test/resources", snippets = CucumberOptions.SnippetType.CAMELCASE,
				dryRun = !true,
				//tags = "@sanity or @smoke",
				tags = "@sanity",
				glue = {"steps" },
				plugin = {  "html:report/cucumber/CucumberReport.html",
							"me.jvt.cucumber.report.PrettyReports:report/cucumber/",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
							//"pretty"
							},
				monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
	 @Override
	    @DataProvider(parallel = false)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	 @AfterSuite
	    public void afterSuite() {
	        System.out.println("================ AFTER SUITE ================");
	    }
}