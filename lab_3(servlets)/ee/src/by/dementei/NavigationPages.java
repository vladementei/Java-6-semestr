package by.dementei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum NavigationPages {
    CARD_HOLDER("Cardholder"),
    CARD_NETWORK("Card network"),
    DESIGN("Design"),
    SUMMARY("Summary");

    private final String label;

    private NavigationPages(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static List<String> getLabels() {
        List<String> list = new ArrayList<String>();
        for (NavigationPages page : NavigationPages.values()) {
            list.add(page.toString());
        }
        return list;
    }
}
