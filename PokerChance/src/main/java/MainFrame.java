import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JPanel mainPanel;
    private JLabel winTotal;
    private JLabel tieTotal;
    private JLabel lossTotal;
    private JComboBox card1;
    private JComboBox card2;
    private JComboBox table1;
    private JComboBox table2;
    private JComboBox table3;
    private JComboBox table4;
    private JComboBox table5;
    private JLabel calcTotal;

    //intialise mainframe
    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Poker chance calculator");
        setSize(800, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        ArrayList<String> cardStrings = generateCardStrings();
        //convert cardStrings to array
        String[] cardStringsArray = new String[cardStrings.size()];
        cardStringsArray = cardStrings.toArray(cardStringsArray);

        for (String option : cardStrings) {
            //for all comboboxes, add card names
            card1.addItem(option);
            card2.addItem(option);
            table1.addItem(option);
            table2.addItem(option);
            table3.addItem(option);
            table4.addItem(option);
            table5.addItem(option);
        }


        //add action listener to button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //if any of the text fields are empty, return
//                if (tfh1.getText().equals("") || tfh2.getText().equals("") || tft1.getText().equals("") || tft2.getText().equals("") || tft3.getText().equals("") || tft5.getText().equals("")){
//                    return;
//                }

                //get all text from comboboxes


                String h1 = card1.getSelectedItem().toString();
                String h2 = card2.getSelectedItem().toString();
                String t1 = table1.getSelectedItem().toString();
                String t2 = table2.getSelectedItem().toString();
                String t3 = table3.getSelectedItem().toString();
                String t4 = table4.getSelectedItem().toString();
                String t5 = table5.getSelectedItem().toString();
                System.out.println(h1 + h2 + t1 + t2 + t3 + t4 + t5);


                PokerBoard board = new PokerBoard(h1, h2, t1, t2, t3, t4, t5);

                //get win, tie and lose chance
                int winChance = board.getWinChance();
                int tieChance = board.getTieChance();
                int loseChance = board.getLoseChance();

                //calculate percantage chance of winning
                int total = winChance + tieChance + loseChance;
                float winChancePercent = (float) winChance / total * 100;

                //set labels to display results
                winTotal.setText("Win: " + winChance);
                tieTotal.setText("Tie: " + tieChance);
                lossTotal.setText("Lose: " + loseChance);
                calcTotal.setText("Total: " + total);
                labelChance.setText(winChancePercent + "%");

                board.setLoseChance(0);
                board.setTieChance(0);
                board.setWinChance(0);

            }
        });
    }

    protected ArrayList<String> generateCardStrings() {

        String[] colors = {"h", "c", "d", "s"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a"};

        ArrayList<String> generatedCards = new ArrayList<>();
        generatedCards.add("**");

        //generate all possible cards of a color and a rank
        for (String color : colors) {
            for (String rank : ranks) {
                generatedCards.add(color + rank);
            }
        }

        return generatedCards;

    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }


}
