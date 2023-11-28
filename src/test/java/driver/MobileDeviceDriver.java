package driver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.WebDriverProvider;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Nonnull;

import config.ConfigReader;
import helper.ApkInfoHelper;
import io.appium.java_client.android.AndroidDriver;

//AndroidDriver initialization class
public class MobileDeviceDriver implements WebDriverProvider, IMobileDeviceDriver {
    protected static AndroidDriver driver;
    public static final String DEVICE_NAME = ConfigReader.mobileDeviceConfig.deviceName();
    public static final String PLATFORM_NAME = ConfigReader.mobileDeviceConfig.platformName();
    public static String APP_PACKAGE = ConfigReader.mobileDeviceConfig.appPackage();
    public static String APP_ACTIVITY = ConfigReader.mobileDeviceConfig.appActivity();
    public static String UDID = ConfigReader.mobileDeviceConfig.udid();
    public static String AUTOMATION_NAME = ConfigReader.mobileDeviceConfig.automationName();
    public static final String APP = ConfigReader.mobileDeviceConfig.app();
    public static final String URL = ConfigReader.mobileDeviceConfig.remoteURL();


    private static URL getUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getting absolute path from root
     *
     * @param filePath path to file from root
     * @return
     */
    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found"); //check if file exists
        return file.getAbsolutePath();
    }

    //Getting AppPackage and AppActivity from apk file
    private void initPackageAndActivity() {
        ApkInfoHelper helper = new ApkInfoHelper();
        APP_PACKAGE = APP_PACKAGE.isEmpty() ? helper.getAppPackageFromApk() : APP_PACKAGE;
        APP_ACTIVITY = APP_ACTIVITY.isEmpty() ? helper.getAppMainActivity() : APP_ACTIVITY;
    }

    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        initPackageAndActivity();
       // desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);
        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);
        desiredCapabilities.setCapability("udid", UDID);
        desiredCapabilities.setCapability("automationName", AUTOMATION_NAME);
        desiredCapabilities.setCapability("app", getAbsolutePath(APP));

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
        return driver;
    }


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return null;
    }
}
