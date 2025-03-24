package com.wire.tests;

import com.wire.utils.AppiumConfig;
import com.wire.utils.AppiumConfig.IDriver;
import com.wire.utils.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestLogin {

    private IDriver driver;
    private String username;
    private String password;

    @BeforeClass
    public void setUp() throws IOException {
        try {
            // Load credentials from a secure file
            Credentials credentials = Credentials.loadCredentials();

            username = credentials.getUsername();
            password = credentials.getPassword();

            System.out.println("Initializing driver...");
            // Initialize the driver
            driver = AppiumConfig.initializeDriver();
            
            System.out.println("Driver initialized successfully");
        } catch (Exception e) {
            System.err.println("Failed to set up test: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testLogin() {
        try {
            System.out.println("Starting login test...");
            
            // Find elements directly
            WebElement usernameField = driver.findElement(By.name("EmailField"));
            WebElement passwordField = driver.findElement(By.name("PasswordField"));
            WebElement loginButton = driver.findElement(By.name("Log In"));

            // Enter credentials and click login
            usernameField.clear();
            usernameField.sendKeys(username);
            passwordField.clear();
            passwordField.sendKeys(password);
            loginButton.click();

            // Find homepage element
            WebElement homePage = driver.findElement(By.id("homePage"));
            
            Assert.assertTrue(homePage.isDisplayed(), "Login failed. Home page not displayed.");
            System.out.println("Login successful.");
        } catch (Exception e) {
            System.err.println("Login test failed: " + e.getMessage());
            Assert.fail("Login test failed with exception: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void testLogout() {
        try {
            System.out.println("Starting logout test...");
            
            // Find logout button
            WebElement logoutButton = driver.findElement(By.id("logoutButton"));
            logoutButton.click();

            // Find login screen after logout
            WebElement loginScreen = driver.findElement(By.id("loginScreen"));
            
            Assert.assertTrue(loginScreen.isDisplayed(), "Logout failed. Login screen not displayed.");
            System.out.println("Logout successful.");
        } catch (Exception e) {
            System.err.println("Logout test failed: " + e.getMessage());
            Assert.fail("Logout test failed with exception: " + e.getMessage());
        }
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Quitting driver...");
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            }
        }
    }
}
