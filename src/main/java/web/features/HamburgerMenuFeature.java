package web.features;

import enums.menu.hamburgermenu.HamburgerMenuItems;
import web.pages.components.HeaderNavigationMenuComponent;

public class HamburgerMenuFeature {

    private final HeaderNavigationMenuComponent headerNavigationMenuComponent;

    public HamburgerMenuFeature() {
        this.headerNavigationMenuComponent = new HeaderNavigationMenuComponent();
    }

    public void openHamburgerMenuItemWithSubMenu(
            HamburgerMenuItems hamburgerMenuItems, HamburgerMenuItems hamburgerSubMenuItems) {
        headerNavigationMenuComponent.clickOnHamburgerButton()
                .clickOnHamburgerMenuBySectionAndItemName(
                        hamburgerMenuItems.getSectionName(),
                        hamburgerMenuItems.getMenuItem())
                .clickOnHamburgerMenuBySectionAndItemName(
                        hamburgerSubMenuItems.getSectionName(),
                        hamburgerSubMenuItems.getMenuItem());
    }
}