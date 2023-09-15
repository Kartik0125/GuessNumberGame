package myjava;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class GuessingGame {

    private JFrame frame;
    private JTextField textField;

    private int guess;
    private int attempts = 0;
    private int maxAttempts = 5;
    private int randomNumber; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuessingGame window = new GuessingGame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GuessingGame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(221, 162, 209));
        frame.setBounds(100, 100, 641, 303);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        ImageIcon logoIcon = new ImageIcon("Alexa_NumberGuess.jpeg");
	    frame.setIconImage(logoIcon.getImage());
	    frame.setTitle("Guess a number ?");
        
        JLabel titleLabel = new JLabel("Number Guessing Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(160, 10, 300, 55);
        frame.getContentPane().add(titleLabel);

        JLabel hintLabel = new JLabel("Guess a number between 0 and 99:");
        hintLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        hintLabel.setBounds(160, 75, 252, 30);
        frame.getContentPane().add(hintLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setBounds(195, 120, 179, 44);
        frame.getContentPane().add(textField);

        JButton btnPlayAgain = new JButton("Play Again");
        btnPlayAgain.setBackground(SystemColor.textHighlight);
        btnPlayAgain.setVisible(false);
        btnPlayAgain.setFont(new Font("Arial", Font.BOLD, 18));
        btnPlayAgain.setBounds(219, 187, 134, 30);
        frame.getContentPane().add(btnPlayAgain);
        
        Random random = new Random();
        
        JButton checkButton = new JButton("Check");
        checkButton.setBackground(SystemColor.textHighlight);
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (attempts >= maxAttempts) {
                    JOptionPane.showMessageDialog(null, "Game over. You've used all your attempts.");
                    return;
                }

                guess = Integer.parseInt(textField.getText());

                if (attempts < maxAttempts - 1) {
                    if (guess == randomNumber) {
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " + (maxAttempts - attempts) + " attempts.");
                        checkButton.setVisible(false);
                        btnPlayAgain.setVisible(true);
                        textField.setEnabled(false);
                    } else if (guess < randomNumber) {
                        JOptionPane.showMessageDialog(null, "Try a larger number.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Try a smaller number.");
                    }
                } else if (attempts == maxAttempts - 1) {
                    if (guess == randomNumber) {
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in your last attempt.");
                        btnPlayAgain.setEnabled(true);
                        checkButton.setVisible(true);
                        textField.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Game over. You've used all your attempts.");
                        btnPlayAgain.setVisible(true);
                        textField.setEnabled(false);
                        
                    }
                }

                attempts++;

                if (attempts >= maxAttempts) {
                    checkButton.setEnabled(false);
                    
                }
            }
        });
        btnPlayAgain.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  attempts = 0;
        	        randomNumber = random.nextInt(100);
        	        System.out.print(randomNumber);
        	        btnPlayAgain.setVisible(false);
        	        checkButton.setVisible(true);
        	        checkButton.setEnabled(true); // Add this line to enable the Check button
        	        textField.setEnabled(true);
        	        textField.setText(null);
        	}
        });
        checkButton.setFont(new Font("Arial", Font.BOLD, 18));
        checkButton.setBounds(423, 126, 127, 30);
        frame.getContentPane().add(checkButton);
        
        

        frame.setVisible(true);
        JOptionPane.showMessageDialog(null, "Welcome to the Number Guessing Game! Try to guess the number between 0 and 99.");        
        randomNumber = random.nextInt(100);
        System.out.println(randomNumber);
    }
}
