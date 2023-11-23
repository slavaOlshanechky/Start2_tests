package screens;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import static helper.EmulatorHelper.closeScreen;
import static helper.EmulatorHelper.sendKeysAndFind;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class LoginScreen {
    private SelenideElement loginButton = $(MobileBy.id("Login"));
    private SelenideElement continueButton = $(MobileBy.id("Continue"));
    private String registeredPhone = "+972522245678";
    private SelenideElement phoneField = $(MobileBy.id("phone"));
    private SelenideElement notificationsPopUp = $(MobileBy.xpath("/hierarchy/android.widget.FrameLayout"));
    private SelenideElement locationPermitPopUp = $(MobileBy.id("com.android.permissioncontroller:id/grant_dialog"));
    private SelenideElement allowNotificationButton = $(MobileBy.id("com.android.permissioncontroller:id/permission_allow_button"));
    private SelenideElement whileUsingAppButton = $(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));


    @Step("Close location permit PopUp")
    public LoginScreen closeLocationPopUp() {
        if (locationPermitPopUp.exists()) {
            whileUsingAppButton.click();
        }
        return this;
    }
    @Step("Close Notifications PopUp")
    public LoginScreen closeNotificationPopUp() {
        if (notificationsPopUp.exists()) {
            allowNotificationButton.click();
        }
        return this;
    }

    @Step("Check, that cursor in the field")
    public void isCursorInField() {
        phoneField.should(Condition.selected);
    }

    @Step("Open phone number field")
    public void openField() {
        phoneField.click();
    }

    @Step("Type phone number")
    public void typePhoneNumber(String registeredPhone) {
        sendKeysAndFind(phoneField, registeredPhone);
    }

    @Step("Login with phone number")
    public void login() {
        loginButton.click();
    }

    @Step("Authorization with received secret code")
    public void secretCodeAuthorization() {
        continueButton.click();
    }
}
