package com.mobile.screens;

import com.mobile.locators.screens.SavedScreenLocators;
import com.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

/** Экран Saved (сохранённые материалы) в Википедии. */
public class SavedScreen {

    private final WaitUtils waitUtils;

    public SavedScreen(final AndroidDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Проверить, что отображается заголовок Saved")
    public boolean isTitleDisplayed() {
        return waitUtils.waitForVisible(SavedScreenLocators.SAVED_TITLE).isDisplayed();
    }
}

