package com.nix.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameOfLifeApplication extends JFrame {

    private static final int size = 25;
    private boolean cellMap[][];
    private JButton cells[][];

    public GameOfLifeApplication() {
        var random = new Random();
        cellMap = new boolean[size][size];
        cells = new JButton[size][size];
        setSize(500, 500);
        setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int b = 0; b < size; b++) {
                cellMap[i][b] = random.nextInt(50) < 15;
                JButton temp = new JButton();
                if (cellMap[i][b])
                    temp.setBackground(Color.BLACK);
                else
                    temp.setBackground(Color.WHITE);
                add(temp);
                cells[i][b] = temp;
            }

        }

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Timer timer = new Timer(150, e -> {

            boolean[][] temp = new boolean[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int count = countNeighbours(i, j);

                    if (cellMap[i][j]) {
                        if (count < 2)
                            temp[i][j] = false;
                        if (count == 3 || count == 2)
                            temp[i][j] = true;
                        if (count > 3)
                            temp[i][j] = false;
                    } else if (count == 3) {
                        temp[i][j] = true;
                    }
                }
            }

            cellMap = temp;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (cellMap[i][j]) {
                        cells[i][j].setBackground(Color.BLACK);
                    } else {
                        cells[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        });
        timer.start();
    }

    private int countNeighbours(int x, int y) {
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    if (cellMap[i][j]) {
                        count++;
                    }
                } catch (Exception ignored){}
            }
        }
        if (cellMap[x][y])
            count--;
        return count;
    }

    public static void main(String[] args) {
        new GameOfLifeApplication();
    }
}
