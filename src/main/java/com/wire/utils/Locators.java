package com.wire.utils;

import org.openqa.selenium.By;

/**
 * Centralized locators for the Wire app UI elements
 */
public class Locators {
    // Deeplink dialog
    public static final By OPEN_BUTTON = By.xpath("//XCUIElementTypeButton[@name=\"Open\"]");
    public static final By PROCEED_BUTTON = By.xpath("//XCUIElementTypeButton[@name=\"Proceed\"]");

    // Login screen
    public static final By LOGIN_BUTTON = By.xpath("//XCUIElementTypeStaticText[@name=\"Log in\"]");
    public static final By EMAIL_FIELD = By.name("EmailField");
    public static final By PASSWORD_FIELD = By.name("PasswordField");

    // Home screen elements
    public static final By IGNORE_BACKUP_BUTTON = By.name("ignore_backup");
    public static final By BOTTOM_BAR_SETTINGS_BUTTON = By.name("bottomBarSettingsButton");
    public static final By ACCOUNT_BUTTON = By.name("Account");
    public static final By LOGOUT_BUTTON = By.name("Log OutField");

}
    

