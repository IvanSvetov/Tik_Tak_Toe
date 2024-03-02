package org.example;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean isXTurn;

    public TicTacToe() {
        super("Крестики-нолики");

        // Установка размеров и расположения окна
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание сетки кнопок 3x3
        buttons = new JButton[3][3];
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        // Инициализация переменных
        isXTurn = true;

        // Отображение окна
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Получение координат кнопки в сетке
        int row = -1, col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                }
            }
        }

        // Проверка, была ли выбрана уже занятая кнопка
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Установка значения кнопки (X или O) в зависимости от текущего игрока
        if (isXTurn) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }

        // Проверка, выиграл ли игрок
        if (checkWin(row, col)) {
            String winner = isXTurn ? "X" : "O";
            JOptionPane.showMessageDialog(this, "Победил игрок " + winner + "!");
            resetGame();
        } else {
            // Проверка на ничью
            if (checkDraw()) {
                JOptionPane.showMessageDialog(this, "Ничья!");
                resetGame();
            } else {
                // Смена хода
                isXTurn = !isXTurn;
            }
        }
    }

    private boolean checkWin(int row, int col) {
        String symbol = isXTurn ? "X" : "O";

        // Проверка горизонтальной линии
        if (buttons[row][0].getText().equals(symbol) &&
                buttons[row][1].getText().equals(symbol) &&
                buttons[row][2].getText().equals(symbol)) {
            return true;
        }

        // Проверка вертикальной линии
        if (buttons[0][col].getText().equals(symbol) &&
                buttons[1][col].getText().equals(symbol) &&
                buttons[2][col].getText().equals(symbol)) {
            return true;
        }

        // Проверка диагональных линий
        if ((row == col &&
                buttons[0][0].getText().equals(symbol) &&
                buttons[1][1].getText().equals(symbol) &&
                buttons[2][2].getText().equals(symbol)) ||
                (row + col == 2 &&
                        buttons[0][2].getText().equals(symbol) &&
                        buttons[1][1].getText().equals(symbol) &&
                        buttons[2][0].getText().equals(symbol))) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        // Проверка заполненности всех кнопок
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }

        return true;
    }

    private void resetGame() {
        // Сброс значений кнопок и переменных
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        isXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe();
            }
        });
    }
}