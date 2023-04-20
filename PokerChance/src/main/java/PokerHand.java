import java.util.*;

public class PokerHand {

    private final int HIGHCARD = 0;
    private final int PAIR = 1;
    private final int TWOPAIR = 2;
    private final int THREEOFAKIND = 3;
    private final int STRAIGHT = 4;
    private final int FLUSH = 5;
    private final int FULLHOUSE = 6;
    private final int FOUROFAKIND = 7;
    private final int STRAIGHTFLUSH = 8;
    private final int ROYALFLUSH = 9;

    private Card[] hand;
    private Card[] table;
    private Card[] cardPool;
    private Set<ArrayList<Card>> permHand;
    private ArrayList<Card> bestHand;
    private int bestHandRank;


    public PokerHand(PokerBoard board) {

        this.hand = board.getHand();
        this.table = board.getTable();
        this.cardPool = makeCardPool();

        this.permHand = generatePermutations();
        //this.bestHand = findBestHand();
        //this.bestHandRank = getHighestCard(bestHand);

    }

    protected PokerHand() {

    }

    /**
     * @return the pool of cards given the 2 cards in hand and the 5 cards on the table
     */
    public Card[] makeCardPool() {
        Card[] cardPool = new Card[hand.length + table.length];
        for (int i = 0; i < hand.length; i++) {
            cardPool[i] = hand[i];
        }
        for (int i = 0; i < table.length; i++) {
            cardPool[i + hand.length] = table[i];
        }
        return cardPool;
    }

    public Set<ArrayList<Card>> generatePermutations() {

        //generate all permutations of 5 cards given 2 cards in hand and 5 cards on table
        return generateAllPermutations();

    }

    /**
     * @return a set of all permutations of 5 cards given 2 cards in hand and 5 cards on table
     */
    protected Set<ArrayList<Card>> generateAllPermutations() {
        Set<ArrayList<Card>> localPermutations = new HashSet<>();
        for (int i = 0; i < cardPool.length - 4; i++) {
            for (int j = i + 1; j < cardPool.length - 3; j++) {
                for (int k = j + 1; k < cardPool.length - 2; k++) {
                    for (int l = k + 1; l < cardPool.length - 1; l++) {
                        for (int m = l + 1; m < cardPool.length; m++) {

                            ArrayList<Card> perm = new ArrayList();
                            perm.add(cardPool[i]);
                            perm.add(cardPool[j]);
                            perm.add(cardPool[k]);
                            perm.add(cardPool[l]);
                            perm.add(cardPool[m]);

                            localPermutations.add(perm);

                        }
                    }
                }
            }
        }
        return localPermutations;
    }

    public ArrayList<Card> findBestHand() {
        ArrayList<Card> currentBest = null;
        int currentBestRank = 0;

        for (ArrayList<Card> localHand : permHand) {

            int localHandRank;

            if (isRoyalFlush(localHand)) {
                localHandRank = ROYALFLUSH;
            } else if (isStraightFlush(localHand)) {
                localHandRank = STRAIGHTFLUSH;
            } else if (isFourOfAKind(localHand)) {
                localHandRank = FOUROFAKIND;
            } else if (isFullHouse(localHand)) {
                localHandRank = FULLHOUSE;
            } else if (isFlush(localHand)) {
                localHandRank = FLUSH;
            } else if (isStraight(localHand)) {
                localHandRank = STRAIGHT;
            } else if (isThreeOfAKind(localHand)) {
                localHandRank = THREEOFAKIND;
            } else if (isTwoPair(localHand)) {
                localHandRank = TWOPAIR;
            } else if (isPair(localHand)) {
                localHandRank = PAIR;
            } else {
                localHandRank = HIGHCARD;
            }

            //TODO: check for tie and compare highest card

            if (localHandRank > currentBestRank) {
                currentBestRank = localHandRank;
                currentBest = localHand;
            }
        }

        return currentBest;
    }

    protected boolean isRoyalFlush(ArrayList<Card> hand) {
        ArrayList<Integer> ranks = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            int cardRank = hand.get(i).getRank();
            if (cardRank == 14) {
                ranks.add(1);
                ranks.add(14);
            } else
                ranks.add(cardRank);
        }

        //sort the arraylist
        Collections.sort(ranks);

        //check if the difference between five consecutive cards is 1
        for (int i = 0; i < ranks.size() - 4; i++) {
            if (ranks.get(i + 1) - ranks.get(i) == 1 &&
                    ranks.get(i + 2) - ranks.get(i + 1) == 1 &&
                    ranks.get(i + 3) - ranks.get(i + 2) == 1 &&
                    ranks.get(i + 4) - ranks.get(i + 3) == 1 &&
                    ranks.get(i) == 10 && ranks.get(i + 4) == 14) {
                return isFlush(hand);
            }
        }

