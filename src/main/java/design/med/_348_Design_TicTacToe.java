package design.med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/13/20.
 * <p>
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * </p>
 */
public class _348_Design_TicTacToe {
    // for 1st approach
    private int[][] board;
    private int endGame;

    // for 2nd approach
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;


    public _348_Design_TicTacToe(int n) {
        // for 1st approach
        this.board = new int[n][n];
        this.endGame = 0;

        // for 2nd approach
        rows = new int[n];
        cols = new int[n];
    }

    // TC: Brute force: O(n^2)
    private int move(int row, int col, int player) {
        if (this.endGame != 0) {
            return endGame;
        }

        board[row][col] = player;
        boolean wins = check(row, col, player);
        if (wins) {
            endGame = player;
            return player;
        }
        return 0;
    }

    // optimal solution: compromised space for time when compared to the first solution
    // as we only need row and column counts to see which player won, just remember the count of rows, columns, diagonals and anti diagonals at every move
    // TC: O(1)
    private int move2(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;  // to distinguish between player 1 and player 2. player 1 moves make +1 and player 2 moves make -1
        int matrixSize = rows.length;

        rows[row] += toAdd;
        cols[col] += toAdd;

        if (row == col) {       // to mark diagonal
            diagonal += toAdd;
        }

        if (row + col == matrixSize - 1) {  // to mark anti diagonal
            antiDiagonal += toAdd;
        }

        if (Math.abs(rows[row]) == matrixSize || Math.abs(cols[col]) == matrixSize || Math.abs(diagonal) == matrixSize || Math.abs(antiDiagonal) == matrixSize) {   // if player 1 wins the sum would be +matrixSize and if player 2 wins, the sum would be -matrixSize
            return player;
        }
        return 0;
    }

    private boolean check(int row, int column, int player) {
        boolean flag = true;
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] != player) {
                flag = false;
            }
        }
        if (flag) {
            return flag;
        }

        flag = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] != player) {
                flag = false;
            }
        }
        if (flag) {
            return flag;
        }

        flag = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] != player) {
                flag = false;
            }
        }
        if (flag) return flag;

        flag = true;
        for (int i = board.length - 1, j = 0; j < board.length; i--, j++) {
            if (board[i][j] != player) {
                flag = false;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        _348_Design_TicTacToe design_ticTacToe = new _348_Design_TicTacToe(3);

        // test method: 1
        assertEquals(design_ticTacToe.move(0, 0, 1), 0);
        assertEquals(design_ticTacToe.move(0, 2, 2), 0);
        assertEquals(design_ticTacToe.move(2, 2, 1), 0);
        assertEquals(design_ticTacToe.move(1, 1, 2), 0);
        assertEquals(design_ticTacToe.move(2, 0, 1), 0);
        assertEquals(design_ticTacToe.move(1, 0, 2), 0);
        assertEquals(design_ticTacToe.move(2, 1, 1), 1);

        // test method: 2
        assertEquals(design_ticTacToe.move2(0, 0, 1), 0);
        assertEquals(design_ticTacToe.move2(0, 2, 2), 0);
        assertEquals(design_ticTacToe.move2(2, 2, 1), 0);
        assertEquals(design_ticTacToe.move2(1, 1, 2), 0);
        assertEquals(design_ticTacToe.move2(2, 0, 1), 0);
        assertEquals(design_ticTacToe.move2(1, 0, 2), 0);
        assertEquals(design_ticTacToe.move2(2, 1, 1), 1);
    }
}