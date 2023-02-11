public class PokerTable {

    private Card t1;
    private Card t2;
    private Card t3;
    private Card t4;
    private Card t5;

    public PokerTable(Card t1, Card t2, Card t3, Card t4, Card t5) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
    }

    public Card getT1() {
        return t1;
    }

    public Card getT2() {
        return t2;
    }

    public Card getT3() {
        return t3;
    }

    public Card getT4() {
        return t4;
    }

    public Card getT5() {
        return t5;
    }
}
