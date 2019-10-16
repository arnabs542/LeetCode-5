package random_practice.microsoftassessment;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/15/19.
 * Find the maximum sum of 2 numbers with equal digit sum
 */
public class Numbers_With_Equal_Digit_Sum {
    // the core logic is using the hashmap and max variable appropriately
    // TC: O(nlogk) -> as for each and every input we also calculate its sum (which takes logarithmic complexity)
    // SC: O(n)
    private static int findMaxSum(int[] input) {
        if (input == null || input.length <= 1) {
            return -1;
        }
        int max = -1;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : input) {
            int sum = findSum(num);
            if (map.containsKey(sum)) {
                max = Math.max(max, map.get(sum) + num); // update the max when you encounter the new max
                if (num > map.get(sum)) {  // update the value in the map only for the numbers greater than the previous number. so by end of the loop only the numbers with the highest digit sums are evaluated
                    map.put(sum, num);
                }
            } else {
                map.put(sum, num);  // seen the sum for the first time
            }
        }
        return max;
    }

    // given a number find the sum of its digits
    private static int findSum(int number) {
        int sum = 0;
        while (number != 0) {
            sum += (number % 10);
            number = number / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        assertEquals(findMaxSum(new int[]{51, 71, 17, 42}), 93);
        assertEquals(findMaxSum(new int[]{42, 33, 60}), 102);
        assertEquals(findMaxSum(new int[]{51, 32, 43}), -1);
    }
}
