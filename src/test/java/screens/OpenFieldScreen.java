package screens;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

public class OpenFieldScreen {
    private SelenideElement dataField = $(MobileBy.id("????"));

    @Step("Check, that cursor in the field")
    public void isCursorInField(){
        dataField.should(Condition.selected);
    }
}
