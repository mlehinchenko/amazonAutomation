package web.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import utils.AllureUtils;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class AboutItemSectionComponent {

    private final SelenideElement rootElement = $("div#featurebullets_feature_div"),
            aboutThisItemSection = rootElement.$("h1"),
            aboutThisItemTextContainer = rootElement.$("ul");

    private final ElementsCollection aboutThisItemTextItems = aboutThisItemTextContainer.$$("li");

    @Step("Verify 'About this Item' section Displayed")
    public AboutItemSectionComponent verifyAboutThisItemDisplayed() {
        aboutThisItemSection.shouldBe(Condition.visible.because("About this Item is not visible")).scrollIntoView(true);
        return this;
    }

    @Step("Log all 'About this Item' texts in console")
    public AboutItemSectionComponent logAllAboutThisItemTextInConsole() {
        aboutThisItemTextItems.texts().forEach(log::info);
       return this;
    }

    @Step("Log all 'About this Item' texts in allure report")
    public void logAllAboutThisItemTextInAllureReport() {
        AllureUtils.logTextInAllureAttachment(aboutThisItemTextContainer.getText());
    }
}
