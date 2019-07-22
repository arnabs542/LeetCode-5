package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udaythota on 6/24/19.
 * <p>
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * </p>
 */
public class _118_Pascals_Triangle {

    // core logic: just iterate through number of rows and generate the given pattern. make sure to handle the '1' for first and last elements
    private static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }

        for (int i = 0; i < numRows; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    rowList.add(1);
                } else {
                    rowList.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(rowList);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generate(5).toArray()));
        System.out.println(Arrays.deepToString(generate(7).toArray()));
    }
}
