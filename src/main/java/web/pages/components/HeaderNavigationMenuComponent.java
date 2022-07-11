package web.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WaitUtils;

import static com.codeborne.selenide.Selenide.$;

public class HeaderNavigationMenuComponent {
    private final SelenideElement rootElement = $("#nav-main"),
        hamburgerMenuButton = rootElement.$("#nav-hamburger-menu");

    @Step("Click on Hamburger button")
    public HamburgerMenuComponent clickOnHamburgerButton(){
        WaitUtils.waitUntilPageReady();
        hamburgerMenuButton.shouldBe(Condition.visible).click();
        return new HamburgerMenuComponent();
    }
}