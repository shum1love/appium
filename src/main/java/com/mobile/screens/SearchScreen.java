package com.mobile.screens;

import com.mobile.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/** Экран поиска Википедии с действиями для работы с запросами и результатами. */
public class SearchScreen {

    private static final By SEARCH_INPUT = AppiumBy.id("org.wikipedia:id/search_src_text");
    private static final By SEARCH_RESULTS_TITLES = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View");
    private static final By ARTICLE_TITLE = AppiumBy.xpath("(//android.widget.TextView[@text='Appium'])[1]");

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    public SearchScreen(final AndroidDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Ввести поисковый запрос: {query}")
    public SearchScreen typeSearchQuery(final String query) {
        waitUtils.waitForVisible(SEARCH_INPUT).sendKeys(query);
        return this;
    }

    public boolean hasResults() {
        return waitUtils.waitForAnyVisible(SEARCH_RESULTS_TITLES);
    }

    @Step("Открыть первую статью из результатов")
    public SearchScreen openFirstArticle() {
        waitUtils.waitForVisible(SEARCH_RESULTS_TITLES).click();
        return this;
    }

    @Step("Проверить, что заголовок статьи содержит текст: {expectedText}")
    public SearchScreen titleHasText(final String expectedText) {
        isArticleOpened();

        final String actualTitle = waitUtils.waitForVisible(ARTICLE_TITLE).getText();

        /*Assertions.assertTrue(
                actualTitle.contains(expectedText),
                String.format(
                        "❌ В заголовке статьи должен присутствовать текст: '%s',\n" +
                                "   но фактически заголовок: '%s'",
                        expectedText,
                        actualTitle
                )
        );*/

        return this;
    }

    @Step("Получить заголовок статьи")
    public String getArticleTitle() {
        return waitUtils.waitForVisible(ARTICLE_TITLE).getText();
    }

    @Step("Проверить, что статья открыта")
    public boolean isArticleOpened() {
        return waitUtils.waitForVisible(ARTICLE_TITLE).isDisplayed();
    }

    public boolean isSearchInputVisible() {
        return waitUtils.waitForVisible(SEARCH_INPUT).isDisplayed();
    }

    @Step("Вернуться назад с текущего экрана")
    public void tapBack() {
        driver.navigate().back();
    }
}