        return false;
    }

    protected boolean isStraightFlush(ArrayList<Card> hand) {
        return isFlush(hand) && isStraight(hand);
    }

    protected boolean isFourOfAKind(ArrayList<Card> hand) {
        Map<Integer, Integer> map = new HashMap<>();

        for (Card c : hand) {

            if (map.containsKey(c.getRank())) { //if it exists add one to the count

                if (map.get(c.getRank()) == 3) { // if already has a value of three return true
                    //System.out.println("Contains three of a kind " + c.getRank());
                    return true;
                } else {
                    map.put(c.getRank(), map.get(c.getRank()) + 1);
                }

            } else { //create new key
                map.put(c.getRank(), 1);
            }
        }

        return false;
    }

    protected boolean isFullHouse(ArrayList<Card> hand) {

        //check for three of a kind and pair for different cards
        Map<Integer, Integer> map = new HashMap<>();
        Boolean hasUniqueThree = false;
        int uniqueFirstPair = 0;
        int uniqueSecondPair = 0;

        for (Card c : hand) {

            if (map.containsKey(c.getRank())) {
                //if it exists add one to the count

                if (map.get(c.getRank()) == 2) {

                    hasUniqueThree = true;

                    map.put(c.getRank(), map.get(c.getRank()) + 1);

                } else if (map.get(c.getRank()) == 1) {

                    if (uniqueFirstPair == 0) {

                        uniqueFirstPair = c.getRank();
                    } else if (uniqueSecondPair == 0) {

                        uniqueSecondPair = c.getRank();
                    }

                    map.put(c.getRank(), map.get(c.getRank()) + 1);
                }

            } else { //create new key
                map.put(c.getRank(), 1);
            }
        }
        return hasUniqueThree &&
                uniqueFirstPair > 0 &&
                uniqueSecondPair > 0;

    }

    protected boolean isFlush(ArrayList<Card> hand) {

        Map<String, Integer> map = new HashMap<>();

        for (Card c : hand) {

            if (map.containsKey(c.getColor())) { //if it exists add one to the count

                if (map.get(c.getColor()) == 4) { // if already has a value of two return true
                    System.out.println("Contains flush " + c.getColor());
                    return true;
                } else {
                    map.put(c.getColor(), map.get(c.getColor()) + 1);
                }

            } else { //create new key
                map.put(c.getColor(), 1);
            }
        }


        return false;
    }

    protected boolean isStraight(ArrayList<Card> hand) {
        ArrayList<Integer> ranks = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            int cardRank = hand.get(i).getRank();
            if (cardRank == 14) {
                ranks.add(1);
                ranks.add(14);
            } else
                ranks.add(cardRank);
        }

        //sort the arraylist
        Collections.sort(ranks);

        //check if the difference between five consecutive cards is 1
        for (int i = 0; i < ranks.size() - 4; i++) {
            if (ranks.get(i + 1) - ranks.get(i) == 1 &&
                    ranks.get(i + 2) - ranks.get(i + 1) == 1 &&
                    ranks.get(i + 3) - ranks.get(i + 2) == 1 &&
                    ranks.get(i + 4) - ranks.get(i + 3) == 1) {
                return true;
            }
        }

        return false;
    }

    protected boolean isThreeOfAKind(ArrayList<Card> hand) {

        Map<Integer, Integer> map = new HashMap<>();

        for (Card c : hand) {

            if (map.containsKey(c.getRank())) { //if it exists add one to the count

                if (map.get(c.getRank()) == 2) { // if already has a value of two return true
                    System.out.println("Contains three of a kind " + c.getRank());
                    return true;
                } else {
                    map.put(c.getRank(), map.get(c.getRank()) + 1);
                }

            } else { //create new key
                map.put(c.getRank(), 1);
            }
        }

        return false;
    }

    protected boolean isTwoPair(ArrayList<Card> hand) {

        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Card> pairCards = new ArrayList<>();

        for (Card c : hand) {

            if (map.containsKey(c.getRank())) {

                if (pairCards.size() > 0 && pairCards.get(0).getRank() == c.getRank()) {
                    continue;
                } else {
                    pairCards.add(c);
                    System.out.println("Contains pair " + c.getRank());
                }
            } else {
                map.put(c.getRank(), 1);
            }

        }

        return pairCards.size() == 2;
    }

    protected boolean isPair(ArrayList<Card> hand) {

        Map<Integer, Integer> map = new HashMap<>();
        Card pairCard;

        for (Card c : hand) {

            if (map.containsKey(c.getRank())) {
                map.put(c.getRank(), map.get(c.getRank()) + 1);
                pairCard = c;
                System.out.println("Contains pair " + c.getRank());
                return true;
            } else {
                map.put(c.getRank(), 1);
            }
        }

        return false;
    }

    protected int getHighestCard(ArrayList<Card> hand) {

        //init first card
        Card currentBest = null;

        for (Card card : hand) {

            //first card is always best
            if (currentBest == null) {
                currentBest = card;
            }

            //compare to previous best
            if (card.getRank() > currentBest.getRank()) {
                currentBest = card;
            }
        }

        System.out.println("Highest card is " + currentBest);
        return currentBest.getRank();
    }

    public Set<ArrayList<Card>> getPermHand() {
        return permHand;
    }
}
