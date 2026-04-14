package com.mobile.utils;

/** Системные настройки тестов, считываемые из системных свойств. */
public final class TestConfig {

    private static final String APPIUM_SERVER_URL = System.getProperty("appiumServerUrl", "http://127.0.0.1:4723");
    private static final String DEVICE_NAME = System.getProperty("deviceName", "Android Emulator");
    private static final String PLATFORM_NAME = System.getProperty("platformName", "Android");
    private static final String PLATFORM_VERSION = System.getProperty("platformVersion", "");
    private static final String AUTOMATION_NAME = System.getProperty("automationName", "UiAutomator2");
    private static final String APP_PACKAGE = System.getProperty("appPackage", "org.wikipedia");
    private static final String APP_ACTIVITY = System.getProperty("appActivity", "org.wikipedia.main.MainActivity");
    private static final boolean NO_RESET = Boolean.parseBoolean(System.getProperty("noReset", "true"));
    private static final int NEW_COMMAND_TIMEOUT_SECONDS = Integer.parseInt(System.getProperty("newCommandTimeoutSec", "120"));

    private TestConfig() {
    }

    public static String getAppiumServerUrl() {
        return APPIUM_SERVER_URL;
    }

    public static String getDeviceName() {
        return DEVICE_NAME;
    }

    public static String getPlatformName() {
        return PLATFORM_NAME;
    }

    public static String getPlatformVersion() {
        return PLATFORM_VERSION;
    }

    public static String getAutomationName() {
        return AUTOMATION_NAME;
    }

    public static String getAppPackage() {
        return APP_PACKAGE;
    }

    public static String getAppActivity() {
        return APP_ACTIVITY;
    }

    public static boolean isNoReset() {
        return NO_RESET;
    }

    public static int getNewCommandTimeoutSeconds() {
        return NEW_COMMAND_TIMEOUT_SECONDS;
    }
}
