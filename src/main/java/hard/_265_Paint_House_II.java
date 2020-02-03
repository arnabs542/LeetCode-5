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
    // core logic: dp approach similar to paint house 1
    // TC: O(n), SC: O(1): in place. modifying the existing input
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

                if (currMinIndex1 < 0 || costs[i][j] < costs[i][currMinIndex1]) {
                    currMinIndex2 = currMinIndex1;
                    currMinIndex1 = j;
                } else if (currMinIndex2 < 0 || costs[i][j] < costs[i][currMinIndex2]) {
                    currMinIndex2 = j;
                }
            }
        }
        return costs[costs.length - 1][currMinIndex1];
    }

    // DP approach: similar to above approach: but doesn't modify the existing input
    // classic greedy approach
    // TC: O(n), SC: O(1)
    private static int minCostIIExtraSpace(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int prevColorIndex = -1, prevMin1 = 0, prevMin2 = 0;  // prev color index indicates the min color index of the prev house, prevMin and prevMin2 indicates first and second min costs of the prev house

        for (int i = 0; i < costs.length; i++) {
            int currColorIndex = 0, currMin1 = Integer.MAX_VALUE, currMin2 = Integer.MAX_VALUE;   // curr color index indicates the min color index of the curr house
            for (int j = 0; j < costs[0].length; j++) {
                int cost = costs[i][j] + (j == prevColorIndex ? prevMin2 : prevMin1);

                if (cost < currMin1) {
                    currMin2 = currMin1;
                    currMin1 = cost;
                    currColorIndex = j;
                } else if (cost < currMin2) {
                    currMin2 = cost;
                }
            }
            prevMin1 = currMin1;
            prevMin2 = currMin2;
            prevColorIndex = currColorIndex;
        }
        return prevMin1;
    }

    public static void main(String[] args) {
        assertEquals(minCostII(new int[][]{{1, 5, 3}, {2, 9, 4}}), 5);
        assertEquals(minCostIIExtraSpace(new int[][]{{1, 5, 3}, {2, 9, 4}}), 5);
    }
}