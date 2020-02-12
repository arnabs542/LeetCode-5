package hard;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/12/20.
 * <p>
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * </p>
 */
public class _149_Max_Points_On_Line {
    // core logic: slopes for all the points on a straight line would be same
    // so logic is to calculate the slope from every point to all other points, keep their counts in a map (slope -> count of points with same slope) and calculate max points at each step
    // TC: O(n^2)
    // FIXME: this doesn't pass for one corner case (very large number inputs exceeding double precision limit) in LC, but this approach is still good, so good to follow. later fix it. may be we need to calculate gcd for larger numbers. investigate later.
    private static int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }

        int result = 2;
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> map = new HashMap<>();
            int dups = 0, verticalLines = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int[] pointX = points[i];
                int[] pointY = points[j];

                if (pointX[0] == pointY[0] && pointX[1] == pointY[1]) {
                    dups++;
                } else if (pointX[0] == pointY[0]) {
                    verticalLines++;   // don't calculate slope using formula as x axis is same and slope is infinity
                } else if (pointX[1] == pointY[1]) {
                    map.put(0.0, map.getOrDefault(0.0, 0) + 1);  // slope is zero for points with same 'Y' coordinates
                } else {
                    double slope = (double) (pointY[1] - pointX[1]) / (pointY[0] - pointX[0]);  // calculate slope for rest of the cases
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                }
            }
            for (Map.Entry<Double, Integer> entry : map.entrySet()) {  // calculate max possible slope with this ith point
                max = Math.max(max, entry.getValue() + dups + 1);
            }
            max = Math.max(max, verticalLines + dups + 1);
            result = Math.max(result, max);   // update the result when needed
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}), 3);
    }
}