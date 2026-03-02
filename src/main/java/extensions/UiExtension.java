package extensions;

import com.google.inject.Guice;
import exceptions.BrowserNotSupportedException;
import factory.WebDriverFactory;
import modules.PagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class UiExtension implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback {

    protected WebDriver driver;

    protected WebDriverFactory webDriverFactory = new WebDriverFactory();

    @Override
    public void beforeEach(ExtensionContext context) {
        try {
            driver = webDriverFactory.create();
        } catch (MalformedURLException | BrowserNotSupportedException ex) {
            Guice.createInjector(new PagesModule(driver))
                    .injectMembers(context
                            .getRequiredTestInstances());


        }
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        webDriverFactory.init();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (driver != null) {
            driver.quit();
        }
    }
}

