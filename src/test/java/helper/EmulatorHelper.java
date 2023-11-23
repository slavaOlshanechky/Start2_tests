package helper;

import com.codeborne.selenide.SelenideElement;

import driver.EmulatorDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EmulatorHelper extends EmulatorDriver {

    public static void closeScreen(String allowButton){
        driver.findElementByAndroidUIAutomator(allowButton).click();
    }


    //Type the text in element field and click button
    public static void sendKeysAndFind(SelenideElement element, String text){
        element.sendKeys(text);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }


}
