package easy;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udaythota on 4/9/20.
 * <p>
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
 * </p>
 */
public class _1213_Intersection_Sorted_Arrays {
    // core logic: use 3 pointers. at any point, when all of them point to the same value, add to the result. if not, move the smaller number of the 3 to the right
    // TC: O(n) - where n is length of the longest array
    private static List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int p1 = 0, p2 = 0, p3 = 0;
        List<Integer> result = new ArrayList<>();
        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {
            int min = Math.min(Math.min(arr1[p1], arr2[p2]), arr3[p3]);
            if (arr1[p1] == min && arr2[p2] == min && arr3[p3] == min) {
                result.add(min);
            }
            if (arr1[p1] == min) {
                p1++;
            }
            if (arr2[p2] == min) {
                p2++;
            }
            if (arr3[p3] == min) {
                p3++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Assert.assertEquals(arraysIntersection(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 5, 7, 9}, new int[]{1, 3, 4, 5, 8}), Arrays.asList(1, 5));
    }
}
