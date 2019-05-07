package main;

import annotations.Name;
import com.codeborne.selenide.Selenide;
import com.google.common.collect.Maps;
import cucumber.api.Scenario;
import pages.PageInterface;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TestBuilder {

    private static final String PROPERTIES_FILE = "src\\test\\resources\\config\\config.properties";

    private static TestBuilder instance = new TestBuilder();

    private Map<String, Class<? extends PageInterface>> pages = Maps.newHashMap();

    private PageInterface<? extends PageInterface> currentPage;

    private Scenario scenario;

    private static final String VARIABLE_PATTERN = "[{]([\\wа-яА-Я]+[\\wа-яА-Я.-]+[\\wа-яА-Я]+)[}]";
    private Map<String, Object> variables = Maps.newHashMap();


    public static TestBuilder getInstance() {
        return instance;
    }

    private TestBuilder() {
        initPages();
        initVar();
    }

    private void initVar() {
        getPropertiesFromFileToSystem();
    }

    public String replaceVariables(String textToReplaceIn) {
        Pattern p = Pattern.compile(VARIABLE_PATTERN);
        Matcher m = p.matcher(textToReplaceIn);
        StringBuffer buffer = new StringBuffer();
        while (m.find()) {
            String varName = m.group(1);
            String value = getVar(varName).toString();
            m.appendReplacement(buffer, value);
        }
        m.appendTail(buffer);
        return buffer.toString();
    }

    public void setCurrentPage(PageInterface page) {
        if (page == null) {
            throw new IllegalArgumentException("Происходит переход на несуществующую страницу. " +
                    "Проверь аннотации @Name у используемых страниц");
        }
        this.currentPage = page;
    }

    public PageInterface getCurrentPage() {
        return this.currentPage;
    }

    public PageInterface getPage(String name) {
        var clazz = this.pages.get(name);
        if (clazz != null)
            return Selenide.page(clazz).init();
        else
            throw new IllegalStateException("Такой страницы не существует " + name);

    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    public void inReport(Object obj) {
        this.scenario.write(String.valueOf(obj));
    }

    public Object getVar(String name) {
        var obj = this.variables.get(name);
        if (obj == null) {
            throw new IllegalArgumentException("Переменная " + name + " не найдена");
        }
        return obj;
    }

    public void setVar(String name, Object value) {
        variables.put(name, value);
    }

    public void clear() {
        variables.clear();
    }

    public Object remove(String key) {
        return variables.remove(key);
    }

    @SuppressWarnings("unchecked")
    private void initPages() {
        new AnnotationScanner().getClassesAnnotatedWith(Name.class, "pages")
                .stream()
                .map(it -> {
                    if (PageInterface.class.isAssignableFrom(it)) {
                        return (Class<? extends PageInterface>) it;
                    } else {
                        throw new IllegalStateException("Класс " + it.getName() + " должен наследоваться от PageInterface");
                    }
                })
                .forEach(clazz -> pages.put(getClassAnnotationValue(clazz), clazz));
    }

    private String getClassAnnotationValue(Class<?> c) {
        return Arrays.stream(c.getAnnotationsByType(Name.class))
                .findAny()
                .map(Name::value)
                .orElseThrow(() -> new AssertionError("Не найдены аннотации Name в классe " + c.getName()));
    }

    public static void sleep(int seconds) {
        Selenide.sleep(TimeUnit.MILLISECONDS.convert(seconds, TimeUnit.SECONDS));
    }

    private static void getPropertiesFromFileToSystem() {
        Properties p = new Properties();
        try {
            p.load(new FileReader(PROPERTIES_FILE));
            for (String name : p.stringPropertyNames()) {
                if (System.getProperty(name) == null)
                    System.setProperty(name, p.getProperty(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
