package com.mobile.locators.screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class OnboardingLocators {

    private OnboardingLocators() {}

    public static final By SKIP_BUTTON = AppiumBy.id("org.wikipedia:id/fragment_onboarding_skip_button");
    public static final By ANNOUNCEMENT_CLOSE_BUTTON = AppiumBy.id("org.wikipedia:id/view_announcement_action_negative");
}

