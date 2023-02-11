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
    private Card[] opponent = new Card[2];
    private Card[] table;
    private Set<ArrayList<Card>> permHand;
    private ArrayList<Card> bestHand;
    private int bestHandRank;

    public PokerHand(PokerBoard board) {

        this.hand = board.getHand();
        this.table = board.getTable();

        this.permHand = generatePermutations();
        this.bestHand = findBestHand();
        this.bestHandRank = getHighestCard(bestHand);

    }

    protected PokerHand() {

    }



    public Set<ArrayList<Card>> generatePermutations() {
        //include atleast 1 card of hand plus all posibilities of table
        generateSinglePermutations();

        //include permutations with 2 cards
        generateDoublePermutations();

        return permHand;
    }

    protected void generateSinglePermutations() {
        for (int i = 0; i < hand.length; i++) {

            // remove 1 card at place k
            for (int k = 0; k < table.length; k++) {
                //add all cards in table
                //create temp arraylist
                ArrayList<Card> perm = new ArrayList();
                //add 1 of the hand

                perm.add(hand[i]);
                for (int j = 0; j < table.length; j++) {
                    perm.add(table[j]);
                }
                perm.remove(table[k]);
                //add permutation to set
                permHand.add(perm);
            }
        }
    }

    protected void generateDoublePermutations() {
        for (int i = 0; i < table.length - 2; i++) {
            for (int j = i + 1; j < table.length - 1; j++) {
                for (int k = j + 1; k < table.length; k++) {

                    ArrayList<Card> perm = new ArrayList();
                    perm.add(hand[0]);
                    perm.add(hand[1]);
                    perm.add(table[i]);
                    perm.add(table[j]);
                    perm.add(table[k]);
                    permHand.add(perm);
                }
            }
        }
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
            } else{
                localHandRank = HIGHCARD;
            }

            if (localHandRank > currentBestRank){
                currentBestRank = localHandRank;
                currentBest = localHand;
            }
        }

        return currentBest;
    }

    protected boolean isRoyalFlush(ArrayList<Card> Hand){
        return false;
    }
    protected boolean isStraightFlush(ArrayList<Card> Hand){
        return false;
    }
    protected boolean isFourOfAKind(ArrayList<Card> Hand){
        return false;
    }
    protected boolean isFullHouse(ArrayList<Card> Hand){
        return false;
    }
    protected boolean isFlush(ArrayList<Card> Hand){
        return false;
    }
    protected boolean isStraight(ArrayList<Card> Hand){
        return false;
    }
    protected boolean isThreeOfAKind(ArrayList<Card> hand){
        return false;
    }
    protected boolean isTwoPair(ArrayList<Card> hand){

        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Card> pairCards = new ArrayList<>();

        for(  Card c  : hand) {

            if(map.containsKey(c.getRank())) {

                if (pairCards.size() > 0 && pairCards.get(0).getRank() == c.getRank() ){
                    continue;
                }else{
                    pairCards.add(c);
                    System.out.println("Contains pair " + c);
                }
            } else {
                map.put(c.getRank(), 1);
            }

        }

        if(pairCards.size() == 2){
            return true;
        }
        return false;
    }

    protected boolean isPair(ArrayList<Card> hand){

        Map<Integer, Integer> map = new HashMap<>();
        Card pairCard;

        for(  Card c  : hand) {

            if(  map.containsKey(c.getRank())   ) {
                map.put(c.getRank(), map.get(c.getRank()) + 1);
                pairCard = c;
                System.out.println("Contains pair " + c);
                return true;
            }
            else {
                map.put(c.getRank(), 1);
            }
        }

        return false;
    }

    protected int getHighestCard(ArrayList<Card> hand){

        //init first card
        Card currentBest = null;

        for(Card card : hand){

            //first card is always best
            if(currentBest == null){
                currentBest = card;
            }

            //compare to previous best
            if(card.getRank() > currentBest.getRank()){
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
