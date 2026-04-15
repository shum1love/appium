package com.mobile.tests.smoke;

import com.mobile.base.BaseTest;
import com.mobile.dto.SearchData;
import com.mobile.screens.SearchScreen;
import com.mobile.screens.components.BottomNavBar;
import com.mobile.utils.AllureAttachments;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Wikipedia Android")
@Feature("Нижнее меню")
@Story("Переключение в Search + поиск")
@Owner("Родион Шумилов")
@Severity(SeverityLevel.BLOCKER)
@Tag("Smoke")
@DisplayName("Нижнее меню: Search")
class BottomMenuSearchNavigationTest extends BaseTest {

    @Test
    @DisplayName("Переключение на Search открывает раздел и позволяет искать")
    void shouldOpenSearchTabAndSearchFromBottomMenu() {
        openMainScreen();

        final SearchScreen searchScreen = new BottomNavBar(driver).openSearch();
        AllureAttachments.attachScreenshot(driver, "Search tab opened");

        Assertions.assertTrue(searchScreen.isSearchHeaderVisible(), "Должен отображаться заголовок Search");

        searchScreen.typeSearchQuery(SearchData.APPIUM);
        AllureAttachments.attachScreenshot(driver, "Search results");

        Assertions.assertTrue(searchScreen.hasResults(), "Результаты поиска должны отображаться");
    }
}

