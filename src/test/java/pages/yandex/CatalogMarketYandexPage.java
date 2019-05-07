package pages.yandex;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Страница каталога Яндекс.Маркет")
public class CatalogMarketYandexPage extends PageInterface<CatalogMarketYandexPage> {

    @Name("Поиск")
    private final ParamBy search = ParamBy.css("input#header-search");

    @Name("Каталог")
    private final ParamBy menu = ParamBy.xpath("//div[contains(@class,'section')]//a[contains(@href,'/catalog') and contains(text(),'%s')]");


    CatalogMarketYandexPage() {
        super(CatalogMarketYandexPage.class);
    }

}
