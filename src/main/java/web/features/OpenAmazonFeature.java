package web.features;

import com.codeborne.selenide.Selenide;
import config.ConfigProvider;
import io.qameta.allure.Step;

public class OpenAmazonFeature {
    private final String baseUrl;

    public OpenAmazonFeature() {
        this.baseUrl = ConfigProvider.CONFIG_PROPS.webUrl();
    }

    @Step("Open Amazon")
    public void openAmazon() {
        Selenide.open(baseUrl);
    }
}