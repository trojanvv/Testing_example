package stepdefinition;

import cucumber.api.java.ru.И;

public class SubPageSteps extends BaseSteps {

    @И("^в блоке \"([^\"]*)\" под номером \"([^\"]*)\" найден элемент \"([^\"]*)\" и сохранен текст в переменную \"([^\"]*)\"$")
    public void getListElementsText(String blockName, String index, String elName, String varName) {
        var variable = testBuilder.getCurrentPage()
                .getElement(elName, index)
                .getText();
        testBuilder.setVar(varName,variable);
        testBuilder.inReport("Значение [" + variable + "] сохранено в переменную [" + varName + "]");

    }

    @И("^в блоке \"([^\"]*)\" под номером \"([^\"]*)\" выбран элемент \"([^\"]*)\"$")
    public void getListElements(String blockName, String index, String elName) {
        testBuilder.getCurrentPage()
                .getElement(elName, index)
                .click();
    }

}
