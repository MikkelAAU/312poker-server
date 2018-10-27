package Main_Package;

import java.util.Comparator;

public class SortByValue implements Comparator<Card> {
    public int compare(Card a, Card b) {
        return a.getCardValue().getCardValue() - b.getCardValue().getCardValue();
    }
}
