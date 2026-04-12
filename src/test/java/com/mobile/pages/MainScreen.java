package com.mobile.pages;

import com.mobile.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class MainScreen {

    private static final By SEARCH_CONTAINER = AppiumBy.id("org.wikipedia:id/search_container");
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public MainScreen(final AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public boolean isOpened() {
        return waitUtils.waitForVisible(SEARCH_CONTAINER).isDisplayed();
    }

    public void ensureOpened() {
        if (waitUtils.isVisible(SEARCH_CONTAINER, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        if (waitUtils.isVisible(SEARCH_CONTAINER, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        if (waitUtils.isVisible(SEARCH_CONTAINER, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        waitUtils.waitForVisible(SEARCH_CONTAINER);
    }

    public SearchScreen openSearch() {
        waitUtils.waitForVisible(SEARCH_CONTAINER).click();
        return new SearchScreen(driver);
    }
}
