package helper;

import static helper.DeviceHelper.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.ConfigReader;

public class ApkInfoHelper {
    /**
     * AndroidManifest.xml from apk
     */
    private String apkInfo;

//    public ApkInfoHelper() {
//        String app = ConfigReader.emulatorConfig.app();// read path to apk from properties
//        if (app == null || app.isEmpty()) {
//            throw new RuntimeException("No value for key 'app' providing apk path in emulator.properties");
//        }
//        //execute bash with command 'aapt dumb badging' path to apk file
//        // for reading AndroidManifest.xml from apk
//        try {
//String pathToAapt="c:\\Users\\User\\AppData\\Local\\Android\\Sdk\\build-tools\\33.0.1\\";

    //            apkInfo = executeSh(pathToAapt + "aapt dumb badging " + ConfigReader.emulatorConfig.app());
//        } catch (IOException | InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public ApkInfoHelper() {
        String app = ConfigReader.mobileDeviceConfig.app();// read path to apk from properties
        if (app == null || app.isEmpty()) {
            throw new RuntimeException("No value for key 'app' providing apk path in mobileDevice.properties");
        }
        //execute bash with command 'aapt dumb badging' path to apk file
        // for reading AndroidManifest.xml from apk
        try {
            String pathToAapt="c:\\Users\\User\\AppData\\Local\\Android\\Sdk\\build-tools\\33.0.1\\";
            apkInfo = executeSh(pathToAapt + "aapt dumb badging " + ConfigReader.mobileDeviceConfig.app());
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAppPackageFromApk(){
        return findGroup1ValueFromString(apkInfo,"package:name='\\s*([^']+?)\\s*'");
    }

    public String getAppMainActivity(){
        return findGroup1ValueFromString(apkInfo,"launchable-activity: name='\\s*([^']+?)\\s*'");
    }

    private String findGroup1ValueFromString(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(text);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }
}
