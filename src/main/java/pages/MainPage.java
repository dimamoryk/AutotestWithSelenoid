package pages;

import annotations.Path;
import extensions.UiExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@ExtendWith(UiExtension.class)
@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    @Inject
    protected CategoriesPage categoriesPage;

    @FindBy(css = "sc-1youhxc")
    protected List<WebElement> categories;

    public MainPage(WebDriver driver) {
        super(driver);
    }


    public String getLearningCategory(int randomIndex) {
        return categories.get(randomIndex).getText();
    }

    public CategoriesPage goToLearningSection(String title) {

        this.clickElementByPredicate.accept(categories, (WebElement element) ->
                element.getText().equals(title));

        return categoriesPage;
    }

    public WebElement firstCategoryElement(int index) {
        return categories.get(index - 1);
    }

    public WebElement findElementByCSS(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }
}
