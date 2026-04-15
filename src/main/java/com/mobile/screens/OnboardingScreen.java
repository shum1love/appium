package com.mobile.screens;

import com.mobile.locators.screens.OnboardingLocators;
import com.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import java.time.Duration;

/** Экран онбординга/объявлений, позволяющий закрыть их перед работой. */
public class OnboardingScreen {

    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(3);

    private final WaitUtils waitUtils;

    public OnboardingScreen(final AndroidDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Пропустить онбординг и объявления, если отображаются")
    public void closeIfDisplayed() {
        waitUtils.clickIfVisible(OnboardingLocators.SKIP_BUTTON, SHORT_TIMEOUT);
        waitUtils.clickIfVisible(OnboardingLocators.ANNOUNCEMENT_CLOSE_BUTTON, SHORT_TIMEOUT);
    }
}
