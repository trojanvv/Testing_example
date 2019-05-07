package pages.yandex;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Главная страница Яндекс")
public class MainYandexPage extends PageInterface<MainYandexPage> {

    @Name("Меню")
    private final ParamBy menu = ParamBy.xpath("//div[contains(@class,'home-tabs')]/a");

    @Name("Поиск")
    private final ParamBy search = ParamBy.css("input.input__control");

    @Name("Кнопка поиска")
    private final ParamBy searchButton = ParamBy.css("button.suggest2-form__button");


    MainYandexPage() {
        super(MainYandexPage.class);
    }


}
