package utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class CustomWebDriverProvider implements WebDriverProvider {


    private final static String VERSION = "version";
    private final static String BROWSER = "chrome";
    private final static String RESOLUTION = "screenResolution";
    private final static String REMOTE_URL = "remoteUrl";


    @Override
    public WebDriver createDriver(DesiredCapabilities caps) {
        caps.setVersion(System.getProperty(VERSION, "latest"));
        caps.setBrowserName(System.getProperty(BROWSER, "chrome"));
        caps.setCapability(RESOLUTION, System.getProperty(RESOLUTION, "1920x1080"));

        if (System.getProperty(REMOTE_URL) != null)
            return remoteDriver(caps);
        return new ChromeDriver(caps);
    }

    public static WebDriver remoteDriver(DesiredCapabilities capabilities) {
        RemoteWebDriver driver;
        capabilities.setCapability("enableVNC", true);

        try {
            driver = new RemoteWebDriver(
                    new URL(System.getProperty(REMOTE_URL)),
                    capabilities
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }


}
