package com.wire.utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {

    public static IOSDriver initializeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set desired capabilities
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:udid", "D0A05791-7A28-425F-A108-433CA671C2A1");
        capabilities.setCapability("appium:includeSafariInWebviews", true);
        capabilities.setCapability("platformVersion", "18.3"); // Example version, change as needed
        capabilities.setCapability("deviceName", "iPhone 15"); // Example device, change as needed
        capabilities.setCapability("appium:app", getValue()); // Path to your IPA file
        capabilities.setCapability("automationName", "XCUITest");

        // Set app to open with deeplink
        //capabilities.setCapability("appPackage", "com.example.app"); // Replace with your app's package name

        // Setup Appium server URL
        URL appiumServerURL = new URL("http://127.0.0.1:4723");

        return new IOSDriver(appiumServerURL, capabilities);

    }

    private static String getValue() {
        return "/Users/myspace/Documents/Wire.ipa";
    }

}
