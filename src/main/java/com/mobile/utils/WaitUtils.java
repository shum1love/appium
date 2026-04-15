package com.mobile.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Утилитный класс для выполнения ожиданий и проверок видимости элементов
 * в мобильных тестах на платформе Appium (Android).
 * <p>
 * Предоставляет методы для явного ожидания появления, исчезновения элементов,
 * а также для безопасного получения первого видимого элемента из коллекции.
 * </p>
 */
public class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    /**
     * Конструктор класса WaitUtils.
     */
    public WaitUtils(final AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    /**
     * Ожидает появления и видимости элемента на экране с таймаутом по умолчанию.
     */
    public WebElement waitForVisible(final By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Ожидает появления и видимости элемента на экране с заданным таймаутом.
     */
    public WebElement waitForVisible(final By locator, final Duration timeout) {
        final WebDriverWait localWait = new WebDriverWait(driver, timeout);
        return localWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Ожидает, пока хотя бы один элемент, соответствующий локатору, станет видимым.
     * Использует таймаут по умолчанию.
     */
    public boolean waitForAnyVisible(final By locator) {
        return wait.until(driver -> {
            final List<WebElement> elements = driver.findElements(locator);
            return elements.stream().anyMatch(WebElement::isDisplayed);
        });
    }

    /**
     * Ожидает, пока элемент, найденный по локатору, исчезнет или станет невидимым.
     * Использует таймаут по умолчанию.
     */
    public boolean waitForInvisible(final By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Проверяет видимость элемента в течение заданного времени.
     * Возвращает результат проверки, не выбрасывая исключение при отсутствии элемента.
     */
    public boolean isVisible(final By locator, final Duration timeout) {
        try {
            waitForVisible(locator, timeout);
            return true;
        } catch (final RuntimeException ignored) {
            return false;
        }
    }

    /**
     * Выполняет клик по элементу, если он становится видимым в течение заданного времени.
     */
    public boolean clickIfVisible(final By locator, final Duration timeout) {
        if (isVisible(locator, timeout)) {
            driver.findElement(locator).click();
            return true;
        }
        return false;
    }

    /**
     * Пытается кликнуть по первому видимому локатору из списка за заданный таймаут.
     */
    public boolean clickFirstVisible(final Duration timeout, final By... locators) {
        for (final By locator : locators) {
            if (clickIfVisible(locator, timeout)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает первый видимый элемент из коллекции по локатору.
     * Ждёт появления хотя бы одного элемента в течение таймаута по умолчанию.
     */
    public WebElement getFirstElement(final By locator) {
        return wait.until(driver -> {
            final List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty()) {
                return null;
            }
            final WebElement first = elements.get(0);
            return first.isDisplayed() ? first : null;
        });
    }

    /**
     * Возвращает первый видимый элемент из коллекции по локатору с кастомным таймаутом.
     */
    public WebElement getFirstElement(final By locator, final Duration timeout) {
        final WebDriverWait localWait = new WebDriverWait(driver, timeout);
        return localWait.until(driver -> {
            final List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty()) {
                return null;
            }
            final WebElement first = elements.get(0);
            return first.isDisplayed() ? first : null;
        });
    }
}