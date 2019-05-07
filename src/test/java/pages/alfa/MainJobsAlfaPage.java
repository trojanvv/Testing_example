package pages.alfa;

import annotations.Name;
import pages.PageInterface;
import utils.ParamBy;

@Name("Главная страница JobsAlfaBank")
public class MainJobsAlfaPage extends PageInterface<MainJobsAlfaPage> {

    @Name("О нас")
    private final ParamBy about  = ParamBy.xpath("//div[@class='container']/h3[text()='О нас']/../div[@class='top-32']");



    MainJobsAlfaPage() {
        super(MainJobsAlfaPage.class);
    }

}
