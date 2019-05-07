package stepdefinition;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import utils.Element;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ListSteps extends BaseSteps {

    @Когда("^в списке \"([^\"]*)\" выбран элемент с (?:текстом|значением) \"(.*)\"$")
    public void checkIfSelectedListElementMatchesValue(String listName, String expectedValue) {
        List<Element> listOfElementsFromPage = castToListEl(testBuilder.getCurrentPage().getElements(listName));
        List<String> elementsText = listOfElementsFromPage.stream()
                .map(element -> element.getText().trim())
                .collect(toList());
        listOfElementsFromPage.stream()
                .filter(element -> element.getText().trim().equalsIgnoreCase(expectedValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Элемент [%s] не найден в списке %s: [%s] ", expectedValue, listName, elementsText)))
                .click();
    }

    @Когда("^выбран (\\d+)-й элемент в списке \"([^\"]*)\"$")
    public void selectElementNumberFromList(Integer elementNumber, String listName) {
        List<Element> listOfElementsFromPage = castToListEl(testBuilder.getCurrentPage().getElements(listName));
        Element elementToSelect;
        Integer selectedElementNumber = elementNumber - 1;
        if (selectedElementNumber < 0 || selectedElementNumber >= listOfElementsFromPage.size()) {
            throw new IndexOutOfBoundsException(
                    String.format("В списке %s нет элемента с номером %s. Количество элементов списка = %s",
                            listName, elementNumber, listOfElementsFromPage.size()));
        }
        elementToSelect = listOfElementsFromPage.get(selectedElementNumber);
        elementToSelect.click();
    }

    @Тогда("^выбран (\\d+)-й элемент в списке \"([^\"]*)\" и его значение сохранено в переменную \"([^\"]*)\"$")
    public void selectElementNumberFromListAndSaveToVar(Integer elementNumber, String listName, String varName) {
        List<Element> listOfElementsFromPage = castToListEl(testBuilder.getCurrentPage().getElements(listName));
        Element elementToSelect;
        Integer selectedElementNumber = elementNumber - 1;
        if (selectedElementNumber < 0 || selectedElementNumber >= listOfElementsFromPage.size()) {
            throw new IndexOutOfBoundsException(
                    String.format("В списке %s нет элемента с номером %s. Количество элементов списка = %s",
                            listName, elementNumber, listOfElementsFromPage.size()));
        }
        elementToSelect = listOfElementsFromPage.get(selectedElementNumber);
        elementToSelect.click();
    }

}
