package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/11/19.
 * <p>
 * Given two arrays, write a function to compute their intersection.
 * </p>
 */
public class _349_Intersection_2_Arrays {
    // use 2 hash sets to save the common elements
    // TC: O(n)
    private static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }

        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                set2.add(num);
            }
        }

        int i = 0;
        int[] res = new int[set2.size()];
        for (int num : set2) {
            res[i++] = num;
        }
        return res;
    }

    // sort the array and compare the equal elements as needed
    // TC: O(nlogn)
    private static int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[set.size()];
        int k = 0;
        for (int num : set) {
            res[k++] = num;
        }
        return res;
    }

    public static void main(String[] args) {
        assertEquals(intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}), new int[]{2});
        assertEquals(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}), new int[]{4, 9});

        assertEquals(intersection2(new int[]{1, 2, 2, 1}, new int[]{2, 2}), new int[]{2});
        assertEquals(intersection2(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}), new int[]{4, 9});
    }
}