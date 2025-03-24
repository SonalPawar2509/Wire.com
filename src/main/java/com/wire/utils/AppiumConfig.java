package com.wire.utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {

    // Appium server URLs to try - we'll attempt these in order
    private static final String[] APPIUM_SERVER_URLS = {
        "http://127.0.0.1:4723/",  // Appium 2.0 base URL 
        "http://127.0.0.1:4723/wd/hub",  // Appium 1.x style URL
        "http://localhost:4723/",  // Try with localhost
        "http://localhost:4723/wd/hub"
    };

    /**
     * Initialize the Appium driver for iOS
     */
    public static IOSDriver initializeDriver() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set basic capabilities - using the Appium 2.0 format
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:automationName", "XCUITest");
        
        // Device capabilities - update these to match your actual device or simulator
        capabilities.setCapability("appium:deviceName", "iPhone 15");
        capabilities.setCapability("appium:platformVersion", "18.3");
        
        // Use this for a real device
        capabilities.setCapability("appium:udid", "D0A05791-7A28-425F-A108-433CA671C2A1");
        
        // App capabilities - try both methods
        // Try the IPA file path (this requires the IPA file to exist)
        capabilities.setCapability("appium:app", "/Users/myspace/Documents/Wire.ipa");
        
        // Also set bundleId as a fallback if the app is already installed
        // capabilities.setCapability("appium:bundleId", "com.wearezeta.zclient.ios");
        
        // Additional capabilities for better testing experience
        capabilities.setCapability("appium:newCommandTimeout", 300);
        capabilities.setCapability("appium:includeSafariInWebviews", true);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);
        capabilities.setCapability("appium:noReset", false);  // Reset app state before test
        capabilities.setCapability("appium:fullReset", false);  // Don't uninstall app after test
        
        // Print capabilities info
        System.out.println("With capabilities: " + capabilities);
        
        // Try each Appium server URL in sequence
        Exception lastException = null;
        
        for (String serverUrl : APPIUM_SERVER_URLS) {
            try {
                System.out.println("Attempting to connect to Appium server at: " + serverUrl);
                URL appiumServerURL = new URL(serverUrl);
                IOSDriver driver = new IOSDriver(appiumServerURL, capabilities);
                System.out.println("Successfully connected to Appium server at: " + serverUrl);
                return driver;
            } catch (Exception e) {
                System.out.println("Connection attempt to " + serverUrl + " failed: " + e.getMessage());
                lastException = e;
                // Continue to the next URL
            }
        }
        
        // If we've tried all URLs and none worked, throw the last exception
        System.out.println("All Appium server connection attempts failed");
        if (lastException != null) {
            throw lastException;
        } else {
            throw new MalformedURLException("Could not connect to any Appium server URL");
        }
    }
}
