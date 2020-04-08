package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 4/7/20.
 * <p>
 * Given an integer array arr, count element x such that x + 1 is also in arr.
 * If there're duplicates in arr, count them seperately.
 * </p>
 */
public class _Counting_Elements_30Day_Challenge {
    private static int countElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        int count = 0;
        for (int num : list) {
            if (list.contains(num + 1)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assertEquals(countElements(new int[]{1, 2, 3}), 2);
        assertEquals(countElements(new int[]{1, 1, 3, 3, 5, 5, 7, 7}), 0);
        assertEquals(countElements(new int[]{1, 3, 2, 3, 5, 0}), 3);
        assertEquals(countElements(new int[]{1, 1, 2, 2}), 2);
    }
}
