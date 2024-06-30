package com.praktika;

import com.google.common.base.Supplier;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.proxy.NotImplementedException;
import java.net.MalformedURLException;
import java.net.URL;

// Static driver factory. Returns Appium driver depends on env variable "PLATFORM"
public class DriverFactory {

    public static AppiumDriver getDriver() {
        // Default values for easier local development and debugging.
        String platform = System.getenv().getOrDefault ("PLATFORM", "android");

        if (platform == null) {
            throw new RuntimeException("PLATFORM environment variable is not set.");
        }

        return switch (platform.toLowerCase()) {
            case "android" -> getAndroidDriver();
            case "ios" -> getIOSDriver();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform);
        };
    }

    private static AppiumDriver getAndroidDriver() {
        // Properties for android driver also in env variables
        String deviceName = System.getenv().getOrDefault ("deviceName", "R6CT500FAVJ");

        // We should change this code if we use .apk file. But for now there is only options for preinstalled app
        String androidPackage = System.getenv().getOrDefault ("Package", "ai.praktika.android");
        String appiumURL = System.getenv().getOrDefault ("AppiumURL", "http://127.0.0.1:4723");

        var options = new UiAutomator2Options()
                .amend("appium:deviceName", deviceName)
                // Appium recommended android driver
                .amend("appium:automationName", "UiAutomator2")
                .amend("platformName", "android")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:commandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true)
                .amend("appPackage",androidPackage)
                .amend("appActivity",".MainActivity")
                .amend("appium:disableIdLocatorAutocompletion",true)
                .amend("waitForSelectorTimeout",10000)
                // Reset cache and storage for every start.
                .amend("noReset",false);

        Supplier<URL> urlSupplier = () -> {
            try {
                return new URL(appiumURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        };
        return new AndroidDriver(urlSupplier.get(), options);

    }

    // This method is only as a placeholder. For this task I decided to skip ios tests.
    private static AppiumDriver getIOSDriver() {
        throw new NotImplementedException();
    }
}
