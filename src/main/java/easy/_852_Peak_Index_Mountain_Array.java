package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * Let's call an array A a mountain if the following properties hold:
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 * </p>
 */
public class _852_Peak_Index_Mountain_Array {
    // core logic: find peak of the element using the binary search: depending on the mid, mid-1 and mid+1 elements, search the appropriate half accordingly
    // NOTE: this same function is also used in LC 1095: find target element in mountain array
    // TC: O(logN)
    private static int peakIndexInMountainArray(int[] mountainArray) {
        int low = 0, high = mountainArray.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int midElement = mountainArray[mid];
            int midLeft = mountainArray[mid - 1];
            int midRight = mountainArray[mid + 1];

            if (midElement > midLeft && midElement > midRight) {
                return mid;
            } else if (midElement < midRight) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assertEquals(peakIndexInMountainArray(new int[]{0, 1, 0}), 1);
        assertEquals(peakIndexInMountainArray(new int[]{0, 2, 1, 0}), 1);
    }
}