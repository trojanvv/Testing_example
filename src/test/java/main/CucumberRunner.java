package main;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import helpers.ReportHelper;
import org.testng.annotations.AfterSuite;

@CucumberOptions(strict = true,
        features = "src/test/resources/features",
        glue = {"stepdefinition", "main"},
        plugin = {"json:target/cucumber.json"})

public class CucumberRunner extends AbstractTestNGCucumberTests {

    @AfterSuite(alwaysRun = true)
    public void generateHTMLReports() {
        ReportHelper.generateCucumberReport();
    }
}
