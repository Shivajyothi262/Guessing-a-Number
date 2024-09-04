import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame {
    private int targetNumber;
    private int attempts;
    private long startTime;

    private JFrame frame;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private JTextField attemptsField;
    private JLabel timeLabel;
    private JTextField timeField;

    public NumberGuessingGame() {
        targetNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;
        startTime = System.currentTimeMillis();

        frame = new JFrame("Number Guessing Game");
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        titleLabel = new JLabel("Guess the Number!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(138, 43, 226)); // Purplish color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        instructionLabel = new JLabel("Enter a number between 1 and 100:");
        instructionLabel.setForeground(new Color(128, 0, 128)); // Dark Purple
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(instructionLabel, gbc);

        guessField = new JTextField(10);
        guessField.setBackground(new Color(216, 191, 216)); // Lavender
        gbc.gridx = 1;
        panel.add(guessField, gbc);

        guessButton = new JButton("Guess");
        guessButton.setBackground(new Color(148, 0, 211)); // Dark Violet
        guessButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(guessButton, gbc);

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(resultLabel, gbc);

        attemptsLabel = new JLabel("Attempts:");
        attemptsLabel.setForeground(new Color(128, 0, 128)); // Dark Purple
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(attemptsLabel, gbc);

        attemptsField = new JTextField(5);
        attemptsField.setEditable(false);
        attemptsField.setBackground(new Color(216, 191, 216)); // Lavender
        gbc.gridx = 1;
        panel.add(attemptsField, gbc);

        timeLabel = new JLabel("Time (s):");
        timeLabel.setForeground(new Color(128, 0, 128)); // Dark Purple
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(timeLabel, gbc);

        timeField = new JTextField(5);
        timeField.setEditable(false);
        timeField.setBackground(new Color(216, 191, 216)); // Lavender
        gbc.gridx = 1;
        panel.add(timeField, gbc);

        guessButton.addActionListener(new GuessButtonListener());

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;

                if (guess < targetNumber) {
                    resultLabel.setText("Try higher than " + guess +". Attempts: " + attempts);
                } else if (guess > targetNumber) {
                    resultLabel.setText("Try lower than " + guess +". Attempts: " + attempts);
                } else {
                    long endTime = System.currentTimeMillis();
                    double timeInSeconds = (endTime - startTime) / 1000.0;
                    resultLabel.setText("Congratulations! You guessed it in " + attempts + " attempts in " + timeInSeconds + " seconds.");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a number.");
            }
            attemptsField.setText(Integer.toString(attempts));
            guessField.setText("");
            timeField.setText(String.format("%.2f", (System.currentTimeMillis() - startTime) / 1000.0));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame());
    }
}

