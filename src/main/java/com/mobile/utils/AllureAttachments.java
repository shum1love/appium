package com.mobile.utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class AllureAttachments {

    private AllureAttachments() {
    }

    public static void attachScreenshot(final AndroidDriver driver, final String name) {
        try {
            final byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            attachPng(bytes, name);
        } catch (final RuntimeException ignored) {
            // attachment is best-effort; test outcome should not depend on it
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    private static byte[] attachPng(final byte[] bytes, final String name) {
        return bytes;
    }
}

