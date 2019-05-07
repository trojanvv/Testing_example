package stepdefinition;

import cucumber.api.java.ru.И;
import org.testng.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CommonSteps extends BaseSteps {

    @И("^сравнение значений переменных \"([^\"]*)\" и \"([^\"]*)\"$")
    public void compareVar(String var1, String var2) {
        testBuilder.inReport("Сравниваем переменные  [" + var1 + "] и [" + var2 + "]");
        Assert.assertEquals(testBuilder.getVar(var1), testBuilder.getVar(var2), "Переменные не равны <" + var1.toString() + "> and <" + var2 + ">");
    }

    @И("^сохранить значение переменной \"([^\"]*)\" в текстовый файл в папку target с тегом в имени \"([^\"]*)\"$")
    public void saveVarToFile(String varName, String tags) {
        var saveContent = testBuilder.getVar(varName).toString();
        var path = "target/";
        var time = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new GregorianCalendar().getTime());
        var browserName = System.getProperty("browser");
        var tagName = testBuilder.replaceVariables(tags);
        var fileName = browserName + "_" + time + "_" + tagName + ".txt";

        testBuilder.getScenario().getLines();
        try {
            File file = new File(path + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file, false);
            outputStream.write(saveContent.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @И("^установлено значение переменной \"([^\"]*)\" равным \"(.*)\"$")
    public void setVariable(String variableName, String value) {
        testBuilder.setVar(variableName, value);
    }

}

