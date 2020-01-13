package design.med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/13/20.
 * <p>
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * </p>
 */
public class _348_Design_TicTacToe {
    int[][] board;
    int endGame;

    public _348_Design_TicTacToe(int n) {
        this.board = new int[n][n];
        this.endGame = 0;
    }

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
        assertEquals(design_ticTacToe.move(0, 0, 1), 0);
        assertEquals(design_ticTacToe.move(0, 2, 2), 0);
        assertEquals(design_ticTacToe.move(2, 2, 1), 0);
        assertEquals(design_ticTacToe.move(1, 1, 2), 0);
        assertEquals(design_ticTacToe.move(2, 0, 1), 0);
        assertEquals(design_ticTacToe.move(1, 0, 2), 0);
        assertEquals(design_ticTacToe.move(2, 1, 1), 1);
    }
}