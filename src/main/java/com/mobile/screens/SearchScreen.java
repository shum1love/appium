package com.mobile.screens;

import com.mobile.locators.screens.SearchScreenLocators;
import com.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

/** Экран поиска Википедии с действиями для работы с запросами и результатами. */
public class SearchScreen {

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public SearchScreen(final AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Ввести поисковый запрос: {query}")
    public SearchScreen typeSearchQuery(final String query) {
        waitUtils.waitForVisible(SearchScreenLocators.SEARCH_INPUT).sendKeys(query);
        return this;
    }

    public boolean hasResults() {
        return waitUtils.waitForAnyVisible(SearchScreenLocators.SEARCH_RESULTS_TITLES);
    }

    @Step("Открыть первую статью из результатов")
    public SearchScreen openFirstArticle() {
        waitUtils.getFirstElement(SearchScreenLocators.SEARCH_RESULTS_TITLES).click();
        return this;
    }

    @Step("Получить заголовок статьи")
    public String getArticleTitle() {
        return waitUtils.waitForVisible(SearchScreenLocators.ARTICLE_TITLE).getText();
    }

    @Step("Проверить, что статья открыта")
    public void isArticleOpened() {
        waitUtils.waitForVisible(SearchScreenLocators.ARTICLE_TITLE).isDisplayed();
    }

    @Step("Проверить, видимость строки поиска")
    public boolean isSearchInputVisible() {
        return waitUtils.waitForVisible(SearchScreenLocators.SEARCH_INPUT).isDisplayed();
    }

    @Step("Проверить, что отображается заголовок Search")
    public boolean isSearchHeaderVisible() {
        return waitUtils.waitForVisible(SearchScreenLocators.SEARCH_HEADER_TEXT).isDisplayed();
    }

    @Step("Вернуться назад с текущего экрана")
    public void tapBack() {
        driver.navigate().back();
    }
}
