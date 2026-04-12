package com.mobile.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(final AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public WebElement waitForVisible(final By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForVisible(final By locator, final Duration timeout) {
        final WebDriverWait localWait = new WebDriverWait(driver, timeout);
        return localWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForAnyVisible(final By locator) {
        return wait.until(driver -> {
            final List<WebElement> elements = driver.findElements(locator);
            return elements.stream().anyMatch(WebElement::isDisplayed);
        });
    }

    public boolean waitForInvisible(final By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean isVisible(final By locator, final Duration timeout) {
        try {
            waitForVisible(locator, timeout);
            return true;
        } catch (final RuntimeException ignored) {
            return false;
        }
    }

    public boolean clickIfVisible(final By locator, final Duration timeout) {
        if (isVisible(locator, timeout)) {
            driver.findElement(locator).click();
            return true;
        }
        return false;
    }
}
