package easy;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 7/8/19.
 * <p>
 * Write an algorithm to determine if a number is "happy".
 * TODO: this can be solved using O(1) space using an algorithm similar to cycle detection in a loop. figure it out later
 * </p>
 */
public class _202_HappyNumber {

    // core logic: compute the sum from each number and save it to the set. repeat this process till the set is repeated (return false) or the sum reaches 1 (return true)
    // see the below approach for slightly optimized version
    private static boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        int sum = n;
        while (sum != 1) {
            sum = 0;
            while (n != 0) {
                int rem = n % 10;
                sum += rem * rem;
                n = n / 10;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
                n = sum;
            }
        }
        return true;
    }

    // core logic: similar to above approach, but with some optimization
    // when you reach the sum to 1, stop the process and return true
    private static boolean isHappy2(int n) {
        if (n <= 0) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        int sum = n;
        while (set.add(sum)) {   // this will return false when set encounters a duplicate number: return false in that case
            sum = 0;
            while (n != 0) {
                int rem = n % 10;
                sum += rem * rem;
                n = n / 10;
            }

            if (sum == 1) {
                return true;
            }
            n = sum;
        }
        return false;
    }

    public static void main(String[] args) {
        assertTrue(isHappy(19));
        assertTrue(isHappy2(19));
    }
}