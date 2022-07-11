package tests;

import data.Constants;
import enums.menu.hamburgermenu.HamburgerMenuItems;
import enums.menu.refinementsmenu.BrandsRefinementsMenuItems;
import enums.select.ResultSortItems;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import web.features.AboutItemFeature;
import web.features.HamburgerMenuFeature;
import web.features.OpenAmazonFeature;
import web.pages.ResultsPage;

public class AmazonSimpleTest extends WebBaseTest {
    private final ResultsPage resultsPage = new ResultsPage();

    @Description("Simple Amazon tests to get information about second highest item in Television section" +
            "and log all info into console and report")
    @Test
    public void getInformationAboutSecondHighestItemInTelevisionSectionTest() {
        new OpenAmazonFeature().openAmazon();
        new HamburgerMenuFeature().openHamburgerMenuItemWithSubMenu(
                HamburgerMenuItems.SBD_TV_APPLIANCES_ELECTRONICS,
                HamburgerMenuItems.TV_APPLIANCES_ELECTRONICS_TELEVISIONS);
        resultsPage.getRefinementsMenuComponent().clickOnRefinementsMenuCheckboxBySectionAndItemName(
                BrandsRefinementsMenuItems.BRANDS_SAMSUNG.getSectionName(),
                BrandsRefinementsMenuItems.BRANDS_SAMSUNG.getMenuItem());
        resultsPage.getResultInfoBarComponent().selectResultSort(ResultSortItems.PRICE_HIGH_TO_LOW.getSelectItem());
        resultsPage.getSearchResultsComponent().openResultItemByPosition(Constants.Numbers.SECOND_HIGHEST_ITEM_NUMBER);
        new AboutItemFeature().verifyAndLogAboutItemSection();
    }
}