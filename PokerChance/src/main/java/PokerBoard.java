/**
 * Public class poker board is represents an active poker board. With input player cards h1 and h2
 * and the table of cards is represented as t1 - t5.
 */
public class PokerBoard {

    private Card[] hand = new Card[2];
    private Card[] table = new Card[5];
    private Card[] handOpponent = new Card[2];

    //will create a poker board
    PokerBoard(String h1, String h2, String t1, String t2, String t3, String t4, String t5) {

        this.hand[0] = new Card(trimCard(h1));
        this.hand[1] = new Card(trimCard(h2));
        this.table[0] = new Card(trimCard(t1));
        this.table[1] = new Card(trimCard(t2));
        this.table[2] = new Card(trimCard(t3));
        this.table[3] = new Card(trimCard(t4));
        this.table[4] = new Card(trimCard(t5));

    }

    public void calculateChances(){

    }

    /**
     * Trims the input card by taking the first two input letters
     * if empty makes it a wildcard **
     * @param c1
     * @return the first 2 letters of the input string or * if the string is empty
     */
    private String trimCard(String c1) {

        if (c1.length() < 2) {
            c1 = "**";
        } else if (c1.length() > 2) {
            c1 = c1.substring(0, 2);
        }
        return c1;
    }

    public Card[] getHand() {
        return hand;
    }

    public Card[] getTable() {
        return table;
    }
}
