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

    public static void main(String[] args) {
        assertEquals(knightProbability(3, 2, 0, 0), 0.0625);
    }
}