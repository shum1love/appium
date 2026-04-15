package com.mobile.utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

/**
 * Утилиты для вложений Allure (скриншоты и т.п.).
 * <p>
 * Вложения делаются best-effort: не должны менять исход теста, даже если
 * в момент снятия скриншота драйвер недоступен.
 * </p>
 */
public final class AllureAttachments {

    private AllureAttachments() {
    }

    /**
     * Прикрепляет скриншот экрана в Allure.
     *
     * @param driver AndroidDriver текущей сессии
     * @param name   имя вложения (отобразится в Allure)
     */
    public static void attachScreenshot(final AndroidDriver driver, final String name) {
        try {
            final byte[] bytes = driver.getScreenshotAs(OutputType.BYTES);
            attachPng(bytes, name);
        } catch (final RuntimeException ignored) {
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    private static byte[] attachPng(final byte[] bytes, @SuppressWarnings("unused") final String name) {
        return bytes;
    }
}

