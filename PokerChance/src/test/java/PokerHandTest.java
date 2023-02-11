import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    @Test
    void generatePermutationsTest(){
        PokerBoard board = new PokerBoard("h2","h3","c9","c10","s2","da","dk");
        PokerHand pokerHand = new PokerHand(board);
        Set<ArrayList<Card>> permutations = pokerHand.generatePermutations();


        for (ArrayList list : permutations){
            System.out.println(list);
        }
        assertTrue(permutations.size()==20);
    }

    @Test
    void generateSinglePermutationsTest(){
        PokerBoard board = new PokerBoard("h2","h3","c9","c10","s2","da","dk");
        PokerHand pokerHand = new PokerHand(board);
        pokerHand.generateSinglePermutations();
        Set<ArrayList<Card>> permutations = pokerHand.getPermHand();

        for (ArrayList list : permutations){
            System.out.println(list);
        }
        assertTrue(permutations.size()==10);
    }

    @Test
    void generateDoublePermutationsTest(){
        PokerBoard board = new PokerBoard("h2","h3","c9","c10","s2","da","dk");
        PokerHand pokerHand = new PokerHand(board);
        pokerHand.generateDoublePermutations();
        Set<ArrayList<Card>> permutations = pokerHand.getPermHand();

        for (ArrayList list : permutations){
            System.out.println(list);
        }
        assertTrue(permutations.size()==10);
    }

    @Test
    void testIsRoyalFlush() {
    }

    @Test
    void testIsStraightFlush() {
    }

    @Test
    void testIsFourOfAKind() {
    }

    @Test
    void testIsFullHouse() {
    }

    @Test
    void testIsFlush() {
    }

    @Test
    void testIsStraight() {
    }

    @Test
    void testIsThreeOfAKind() {
    }

    @Test
    void testIsTwoPair() {
        ArrayList<Card> hand = new ArrayList();
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
    void testIsPair1() {
        ArrayList<Card> hand = new ArrayList();
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
    void testIsPair2() {
        ArrayList<Card> hand = new ArrayList();
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
    void testGetHighestCard() {
        ArrayList<Card> hand = new ArrayList();
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