import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    @Test
    void generatePermutationsTest(){
        PokerBoard board = new PokerBoard("h2","h3","c9","c10","s2","da","dk");
        PokerHand pokerHand = new PokerHand(board);
        Set<ArrayList<Card>> permutations = pokerHand.getPermHand();


        for (ArrayList<Card> list : permutations){
            System.out.println(list);
        }
        assertEquals(21, permutations.size());
    }

    @Test
    void testIsRoyalFlushTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h10"));
        hand.add(new Card("hj"));
        hand.add(new Card("hq"));
        hand.add(new Card("hk"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isRoyalFlush(hand));
    }

    @Test
    void testIsRoyalFlushFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h10"));
        hand.add(new Card("cj"));
        hand.add(new Card("hq"));
        hand.add(new Card("hk"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isRoyalFlush(hand));
    }

    @Test
    void testIsRoyalFlushFalse2() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h10"));
        hand.add(new Card("h2"));
        hand.add(new Card("hq"));
        hand.add(new Card("hk"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isRoyalFlush(hand));
    }

    @Test
    void testIsStraightFlushTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h2"));
        hand.add(new Card("h3"));
        hand.add(new Card("h4"));
        hand.add(new Card("h5"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isStraightFlush(hand));
    }
    @Test
    void testIsStraightFlushFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h9"));
        hand.add(new Card("c9"));
        hand.add(new Card("d9"));
        hand.add(new Card("s9"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isStraightFlush(hand));
    }

    @Test
    void testIsFourOfAKindTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h9"));
        hand.add(new Card("c9"));
        hand.add(new Card("d9"));
        hand.add(new Card("s9"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isFourOfAKind(hand));
    }

    @Test
    void testIsFullHouseTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h9"));
        hand.add(new Card("c9"));
        hand.add(new Card("d9"));
        hand.add(new Card("s4"));
        hand.add(new Card("h4"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isFullHouse(hand));
    }

    @Test
    void testIsFullHouseFalse1() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h9"));
        hand.add(new Card("c5"));
        hand.add(new Card("d9"));
        hand.add(new Card("s4"));
        hand.add(new Card("h4"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isFullHouse(hand));
    }

    @Test
    void testIsFullHouseFalse2() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h3"));
        hand.add(new Card("c4"));
        hand.add(new Card("d4"));
        hand.add(new Card("s4"));
        hand.add(new Card("h4"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isFullHouse(hand));
    }

    @Test
    void testIsFlushTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("h8"));
        hand.add(new Card("h9"));
        hand.add(new Card("h2"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isFlush(hand));
    }

    @Test
    void testIsFlushFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("h8"));
        hand.add(new Card("c9"));
        hand.add(new Card("h2"));
        hand.add(new Card("ha"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isFlush(hand));
    }

    @Test
    void testIsStraightTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("c2"));
        hand.add(new Card("h3"));
        hand.add(new Card("c4"));
        hand.add(new Card("h5"));
        hand.add(new Card("h6"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isStraight(hand));
    }
    @Test
    void testIsStraightTrue2() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("h3"));
        hand.add(new Card("c2"));
        hand.add(new Card("h5"));
        hand.add(new Card("ca"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isStraight(hand));
    }

    @Test
    void testIsStraightFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h2"));
        hand.add(new Card("h3"));
        hand.add(new Card("h8"));
        hand.add(new Card("h5"));
        hand.add(new Card("h6"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isStraight(hand));
    }

    @Test
    void testIsThreeOfAKindTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("s4"));
        hand.add(new Card("c4"));
        hand.add(new Card("d5"));
        hand.add(new Card("s5"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isThreeOfAKind(hand));
    }

    @Test
    void testIsThreeOfAKindFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("s4"));
        hand.add(new Card("ca"));
        hand.add(new Card("d5"));
        hand.add(new Card("s5"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isThreeOfAKind(hand));
    }

    @Test
    void testIsTwoPairTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("s4"));
        hand.add(new Card("c4"));
        hand.add(new Card("d5"));
        hand.add(new Card("s5"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isTwoPair(hand));
    }

    /**
     * Test to check if it identifies a pair.
     */
    @Test
    void testIsPairFalse1() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("s4"));
        hand.add(new Card("c4"));
        hand.add(new Card("d4"));
        hand.add(new Card("s4"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.isPair(hand));
    }

    /**
     * Test to see it does not have false positives.
     */
    @Test
    void testIsPairFalse2() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("s6"));
        hand.add(new Card("c7"));
        hand.add(new Card("da"));
        hand.add(new Card("s5"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertFalse(pokerHand.isPair(hand));
    }

    @Test
    void testGetHighestCardTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("h4"));
        hand.add(new Card("s1"));
        hand.add(new Card("ck"));
        hand.add(new Card("da"));
        hand.add(new Card("sq"));

        //create empty pokerhand object for testing
        PokerHand pokerHand = new PokerHand();
        assertTrue(pokerHand.getHighestCard(hand) == 14);
    }
}