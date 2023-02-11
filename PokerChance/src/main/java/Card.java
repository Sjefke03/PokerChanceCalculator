/**
 * Abstract data type Card
 */
public class Card {
    //color of a card
    private String color;
    //rank of a card
    private String rank;

    /**
     * Constructor for a card.
     *
     * @param value input card string
     * @pre {@code color \in {"*", "h", "c", "d", "s"}}
     * @pre {@code rank \in {"*", "2", "3", "4", "5", "6", "7", "8", "9", "1", "j", "q", "k", "a"}}
     * @throws IllegalArgumentException if not a valid color or rank
     */
    Card(String value) throws IllegalArgumentException {
        this.color = value.substring(0, 1);
        this.rank = value.substring(1, 2);

        if (!isValidColor(color)) {
            throw new IllegalArgumentException("Color was invalid");
        }
        if (!isValidRank(rank)) {
            throw new IllegalArgumentException("Rank was invalid");
        }

        System.out.println("Created card: " + color + rank);
    }

    /**
     * returns if a color is valid with that of a card deck.
     *
     * @param c color
     * @return if the color is valid
     */
    private boolean isValidColor(String c) {
        //is a Hearts Clubs Diamonds or Spades
        String[] colors = {"*", "h", "c", "d", "s"};

        //initialy false turns true if its in the list
        boolean valid = false;
        for (int i = 0; i < colors.length; i++) {
            if (c.equals(colors[i])) {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * returns if a color is valid with that of a card deck.
     *
     * @param r rank
     * @return if the color is valid
     */
    private boolean isValidRank(String r) {
        //is a Hearts Clubs Diamonds or Spades
        String[] ranks = {"*", "2", "3", "4", "5", "6", "7", "8", "9", "1", "j", "q", "k", "a"};

        //initialy false turns true if its in the list
        boolean valid = false;
        for (int i = 0; i < ranks.length; i++) {
            if (r.equals(ranks[i])) {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Gets the color.
     *
     * @return color of a card
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the rank of a card in integer form.
     *
     * @return rank of a card
     */
    public int getRank() {

        if (rank.equals("2")){
            return 2;
        }
        if (rank.equals("3")){
            return 3;
        }
        if (rank.equals("4")){
            return 4;
        }
        if (rank.equals("5")){
            return 5;
        }
        if (rank.equals("6")){
            return 6;
        }
        if (rank.equals("7")){
            return 7;
        }
        if (rank.equals("8")){
            return 8;
        }
        if (rank.equals("9")){
            return 9;
        }
        if (rank.equals("1")){
            return 10;
        }
        if (rank.equals("j")){
            return 11;
        }
        if (rank.equals("q")){
            return 12;
        }
        if (rank.equals("k")){
            return 13;
        }
        if (rank.equals("a")){
            return 14;
        }
        else{
            return -1;
        }


    }

    /**
     * toString returns the card values as a string I.E. H4 = 4 of hearts
     *
     * @return string representation of a card
     */
    @Override
    public String toString() {
        return color + rank;
    }
}
