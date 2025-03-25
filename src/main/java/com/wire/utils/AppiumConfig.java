package com.wire.utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Configuration for Appium Driver
 */
public class AppiumConfig {

    // Default Appium server URL for Appium 2.0
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/";

    /**
     * Initializes and returns an iOS driver with the appropriate capabilities
     */
    public static IOSDriver initializeDriver() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set up capabilities for iOS testing
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:deviceName", "iPhone 15");
        capabilities.setCapability("appium:platformVersion", "18.3");
        capabilities.setCapability("appium:udid", "D0A05791-7A28-425F-A108-433CA671C2A1");
        capabilities.setCapability("appium:app", "/Users/myspace/Documents/Wire.ipa");
        capabilities.setCapability("appium:newCommandTimeout", 300);
        capabilities.setCapability("appium:includeSafariInWebviews", true);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);
        capabilities.setCapability("appium:noReset", false);
        capabilities.setCapability("appium:fullReset", false);
        
        try {
            IOSDriver driver = new IOSDriver(new URL(APPIUM_SERVER_URL), capabilities);
            System.out.println("Successfully connected to Appium server at: " + APPIUM_SERVER_URL);
            return driver;
        } catch (Exception e) {
            System.err.println("Failed to connect to Appium server: " + e.getMessage());
            throw e;
        }
    }
}
