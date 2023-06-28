import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HandSetTest {

    @Test
    void generatePermutationsTest(){
        PokerBoard board = new PokerBoard("h2","h3","c9","c10","s2","da","dk");
        HandSet handSet = new HandSet(board);
        Set<ArrayList<Card>> permutations = handSet.getPermHand();


        for (ArrayList<Card> list : permutations){
            System.out.println(list);
        }
        assertEquals(21, permutations.size());
    }
    @Test
    void findBestHandHighCard(){
        PokerBoard board = new PokerBoard("h2","h3","c9","c10","s4","da","dk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(0, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(14, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
        assertEquals(13, handSet.getBestHandIdentifiers().get(1), "check best hand identifiers");
        assertEquals(10, handSet.getBestHandIdentifiers().get(2), "check best hand identifiers");
        assertEquals(9, handSet.getBestHandIdentifiers().get(3), "check best hand identifiers");
        assertEquals(4, handSet.getBestHandIdentifiers().get(4), "check best hand identifiers");

    }

    @Test
    void findBestHandPair(){
        PokerBoard board = new PokerBoard("h2","h3","c9","c10","s9","da","dk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(1, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(9, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
        assertEquals(14, handSet.getBestHandIdentifiers().get(1), "check best hand identifiers");
        assertEquals(13, handSet.getBestHandIdentifiers().get(2), "check best hand identifiers");
        assertEquals(10, handSet.getBestHandIdentifiers().get(3), "check best hand identifiers");
    }

    @Test
    void findBestHandTwoPair(){
        PokerBoard board = new PokerBoard("h2","hk","c9","c10","s2","da","dk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(2, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(13, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
        assertEquals(2, handSet.getBestHandIdentifiers().get(1), "check best hand identifiers");
        assertEquals(14, handSet.getBestHandIdentifiers().get(2), "check best hand identifiers");
    }

    @Test
    void findBestHandThreeOfAKind(){
        PokerBoard board = new PokerBoard("h2","hk","c9","c10","s2","da","dk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(2, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(13, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
        assertEquals(2, handSet.getBestHandIdentifiers().get(1), "check best hand identifiers");
        assertEquals(14, handSet.getBestHandIdentifiers().get(2), "check best hand identifiers");
    }

    @Test
    void findBestHandStraight(){
        PokerBoard board = new PokerBoard("h2","h7","c9","c10","sj","d8","dk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(4, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(11, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
    }

    @Test
    void findBestHandFlush(){
        PokerBoard board = new PokerBoard("h2","h3","h9","c10","s4","ha","hk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(5, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(14, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
    }

    @Test
    void findBestHandFullHouse(){
        PokerBoard board = new PokerBoard("h2","h3","c3","c10","s3","da","d10");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(6, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(3, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
        assertEquals(10, handSet.getBestHandIdentifiers().get(1), "check best hand identifiers");
    }

    @Test
    void findBestHandFourOfAKind(){
        PokerBoard board = new PokerBoard("h10","h10","c9","c10","s10","da","dk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(7, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(10, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
        assertEquals(14, handSet.getBestHandIdentifiers().get(1), "check best hand identifiers");
    }

    @Test
    void findBestHandStraightFlush(){
        PokerBoard board = new PokerBoard("h2","h7","h9","h10","hj","h8","hk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(8, handSet.getBestHandRank(), "Check best hand rank");
        assertEquals(11, handSet.getBestHandIdentifiers().get(0), "check best hand identifiers");
    }
    @Test
    void findBestHandRoyalFlush(){
        PokerBoard board = new PokerBoard("hj","hq","c9","h10","s2","ha","hk");
        HandSet handSet = new HandSet(board);
        System.out.println(handSet.getBestHandIdentifiers());

        assertEquals(9, handSet.getBestHandRank(), "Check best hand rank");
    }

}