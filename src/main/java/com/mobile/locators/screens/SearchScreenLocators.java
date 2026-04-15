package com.mobile.locators.screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Локаторы экрана поиска Wikipedia (Search screen).
 */
public final class SearchScreenLocators {

    private SearchScreenLocators() {
    }

    public static final By SEARCH_INPUT = AppiumBy.id("org.wikipedia:id/search_src_text");
    public static final By SEARCH_RESULTS_TITLES = AppiumBy.xpath(
            "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View"
    );
    public static final By ARTICLE_TITLE = AppiumBy.xpath("(//android.widget.TextView[@text='Appium'])[1]");
}

