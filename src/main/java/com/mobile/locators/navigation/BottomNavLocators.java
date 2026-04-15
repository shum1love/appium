package com.mobile.locators.navigation;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Локаторы нижнего меню (Bottom Navigation) приложения Wikipedia.
 */
public final class BottomNavLocators {

    private BottomNavLocators() {
    }

    public static final By TAB_SAVED_BY_ID = AppiumBy.id("org.wikipedia:id/nav_tab_saved");
    public static final By TAB_SAVED_READING_LISTS_BY_ID = AppiumBy.id("org.wikipedia:id/nav_tab_reading_lists");
    public static final By TAB_SAVED_BY_TEXT = AppiumBy.xpath("//*[@text='Saved' or @content-desc='Saved']");
}

