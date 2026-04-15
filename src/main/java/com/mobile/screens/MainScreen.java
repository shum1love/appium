package com.mobile.screens;

import com.mobile.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

/** Экран главной страницы Википедии. */
public class MainScreen {

    private static final By WIKIPEDIA_LOGO = AppiumBy.xpath("//android.widget.ImageView[@resource-id=\"org.wikipedia:id/main_toolbar_wordmark\"]");
    private static final By SEARCH_CONTAINER = AppiumBy.id("org.wikipedia:id/search_container");
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public MainScreen(final AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Проверить, что открыта главная страница")
    public boolean isOpened() {
        return waitUtils.waitForVisible(WIKIPEDIA_LOGO).isDisplayed();
    }

    @Step("Убедиться, что открыта главная страница (обработка back stack)")
    public void ensureOpened() {
        if (waitUtils.isVisible(WIKIPEDIA_LOGO, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        if (waitUtils.isVisible(WIKIPEDIA_LOGO, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        if (waitUtils.isVisible(WIKIPEDIA_LOGO, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        waitUtils.waitForVisible(WIKIPEDIA_LOGO);
    }

    @Step("Открыть поиск")
    public SearchScreen openSearch() {
        waitUtils.waitForVisible(SEARCH_CONTAINER).click();
        return new SearchScreen(driver);
    }
}
