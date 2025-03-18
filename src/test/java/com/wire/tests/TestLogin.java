package com.wire.tests;


import com.wire.utils.AppiumConfig;
import com.wire.utils.Credentials;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestLogin {

    private IOSDriver driver;
    private String username;
    private String password;

    @BeforeClass
    public void setUp() throws IOException {
        // Load credentials from a secure file
        Credentials credentials = Credentials.loadCredentials();

        username = credentials.getUsername();
        password = credentials.getPassword();

        // Initialize the Appium driver (iOS)
        driver = AppiumConfig.initializeDriver();

        // Open the app via deeplink
        driver.get("wire://access/?config=https://staging-nginz-https.zinfra.io/deeplink.json");
    }

    @Test
    public void testLogin() {
        // Find username and password fields and input data
        WebElement usernameField = driver.findElement(By.name("EmailField"));
        WebElement passwordField = driver.findElement(By.name("PasswordField"));
        WebElement loginButton = driver.findElement(By.name("Log In"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        // Wait for login to complete (could use WebDriverWait or implicit wait)
        // Add logic to wait for login success element, e.g., a home page element
        WebElement homePage = driver.findElement(By.id("homePage"));
        Assert.assertTrue(homePage.isDisplayed(), "Login failed. Home page not displayed.");

        System.out.println("Login successful.");
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void testLogout() {
        // Find and click the logout button
        WebElement logoutButton = driver.findElement(By.id("logoutButton"));
        logoutButton.click();

        // Verify if logout is successful by checking the presence of the login screen
        WebElement loginScreen = driver.findElement(By.id("loginScreen"));
        Assert.assertTrue(loginScreen.isDisplayed(), "Logout failed. Login screen not displayed.");

        System.out.println("Logout successful.");
    }
}
