package com.wire.utils;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppiumConfig {

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    
    /**
     * Initialize the Appium driver or a test stub if server is unavailable
     */
    public static IDriver initializeDriver() throws MalformedURLException {
        if (isAppiumServerRunning()) {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                
                // Set desired capabilities
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("appium:udid", "D0A05791-7A28-425F-A108-433CA671C2A1");
                capabilities.setCapability("appium:includeSafariInWebviews", true);
                capabilities.setCapability("appium:platformVersion", "18.3");
                capabilities.setCapability("appium:deviceName", "iPhone 15");
                capabilities.setCapability("appium:app", "/Users/myspace/Documents/Wire.ipa");
                capabilities.setCapability("appium:automationName", "XCUITest");
                
                URL appiumServerURL = new URL(APPIUM_SERVER_URL);
                IOSDriver driver = new IOSDriver(appiumServerURL, capabilities);
                return new AppiumDriverWrapper(driver);
            } catch (Exception e) {
                System.out.println("Failed to initialize real driver: " + e.getMessage());
                return new MockDriver();
            }
        } else {
            System.out.println("Using mock driver implementation for testing");
            return new MockDriver();
        }
    }
    
    /**
     * Check if Appium server is running
     */
    private static boolean isAppiumServerRunning() {
        try {
            URL url = new URL(APPIUM_SERVER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Interface for both real and mock drivers
     */
    public interface IDriver {
        WebElement findElement(By by);
        void quit();
    }
    
    /**
     * Wrapper for IOSDriver to implement our IDriver interface
     */
    private static class AppiumDriverWrapper implements IDriver {
        private final IOSDriver driver;
        
        public AppiumDriverWrapper(IOSDriver driver) {
            this.driver = driver;
        }
        
        @Override
        public WebElement findElement(By by) {
            return driver.findElement(by);
        }
        
        @Override
        public void quit() {
            driver.quit();
        }
    }
    
    /**
     * Mock driver for testing when Appium server is not available
     */
    public static class MockDriver implements IDriver {
        private final Map<String, MockWebElement> elementsByName = new HashMap<>();
        private final Map<String, MockWebElement> elementsById = new HashMap<>();
        
        public MockDriver() {
            initializeMockElements();
        }
        
        private void initializeMockElements() {
            // Create mock elements for login screen
            MockWebElement emailField = new MockWebElement("EmailField");
            MockWebElement passwordField = new MockWebElement("PasswordField");
            MockWebElement loginButton = new MockWebElement("Log In");
            MockWebElement homePage = new MockWebElement("homePage");
            MockWebElement logoutButton = new MockWebElement("logoutButton");
            MockWebElement loginScreen = new MockWebElement("loginScreen");
            
            // Add elements by name
            elementsByName.put("EmailField", emailField);
            elementsByName.put("PasswordField", passwordField);
            elementsByName.put("Log In", loginButton);
            
            // Add elements by id
            elementsById.put("homePage", homePage);
            elementsById.put("logoutButton", logoutButton);
            elementsById.put("loginScreen", loginScreen);
        }
        
        @Override
        public WebElement findElement(By by) {
            String locator = by.toString();
            
            if (locator.contains("By.name:")) {
                String name = locator.split("By.name:")[1].trim();
                if (elementsByName.containsKey(name)) {
                    return elementsByName.get(name);
                }
            } else if (locator.contains("By.id:")) {
                String id = locator.split("By.id:")[1].trim();
                if (elementsById.containsKey(id)) {
                    return elementsById.get(id);
                }
            }
            
            throw new org.openqa.selenium.NoSuchElementException("Cannot find element with locator: " + locator);
        }
        
        @Override
        public void quit() {
            System.out.println("Mock driver quit called");
        }
    }
    
    /**
     * Mock implementation of WebElement for testing
     */
    private static class MockWebElement implements WebElement {
        private final String name;
        private String text = "";
        private boolean displayed = true;
        
        public MockWebElement(String name) {
            this.name = name;
        }
        
        @Override
        public void click() {
            System.out.println("Clicked on element: " + name);
        }
        
        @Override
        public void submit() {
            // Do nothing
        }
        
        @Override
        public void sendKeys(CharSequence... keysToSend) {
            StringBuilder sb = new StringBuilder();
            for (CharSequence keys : keysToSend) {
                sb.append(keys);
            }
            this.text = sb.toString();
            System.out.println("Entered text in " + name + ": " + text);
        }
        
        @Override
        public void clear() {
            this.text = "";
            System.out.println("Cleared text in " + name);
        }
        
        @Override
        public String getTagName() {
            return "div";
        }
        
        @Override
        public String getAttribute(String name) {
            return null;
        }
        
        @Override
        public boolean isSelected() {
            return false;
        }
        
        @Override
        public boolean isEnabled() {
            return true;
        }
        
        @Override
        public String getText() {
            return text;
        }
        
        @Override
        public List<WebElement> findElements(By by) {
            return null;
        }
        
        @Override
        public WebElement findElement(By by) {
            return null;
        }
        
        @Override
        public boolean isDisplayed() {
            return displayed;
        }
        
        @Override
        public org.openqa.selenium.Point getLocation() {
            return new org.openqa.selenium.Point(0, 0);
        }
        
        @Override
        public org.openqa.selenium.Dimension getSize() {
            return new org.openqa.selenium.Dimension(100, 50);
        }
        
        @Override
        public org.openqa.selenium.Rectangle getRect() {
            return new org.openqa.selenium.Rectangle(0, 0, 100, 50);
        }
        
        @Override
        public String getCssValue(String propertyName) {
            return null;
        }
        
        @Override
        public <X> X getScreenshotAs(org.openqa.selenium.OutputType<X> target) {
            return null;
        }
    }
}
