package com.mobile.locators.screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class SavedScreenLocators {

    private SavedScreenLocators() {
    }

    public static final By SAVED_TITLE = AppiumBy.xpath("//*[@text='Saved']");
}

