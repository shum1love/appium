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
@Feature("Навигация")
@Story("Возврат из статьи в поиск")
@Owner("Родион Шумилов")
@Severity(SeverityLevel.BLOCKER)
@Tag("Smoke")
@DisplayName("Назад из статьи в поиск")
class BackNavigationTest extends BaseTest {

    @Test
    @DisplayName("Кнопка 'Назад' возвращает на экран поиска")
    void backButtonShouldReturnToSearchScreen() {
        final MainScreen mainScreen = openMainScreen();
        final SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.typeSearchQuery(SearchData.DEFAULT_QUERY)
                .openFirstArticle();

        searchScreen.tapBack();

        Assertions.assertTrue(searchScreen.isSearchInputVisible(), "После возврата должен быть виден экран поиска");
    }
}
