package random_practice;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 10/13/19.
 * Simple binary search for practice
 */
public class BinarySearch {
    // simple iterative approach
    private static boolean binarySearch(int[] input, int target) {
        if (input == null || input.length == 0) {
            return false;
        }
        Arrays.sort(input);
        int start = 0;
        int end = input.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (input[mid] == target) {
                return true;
            } else if (input[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    // simple recursive approach
    private static boolean binarySearchRecursive(int[] input, int target) {
        if (input == null || input.length == 0) {
            return false;
        }
        Arrays.sort(input);
        return helper(input, 0, input.length - 1, target);
    }

    private static boolean helper(int[] input, int start, int end, int target) {
        if (start <= end) {
            int mid = start + (end - start) / 2;
            if (input[mid] == target) {
                return true;
            } else if (input[mid] < target) {
                return helper(input, mid + 1, end, target);
            } else {
                return helper(input, start, mid - 1, target);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assertTrue(binarySearch(new int[]{1, 4, 3, 9, 6, 5, 10, 15, 20, 11, 14}, 6));
        assertFalse(binarySearch(new int[]{1, 4, 3, 9, 6, 5, 10, 15, 20, 11, 14}, 19));

        assertTrue(binarySearchRecursive(new int[]{1, 4, 3, 9, 6, 5, 10, 15, 20, 11, 14}, 6));
        assertFalse(binarySearchRecursive(new int[]{1, 4, 3, 9, 6, 5, 10, 15, 20, 11, 14}, 19));
    }
}
