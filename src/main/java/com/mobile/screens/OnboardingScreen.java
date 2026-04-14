package com.mobile.screens;

import com.mobile.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

/** Экран онбординга/объявлений, позволяющий закрыть их перед работой. */
public class OnboardingScreen {

    private static final By SKIP_BUTTON = AppiumBy.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private static final By ANNOUNCEMENT_CLOSE_BUTTON = AppiumBy.id("org.wikipedia:id/view_announcement_action_negative");
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);

    private final WaitUtils waitUtils;

    public OnboardingScreen(final AndroidDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Пропустить онбординг и объявления, если отображаются")
    public void closeIfDisplayed() {
        waitUtils.clickIfVisible(SKIP_BUTTON, SHORT_TIMEOUT);
        waitUtils.clickIfVisible(ANNOUNCEMENT_CLOSE_BUTTON, SHORT_TIMEOUT);
    }
}
