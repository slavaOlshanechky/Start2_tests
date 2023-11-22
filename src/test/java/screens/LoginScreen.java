package screens;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class LoginScreen {
    private SelenideElement loginButton = $(MobileBy.id("Login"));
    private String registeredPhone = "+972522245678";
    private SelenideElement phoneField = $(MobileBy.id("phone"));

    @Step("Check, that cursor in the field")
    public void isCursorInField() {
        phoneField.should(Condition.selected);
    }

    @Step("Open phone number field")
    public void openField() {
        phoneField.click();
    }

    @Step("Type phone number")
    public void typePhoneNumber() {
        phoneField.data(registeredPhone);
    }

    @Step("Login with phone number")
    public void login() {
        loginButton.click();
    }
}
