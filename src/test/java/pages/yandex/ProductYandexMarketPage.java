package pages.yandex;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Страница товара Яндекс.Маркет")
public class ProductYandexMarketPage extends PageInterface<ProductYandexMarketPage> {

    @Name("Изображение")
    private final ParamBy search = ParamBy.xpath("//div[@class='n-gallery__item']//img[@class='n-gallery__image image']");

    @Name("Название")
    private final ParamBy menu = ParamBy.xpath("//div[@class='n-title__text']/h1");


    ProductYandexMarketPage() {
        super(ProductYandexMarketPage.class);
    }

}
