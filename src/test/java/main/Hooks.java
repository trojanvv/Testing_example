package main;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import stepdefinition.BaseSteps;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class Hooks extends BaseSteps {

    @Before(order = 20)
    public void setDriver() throws Exception {
        WebDriverManager.chromedriver().setup();
    }

    @Before(order = 20)
    public void setScenario(Scenario scenario) throws Exception {
        testBuilder.setScenario(scenario);
    }

    @After(order = 20)
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed() && hasWebDriverStarted()) {
            TestBuilder.sleep(2);
            final byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

    @After(order = 10)
    public void closeWebDriver() {
        if (hasWebDriverStarted()) {
            getWebDriver().manage().deleteAllCookies();
            WebDriverRunner.closeWebDriver();
        }
    }

}
