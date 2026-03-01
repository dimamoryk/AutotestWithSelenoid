package factory.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverCreator implements IDriverCreator {

    private final String selenoidURL;

    public ChromeDriverCreator(String selenoidURL) {
        this.selenoidURL = selenoidURL;
    }

    @Override
    public WebDriver create() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--no-sandbox", "--disable-dev-shm-usage");
        return new RemoteWebDriver(new URL(selenoidURL), options);
    }
}

