package com.mobile.screens;

import com.mobile.locators.screens.MainScreenLocators;
import com.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import java.time.Duration;

/** Экран главной страницы Википедии. */
public class MainScreen {

    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public MainScreen(final AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Проверить, что открыта главная страница")
    public boolean isOpened() {
        return waitUtils.waitForVisible(MainScreenLocators.WIKIPEDIA_LOGO).isDisplayed();
    }

    @Step("Убедиться, что открыта главная страница (обработка back stack)")
    public void ensureOpened() {
        if (waitUtils.isVisible(MainScreenLocators.WIKIPEDIA_LOGO, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        if (waitUtils.isVisible(MainScreenLocators.WIKIPEDIA_LOGO, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        if (waitUtils.isVisible(MainScreenLocators.WIKIPEDIA_LOGO, SHORT_TIMEOUT)) {
            return;
        }
        driver.navigate().back();
        waitUtils.waitForVisible(MainScreenLocators.WIKIPEDIA_LOGO);
    }

    @Step("Открыть поиск")
    public SearchScreen openSearch() {
        waitUtils.waitForVisible(MainScreenLocators.SEARCH_CONTAINER).click();
        return new SearchScreen(driver);
    }
}
