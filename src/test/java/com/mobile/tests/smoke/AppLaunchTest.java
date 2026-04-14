package com.mobile.tests.smoke;

import com.mobile.base.BaseTest;
import com.mobile.screens.MainScreen;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Смок-тест запуска приложения и открытия главного экрана. */
@Epic("Wikipedia Android")
@Feature("Запуск приложения")
@Story("Открытие главного экрана")
@Owner("Родион Шумилов")
@Severity(SeverityLevel.BLOCKER)
@Tag("Smoke")
@DisplayName("Запуск приложения")
class AppLaunchTest extends BaseTest {

    @Test
    @DisplayName("Главная страница открывается при запуске")
    void shouldOpenMainScreen() {
        final MainScreen mainScreen = openMainScreen();
        Assertions.assertTrue(mainScreen.isOpened(), "Должна открыться главная страница Википедии");
    }
}
