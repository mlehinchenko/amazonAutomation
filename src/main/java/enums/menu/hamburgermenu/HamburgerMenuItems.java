package enums.menu.hamburgermenu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HamburgerMenuItems {
    SBD_TV_APPLIANCES_ELECTRONICS("shop by department", "TV, Appliances, Electronics"),
    TV_APPLIANCES_ELECTRONICS_TELEVISIONS("tv, audio & cameras", "Televisions");

    private final String sectionName;
    private final String menuItem;
}