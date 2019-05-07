package stepdefinition;

import cucumber.api.java.ru.И;

import static com.codeborne.selenide.Selenide.open;

public class WebPageSteps extends BaseSteps {

    @И("^совершен переход на страницу \"([^\"]*)\" по ссылке \"([^\"]*)\"$")
    public void goToSelectedPageByLink(String pageName, String urlOrName) {
        urlOrName = testBuilder.replaceVariables(urlOrName);
        open(urlOrName);
        loadPage(pageName);
    }

    @И("^переход на страницу \"([^\"]*)\"$")
    public void urlClickAndCheckRedirection(String pageName) {
        loadPage(pageName);
    }


}
