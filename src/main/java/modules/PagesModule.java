package modules;

import com.google.inject.AbstractModule;
import components.CatalogCoursesComponent;
import components.popups.AuthPopup;
import org.openqa.selenium.WebDriver;
import pages.CategoriesPage;
import pages.MainPage;

public class PagesModule extends AbstractModule {

    protected WebDriver driver;

    public PagesModule(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void configure() {
        bind(MainPage.class).toProvider(() -> new MainPage(driver));
        bind(CategoriesPage.class).toProvider(() -> new CategoriesPage(driver));
        bind(AuthPopup.class).toProvider(() -> new AuthPopup(driver));
        bind(CatalogCoursesComponent.class).toProvider(() -> new CatalogCoursesComponent(driver));
    }
}