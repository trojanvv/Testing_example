package pages;

import annotations.Name;
import utils.Element;
import utils.ElementFactory;
import utils.ParamBy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class PageInterface<T extends PageInterface> {

    private final Class<T> pageType;

    public PageInterface(Class<T> clazz) {
        super();
        pageType = clazz;
    }

    private Map<String, Object> pageElements;

    public Element<T> getElement(String nameEl, String... parameters) {
        return ElementFactory.getElement(getSelector(nameEl).getBy(parameters), pageType.cast(this));
    }

    public List<Element<T>> getElements(String nameEl, String... parameters) {
        return ElementFactory.getElements(getSelector(nameEl).getBy(parameters), pageType.cast(this));
    }

    public PageInterface<T> init() {
        pageElements = getPageElements();
        return this;
    }

    public PageInterface<T> getSubPage() {

        return null;
    }

    public PageInterface<T> getSubPage(int i) {
        return null;
    }

    private ParamBy getSelector(String elementName) {
        return (ParamBy) Optional.ofNullable(pageElements.get(elementName))
                .orElseThrow(
                        () -> new IllegalArgumentException("Элемент " + elementName + " не описан на странице " + this.getClass().getName()));

    }

    private Map<String, Object> getPageElements() {
        checkNamedAnnotations();
        return Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .peek((Field f) -> {
                    if (!ParamBy.class.isAssignableFrom(f.getType()) && !PageInterface.class.isAssignableFrom(f.getType())) {
                        if (List.class.isAssignableFrom(f.getType())) {
                            ParameterizedType listType = (ParameterizedType) f.getGenericType();
                            Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
                            if (ParamBy.class.isAssignableFrom(listClass) || PageInterface.class.isAssignableFrom(listClass)) {
                                return;
                            }
                        }
                        throw new IllegalStateException(
                                format("Поле с аннотацией @Name должно иметь тип ParamBy \n" +
                                        "Если поле описывает блок, оно должно принадлежать классу, унаследованному от ElementCollection.\n" +
                                        "Найдено поле с типом %s", f.getType()));
                    }
                })
                .collect(toMap(f -> f.getDeclaredAnnotation(Name.class).value(), this::extractFieldValueViaReflection));
    }

    private void checkNamedAnnotations() {
        List<String> list = Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null)
                .map(f -> f.getDeclaredAnnotation(Name.class).value())
                .collect(toList());
        if (list.size() != new HashSet<>(list).size()) {
            throw new IllegalStateException("Найдено несколько аннотаций @Name с одинаковым значением в классе " + this.getClass().getName());
        }
    }

    private Object extractFieldValueViaReflection(Field field) {
        field.setAccessible(true);
        try {
            return field.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}
