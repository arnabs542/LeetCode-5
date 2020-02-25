package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/25/20.
 * <p>
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.
 * </p>
 */
public class _688_Knight_Probability_Chess_Board {
    // for every point on the grid, navigate all the 8 directions and calculate the probabilities accordingly. repeat this process K times.
    // remember when the point on the grid is visited again (eg: for the 2nd time), its probability will become existing value / 8 and this continues..
    // we start the process by setting the probability to 1 at the given row and column. in the first iteration of K, all 8 valid (which still falls on board after the move) points from which we can move from given x and y will be set to probability of 1/8. in the next iteration of K, from each of these new points, we again move 8 valid points and adjust the probability as needed
    // TC: O(k * n^2), SC: O(k * n^2)
    private static double knightProbability(int N, int K, int r, int c) {
        int[][] directions = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};   // all the possible 8 directions a knight can move from a given position
        double[][][] dp = new double[K + 1][N][N];    // dp array to remember the probabilities
        dp[0][r][c] = 1.0;

        for (int currentMove = 1; currentMove <= K; currentMove++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] direction : directions) {
                        int newRow = i + direction[0];
                        int newColumn = j + direction[1];
                        if (newRow >= 0 && newColumn >= 0 && newRow < N && newColumn < N) {   // valid move: the new move position should still be on the board
                            dp[currentMove][i][j] += dp[currentMove - 1][newRow][newColumn] / 8.0;
                        }
                    }
                }
            }
        }

        double totalProbability = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                totalProbability += dp[K][i][j];
            }
        }
        return totalProbability;
    }

    // core logic: same as above but we are optimizing for space here, as we only need k-1th level probability values when calculating the probabilities at kth level. uses a temp dp array to calculate the probabilities at kth level and then assign it back to the actual dp array so it can be used in next to be computed levels.
    // TC: O(k * n^2), SC: O(n^2)
    private static double knightProbability2(int N, int K, int r, int c) {
        int[][] directions = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};   // all the possible 8 directions a knight can move from a given position
        double[][] dp = new double[N][N];    // dp array to remember the probabilities
        dp[r][c] = 1.0;

        for (int currentMove = 1; currentMove <= K; currentMove++) {
            double[][] tempDp = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] direction : directions) {
                        int newRow = i + direction[0];
                        int newColumn = j + direction[1];
                        if (newRow >= 0 && newColumn >= 0 && newRow < N && newColumn < N) {   // valid move: the new move position should still be on the board
                            tempDp[i][j] += dp[newRow][newColumn] / 8.0;
                        }
                    }
                }
            }
            dp = tempDp;
        }

        double totalProbability = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                totalProbability += dp[i][j];
            }
        }
        return totalProbability;
    }

    public static void main(String[] args) {
        assertEquals(knightProbability(3, 2, 0, 0), 0.0625);
        assertEquals(knightProbability2(3, 2, 0, 0), 0.0625);
    }
}