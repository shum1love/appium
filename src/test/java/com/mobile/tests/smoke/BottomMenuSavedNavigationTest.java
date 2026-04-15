package com.mobile.tests.smoke;

import com.mobile.base.BaseTest;
import com.mobile.screens.SavedScreen;
import com.mobile.screens.components.BottomNavBar;
import com.mobile.utils.AllureAttachments;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Wikipedia Android")
@Feature("Нижнее меню")
@Story("Переключение в Saved")
@Owner("Родион Шумилов")
@Severity(SeverityLevel.BLOCKER)
@Tag("Smoke")
@DisplayName("Нижнее меню: Saved")
class BottomMenuSavedNavigationTest extends BaseTest {

    @Test
    @DisplayName("Переключение на Saved отображает заголовок Saved")
    void shouldOpenSavedTabFromBottomMenu() {
        openMainScreen();

        final SavedScreen savedScreen = new BottomNavBar(driver).openSaved();
        AllureAttachments.attachScreenshot(driver, "Saved tab");

        Assertions.assertTrue(savedScreen.isTitleDisplayed(), "Должен отображаться заголовок Saved");
    }
}

