package screens;

import com.codeborne.selenide.SelenideElement;

public class SecretCodeScreen {

    private SelenideElement continueButton = $(MobileBy.id("Continue"));

    //secretCode selectors by XPath
    private String secretCodeFirstSelector="//android.widget.TextView[@text=\"7\"]";
    private String secretCodeSecondSelector="//android.widget.TextView[@text=\"6\"]";
    private String secretCodeThirdSelector="//android.widget.TextView[@text=\"1\"]";
    private String secretCodeFourthSelector="//android.widget.TextView[@text=\"3\"]";
    String [] secretCode  = {};
    private SelenideElement secretCodeField = $(MobileBy.id("phone"));

}
