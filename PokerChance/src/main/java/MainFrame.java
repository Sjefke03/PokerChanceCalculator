import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField tfh2;
    private JTextField tfh1;
    private JTextField tft1;
    private JTextField tft3;
    private JTextField tft4;
    private JTextField tft5;
    private JTextField tft2;
    private JButton calculateButton;
    private JLabel labelChance;
    private JLabel labelCalculations;
    private JPanel mainPanel;
    private JLabel winTotal;
    private JLabel tieTotal;
    private JLabel lossTotal;

    //intialise mainframe
    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Poker chance calculator");
        setSize(900, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //add action listener to button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //if any of the text fields are empty, return
                if (tfh1.getText().equals("") || tfh2.getText().equals("") || tft1.getText().equals("") || tft2.getText().equals("") || tft3.getText().equals("") || tft5.getText().equals("")){
                    return;
                }

                String h1 = tfh1.getText();
                String h2 = tfh2.getText();
                String t1 = tft1.getText();
                String t2 = tft2.getText();
                String t3 = tft3.getText();
                String t4 = tft4.getText();
                String t5 = tft5.getText();
                System.out.println(h1+h2+t1+t2+t3+t4+t5);

                PokerBoard board = new PokerBoard(h1,h2,t1,t2,t3,t4,t5);

                //get win, tie and lose chance
                int winChance = board.getWinChance();
                int tieChance = board.getTieChance();
                int loseChance = board.getLoseChance();

                //calculate percantage chance of winning
                int total = winChance + tieChance + loseChance;
                float winChancePercent =  (float) winChance / total * 100;

                //set labels to display results
                winTotal.setText("Win: " + winChance);
                tieTotal.setText("Tie: " + tieChance);
                lossTotal.setText("Lose: " + loseChance);
                labelChance.setText( winChancePercent + "%");

                board.setLoseChance(0);
                board.setTieChance(0);
                board.setWinChance(0);

            }
        });
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}
