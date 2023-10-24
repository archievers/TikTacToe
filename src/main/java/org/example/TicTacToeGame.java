package org.example;

import java.util.Scanner;

public class TicTacToeGame {
    private static final int BOARD_SIZE = 3;
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        boolean gameWon = false;
        int moves = 0;

        while (!gameWon && moves < BOARD_SIZE * BOARD_SIZE) {
            printBoard();
            int[] move = getPlayerMove();
            int row = move[0];
            int col = move[1];

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                gameWon = checkWin(row, col);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                moves++;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        printBoard();
        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];

        System.out.print("Player " + currentPlayer + ", enter row (0, 1, 2) and column (0, 1, 2): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();

        return move;
    }

    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == ' ');
    }

    private static boolean checkWin(int row, int col) {
        // Check row, column, and diagonals for a win
        return (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) ||
                (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) ||
                (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }
}
