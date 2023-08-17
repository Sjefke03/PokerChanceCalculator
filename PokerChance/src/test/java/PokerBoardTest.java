import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


class PokerBoardTest {
   

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
        assertTrue(total == 990);

    }

    @Test
    void calculateChances1Wildcard() {
        PokerBoard board = new PokerBoard("ha","hk","da","hj","","c2","c3");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent =  (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance+ " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);
        assertTrue(total == 45540);

    }

    @Test
    void calculateChances2Wildcard1() {
        PokerBoard board = new PokerBoard("ha", "da", "sa", "hj", "", "c2", "");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent = (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance + " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);
        assertTrue(total == 1070190 || total == 1070189);
    }

    @Test
    void calculateChances2Wildcard2() {
        PokerBoard board = new PokerBoard("h2", "d2", "sa", "hj", "", "c2", "");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent = (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance + " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);
        assertTrue(total == 1070190 || total == 1070189);
    }


    @Test
    void calculateChances2Wildcard3() {
        PokerBoard board = new PokerBoard("h5", "da", "sa", "hj", "", "c2", "");

        int winChance = board.getWinChance();
        int tieChance = board.getTieChance();
        int loseChance = board.getLoseChance();

        int total = winChance + tieChance + loseChance;
        float winChancePercent = (float) winChance / total * 100;
        System.out.println("W " + winChance + " T " + tieChance + " L " + loseChance + " TOTAL " + total);
        System.out.println("Chance of winning: " + winChancePercent);
        assertTrue(total == 1070190 || total == 1070189);
    }

}