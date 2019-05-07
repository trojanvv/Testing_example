package stepdefinition;

import cucumber.api.java.ru.Когда;
import utils.Element;

public class InputSteps extends BaseSteps {

    @Когда("^в поле \"([^\"]*)\" введено значение \"(.*)\"$")
    public void setFieldValue(String elementName, String value) {
        Element valueInput = testBuilder.getCurrentPage().getElement(elementName);
        valueInput.setText(value);
    }
}
