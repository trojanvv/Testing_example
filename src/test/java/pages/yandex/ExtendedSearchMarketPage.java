package pages.yandex;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Страница результата Яндекс.Маркет")
public class ExtendedSearchMarketPage extends PageInterface<ExtendedSearchMarketPage> {

    @Name("Результат")
    private final ParamBy products = ParamBy.xpath("//div[contains(@class,'n-snippet-cell2_type_product')][%s]");

    @Name("Результат.Название")
    private final ParamBy product_title = products.addXpath("//div[contains(@class,'n-snippet-cell2__title')]/a");

    private final ParamBy filters = ParamBy.xpath("//div[@id='search-prepack']");

    @Name("Фильтры.Цена от")
    private final ParamBy priceFrom = filters.addXpath("//input[contains(@name,'Цена от')]");

    @Name("Фильтры.Цена до")
    private final ParamBy priceTo = filters.addXpath("//input[contains(@name,'Цена до')]");

    @Name("Фильтры.Производитель")
    private final ParamBy manufact = filters.addXpath("//fieldset[@data-autotest-id='7893318']/ul/li");

    ExtendedSearchMarketPage() {
        super(ExtendedSearchMarketPage.class);
    }

}
