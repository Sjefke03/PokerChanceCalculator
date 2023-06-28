import java.util.*;

public class HandSet {

    private Card[] handCards;
    private Card[] tableCards;
    private Card[] cardPool;
    private Set<ArrayList<Card>> permutationsHand;
    private ArrayList<Card> bestHand;
    private int bestHandRank;
    private ArrayList<Integer> bestHandIdentifiers;

    /**
     * Constructor for PokerHand
     *
     * @param board the board object containing the hand and table
     */
    public HandSet(PokerBoard board) {

        this.handCards = board.getHand();
        this.tableCards = board.getTable();
        this.cardPool = makeCardPool();

        this.permutationsHand = generatePermutations();
        findBestHand();
    }

    /**
     * Constructor for PokerHand
     */
    protected HandSet(ArrayList<Card> hand1, ArrayList<Card> hand2) {

    }

    /**
     * @return the pool of cards given the 2 cards in hand and the 5 cards on the table
     */
    public Card[] makeCardPool() {
        Card[] cardPool = new Card[handCards.length + tableCards.length];

        for (int i = 0; i < handCards.length; i++) {
            cardPool[i] = handCards[i];
        }

        for (int i = 0; i < tableCards.length; i++) {
            cardPool[i + handCards.length] = tableCards[i];
        }
        return cardPool;
    }

    /**
     * @return a set of all permutations of 5 cards given 2 cards in hand and 5 cards on table
     */
    public Set<ArrayList<Card>> generatePermutations() {

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

    /**
     * @return the best hand given the permutations
     */
    public ArrayList<Card> findBestHand() {

        ArrayList<Card> bestHand = new ArrayList<>();
        ArrayList<Integer> bestHandIdentifiers = new ArrayList<>();
        int bestHandRank = -1;

        for (ArrayList<Card> perm : permutationsHand) {
            Hand hand = new Hand(perm);
            int rank = hand.getRank();

            if (rank > bestHandRank) {
                bestHand = perm;
                bestHandRank = rank;
                bestHandIdentifiers = hand.getHandIdentifiers();

            } else if (rank==bestHandRank){

                int decision = compareIdentifiers(bestHandIdentifiers, hand.getHandIdentifiers());

                //if the new hand is better, replace the best hand by the new hand
                if (decision==2){
                    bestHand = perm;
                    bestHandRank = rank;
                    bestHandIdentifiers = hand.getHandIdentifiers();
                }

            }
        }

        this.bestHand = bestHand;
        this.bestHandRank = bestHandRank;
        this.bestHandIdentifiers = bestHandIdentifiers;

        return bestHand;
    }

    private int compareIdentifiers(ArrayList<Integer> bestHandIdentifiers, ArrayList<Integer> trialHandIdentifiers){
        int decision = 0;
        for (int i = 0; i < bestHandIdentifiers.size(); i++) {
            if (bestHandIdentifiers.get(i) > trialHandIdentifiers.get(i)){
                return 1;
            }
            else if (bestHandIdentifiers.get(i) < trialHandIdentifiers.get(i)){
                return 2;
            }
        }
        return decision;

    }

    public Set<ArrayList<Card>> getPermHand() {
        return permutationsHand;
    }

    public ArrayList<Integer> getBestHandIdentifiers() {
        return bestHandIdentifiers;
    }

    public ArrayList<Card> getBestHand() {
        return bestHand;
    }

    public int getBestHandRank() {
        return bestHandRank;
    }
}
