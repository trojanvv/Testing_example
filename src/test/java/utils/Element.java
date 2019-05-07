package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Element<T> {
    private final SelenideElement selenideElement;
    private final T context;

    Element(SelenideElement elem, T ctx) {
        context = ctx;
        selenideElement = elem;
    }

    public T click() {
        scrollTo();
        mouseOver();
        selenideElement.click();
        makeDelay();
        return context;
    }

    public T clickAndWaitForAbsence() {
        click();
        return waitForAbsent();
    }

    public T click(int offsetX, int offsetY) {
        scrollTo();
        mouseOver();
        selenideElement.click(offsetX, offsetY);
        makeDelay();
        return context;
    }

    public String getText() {
        return selenideElement.getText();
    }

    public String mouseOverAndGetText() {
        mouseOver();
        return selenideElement.getText();
    }

    public String getNotEmptyText() {
        return selenideElement
                .shouldNot(Condition.exactText(""))
                .getText();
    }

    public String getValue() {
        return selenideElement.getValue();
    }

    public Boolean isDisplayed() {
        return selenideElement.isDisplayed();
    }

    public String getAttributeValue(String attributeName) {
        return selenideElement.getAttribute(attributeName);
    }

    public T mouseOver() {
        selenideElement.hover();
        makeDelay();
        return context;
    }

    public T waitFor() {
        selenideElement.should(Condition.visible);
        return context;
    }

    public T scrollTo() {
        selenideElement.scrollIntoView("{block: \"center\"}");
        return context;
    }

    public T waitText(String text) {
        selenideElement.should(Condition.text(text));
        return context;
    }

    public T waitValue(String text) {
        selenideElement.should(Condition.value(text));
        return context;
    }

    public T waitTextNot(String text) {
        selenideElement.shouldNot(Condition.exactText(text));
        return context;
    }

    public T waitForAbsent() {
        selenideElement.should(Condition.hidden);
        return context;
    }

    public T input(String text) {
        click();
        if (text != null) selenideElement.append(text);
        makeDelay();
        return context;
    }

    public T clear() {
        mouseOver();
        selenideElement.clear();
        makeDelay();
        return context;
    }

    public T setText(String text) {
        clear();
        return input(text);
    }

    public T setTextCtrV(String text) {
        clear();
        StringSelection stringSelection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        selenideElement.click();
        new Actions(WebDriverRunner.getWebDriver()).sendKeys(String.valueOf('\u0016')).build().perform();

        return input(text);
    }

    public T inputAndSubmit(String text) {
        input(text);
        selenideElement.pressEnter();
        makeDelay();
        return context;
    }

    public T sendKeysByOne(String text) {
        scrollTo();
        selenideElement.click();
        text.chars()
                .mapToObj(i -> String.valueOf((char) i)).forEach(s -> {
                        selenideElement.getWrappedElement().sendKeys(s);
                        Selenide.sleep(100);
                });
        return context;
    }

    SelenideElement getSelenideElement() {
        return selenideElement;
    }

    private void makeDelay() {
        Selenide.sleep(500);
    }
}
