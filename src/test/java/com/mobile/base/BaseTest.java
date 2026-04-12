package com.mobile.base;

import com.mobile.driver.DriverFactory;
import com.mobile.pages.MainScreen;
import com.mobile.pages.OnboardingScreen;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    protected AndroidDriver driver;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createAndroidDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected MainScreen openMainScreen() {
        final OnboardingScreen onboardingScreen = new OnboardingScreen(driver);
        onboardingScreen.closeIfDisplayed();

        final MainScreen mainScreen = new MainScreen(driver);
        mainScreen.ensureOpened();
        return mainScreen;
    }
}
