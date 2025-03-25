# Wire iOS Login Automation

This project contains automated tests for the Wire iOS app login functionality using Appium 2.0, Java, and TestNG.

## Project Overview

The automation tests verify the following functionality:
- Opening the Wire app with a deeplink
- Navigating through the login flow
- Entering credentials
- Verifying successful login

## Prerequisites

- macOS (required for iOS testing)
- Java JDK 11 or higher
- Maven
- Node.js (v16 or higher)
- Xcode (latest version recommended)
- iOS Simulator or real device
- Appium 2.0

## Installation Steps

### 1. Install Java JDK

```bash
# Using Homebrew
brew install openjdk@17

# Verify installation
java -version
javac -version
```

### 2. Install Maven

```bash
# Using Homebrew
brew install maven

# Verify installation
mvn -version
```

### 3. Install Node.js

```bash
# Using Homebrew
brew install node

# Verify installation
node -v
npm -v
```

### 4. Install Appium 2.0

```bash
# Install Appium globally
npm install -g appium@next

# Verify installation
appium -v
```

### 5. Install Appium Drivers

```bash
# Install XCUITest driver (for iOS)
appium driver install xcuitest

# Verify installed drivers
appium driver list
```

### 6. Install Appium Inspector (Optional, but recommended)

Download and install Appium Inspector from the [GitHub releases page](https://github.com/appium/appium-inspector/releases).

### 7. Configure iOS for Automation

1. Install Xcode from the App Store
2. Install Xcode Command Line Tools:
   ```bash
   xcode-select --install
   ```
3. Set up iOS Simulator or real device for testing

## Project Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/wire-qa-challenge.git
   cd wire-qa-challenge
   ```

2. Update credentials:
   Edit `src/test/resources/credentials.json` with valid Wire test account credentials:
   ```json
   {
     "username": "your_test_email@example.com",
     "password": "your_test_password",
     "deeplink": "wire://access/?config=https://staging-nginz-https.zinfra.io/deeplink.json"
   }
   ```

3. Configure the Appium capabilities:
   If needed, update the `AppiumConfig.java` file in `src/main/java/com/wire/utils/` to match your device configuration.

## Running Tests

1. Start the Appium server:
   ```bash
   appium
   ```

2. In a separate terminal, run the tests:
   ```bash
   cd /path/to/WireQAChallenge/com.wire
   mvn clean test
   ```

## Project Structure

```
com.wire/
├── pom.xml                  # Maven configuration file
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── wire/
│   │               └── utils/
│   │                   ├── AppiumConfig.java    # Appium configuration
│   │                   ├── Credentials.java     # Credentials manager
│   │                   └── Locators.java        # UI element locators
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── wire/
│       │           └── tests/
│       │               └── TestLogin.java       # Login test
│       └── resources/
│           └── credentials.json                 # Credentials file
└── README.md
```

## Key Components

- **AppiumConfig**: Configures the Appium driver with all necessary capabilities for iOS testing
- **Credentials**: Handles loading and management of test credentials
- **Locators**: Centralized repository of UI element locators
- **TestLogin**: Test class that executes the login flow

## Troubleshooting

### Common Issues and Solutions

1. **Appium Server Connection Failed**
   - Make sure Appium server is running
   - Check server URL and port in AppiumConfig
   - Verify no firewall is blocking the connection

2. **Device Not Found**
   - For real devices, verify device is connected and trusted
   - For simulators, verify the simulator is created and running
   - Check the UDID is correct in AppiumConfig

3. **Elements Not Found**
   - Use Appium Inspector to verify locators
   - Increase wait timeouts if needed
   - Check if app UI has changed

4. **WebDriverException**
   - Restart Appium server
   - Reinstall Appium drivers
   - Update Appium to the latest version

### Appium Logs

If you encounter issues, check the Appium server logs for detailed information. You can enable more verbose logging by starting Appium with:

```bash
appium --log-level debug
```

## Best Practices

1. Keep credentials in a separate file and never commit sensitive data to version control
2. Centralize element locators for easy maintenance
3. Add proper waits to handle UI transitions
4. Use descriptive test and method names
5. Add proper error handling and logging

## Resources

- [Appium Documentation](https://appium.io/docs/en/2.0/)
- [XCUITest Driver Documentation](https://github.com/appium/appium-xcuitest-driver)
- [TestNG Documentation](https://testng.org/doc/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Java Client for Appium](https://github.com/appium/java-client)

## License

[Include license information here]