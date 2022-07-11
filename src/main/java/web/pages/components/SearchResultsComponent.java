package web.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WaitUtils;
import web.pages.helpers.DriverHelper;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultsComponent {

    private final SelenideElement rootElement = $("span[data-component-type='s-search-results']");
    private final ElementsCollection searchResultContainers = rootElement.$$(
            "div[data-component-type='s-search-result']");

    @Step("Open result item by position: {itemPosition}")
    public void openResultItemByPosition(int itemPosition) {
        if (itemPosition <= 0) {
            throw new IllegalArgumentException("Item position can't be lees than 1, current position: " + itemPosition);
        }
        WaitUtils.waitUntilPageReady();
        searchResultContainers.asDynamicIterable()
                .stream()
                .filter(searchResult -> searchResult.$("span.a-price-whole")
                        .is(Condition.visible))
                .skip(itemPosition - 1)
                .findFirst()
                .orElseThrow()
                .$(".s-title-instructions-style a")
                .click();
        DriverHelper.switchToWindow();
    }
}