package hard;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/15/20.
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * </p>
 */
public class _4_Median_Sorted_Arrays {
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // make sure we always perform binary search on smaller sized array in order to minimize time complexity
        // TC: O(log(min(m,n))) -> where m is the nums1 length and n is the nums2 length
        // see this if you can't make more sense: https://www.youtube.com/watch?v=LPFhl65R7ww
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);  // always perform the binary search on array with smaller size
        }
        int length1 = nums1.length;
        int length2 = nums2.length;

        int low = 0;
        int high = length1;

        while (low <= high) {
            int partitionX = (low + high) / 2;   // we are doing binary search on first array (X)
            int partitionY = (length1 + length2 + 1) / 2 - partitionX;  // sum of elements in leftX and leftY should be (length1 + length2) / 2

            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];   // assume max of leftX as Integer.Min when there are are no elements in the first half
            int minRightX = partitionX == length1 ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = partitionY == length2 ? Integer.MAX_VALUE : nums2[partitionY];   // assume min of rightY as Integer.Min when there are are no elements in the first half

            if (maxLeftX <= minRightY && minRightX >= maxLeftY) {   // we encountered the right partitions in both the arrays, so go ahead and calculate the median
                if ((length1 + length2) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;   // even number of elements in num1 + num2
                } else {
                    return Math.max(maxLeftX, maxLeftY);   // odd number of elements in num1 + num2
                }
            } else if (maxLeftX > minRightY) {   // leftX is too high, so decrease it
                high = partitionX - 1;
            } else {                            // leftX is too small, so increase it
                low = partitionX + 1;
            }
        }
        return -1;  // this ideally never occurs if both arrays are sorted. so just return -1 if reached here
    }

    public static void main(String[] args) {
        assertEquals(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), 2.0);
        assertEquals(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 2.5);
        assertEquals(findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10}), 5.5);
        assertEquals(findMedianSortedArrays(new int[]{2, 8, 14, 20}, new int[]{1, 3, 6, 9, 12}), 8.0);
    }
}