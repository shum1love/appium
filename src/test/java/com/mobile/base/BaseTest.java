package com.mobile.base;

import com.mobile.helper.DriverFactory;
import com.mobile.screens.MainScreen;
import com.mobile.screens.OnboardingScreen;
import com.mobile.utils.AllureAttachments;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Базовый класс для мобильных UI-тестов на Appium.
 **/
@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {

    protected AndroidDriver driver;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createAndroidDriver();
        driver.terminateApp("org.wikipedia");
        driver.activateApp("org.wikipedia");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            AllureAttachments.attachScreenshot(driver, "Final screenshot");
            driver.quit();
        }
    }

    @Step("Открыть главную страницу Википедии")
    protected MainScreen openMainScreen() {
        final OnboardingScreen onboardingScreen = new OnboardingScreen(driver);
        onboardingScreen.closeIfDisplayed();

        final MainScreen mainScreen = new MainScreen(driver);
        mainScreen.ensureOpened();
        return mainScreen;
    }
}
