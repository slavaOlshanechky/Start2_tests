package helper;

import config.ConfigReader;
//import driver.EmulatorDriver;
import driver.MobileDeviceDriver;

//class for creation universal instance of AndroidDriver
public class RunHelper {
    private RunHelper() {
    }

    public static RunHelper runHelper() {
        return new RunHelper();
    }

    //AndroidDriver realization with custom classes for different devices
    public Class<?> getDriverClass() {
        String deviceHost = ConfigReader.testConfig.deviceHost();

        switch (deviceHost) {
            case "browserstack":
                //    return BrowserstackMobileDriver.class; //class for Browserstack logic
            case "selenoid":
                //   return SelenoidMobileDriver.class; //class for Selenoid and multithreading
            case "emulator":
               // return EmulatorDriver.class; //class for emulator
            case "mobileDevice":
                    return MobileDeviceDriver.class; //class for real mobile devices
            default:
                throw new RuntimeException("There are no deviceHost parameter in config file: " +
                        "browserstack/selenoid/emulator/real");
        }
    }
}
