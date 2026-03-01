package components;

import annotations.Component;
import commons.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AbsComponent extends AbsCommon {

    {

        assertThat(waiter
                .waitForCondition(ExpectedConditions.visibilityOfAllElementsLocatedBy(Objects.requireNonNull
                        (getByComponent(), "Locator is required for visibility check"))))
                .as("Error")
                .isTrue();
    }

    public AbsComponent(WebDriver driver) {
        super(driver);
    }

    private By getByComponent() {
        Class<?> clazz = getClass();

        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            String[] componentVal = component.value().split(":");

            switch (componentVal[0].trim()) {
                case "css":
                    return By.cssSelector(componentVal[1]);
                case "xpath":
                    return By.xpath(componentVal[1]);
            }
        }
        return null;
    }

    public WebElement getComponentEntry() {
        return $(getByComponent());
    }
}
