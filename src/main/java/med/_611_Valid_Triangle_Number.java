package med;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/1/20.
 * <p>
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * </p>
 */
public class _611_Valid_Triangle_Number {
    // core logic: similar to 3 sum. sort the array and make sure to cover all the combinations. for triangle, sum of any 2 sides should be greater than 3rd side.
    // NOTE: the only thing to notice is to place the left and right pointers in the beginning rather than the end
    // TC: O(n^2)
    private static int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0;

        for (int i = 2; i < nums.length; i++) {   // be careful of the left and right pointers, they are in the beginning rather than at the end
            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    result += (right - left);   // as numbers between these also satisfy the property
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(triangleNumber(new int[]{2, 2, 3, 4}), 3);
    }
}