package com.wire.tests;

import com.wire.utils.AppiumConfig;
import com.wire.utils.Credentials;
import com.wire.utils.Locators;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

/**
 * Test for verifying login functionality
 */
public class TestLogin {
    private IOSDriver driver;
    private String username;
    private String password;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {
        Credentials credentials = Credentials.loadCredentials();
        username = credentials.getUsername();
        password = credentials.getPassword();
        driver = AppiumConfig.initializeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testLogin() {
        try {
            String deeplink;
            try {
                deeplink = Credentials.loadCredentials().getDeeplink();
            } catch (IOException e) {
                deeplink = "wire://access/?config=https://staging-nginz-https.zinfra.io/deeplink.json";
            }
            driver.get(deeplink);

            clickIfExists(Locators.OPEN_BUTTON);
            safeWait(2);
            clickIfExists(Locators.PROCEED_BUTTON);
            safeWait(2);
            clickIfExists(Locators.LOGIN_BUTTON);

            enterText(Locators.EMAIL_FIELD, username);
            enterText(Locators.PASSWORD_FIELD, password);
            clickIfExists(Locators.LOGIN_BUTTON);

            safeWait(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogout() {
        clickIfExists(Locators.BOTTOM_BAR_SETTINGS_BUTTON);
        clickIfExists(Locators.ACCOUNT_BUTTON);
        clickIfExists(Locators.LOGOUT_BUTTON);
    }

    private void clickIfExists(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception ignored) {
        }
    }

    private void enterText(By locator, String text) {
        try {
            WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
            field.clear();
            field.sendKeys(text);
        } catch (Exception ignored) {
        }
    }

    private void safeWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ignored) {
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
