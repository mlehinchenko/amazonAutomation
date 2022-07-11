package enums.select;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultSortItems {
    PRICE_HIGH_TO_LOW("Price: High to Low");

    private final String selectItem;
}