/**
 * Copyright 2017 Alfa Laboratory
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package stepdefinition;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;

public class ElementsSteps extends BaseSteps {

    /**
     * На странице происходит клик по заданному элементу
     */
    @И("^выполнено нажатие на (?:кнопку|поле|блок) \"([^\"]*)\"$")
    public void clickOnElement(String elementName) {
        testBuilder.getCurrentPage().getElement(elementName).click();
    }

    @И("^выполнено нажатие на (?:кнопку|поле|блок) \"([^\"]*)\" в списке \"([^\"]*)\"$")
    public void clickOnElementIn(String subElement, String elementName) {
        testBuilder.getCurrentPage().getElement(elementName,subElement).click();
    }

    @Когда("^значение (?:элемента|поля) \"([^\"]*)\" сохранено в переменную \"([^\"]*)\"$")
    public void storeElementValueInVariable(String elementName, String variableName) {
        var variable = testBuilder.getCurrentPage().getElement(elementName).getText();
        testBuilder.setVar(variableName, variable);
        testBuilder.inReport("Значение [" + variable + "] сохранено в переменную [" + variableName + "]");
    }

    @Когда("^страница прокручена до элемента \"([^\"]*)\"$")
    public void scrollPageToElement(String elementName) {
        testBuilder.getCurrentPage().getElement(elementName).scrollTo();
    }
}