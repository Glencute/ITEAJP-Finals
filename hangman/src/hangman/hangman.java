package hangman;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Image;

public class hangman extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private String secretWord = "DILLDOUGH"; 
    private int mistakes = 0;
    private ArrayList<String> guessedLetters = new ArrayList<>();
    
    private JLabel HEAD, LEFTARM, RIGHTARM, RIGHTLEG, LEFTLEG, BODY;
    private JLabel lblWordDisplay; 
    private JLabel Logo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {           
                    hangman frame = new hangman();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public hangman() {
    	setForeground(Color.WHITE);
        setTitle("Hangman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 545);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        
        HEAD = new JLabel("O");
        HEAD.setHorizontalAlignment(SwingConstants.CENTER);
        HEAD.setFont(new Font("Tahoma", Font.PLAIN, 45));
        HEAD.setBounds(284, 91, 50, 45);
        HEAD.setVisible(false);
        contentPane.add(HEAD);

        BODY = new JLabel("|");
        BODY.setHorizontalAlignment(SwingConstants.CENTER);
        BODY.setFont(new Font("Tahoma", Font.PLAIN, 45));
        BODY.setBounds(284, 119, 50, 45);
        BODY.setVisible(false);
        contentPane.add(BODY);

        LEFTARM = new JLabel("_");
        LEFTARM.setHorizontalAlignment(SwingConstants.CENTER);
        LEFTARM.setFont(new Font("Tahoma", Font.PLAIN, 45));
        LEFTARM.setBounds(273, 80, 41, 66);
        LEFTARM.setVisible(false);
        contentPane.add(LEFTARM);

        RIGHTARM = new JLabel("_");
        RIGHTARM.setHorizontalAlignment(SwingConstants.CENTER);
        RIGHTARM.setFont(new Font("Tahoma", Font.PLAIN, 45));
        RIGHTARM.setBounds(306, 80, 41, 66);
        RIGHTARM.setVisible(false);
        contentPane.add(RIGHTARM);

        RIGHTLEG = new JLabel("\\");
        RIGHTLEG.setHorizontalAlignment(SwingConstants.CENTER);
        RIGHTLEG.setFont(new Font("Tahoma", Font.PLAIN, 45));
        RIGHTLEG.setBounds(306, 156, 20, 40);
        RIGHTLEG.setVisible(false);
        contentPane.add(RIGHTLEG);

        LEFTLEG = new JLabel("/");
        LEFTLEG.setHorizontalAlignment(SwingConstants.CENTER);
        LEFTLEG.setFont(new Font("Tahoma", Font.PLAIN, 45));
        LEFTLEG.setBounds(294, 156, 20, 40);
        LEFTLEG.setVisible(false);
        contentPane.add(LEFTLEG);

        // --- GALLOWS ---
        JLabel post1 = new JLabel("|");
        post1.setFont(new Font("Tahoma", Font.PLAIN, 70));
        post1.setHorizontalAlignment(SwingConstants.CENTER);
        post1.setBounds(218, 100, 45, 96);
        contentPane.add(post1);

        JLabel post2 = new JLabel("|");
        post2.setHorizontalAlignment(SwingConstants.CENTER);
        post2.setFont(new Font("Tahoma", Font.PLAIN, 70));
        post2.setBounds(218, 40, 45, 96);
        contentPane.add(post2);

        JLabel topBar1 = new JLabel("_");
        topBar1.setHorizontalAlignment(SwingConstants.CENTER);
        topBar1.setFont(new Font("Tahoma", Font.PLAIN, 70));
        topBar1.setBounds(243, -21, 45, 96);
        contentPane.add(topBar1);

        JLabel topBar2 = new JLabel("_");
        topBar2.setHorizontalAlignment(SwingConstants.CENTER);
        topBar2.setFont(new Font("Tahoma", Font.PLAIN, 70));
        topBar2.setBounds(273, -21, 45, 96);
        contentPane.add(topBar2);

        JLabel ROPE = new JLabel("|");
        ROPE.setHorizontalAlignment(SwingConstants.CENTER);
        ROPE.setFont(new Font("Tahoma", Font.PLAIN, 38));
        ROPE.setBounds(289, 29, 45, 96);
        contentPane.add(ROPE);
        
        lblWordDisplay = new JLabel("");
        lblWordDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        lblWordDisplay.setFont(new Font("Monospaced", Font.BOLD, 24));
        lblWordDisplay.setBounds(10, 184, 616, 50);
        updateWordDisplay(); 
        contentPane.add(lblWordDisplay);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        layeredPane.setBounds(10, 234, 616, 264); 
        contentPane.add(layeredPane);

        ActionListener letterListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String letter = clickedButton.getText();
                
                if (secretWord.contains(letter)) {
                    clickedButton.setBackground(Color.GREEN);
                } else {
                    clickedButton.setBackground(Color.RED);
                }
                clickedButton.setEnabled(false);       
                checkGuess(letter);
            }
        };

        createButton(layeredPane, "A", 10, 10, letterListener);
        createButton(layeredPane, "B", 83, 10, letterListener);
        createButton(layeredPane, "C", 156, 10, letterListener);
        createButton(layeredPane, "D", 229, 10, letterListener);
        createButton(layeredPane, "E", 302, 10, letterListener);
        createButton(layeredPane, "F", 375, 10, letterListener);
        createButton(layeredPane, "G", 448, 10, letterListener);
        createButton(layeredPane, "H", 521, 10, letterListener);
        
        createButton(layeredPane, "I", 10, 73, letterListener);
        createButton(layeredPane, "J", 83, 73, letterListener);
        createButton(layeredPane, "K", 156, 73, letterListener);
        createButton(layeredPane, "L", 229, 73, letterListener);
        createButton(layeredPane, "M", 302, 73, letterListener);
        createButton(layeredPane, "N", 375, 73, letterListener);
        createButton(layeredPane, "O", 448, 73, letterListener);
        createButton(layeredPane, "P", 521, 73, letterListener);
        
        createButton(layeredPane, "Q", 10, 136, letterListener);
        createButton(layeredPane, "R", 83, 136, letterListener);
        createButton(layeredPane, "S", 156, 136, letterListener);
        createButton(layeredPane, "T", 229, 136, letterListener);
        createButton(layeredPane, "U", 302, 136, letterListener);
        createButton(layeredPane, "V", 375, 136, letterListener);
        createButton(layeredPane, "W", 448, 136, letterListener);
        createButton(layeredPane, "X", 521, 136, letterListener);
        
        createButton(layeredPane, "Y", 229, 199, letterListener);
        createButton(layeredPane, "Z", 302, 199, letterListener);
        

    }
    
    private void createButton(JLayeredPane panel, String text, int x, int y, ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.DARK_GRAY);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(Color.WHITE);
        btn.setBounds(x, y, 63, 53); 
        btn.setOpaque(true); 
        btn.addActionListener(listener);
        panel.add(btn);
    }

    private void checkGuess(String letter) {
        guessedLetters.add(letter);
        
        if (secretWord.contains(letter)) {
            updateWordDisplay();
            checkWin();
        } else {
            mistakes++;
            updateMan();
            if (mistakes >= 6) {
                JOptionPane.showMessageDialog(this, "Game Over! The word was: " + secretWord);
                resetGame();
            }
        }
    }
    
    private void updateWordDisplay() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            String currentLetter = String.valueOf(secretWord.charAt(i));
            if (guessedLetters.contains(currentLetter)) {
                display.append(currentLetter).append(" ");
            } else {
                display.append("_ ");
            }
        }
        lblWordDisplay.setText(display.toString());
    }
    
    private void updateMan() {
        if (mistakes == 1) HEAD.setVisible(true);
        if (mistakes == 2) BODY.setVisible(true);
        if (mistakes == 3) LEFTARM.setVisible(true);
        if (mistakes == 4) RIGHTARM.setVisible(true);
        if (mistakes == 5) LEFTLEG.setVisible(true);
        if (mistakes == 6) RIGHTLEG.setVisible(true);
    }
    
    private void checkWin() {
        if (!lblWordDisplay.getText().contains("_")) {
            JOptionPane.showMessageDialog(this, "You Won! Great job.");
            resetGame();
        }
    }
    
    private void resetGame() {
        mistakes = 0;
        guessedLetters.clear();
        HEAD.setVisible(false);
        BODY.setVisible(false);
        LEFTARM.setVisible(false);
        RIGHTARM.setVisible(false);
        LEFTLEG.setVisible(false);
        RIGHTLEG.setVisible(false);
        updateWordDisplay();

        this.dispose();
        new hangman().setVisible(true);
    }
}