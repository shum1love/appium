package com.mobile.tests;

import com.mobile.base.BaseTest;
import com.mobile.pages.MainScreen;
import com.mobile.pages.SearchScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SmokeTests extends BaseTest {

    private static final String SEARCH_QUERY = "Appium";

    @Test
    void appShouldOpenSuccessfully() {
        MainScreen mainScreen = new MainScreen(driver);

        Assertions.assertTrue(mainScreen.isOpened(), "Wikipedia main screen should be opened");
    }

    @Test
    void searchShouldWork() {
        MainScreen mainScreen = new MainScreen(driver);
        SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.typeSearchQuery(SEARCH_QUERY);

        Assertions.assertTrue(searchScreen.isSearchInputVisible(), "Search input should stay visible after typing");
    }

    @Test
    void searchResultsShouldBeDisplayed() {
        MainScreen mainScreen = new MainScreen(driver);
        SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.typeSearchQuery(SEARCH_QUERY);

        Assertions.assertTrue(searchScreen.hasResults(), "Search results should be displayed");
    }

    @Test
    void shouldOpenArticleFromSearchResults() {
        MainScreen mainScreen = new MainScreen(driver);
        SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.typeSearchQuery(SEARCH_QUERY)
                .openFirstArticle();

        Assertions.assertTrue(searchScreen.isArticleOpened(), "Article screen should be opened");
    }

    @Test
    void backButtonShouldReturnToSearchScreen() {
        MainScreen mainScreen = new MainScreen(driver);
        SearchScreen searchScreen = mainScreen.openSearch();

        searchScreen.typeSearchQuery(SEARCH_QUERY)
                .openFirstArticle();

        searchScreen.tapBack();

        Assertions.assertTrue(searchScreen.isSearchInputVisible(), "Search screen should be visible after tapping back");
    }
}
