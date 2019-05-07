package pages.yandex;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Результаты поиска Яндекс")
public class SearchResultYandexPage extends PageInterface<SearchResultYandexPage> {

    @Name("Выдача")
    private final ParamBy result  = ParamBy.xpath("//li[@class='serp-item']/div[contains(@class,'organic bno')]/h2/a");


    SearchResultYandexPage() {
        super(SearchResultYandexPage.class);
    }


}
