package easy;

import java.util.Arrays;

/**
 * Created by udaythota on 7/2/19.
 * <p>
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * </p>
 */
public class _88_MergeSortedArray {
    // core logic: start filling the nums1 array from right to left (descending order)
    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        while (j >= 0) {   // if the nums2 array is not empty (j>=0), append those elements to the beginning of nums1 array (if the nums1 array is not empty: i>=0, no action needed as the nums1 array is already sorted)
            nums1[k--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        merge(nums1, 5, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}