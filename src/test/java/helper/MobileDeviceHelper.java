package helper;

import com.codeborne.selenide.SelenideElement;

import driver.MobileDeviceDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MobileDeviceHelper extends MobileDeviceDriver {

    public static void closeScreen(String allowButton){
        driver.findElementByAndroidUIAutomator(allowButton).click();
    }


    //Type the text in element field and click button
    public static void sendKeys(SelenideElement element, String text){
        element.sendKeys(text);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }


}
