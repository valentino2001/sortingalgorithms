package p1.comparator;

import p1.card.Card;
import p1.card.CardColor;

import java.util.Comparator;

/**
 * Compares two {@link Card}s.
 * <p>
 * The cards are first compared by their value and then by their {@link CardColor}.
 *
 * @see Card
 */
public class CardComparator implements Comparator<Card> {

    /**
     * Compares two {@link Card}s.
     * <p>
     * The cards are first compared by their value and then by their {@link CardColor}.
     * <p>
     * The value of the cards compared by the natural order of the {@link Integer} class.
     * <p>
     * The color of the cards compared using the following order: {@link CardColor#CLUBS} > {@link CardColor#SPADES} >.{@link CardColor#HEARTS} > {@link CardColor#DIAMONDS}.
     *
     * @param o1 the first {@link Card} to compare.
     * @param o2 the second {@link Card} to compare.
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     * @throws NullPointerException if either of the {@link Card}s is null.
     *
     * @see Card
     * @see CardColor
     * @see Comparator#compare(Object, Object)
     */
    @Override
    public int compare(Card o1, Card o2) {
        //first checking value, then color of cards
        //first comparing the value of two cards o1 and 02 (integers), casting with integer bc card values are integer
        int valueOfEqualCards = Integer.compare(o1.cardValue(),o2.cardValue());
        //checking if the value of the compared cards are unequal and then returning value to break up
        if(valueOfEqualCards != 0){
            return valueOfEqualCards;
        }
        //if the value of the cards is the same, then we also check for identical colors, cardcolor is an enum, ordinal returns ordinal for enum
        return Integer.compare(o1.cardColor().ordinal() , o2.cardColor().ordinal());
    }
}
