package web.pages.components;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WaitUtils;

import static com.codeborne.selenide.Selenide.$;

public class RefinementsMenuComponent {

    private final SelenideElement rootElement = $("#s-refinements");
    private final ElementsCollection refinementsSections = rootElement.$$("div.a-section");

    @Step("Click on Refinements menu checkbox by section: {sectionName} and item: {itemName}")
    public RefinementsMenuComponent clickOnRefinementsMenuCheckboxBySectionAndItemName(String sectionName, String itemName) {
        WaitUtils.waitUntilPageReady();
        refinementsSections.find(Condition.exactText(sectionName))
                .parent()
                .$$("li a.a-link-normal")
                .find(Condition.text(itemName))
                .scrollIntoView(true)
                .$("input")
                .click(ClickOptions.usingJavaScript());
        return this;
    }
}