import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;


class PokerBoardTest {

    @Test
    void findBestHandTestHighCard() {
        PokerBoard board = new PokerBoard("**","**","**","**","**","**","**");
        assertEquals(52,board.generateRealCards().size());

    }

    @Test
    void calculateChancesRoyalFlush() {
        PokerBoard board = new PokerBoard("ha","hk","h10","hj","hq","c2","c5");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent =  (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance+ " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);

    }

    @Test
    void calculateChancesHighCard() {
        PokerBoard board = new PokerBoard("ha","h3","c10","hj","hq","c2","c5");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent =  (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance+ " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);

    }

    @Test
    void calculateChances1Wildcard() {
        PokerBoard board = new PokerBoard("ha","hk","h10","hj","","c2","c5");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent =  (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance+ " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);

    }

    @Test
    void calculateChances2Wildcard() {
        PokerBoard board = new PokerBoard("ha","hk","h10","hj","","c2","");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent =  (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance+ " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);

    }

}