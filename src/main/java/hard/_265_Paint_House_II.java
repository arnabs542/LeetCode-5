package hard;

import static org.testng.Assert.assertEquals;

/**
 * Created by z002jsf on 1/30/20.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 * </p>
 */
public class _265_Paint_House_II {
    private static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int currMinIndex1 = -1, currMinIndex2 = -1;

        for (int i = 0; i < costs.length; i++) {
            int lastMinIndex1 = currMinIndex1, lastMinIndex2 = currMinIndex2;
            currMinIndex1 = -1;
            currMinIndex2 = -1;

            for (int j = 0; j < costs[0].length; j++) {
                if (j != lastMinIndex1) {
                    costs[i][j] += lastMinIndex1 < 0 ? 0 : costs[i - 1][lastMinIndex1];
                } else {
                    costs[i][j] += lastMinIndex2 < 0 ? 0 : costs[i - 1][lastMinIndex2];
                }

                if (lastMinIndex1 < 0 || costs[i][j] < costs[i][lastMinIndex1]) {
                    lastMinIndex2 = lastMinIndex1;
                    lastMinIndex1 = j;
                } else if (lastMinIndex2 < 0 || costs[i][j] < costs[i][lastMinIndex2]) {
                    lastMinIndex2 = j;
                }
            }
        }
        return costs[costs.length - 1][currMinIndex1];
    }

    public static void main(String[] args) {
        assertEquals(minCostII(new int[][]{{1, 5, 3}, {2, 9, 4}}), 5);
    }
}