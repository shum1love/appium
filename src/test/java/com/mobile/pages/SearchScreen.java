package com.mobile.pages;

import com.mobile.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SearchScreen {

    private static final By SEARCH_INPUT = AppiumBy.id("org.wikipedia:id/search_src_text");
    private static final By SEARCH_RESULTS_TITLES = AppiumBy.id("org.wikipedia:id/page_list_item_title");
    private static final By ARTICLE_TITLE = AppiumBy.id("org.wikipedia:id/view_page_title_text");

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public SearchScreen(final AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public SearchScreen typeSearchQuery(final String query) {
        waitUtils.waitForVisible(SEARCH_INPUT).sendKeys(query);
        return this;
    }

    public boolean hasResults() {
        return waitUtils.waitForAnyVisible(SEARCH_RESULTS_TITLES);
    }

    public SearchScreen openFirstArticle() {
        waitUtils.waitForVisible(SEARCH_RESULTS_TITLES).click();
        return this;
    }

    public boolean isArticleOpened() {
        return waitUtils.waitForVisible(ARTICLE_TITLE).isDisplayed();
    }

    public boolean isSearchInputVisible() {
        return waitUtils.waitForVisible(SEARCH_INPUT).isDisplayed();
    }

    public void tapBack() {
        driver.navigate().back();
    }
}
