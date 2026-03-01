package factory.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxDriverCreator implements IDriverCreator {
    private final String selenoidURL;

    public FirefoxDriverCreator(String selenoidURL) {
        this.selenoidURL = selenoidURL;
    }

    @Override
    public WebDriver create() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized", "--no-sandbox", "--disable-dev-shm-usage");
        return new RemoteWebDriver(new URL(selenoidURL), options);
    }
}
