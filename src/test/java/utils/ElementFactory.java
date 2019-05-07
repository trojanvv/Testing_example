package utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ElementFactory {

    public static  <T> Element<T> getElement(By locator, T context) {
        return new Element<>(
                getSelenideElement(locator, null),
                context
        );
    }
    public static <T> Element<T> getElement(By locator, T context, Element parent) {
        return new Element<>(
                getSelenideElement(locator, parent.getSelenideElement()),
                context
        );
    }

    public static <T> List<Element<T>> getElements(By locator, T context) {
        return getElementsCollection(locator, null)
                .stream()
                .map(element -> new Element<>(element, context))
                .collect(Collectors.toList());
    }

    public static <T> List<Element<T>> getElements(By locator, T context, Element parent) {
        return getElementsCollection(locator, parent.getSelenideElement())
                .stream()
                .map(element -> new Element<>(element, context))
                .collect(Collectors.toList());
    }

    private static SelenideElement getSelenideElement(By locator, SelenideElement parent) {
        return parent == null ? Selenide.$(locator) : parent.$(locator);
    }

    private static ElementsCollection getElementsCollection(By locator, SelenideElement parent) {
        return parent == null ? Selenide.$$(locator) : parent.$$(locator);
    }
}
