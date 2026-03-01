package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeDriverCreator;
import factory.settings.FirefoxDriverCreator;
import factory.settings.IDriverCreator;
import factory.settings.MobileChromeDriverCreator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.Map;

public class WebDriverFactory {

    private final String selenoidURL = System.getProperty("selenoid.url", "http://localhost:4444/wd/hub");
    private final String browser = System.getProperty("browser.type", "chrome");

    private final Map<String, IDriverCreator> creatorsMapping = Map.of(
            "chrome", new ChromeDriverCreator(selenoidURL),
            "firefox", new FirefoxDriverCreator(selenoidURL),
            "mobile", new MobileChromeDriverCreator(selenoidURL)
    );
    public WebDriver create() throws MalformedURLException {
        IDriverCreator creator = creatorsMapping.get(browser.toLowerCase());
        if (creator == null) {
            throw new BrowserNotSupportedException(browser);
        }
        return creator.create();
    }

    public void init() {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "mobile":
                // Ничего не делаем для мобильного браузера
                break;
            default:
                throw new BrowserNotSupportedException(browser);
        }
    }
}