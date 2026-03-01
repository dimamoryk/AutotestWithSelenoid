package extensions;

import com.google.inject.Guice;
import com.google.inject.Inject;
import exceptions.BrowserNotSupportedException;
import factory.WebDriverFactory;
import modules.PagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.NoSuchElementException;

public class UiExtension implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback {

    protected WebDriver driver;

    @Inject
    protected WebDriverFactory webDriverFactory;

    @Override
    public void beforeEach(ExtensionContext context) {
        try {
            driver = webDriverFactory.create();
        } catch (MalformedURLException | BrowserNotSupportedException ex) {
            Guice.createInjector(new PagesModule(driver))
                    .injectMembers(context
                            .getTestInstance()
                            .orElseThrow(() -> new NoSuchElementException("Without WebDriverFactory")));

        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (driver != null) {
            driver.quit();
        }
    }


    @Override
    public void beforeAll(ExtensionContext context) {
        webDriverFactory.init();
    }
}

