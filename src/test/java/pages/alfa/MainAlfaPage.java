package pages.alfa;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Главная страница AlfaBank")
public class MainAlfaPage extends PageInterface<MainAlfaPage> {

    @Name("Футер")
    private final ParamBy footer  = ParamBy.xpath("//div[contains(@class,'footer-content')]");

    @Name("Футер.Ссылки")
    private final ParamBy footerLink  = footer.addXpath("//a");


    MainAlfaPage() {
        super(MainAlfaPage.class);
    }

}
