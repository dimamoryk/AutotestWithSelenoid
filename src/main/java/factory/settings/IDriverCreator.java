package factory.settings;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IDriverCreator {
    WebDriver create() throws MalformedURLException;
}
