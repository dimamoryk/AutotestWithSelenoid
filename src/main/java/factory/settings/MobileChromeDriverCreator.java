package factory.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MobileChromeDriverCreator implements IDriverCreator {

    private final String selenoidURL;

    public MobileChromeDriverCreator(String selenoidURL) {
        this.selenoidURL = selenoidURL;
    }

    @Override
    public WebDriver create() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", Map.of("deviceName", "Google Nexus 5"));
        return new RemoteWebDriver(new URL(selenoidURL), options);
    }
}