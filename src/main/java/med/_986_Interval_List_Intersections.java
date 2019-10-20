package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udaythota on 8/18/19.
 * <p>
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * Return the intersection of these two interval lists.
 * </p>
 */
public class _986_Interval_List_Intersections {
    // core logic: merge interval appropriately as needed
    // 2 point to take care: a) after calculating the startMax and endMin, make sure to compare them if they are valid before adding to the result, b) the array with lower second element should be incremented as we don't need that interval anymore
    private static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            int startMax = Math.max(A[i][0], B[j][0]);
            int endMin = Math.min(A[i][1], B[j][1]);

            if (startMax <= endMin) {    // make sure the start and end intervals are valid (start < end)
                result.add(new int[]{startMax, endMin});
            }

            if (A[i][1] < B[j][1]) {    // discard the interval which is not needed
                i++;
            } else {
                j++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(intervalIntersection(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}}, new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}})));
    }
}