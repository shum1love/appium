package com.mobile.screens.components;

import com.mobile.locators.navigation.BottomNavLocators;
import com.mobile.screens.SavedScreen;
import com.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import java.time.Duration;

/**
 * Компонент нижнего меню (Bottom Navigation) Wikipedia.
 */
public class BottomNavBar {

    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public BottomNavBar(final AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Перейти в раздел Saved через нижнее меню")
    public SavedScreen openSaved() {
        final boolean clicked = waitUtils.clickFirstVisible(
                SHORT_TIMEOUT,
                BottomNavLocators.TAB_SAVED_BY_ID,
                BottomNavLocators.TAB_SAVED_READING_LISTS_BY_ID,
                BottomNavLocators.TAB_SAVED_BY_TEXT
        );
        if (!clicked) {
            throw new AssertionError("Не удалось нажать на вкладку Saved в нижнем меню");
        }
        return new SavedScreen(driver);
    }
}

