package com.mobile.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public final class DriverFactory {

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String DEVICE_NAME = System.getProperty("deviceName", "Android Emulator");
    private static final String PLATFORM_VERSION = System.getProperty("platformVersion", "");
    private static final String AUTOMATION_NAME = "UiAutomator2";
    private static final String APP_PACKAGE = "org.wikipedia";
    private static final String APP_ACTIVITY = "org.wikipedia.main.MainActivity";

    private DriverFactory() {
    }

    public static AndroidDriver createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName(AUTOMATION_NAME)
                .setDeviceName(DEVICE_NAME)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setNoReset(true)
                .setNewCommandTimeout(Duration.ofSeconds(120));

        if (!PLATFORM_VERSION.isBlank()) {
            options.setPlatformVersion(PLATFORM_VERSION);
        }

        try {
            return new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium server URL: " + APPIUM_SERVER_URL, e);
        }
    }
}
