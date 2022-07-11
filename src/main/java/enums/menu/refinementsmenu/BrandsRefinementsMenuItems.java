package enums.menu.refinementsmenu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrandsRefinementsMenuItems {
    BRANDS_SAMSUNG("Brands", "Samsung");

    private final String sectionName;
    private final String menuItem;
}