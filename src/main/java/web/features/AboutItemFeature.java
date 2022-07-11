package web.features;

import web.pages.components.AboutItemSectionComponent;

public class AboutItemFeature {
    private final AboutItemSectionComponent aboutItemSectionComponent;

    public AboutItemFeature(){
        this.aboutItemSectionComponent = new AboutItemSectionComponent();
    }

    public void verifyAndLogAboutItemSection(){
        aboutItemSectionComponent.verifyAboutThisItemDisplayed()
                .logAllAboutThisItemTextInConsole()
                .logAllAboutThisItemTextInAllureReport();

    }
}