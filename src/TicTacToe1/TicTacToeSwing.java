package TicTacToe1;

/**
 * Tic Tac Toe Game - Java Swing GUI
 * Author: Simran Duggal
 * Description: A 2-player Tic Tac Toe game with GUI using Java Swing.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeSwing implements ActionListener {
    JFrame frame = new JFrame("Tic Tac Toe");
    JButton[] buttons = new JButton[9];
    boolean playerXTurn = true;

    public TicTacToeSwing() {
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) return;

        clicked.setText(playerXTurn ? "X" : "O");
        clicked.setForeground(playerXTurn ? Color.BLUE : Color.RED);

        if (checkWinner()) {
            JOptionPane.showMessageDialog(frame,
                "Player " + (playerXTurn ? "X" : "O") + " wins!");
            resetBoard();
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetBoard();
        } else {
            playerXTurn = !playerXTurn;
        }
    }

    boolean checkWinner() {
        int[][] winCombos = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // cols
            {0,4,8}, {2,4,6}           // diagonals
        };

        for (int[] combo : winCombos) {
            String b1 = buttons[combo[0]].getText();
            String b2 = buttons[combo[1]].getText();
            String b3 = buttons[combo[2]].getText();

            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) return false;
        }
        return true;
    }

    void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
            button.setForeground(Color.BLACK);
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToeSwing();
    }
}
