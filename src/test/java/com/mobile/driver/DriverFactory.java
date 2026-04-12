package com.mobile.driver;

import com.mobile.config.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static AndroidDriver createAndroidDriver() {
        final UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(TestConfig.getPlatformName())
                .setAutomationName(TestConfig.getAutomationName())
                .setDeviceName(TestConfig.getDeviceName())
                .setAppPackage(TestConfig.getAppPackage())
                .setAppActivity(TestConfig.getAppActivity())
                .setNoReset(TestConfig.isNoReset())
                .setNewCommandTimeout(Duration.ofSeconds(TestConfig.getNewCommandTimeoutSeconds()));

        if (!TestConfig.getPlatformVersion().isBlank()) {
            options.setPlatformVersion(TestConfig.getPlatformVersion());
        }

        try {
            return new AndroidDriver(new URL(TestConfig.getAppiumServerUrl()), options);
        } catch (final MalformedURLException exception) {
            throw new IllegalStateException("Invalid Appium server URL: " + TestConfig.getAppiumServerUrl(), exception);
        }
    }
}
