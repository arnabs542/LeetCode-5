package random_practice.microsoftassessment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/20/19.
 * <p>
 * Write a function that, given an array A of N integers, returns the lagest integer K > 0 such that both values K and -K exisit in array A. If there is no such integer, the function should return 0.
 * </p>
 */
public class Largest_Integer {
    private static int largestInteger(int[] input) {
        if (input == null || input.length <= 1) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : input) {
            set.add(num);
        }

        int max = 0;
        for (int num : input) {
            if (num > 0) {
                if (set.contains(-num) && num > max) {
                    max = num;
                }
            }
        }
        return max;
    }

    private static int largestInteger2(int[] input) {
        if (input == null || input.length <= 1) {
            return 0;
        }

        int max = 0;
        Arrays.sort(input);
        int i = 0;
        int j = input.length - 1;

        while (i < j) {
            int sum = input[i] + input[j];
            if (sum == 0) {
                max = Math.max(max, Math.max(input[i], input[j]));
                i++;
                j--;
            } else if (sum < 0) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        assertEquals(largestInteger(new int[]{3, 2, -2, 5, -3}), 3);
        assertEquals(largestInteger(new int[]{1, 2, 3, -4}), 0);

        assertEquals(largestInteger2(new int[]{3, 2, -2, 5, -3}), 3);
        assertEquals(largestInteger2(new int[]{1, 2, 3, -4}), 0);
    }
}