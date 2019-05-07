package pages.yandex;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Главная страница Яндекс.Маркет")
public class MarketYandexMainPage extends PageInterface<MarketYandexMainPage> {

    @Name("Поиск")
    private final ParamBy search = ParamBy.css("input#header-search");
    
    @Name("Меню")
    private final ParamBy menu = ParamBy.css("div.n-w-tabs__horizontal-tabs-background a");


    MarketYandexMainPage() {
        super(MarketYandexMainPage.class);
    }

}
