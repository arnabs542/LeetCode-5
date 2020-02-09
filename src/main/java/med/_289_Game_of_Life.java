package med;

import java.util.Arrays;

/**
 * Created by udaythota on 2/8/20.
 * <p>
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 * </p>
 */
public class _289_Game_of_Life {
    private static int newLive = 2;  // to represent the state of cells which got lives from original state being death
    private static int newDie = 3;  // to represent the state of cells which got died from original state being live

    // core logic: as state of the current cell changes only based on number of number of LIVE CELL NEIGHBORS for the current cell, at each and every point of the grid, count the number of neighboring live cells and based on that, change the state of the current cell
    // the meat of the problem lies in defining the new states for new lives and new deads and using them in counting the live neighbors (these new states are needed as we are modifying the input matrix simultaneously while reading the original values: the question asks this to solve in place)
    // finally revert back the newLives and newDies to 1's and 0's
    // NOTE: we can use the brute force approach to do this without having the new states and by using additional space. see here for explanation: https://leetcode.com/problems/game-of-life/discuss/502561/Easy-Interview-Friendly-Java-Solution.
    private static void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveNeighbors = countOfLiveNeighbors(i, j, board);
                if (board[i][j] == 0) {    // 4th condition
                    if (liveNeighbors == 3) {
                        board[i][j] = newLive;
                    }
                } else if (board[i][j] == 1) {
                    if (liveNeighbors == 2 || liveNeighbors == 3) {  // 2nd condition
                        continue;
                    }
                    if (liveNeighbors < 2 || liveNeighbors > 3) {  // 1st and 3rd condition
                        board[i][j] = newDie;
                    }
                }
            }
        }

        // finally revert back the new die to 0's and new lives to 1's in the grid
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == newDie) {
                    board[i][j] = 0;
                }
                if (board[i][j] == newLive) {
                    board[i][j] = 1;
                }
            }
        }
    }

    // returns the count of live neighbors for given coordinate in the grid by checking all its 8 possible neighbors
    private static int countOfLiveNeighbors(int i, int j, int[][] board) {
        int liveNeighbors = 0;
        int[][] neighbors = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};  // left, right, up, down, left(2) and right diagonals (2)

        for (int[] neighbor : neighbors) {
            int x = i + neighbor[0];
            int y = j + neighbor[1];

            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {
                if (board[x][y] == 1 || board[x][y] == newDie) {  // new die meaning its previous state SHOULD BE 1, so count that
                    liveNeighbors++;
                }
            }
        }
        return liveNeighbors;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}