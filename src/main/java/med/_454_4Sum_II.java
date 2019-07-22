package med;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/21/19.
 * <p>
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * </p>
 */
public class _454_4Sum_II {

    // core logic: if a + b + c + d = 0 exists, a + b = -(c + d)
    // step 1: take any 2 arrays, for all the combinations of 2 numbers, calculate the sum and save it to map
    // step 2: take the remaining 2 arrays, for all the combinations of 2 numbers, calculate the sum and see if the negative of sum exists in the map. if yes, get the value of sum and add it to result
    // TC: O(n^2), SC: O(n)
    private static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer num1 : A) {
            for (Integer num2 : B) {
                int sum = num1 + num2;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (Integer num1 : C) {
            for (Integer num2 : D) {
                int sum = -(num1 + num2);    // negative of sum - to look up in map
                if (map.containsKey(sum)) {
                    count += map.get(sum);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assertEquals(fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}), 2);
    }
}