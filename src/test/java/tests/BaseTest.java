package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static helper.Constants.SCREENSHOT_TO_SAVE_FOLDER;
//import static helper.DeviceHelper.executeBash;
import static helper.RunHelper.runHelper;
import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.StandardCopyOption;

import config.ConfigReader;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.AllureListener;


@ExtendWith(AllureListener.class)
public class BaseTest {

//    @BeforeAll
//    public static void setup() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//
//        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;
//
//        //android driver initialization
//        Configuration.browser = runHelper().getDriverClass().getName();
//        Configuration.startMaximized = false;
//        Configuration.browserSize = null;
//        Configuration.timeout = 10000;
//       // disableAnimationOnEmulator();
//    }

//    private static void disableAnimationOnEmulator() {
//        executeBash("adb -s shell settings put global transition_animation_scale 0.0");
//        executeBash("adb -s shell settings put global window_animation_scale 0.0");
//        executeBash("adb -s shell settings put global animator_duration_scale 0.0");
//    }

    //UI check by comparing with reference screenshot
    public void assertScreenshot(File actualScreenshot, String expectedFileName) {
        expectedFileName = expectedFileName.replace("()", ".png");
        String expectedScreensDir = "src/test/resources/expectedScreenshots/";
        if (ConfigReader.testConfig.isScreenshotsNeedToUpdate()) {
            try {
                Files.move(actualScreenshot.toPath(), new File(expectedScreensDir + expectedFileName).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        BufferedImage expectedImage = ImageComparisonUtil
                .readImageFromResources(expectedScreensDir + expectedFileName);

        BufferedImage actualImage = ImageComparisonUtil
                .readImageFromResources(SCREENSHOT_TO_SAVE_FOLDER + actualScreenshot.getName());

        File resultDestination = new File("diff/diff_" + expectedFileName);

        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage, resultDestination)
                .compareImages();

        if (imageComparisonResult.getImageComparisonState().equals(ImageComparisonState.MISMATCH)) {
            try {
                byte[] diffImageBytes = Files.readAllBytes(resultDestination.toPath());
                AllureListener.saveScreenshot(diffImageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }

//    @BeforeEach
//    public void startDriver() {
//        step("Open App", (Allure.ThrowableRunnableVoid) Selenide::open);
//    }
//
//    @AfterEach
//    public void afterEach(){
//        step("Close App", Selenide::closeWebDriver);
//    }
}
