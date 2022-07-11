package web.pages;

import lombok.Getter;
import web.pages.components.RefinementsMenuComponent;
import web.pages.components.ResultInfoBarComponent;
import web.pages.components.SearchResultsComponent;

public class ResultsPage {
    @Getter
    private final RefinementsMenuComponent refinementsMenuComponent = new RefinementsMenuComponent();
    @Getter
    private final ResultInfoBarComponent resultInfoBarComponent = new ResultInfoBarComponent();
    @Getter
    private final SearchResultsComponent searchResultsComponent = new SearchResultsComponent();

}