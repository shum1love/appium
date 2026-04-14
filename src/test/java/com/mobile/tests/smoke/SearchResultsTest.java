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
@Story("Получение результатов поиска")
@Owner("Родион Шумилов")
@Severity(SeverityLevel.BLOCKER)
@Tag("Smoke")
@DisplayName("Поиск возвращает результаты")
class SearchResultsTest extends BaseTest {

    @Test
    @DisplayName("Поиск по запросу выдаёт результаты")
    void searchResultsShouldBeDisplayed() {
        final MainScreen mainScreen = openMainScreen();
        final SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.typeSearchQuery(SearchData.DEFAULT_QUERY);

        Assertions.assertTrue(searchScreen.hasResults(), "Результаты поиска должны отображаться");
    }
}
