import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Public class poker board is represents an active poker board. With input player cards h1 and h2
 * and the table of cards is represented as t1 - t5.
 */
public class PokerBoard {

    private AtomicInteger requiredCalculations = new AtomicInteger(0);
    private AtomicInteger winChance = new AtomicInteger(0);
    private AtomicInteger loseChance = new AtomicInteger(0);
    private AtomicInteger tieChance = new AtomicInteger(0);
    private Card[] hand1Cards = new Card[2];
    private Card[] hand2Cards = new Card[2];
    private Card[] openCards = new Card[5];
    private Card[] handOpponent = new Card[2];

    private ArrayList<Card> cardPool;

    //will create a poker board
    PokerBoard(String h1, String h2, String t1, String t2, String t3, String t4, String t5) {

        this.hand1Cards[0] = new Card(trimCard(h1));
        this.hand1Cards[1] = new Card(trimCard(h2));
        this.openCards[0] = new Card(trimCard(t1));
        this.openCards[1] = new Card(trimCard(t2));
        this.openCards[2] = new Card(trimCard(t3));
        this.openCards[3] = new Card(trimCard(t4));
        this.openCards[4] = new Card(trimCard(t5));

        this.cardPool = makeKnownCardPool(hand1Cards, openCards);

        calculateChances();
    }

    /**
     * @return the pool of cards given the 2 cards in hand and the 5 cards on the table
     */
    public ArrayList<Card> makeKnownCardPool(Card[] handCards, Card[] openCards) {
        ArrayList<Card> cardPool = new ArrayList<Card>();

        cardPool.addAll(Arrays.asList(handCards));
        cardPool.addAll(Arrays.asList(openCards));

        return cardPool;
    }

