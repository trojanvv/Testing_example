package utils;

import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.IllegalFormatException;

public class ParamBy {
    private static final String METHOD_FOR_XPATH = "byXpath";
    private static final String METHOD_FOR_CSS = "byCssSelector";
    private final String method;
    private final String expression;

    private ParamBy(String method, String expression) {
        this.expression = expression;
        this.method = method;
    }

    public static ParamBy xpath(String xpath) {
        return new ParamBy(METHOD_FOR_XPATH, xpath);
    }

    public static ParamBy css(String css) {
        return new ParamBy(METHOD_FOR_CSS, css);
    }

    public By getBy(String... parameter) {
        By result;
        try {
            Method mt = Selectors.class.getMethod(method, String.class);
            result = (By) mt.invoke(null, getFormatted(parameter));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ParamBy addCss(String child) {
        if (this.method.equals(METHOD_FOR_XPATH)) {
            throw new RuntimeException("Incompatible method addCss for xpath ParamBy");
        }
        child = !child.startsWith(".") & !child.startsWith("#") ? " " + child.stripLeading() : child;
        return new ParamBy(this.method, this.expression + child);
    }

    public ParamBy addXpath(String child) {
        if (this.method.equals(METHOD_FOR_CSS)) {
            throw new RuntimeException("Incompatible method addXpath for css ParamBy");
        }
        return new ParamBy(this.method, this.expression + child);
    }

    private String getFormatted(String... parameter) {
        String formattedExpression = expression;
        if (parameter.length > 0) {
            try {
                formattedExpression = String.format(expression, (Object[]) parameter);
            } catch (IllegalFormatException e) {
                e.printStackTrace();
            }
        } else
            return String.format(expression, "");

        return formattedExpression;
    }
}
