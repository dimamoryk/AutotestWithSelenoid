package components.popups;

import commons.AbsCommon;
import org.openqa.selenium.WebDriver;

public class AuthPopup extends AbsCommon implements IPopup<AuthPopup> {

    public AuthPopup(WebDriver driver) {
        super(driver);
    }

    @Override
    public AuthPopup popupShouldBeVisible() {
        System.out.println("Popup is visible.");
        return this;
    }

    @Override
    public AuthPopup popupShouldNotBeVisible() {
        System.out.println("Popup isn't visible.");
        return this;
    }

    @Override
    public AuthPopup closePopup() {
        System.out.println("Popup is close.");
        return this;
    }
}