    public void calculateChances() {

        //generate all possible cards
        ArrayList<Card> availableCards = generateRealCards();

        int unknownCounter = 0;

        //remove wildcards from the card pool
        for (int i = 0; i < cardPool.size(); i++) {
            if (cardPool.get(i).equals(new Card("**"))) {
                unknownCounter++;
                cardPool.remove(i);
            }
        }


        //remove the cards that are already known
        for (Card c : cardPool) {
            for (int i = 0; i < availableCards.size(); i++) {
                if (c.equals(availableCards.get(i))) {
                    availableCards.remove(i);
                }
            }
        }

        boolean roundOne = true;
        //supply the opponent with all possible ways of unknown hands
        for (int i = 0; i < availableCards.size(); i++) {
            for (int j = i + 1; j < availableCards.size(); j++) {
                //for (int k=0; k <= unknownCounter; k++) {

                hand2Cards[0] = availableCards.get(i);
                hand2Cards[1] = availableCards.get(j);


                if (unknownCounter == 1) {
                    if (roundOne) {
                        requiredCalculations.set(45540);
                        roundOne = false;
                    }

                    supplyOneOpenCards(availableCards, i, j, 0);

                } else if (unknownCounter == 2) {
                    if (roundOne) {
                        requiredCalculations.set(1070190);
                        roundOne = false;
                    }

                    supplyTwoOpenCards(availableCards, i, j);

                } else if (unknownCounter == 3) {

                    if (roundOne) {
                        requiredCalculations.set(17123040);
                        roundOne = false;
                    }
                    supplyThreeOpenCards(availableCards, i, j);

                } else if (unknownCounter == 4) {
                    if (roundOne) {
                        requiredCalculations.set(209757240);
                        roundOne = false;
                    }
                    supplyFourOpenCards(availableCards, i, j);

                } else if (unknownCounter == 5) {
                    if (roundOne) {
                        requiredCalculations.set(2097572400);
                        roundOne = false;
                    }
                    supplyFiveOpenCards(availableCards, i, j);

                } else {
                    if (roundOne) {
                        requiredCalculations.set(990);
                        roundOne = false;
                    }

                    compareHandSets();

                }
            }
        }

        //wait until all calculations are done by checking if the required calculations are done
        while (requiredCalculations.get() > 1) {
//            try {
//                Thread.sleep(1);
//                System.out.println(requiredCalculations.get());
//                System.out.println("Waiting to finish threads...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }


    protected void supplyFiveOpenCards(ArrayList<Card> availableCards, int i, int j) {
        //given the index of two used cards, supply the open cards with all possible combinations
        for (int k = 0; k < availableCards.size(); k++) {
            if (k == i || k == j) {
                continue;
            }

            for (int l = k + 1; l < availableCards.size(); l++) {
                if (l == i || l == j) {
                    continue;
                }

                for (int m = l + 1; m < availableCards.size(); m++) {
                    if (m == i || m == j) {
                        continue;
                    }

                    for (int n = m + 1; n < availableCards.size(); n++) {
                        if (n == i || n == j) {
                            continue;
                        }

                        for (int o = n + 1; o < availableCards.size(); o++) {

                            if (o == i || o == j) {
                                continue;
                            }

                            openCards[0] = availableCards.get(k);
                            openCards[1] = availableCards.get(l);
                            openCards[2] = availableCards.get(m);
                            openCards[3] = availableCards.get(n);
                            openCards[4] = availableCards.get(o);


                            //compare the hands
                            compareHandSets();


                        }
                    }
                }
            }
        }
    }

    protected void supplyFourOpenCards(ArrayList<Card> availableCards, int i, int j) {
        //given the index of two used cards, supply the open cards with all possible combinations

        for (int l = 0; l < availableCards.size(); l++) {
            if (l == i || l == j) {
                continue;
            }
            for (int m = l + 1; m < availableCards.size(); m++) {
                if (m == i || m == j) {
                    continue;
                }
                for (int n = m + 1; n < availableCards.size(); n++) {
                    if (n == i || n == j) {
                        continue;
                    }
                    for (int o = n + 1; o < availableCards.size(); o++) {
                        if (o == i || o == j) {
                            continue;
                        }

                        openCards[1] = availableCards.get(l);
                        openCards[2] = availableCards.get(m);
                        openCards[3] = availableCards.get(n);
                        openCards[4] = availableCards.get(o);


                        //compare the hands
                        compareHandSets();

                    }
                }
            }
        }

    }

    protected void supplyThreeOpenCards(ArrayList<Card> availableCards, int i, int j) {
        //given the index of two used cards, supply the open cards with all possible combinations
        for (int m = 0; m < availableCards.size(); m++) {
            if (m == i || m == j) {
                continue;
            }

            for (int n = m + 1; n < availableCards.size(); n++) {
                if (n == i || n == j) {
                    continue;
                }

                for (int o = n + 1; o < availableCards.size(); o++) {
                    if (o == i || o == j) {
                        continue;
                    }

                    openCards[2] = availableCards.get(m);
                    openCards[3] = availableCards.get(n);
                    openCards[4] = availableCards.get(o);
                    //compare the hands
                    compareHandSets();


                }
            }
        }


    }

    protected void supplyTwoOpenCards(ArrayList<Card> availableCards, int i, int j) {
        //given the index of two used cards, supply the open cards with all possible combinations

            for (int n = 0; n < availableCards.size(); n++) {
                if (n == i || n == j) {
                    continue;
                }
                int pass = n;

                //new Thread(() -> {
                openCards[3] = availableCards.get(pass);

                for (int o = pass + 1; o < availableCards.size(); o++) {
                    if (o == i || o == j) {
                        continue;
                    }

                    openCards[4] = availableCards.get(o);


                    //compare the hands
                    compareHandSets();

                }
                //}).start();
            }

    }

    protected void supplyOneOpenCards(ArrayList<Card> availableCards, int i, int j, int counter1) {
        //given the index of two used cards, supply the open cards with all possible combinations


            for (int o = 0; o < availableCards.size(); o++) {
                if (o == i || o == j) {
                    continue;
                }
                openCards[4] = availableCards.get(o);

                //new Thread(() -> {
                //compare the hands
                compareHandSets();
                //}).start();

            }


    }

    protected void compareHandSets() {

        //perform with current cards
        HandSet handSet1 = new HandSet(makeKnownCardPool(hand1Cards, openCards));
        HandSet handSet2 = new HandSet(makeKnownCardPool(hand2Cards, openCards));

        int result = compareHands(handSet1, handSet2);
        if (result == 1) {
            winChance.getAndIncrement();
        } else if (result == 2) {
            loseChance.getAndIncrement();
        } else if (result == 0) {
            tieChance.getAndIncrement();
        }

        //countdown from required calculations
        requiredCalculations.getAndDecrement();
    }


    //compare two handsets and their best hands
    public int compareHands(HandSet handSet1, HandSet handSet2) {
        int result = 0;

        //if the best hands are equal compare the second best hands
        if (handSet1.getBestHandRank() > handSet2.getBestHandRank()) {
            return 1;
        } else if (handSet2.getBestHandRank() > handSet1.getBestHandRank()) {
            return 2;
        } else if (handSet1.getBestHandRank() == handSet2.getBestHandRank()) {

            //loop through the best hands and compare by cards
            for (int i = 0; i < handSet1.getBestHandIdentifiers().size(); i++) {
                if (handSet1.getBestHandIdentifiers().get(i) > handSet2.getBestHandIdentifiers().get(i)) {
                    return 1;
                } else if (handSet1.getBestHandIdentifiers().get(i) < handSet2.getBestHandIdentifiers().get(i)) {
                    return 2;
                }
            }

        } else {
            return 0;
        }

        return result;
    }

    protected ArrayList<Card> generateRealCards() {

        String[] colors = {"h", "c", "d", "s"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a"};

        ArrayList<Card> generatedCards = new ArrayList<Card>();
        //generate all possible cards of a color and a rank
        for (String color : colors) {
            for (String rank : ranks) {
                generatedCards.add(new Card(color + rank));
            }
        }

        return generatedCards;

    }

    /**
     * Trims the input card by taking the first two input letters
     * if empty makes it a wildcard **
     *
     * @param c1
     * @return the first 2 letters of the input string or * if the string is empty
     */
    private String trimCard(String c1) {

        if (c1 == null || c1.length() < 2) {
            c1 = "**";
        } else if (c1.length() > 2) {
            c1 = c1.substring(0, 2);
        }
        return c1;
    }

    public ArrayList<Card> getCardPool() {
        return cardPool;
    }

    public int getWinChance() {
        return winChance.get();
    }

    public int getTieChance() {
        return tieChance.get();
    }

    public int getLoseChance() {
        return loseChance.get();
    }

    //create setters for the chances
    public void setWinChance(int winChance) {
        this.winChance.set(winChance);
    }

    public void setTieChance(int tieChance) {
        this.tieChance.set(tieChance);
    }

    public void setLoseChance(int loseChance) {
        this.loseChance.set(loseChance);
    }
}
