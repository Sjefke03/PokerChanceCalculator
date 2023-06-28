import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Hand {

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

    private ArrayList<Card> cards;

    private ArrayList<Integer> handIdentifiers;

    private int rank;

    /**
     * Constructor for a hand.
     *
     * @param cards cards in a hand
     */
    public Hand(ArrayList<Card> cards) {

        if (cards.size() != 5) {
            throw new IllegalArgumentException("A hand must have 5 cards");
        }

        //initialize the bestHandIdentifiers
        handIdentifiers = new ArrayList<>();

        this.cards = cards;
        processHand();

    }

    public ArrayList<Integer> getHandIdentifiers() {
        return handIdentifiers;
    }

    public int getRank() {
        return rank;
    }

    private void processHand() {

        //initialize the rankMap
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        HashMap<String, Integer> typeMap = new HashMap<>();
        ArrayList<Integer> rankList = new ArrayList<>();

        for (Card c : cards) {

            rankList.add(c.getRank());

            //if the rank is not in the map add it
            if (!rankMap.containsKey(c.getRank())) {
                rankMap.put(c.getRank(), 1);
            } else {
                //if it is in the map increment the value
                rankMap.put(c.getRank(), rankMap.get(c.getRank()) + 1);
            }

            //if the type is not in the map add it
            if (!typeMap.containsKey(c.getColor())) {
                typeMap.put(c.getColor(), 1);
            } else {
                //if it is in the map increment the value
                typeMap.put(c.getColor(), typeMap.get(c.getColor()) + 1);
            }

        }

        //sort the Ranks in descending order
        Collections.sort(rankList, Collections.reverseOrder());

        //find the hand rank
        this.rank = findRank(typeMap, rankMap, rankList);
    }

    /**
     * Gets the rank of a hand.
     *
     * @param typeMap  map of the types
     * @param rankMap  map of the ranks
     * @param rankList list of the ranks
     * @return rank of a hand
     */
    public int findRank(HashMap<String, Integer> typeMap, HashMap<Integer, Integer> rankMap, ArrayList<Integer> rankList) {

        if (isRoyalFlush(rankList, typeMap, rankMap)) {
            return ROYALFLUSH;
        }
        if (isStraightFlush(rankList, typeMap, rankMap)) {
            return STRAIGHTFLUSH;
        }
        if (isFourOfAKind(rankList, rankMap)){
            return FOUROFAKIND;
        }
        if (isFullHouse(rankList, rankMap)) {
            return FULLHOUSE;
        }
        if (isFlush(rankList, typeMap)) {
            return FLUSH;
        }
        if (isStraight(rankList, rankMap)) {
            return STRAIGHT;
        }
        if (isThreeOfAKind(rankList, rankMap)) {
            return THREEOFAKIND;
        }
        if (isTwoPair(rankList, rankMap)) {
            return TWOPAIR;
        }
        if (isPair(rankList, rankMap)) {
            return PAIR;
        } else {
            getHighestCard(rankList);
            return HIGHCARD;
        }

    }

    /**
     * @return if the hand is a royal flush
     */
    protected boolean isRoyalFlush(ArrayList<Integer> rankList, HashMap<String, Integer> typeMap, HashMap<Integer, Integer> rankMap) {

        if (typeMap.size() != 1) {
            return false;
        }

        if (rankMap.size() != 5) {
            return false;
        }

        if (rankList.get(4) != 10) {
            return false;
        }

        return true;
    }

    /**
     * @return true if the hand is a straight flush
     */
    protected boolean isStraightFlush(ArrayList<Integer> rankList, HashMap<String,Integer> typeMap, HashMap<Integer, Integer> rankMap) {

        if (typeMap.size() == 1) {
            return isStraight(rankList, rankMap);
        }

        return false;
    }

    /**
     * @return true if the hand is a flush
     * @modifies handIdentifiers such that element 0 is the 4 of a kind and element 2 is the kicker
     */
    protected boolean isFourOfAKind(ArrayList<Integer> rankList, HashMap<Integer, Integer> rankMap) {

        if (rankMap.size() != 2) {
            return false;
        }

        //check the entries of the rankMap and if the value is 4 return true
        Boolean hasFour = false;
        int lesserKey = 0;

        for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {

            if (entry.getValue() == 4) {
                handIdentifiers.add(entry.getKey());
                hasFour = true;
            } else {
                lesserKey = entry.getKey();
            }
        }

        if (hasFour) {
            handIdentifiers.add(lesserKey);
        }

        return hasFour;
    }

    /**
     * @param rankList list of the ranks
     * @param rankMap  map of the ranks
     * @return true if the hand is a full house
     * @modifies handIdentifiers such that element 0 is the 3 of a kind and element 1 is the pair
     */
    protected boolean isFullHouse(ArrayList<Integer> rankList, HashMap<Integer, Integer> rankMap) {

        //since the mapsize is 2 and it is not a four of a kind it must be a full house
        //therefore return true but first find the identifiers.
        if (rankMap.size() != 2) {
            return false;
        }

        //check the entries of the rankMap and if the value is 4 return true
        int lesserKey = 0;

        for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {

            if (entry.getValue() == 3) {
                handIdentifiers.add(entry.getKey());
            } else {
                lesserKey = entry.getKey();
            }
        }

        handIdentifiers.add(lesserKey);


        return true;

    }

    /**
     * @return true if the hand is a flush
     * @modifies handIdentifiers such that the elements are the sorted ranks of the cards
     */
    protected boolean isFlush(ArrayList<Integer> rankList, Map<String, Integer> typeMap) {

        if (typeMap.size() == 1) {
            handIdentifiers.addAll(rankList);
            return true;
        }

        return false;
    }

    /**
     * @return true if the hand is a straight
     */
    protected boolean isStraight(ArrayList<Integer> rankList, HashMap<Integer, Integer> rankMap) {

        ArrayList<Integer> tempRankList = new ArrayList<Integer>(rankList);

        //check if the rankMap has 5 entries
        if (rankMap.size() != 5) {
            return false;
        }

        //ace counts for both 1 and 14
        if (rankList.get(0) == 14) {
            tempRankList.add(1);
        }

        //check if the difference between five consecutive cards is 1
        for (int i = 0; i < tempRankList.size() - 4; i++) {

            //check if the difference between five consecutive cards is 1
            if (tempRankList.get(i) - tempRankList.get(i+1) == 1
                    && tempRankList.get(i + 1) - tempRankList.get(i + 2) == 1
                    && tempRankList.get(i + 2) - tempRankList.get(i + 3) == 1
                    && tempRankList.get(i + 3) - tempRankList.get(i + 4) == 1) {

                //set the identifiers as the highest straight card
                handIdentifiers.add(rankList.get(i));

                return true;

            }
        }

        return false;
    }

    /**
     * @return true if the hand is a three of a kind
     */
    protected boolean isThreeOfAKind(ArrayList<Integer> rankList, HashMap<Integer, Integer> rankMap) {

        //since you need 3 of the same the map must be atleast of size 3
        // and since it is not a four of a kind or a full house it must be at most size 3
        if (rankMap.size() != 3) {
            return false;
        }

        //check the entries of the rankMap and if the value is 4 return true
        Boolean hasThree = false;
        ArrayList<Integer> lesserKeys = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {

            if (entry.getValue() == 3) {
                handIdentifiers.add(entry.getKey());
                hasThree = true;
            } else {
                lesserKeys.add(entry.getKey());
            }
        }

        if (hasThree) {
            Collections.sort(lesserKeys, Collections.reverseOrder());
            handIdentifiers.addAll(lesserKeys);
        }

        return hasThree;
    }

    /**
     * @return true if the hand is a two pair
     */
    protected boolean isTwoPair(ArrayList<Integer> rankList, HashMap<Integer, Integer> rankMap) {

        //since you need 3 of the same the map must be atleast of size 3
        // and since it is not a four of a kind or a full house it must be at most size 3
        if (rankMap.size() != 3) {
            return false;
        }

        //check the entries if they contain 2 pairs return true
        Boolean hasTwo = false;
        ArrayList<Integer> candidateKeys = new ArrayList<>();
        int lesserKey = 0;


        for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {

            if (entry.getValue() == 2) {
                candidateKeys.add(entry.getKey());
            } else {
                lesserKey = entry.getKey();
            }
        }

        //check if there were two pairs
        if (candidateKeys.size() == 2) {
            Collections.sort(candidateKeys, Collections.reverseOrder());

            handIdentifiers.addAll(candidateKeys);
            handIdentifiers.add(lesserKey);
        }

        return candidateKeys.size() == 2;
    }

    /**
     * @return true if the hand is a pair
     */
    protected boolean isPair(ArrayList<Integer> rankList, HashMap<Integer, Integer> rankMap) {

        if (rankMap.size() > 4) {
            return false;
        }

        //check the entries if they contain 2 pairs return true
        Boolean hasTwo = false;
        ArrayList<Integer> lesserKeys = new ArrayList<>();


        for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {

            if (entry.getValue() == 2) {
                handIdentifiers.add(entry.getKey());
                hasTwo = true;
            } else {
                lesserKeys.add(entry.getKey());
            }
        }

        //check if there were two pairs
        if (hasTwo) {
            Collections.sort(lesserKeys, Collections.reverseOrder());
            handIdentifiers.addAll(lesserKeys);
        }

        return hasTwo;
    }

    /**
     * @return the highest card in the hand
     */
    protected int getHighestCard(ArrayList<Integer> rankList) {

        //set the identifiers
        handIdentifiers.addAll(rankList);

        return rankList.get(0);
    }

}

