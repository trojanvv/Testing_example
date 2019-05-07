package stepdefinition;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.ru.Когда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.function.Function;

public class BrowserSteps extends BaseSteps {

    @Когда("^выполнено переключение на вкладку с заголовком \"([^\"]*)\"$")
    public void switchToTheTab(String title) {
        try {
            var previousTitleWindows = WebDriverRunner.getWebDriver().getTitle();
        } catch (Exception ignored) {
        }

        new WebDriverWait(WebDriverRunner.getWebDriver(), 60).until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                for (String item : handles) {
                    driver.switchTo().window(item);
                    if (driver.getTitle().contains(title)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }
}

