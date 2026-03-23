package org.mrtxee.playgrnd.sandbox.hs;

import java.util.Arrays;

public class QueenBoard {
    int[] board;

    QueenBoard(int size) {
        this.board = new int[size];
        Arrays.fill(this.board, -1);
    }

    public static void main(String[] args) {
        QueenBoard board = new QueenBoard(20);
        board.placeMaxQueens(0);
        board.printBoard();
        board.printQueensCount();
        board.printBoardData();
    }

    private void printBoardData() {
        System.out.println("BoardData: " + Arrays.toString(this.board));
    }

    public void printBoard() {
        int[] xRow = new int[this.board.length];
        for (int y : this.board) {
            Arrays.fill(xRow, 0);
            if (y >= 0) {
                xRow[y] = 1;
            }
            System.out.println(Arrays.toString(xRow));
        }
    }

    public void printQueensCount() {
        int count = 0;
        for (int val : this.board) {
            if (-1 < val) count++;
        }
        System.out.println("Quens count: " + count);
    }

    private boolean isSafeCell(int y, int x) {
        // нельзя ставить в ту же диагональ
        // нельзя ставить в ту же колонку
        for (int i = 0; i < y; i++) {
            if (y - x == i - board[i] || y + x == i + board[i] || this.board[i] == x) {
                return false;
            }
        }
        return true;
    }

    public boolean placeMaxQueens(int y) {
        if (y >= this.board.length)
            return true;
        for (int x = 0; x < this.board.length; x++) {
            if (this.isSafeCell(y, x)) {
                this.board[y] = x;
                if (placeMaxQueens(y + 1))
                    return true;
                this.board[y] = -1;
            }
        }
        return false;
    }
}
