public class AtomicOpenCards {
    private Card[] openCards;

    public AtomicOpenCards() {
        this.openCards = new Card[5];
    }

    public synchronized void setCard(Card card, int index) {
        this.openCards[index] = card;
    }

    public synchronized Card getOpenCard(int index) {
        return this.openCards[index];
    }

    public synchronized Card[] getOpenCards() {
        return this.openCards;
    }
}
