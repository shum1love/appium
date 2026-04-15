package com.mobile.locators.screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class MainScreenLocators {

    private MainScreenLocators() {}

    public static final By WIKIPEDIA_LOGO = AppiumBy.xpath(
            "//android.widget.ImageView[@resource-id=\"org.wikipedia:id/main_toolbar_wordmark\"]"
    );
    public static final By SEARCH_CONTAINER = AppiumBy.id("org.wikipedia:id/search_container");
}

