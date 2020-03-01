package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/5/19.
 * <p>
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * </p>
 */
public class _120_Triangle_MinPath_DP {
    // DP Tabulation: top down approach
    // core logic: iterate through all the elements in the grid and for every element calculate the min path sum. return element corresponding to min sum from the last row
    private static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {   // for 1st elements of all the rows
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j));
                } else if (j == triangle.get(i).size() - 1) {  // for last elements of all the rows
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1));
                } else {  // for rest of the elements
                    triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i - 1).get(j), triangle.get(i - 1).get(j - 1)));
                }
            }
        }
        return Collections.min(triangle.get(triangle.size() - 1));
    }

    // DP: bottom up approach
    // core logic: start from bottom row and keep calculating the min sums for top rows (with the help of bottom row values)
    private static int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int size = triangle.size();
        int[] dp = new int[size];

        // initialize the dp (result) array with last row values
        for (int j = 0; j < size; j++) {
            dp[j] = triangle.get(size - 1).get(j);
        }
        // bottom up comparison: start from last but one row
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        // test case: 1
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(2));
        input.add(Arrays.asList(3, 4));
        input.add(Arrays.asList(6, 5, 7));
        input.add(Arrays.asList(4, 1, 8, 3));
        assertEquals(minimumTotal(input), 11);

        // as the first input is being modified
        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(Arrays.asList(2));
        input2.add(Arrays.asList(3, 4));
        input2.add(Arrays.asList(6, 5, 7));
        input2.add(Arrays.asList(4, 1, 8, 3));
        assertEquals(minimumTotal2(input2), 11);

        // test case: 2
        List<List<Integer>> input3 = new ArrayList<>();
        input3.add(Arrays.asList(-1));
        input3.add(Arrays.asList(2, 3));
        input3.add(Arrays.asList(1, -1, 3));
        assertEquals(minimumTotal(input3), 0);

        List<List<Integer>> input4 = new ArrayList<>();
        input4.add(Arrays.asList(-1));
        input4.add(Arrays.asList(2, 3));
        input4.add(Arrays.asList(1, -1, 3));
        assertEquals(minimumTotal(input4), 0);
        assertEquals(minimumTotal2(input4), 0);
    }
}
