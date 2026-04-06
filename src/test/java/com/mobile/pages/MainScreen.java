package com.mobile.pages;

import com.mobile.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MainScreen {

    private static final By SEARCH_CONTAINER = AppiumBy.id("org.wikipedia:id/search_container");

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public MainScreen(AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public boolean isOpened() {
        return waitUtils.waitForVisible(SEARCH_CONTAINER).isDisplayed();
    }

    public SearchScreen openSearch() {
        waitUtils.waitForVisible(SEARCH_CONTAINER).click();
        return new SearchScreen(driver);
    }
}
