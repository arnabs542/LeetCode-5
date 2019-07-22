package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z002jsf on 7/1/19.
 * <p>
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 * </p>
 */
public class _119_PascalsTriangle_II {

    // core logic: for every iteration, start calculating from j-1th to 1st element (as jth and 0th element are always 1). get the results from previous row and add appropriately
    // instead of calculating from start, start calculating from end
    // SC: O(k) -> where k represents the row index
    private static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            result.add(1);  // as the last row is always 1
            for (int j = i - 1; j > 0; j--) {
                result.set(j, result.get(j - 1) + result.get(j));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getRow(3));
        System.out.println(getRow(4));
        System.out.println(getRow(5));
    }
}
