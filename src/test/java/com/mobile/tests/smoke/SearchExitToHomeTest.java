package com.mobile.tests.smoke;

import com.mobile.base.BaseTest;
import com.mobile.screens.MainScreen;
import com.mobile.screens.SearchScreen;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Wikipedia Android")
@Feature("Навигация")
@Story("Закрытие поиска")
@Owner("Родион Шумилов")
@Severity(SeverityLevel.BLOCKER)
@Tag("Smoke")
@DisplayName("Выход из поиска на главную")
class SearchExitToHomeTest extends BaseTest {

    @Test
    @DisplayName("Кнопка \"Назад\" из поиска возвращает на главную страницу")
    void shouldReturnToMainFromSearch() {
        final MainScreen mainScreen = openMainScreen();
        final SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.tapBack();

        Assertions.assertTrue(mainScreen.isOpened(), "После выхода из поиска должна открыться главная страница");
    }
}
