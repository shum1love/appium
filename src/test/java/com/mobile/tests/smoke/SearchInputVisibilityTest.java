package com.mobile.tests.smoke;

import com.mobile.base.BaseTest;
import com.mobile.dto.SearchData;
import com.mobile.screens.MainScreen;
import com.mobile.screens.SearchScreen;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Wikipedia Android")
@Feature("Поиск")
@Story("Отображение поля поиска")
@Owner("Родион Шумилов")
@Severity(SeverityLevel.BLOCKER)
@Tag("Smoke")
@DisplayName("Поле поиска видно после ввода")
class SearchInputVisibilityTest extends BaseTest {

    @Test
    @DisplayName("Строка поиска остаётся видимой после ввода")
    void searchShouldKeepInputVisible() {
        final MainScreen mainScreen = openMainScreen();
        final SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.typeSearchQuery(SearchData.APPIUM);

        Assertions.assertTrue(searchScreen.isSearchInputVisible(), "Поле поиска должно оставаться видимым после ввода");
    }
}
