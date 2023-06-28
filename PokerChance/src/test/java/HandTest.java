import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    //test the findBestHand method
    @Test
    void findBestHandTestHighCard() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("h2"));
        cards.add(new Card("cj"));
        cards.add(new Card("ck"));
        cards.add(new Card("ca"));
        cards.add(new Card("s4"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(0, hand.getRank(), "Best hand rank expected 0 got: " + hand.getRank());
        assertEquals(14, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
        assertEquals(13, hand.getHandIdentifiers().get(1), "Best hand identifier got: " + hand.getHandIdentifiers().get(1));
        assertEquals(11, hand.getHandIdentifiers().get(2), "Best hand identifier got: " + hand.getHandIdentifiers().get(2));
        assertEquals(4, hand.getHandIdentifiers().get(3), "Best hand identifier got: " + hand.getHandIdentifiers().get(3));
        assertEquals(2, hand.getHandIdentifiers().get(4), "Best hand identifier got: " + hand.getHandIdentifiers().get(4));
    }

    @Test
    void findBestHandTestPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("h2"));
        cards.add(new Card("c3"));
        cards.add(new Card("ck"));
        cards.add(new Card("ck"));
        cards.add(new Card("s4"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(1, hand.getRank(), "Best hand rank expected 2 got: " + hand.getRank());
        //test identifiers
        assertEquals(13, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
        assertEquals(4, hand.getHandIdentifiers().get(1), "Best hand identifier got: " + hand.getHandIdentifiers().get(1));
        assertEquals(3, hand.getHandIdentifiers().get(2), "Best hand identifier got: " + hand.getHandIdentifiers().get(2));
        assertEquals(2, hand.getHandIdentifiers().get(3), "Best hand identifier got: " + hand.getHandIdentifiers().get(3));
    }

    @Test
    void findBestHandTestTwoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("h2"));
        cards.add(new Card("c2"));
        cards.add(new Card("ca"));
        cards.add(new Card("ca"));
        cards.add(new Card("s4"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(2, hand.getRank(), "Best hand rank expected 2 got: " + hand.getRank());
        //test identifiers
        assertEquals(14, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
        assertEquals(2, hand.getHandIdentifiers().get(1), "Best hand identifier got: " + hand.getHandIdentifiers().get(1));
        assertEquals(4, hand.getHandIdentifiers().get(2), "Best hand identifier got: " + hand.getHandIdentifiers().get(2));
    }

    @Test
    void findBestHandTestThreeOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("h4"));
        cards.add(new Card("c4"));
        cards.add(new Card("ck"));
        cards.add(new Card("ca"));
        cards.add(new Card("s4"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(3, hand.getRank(), "Best hand rank expected 3 got: " + hand.getRank());
        //test identifiers
        assertEquals(4, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
        assertEquals(14, hand.getHandIdentifiers().get(1), "Best hand identifier got: " + hand.getHandIdentifiers().get(1));
        assertEquals(13, hand.getHandIdentifiers().get(2), "Best hand identifier got: " + hand.getHandIdentifiers().get(2));
    }

    @Test
    void findBestHandTestStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("ha"));
        cards.add(new Card("c2"));
        cards.add(new Card("c5"));
        cards.add(new Card("c4"));
        cards.add(new Card("s3"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(4, hand.getRank(), "Best hand rank expected 4 got: " + hand.getRank());
        //test identifiers
        assertEquals(5, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
    }

    @Test
    void findBestHandTestFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("ca"));
        cards.add(new Card("ck"));
        cards.add(new Card("cj"));
        cards.add(new Card("c4"));
        cards.add(new Card("c3"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(5, hand.getRank(), "Best hand rank expected 5 got: " + hand.getRank());
        //test identifiers
        assertEquals(14, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
    }

    @Test
    void findBestHandTestFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("c10"));
        cards.add(new Card("ck"));
        cards.add(new Card("dk"));
        cards.add(new Card("ck"));
        cards.add(new Card("d10"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(6, hand.getRank(), "Best hand rank expected 6 got: " + hand.getRank());
        //test identifiers
        assertEquals(13, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
        assertEquals(10, hand.getHandIdentifiers().get(1), "Best hand identifier got: " + hand.getHandIdentifiers().get(1));

    }

    @Test
    void findBestHandTestFourOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("c10"));
        cards.add(new Card("s10"));
        cards.add(new Card("d10"));
        cards.add(new Card("ca"));
        cards.add(new Card("h10"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(7, hand.getRank(), "Best hand rank expected 7 got: " + hand.getRank());
        //test identifiers
        assertEquals(10, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
        assertEquals(14, hand.getHandIdentifiers().get(1), "Best hand identifier got: " + hand.getHandIdentifiers().get(1));

    }

    @Test
    void findBestHandTestStraightFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("h8"));
        cards.add(new Card("h10"));
        cards.add(new Card("h9"));
        cards.add(new Card("hj"));
        cards.add(new Card("h7"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(8, hand.getRank(), "Best hand rank expected 8 got: " + hand.getRank());
        //test identifiers
        assertEquals(11, hand.getHandIdentifiers().get(0), "Best hand identifier got: " + hand.getHandIdentifiers().get(0));
    }

    @Test
    void findBestHandTestRoyalFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("hq"));
        cards.add(new Card("h10"));
        cards.add(new Card("ha"));
        cards.add(new Card("hk"));
        cards.add(new Card("hj"));

        Hand hand = new Hand(cards);

        System.out.println("Hand rank: " + hand.getRank());

        assertEquals(9, hand.getRank(), "Best hand rank expected 9 got: " + hand.getRank());
    }


}