package web.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WaitUtils;

import static com.codeborne.selenide.Selenide.$;

public class HamburgerMenuComponent {

    private final SelenideElement rootElement = $("#hmenu-container");

    @Step("Click on Hamburger menu by section: {sectionName} and item: {itemName}")
    public HamburgerMenuComponent clickOnHamburgerMenuBySectionAndItemName(String sectionName, String itemName) {
        WaitUtils.waitUntilPageReady();
        rootElement.$$("div.hmenu-title")
                .find(Condition.text(sectionName))
                .parent()
                .$$x("./following-sibling::li")
                .find(Condition.text(itemName))
                .click();
        return this;
    }
}