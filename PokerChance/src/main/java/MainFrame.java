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
    private JProgressBar progressBar;
    private JLabel labelChance;
    private JLabel labelCalculations;
    private JPanel mainPanel;

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
                String h1 = tfh1.getText();
                String h2 = tfh2.getText();
                String t1 = tft1.getText();
                String t2 = tft2.getText();
                String t3 = tft3.getText();
                String t4 = tft4.getText();
                String t5 = tft5.getText();
                System.out.println(h1+h2+t1+t2+t3+t4+t5);

                PokerBoard board = new PokerBoard(h1,h2,t1,t2,t3,t4,t5);
            }
        });
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }
}
