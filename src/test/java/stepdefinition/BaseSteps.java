package stepdefinition;

import main.TestBuilder;
import utils.Element;

import java.util.ArrayList;
import java.util.List;

public class BaseSteps {

    protected TestBuilder testBuilder = TestBuilder.getInstance();

    public void loadPage(String nameOfPage) {
        testBuilder.setCurrentPage(testBuilder.getPage(nameOfPage));
    }

    public List<Element> castToListEl(List l) {
        List<Element> list = new ArrayList<>();

        for (Object ob : l)
            list.add((Element) ob);
        return list;
    }
}
