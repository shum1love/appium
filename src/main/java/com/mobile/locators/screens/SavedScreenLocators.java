package com.mobile.locators.screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Локаторы экрана Saved (сохранённые материалы) Wikipedia.
 * <p>
 * Для смок-проверок достаточно наличия заголовка раздела.
 * </p>
 */
public final class SavedScreenLocators {

    private SavedScreenLocators() {
    }

    /** Заголовок раздела Saved. */
    public static final By SAVED_TITLE = AppiumBy.xpath("//*[@text='Saved']");
}

