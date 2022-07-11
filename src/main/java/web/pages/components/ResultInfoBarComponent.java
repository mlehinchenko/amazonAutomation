package web.pages.components;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static utils.WaitUtils.waitUntilPageReady;

public class ResultInfoBarComponent {
    private final SelenideElement rootElement = $("span[data-component-type='s-result-info-bar']"),
            resultSortSelect = rootElement.$("#s-result-sort-select");

    @Step("Select Result sort: {resultSortName}")
    public ResultInfoBarComponent selectResultSort(String resultSortName){
        waitUntilPageReady();
        resultSortSelect.click(ClickOptions.usingJavaScript());
        resultSortSelect.$$("option").find(Condition.text(resultSortName)).click();
        return this;
    }
}
