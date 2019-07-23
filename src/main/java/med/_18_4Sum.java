package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/23/19.
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 */
public class _18_4Sum {

    // core logic: same as 3 sum except that add 1 more outer loop here. make sure to avoid duplicates carefully
    private static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        int length = nums.length;

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {    // avoid duplicates
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {   // avoid duplicates
                    continue;
                }

                int low = j + 1;
                int high = length - 1;
                int remainingTarget = target - nums[j] - nums[i];

                while (low < high) {
                    if (nums[low] + nums[high] == remainingTarget) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[low]);
                        temp.add(nums[high]);
                        result.add(temp);

                        while (low < high && nums[low] == nums[low + 1]) {    // avoid duplicates
                            low++;
                        }
                        low++;   // once you move to the last duplicate element, increment one more to get a different element

                        while (high > low && nums[high] == nums[high - 1]) {   // avoid duplicates
                            high--;
                        }
                        high--;  // once you move to the last duplicate element, decrement one more to get a different element
                    } else if (nums[low] + nums[high] < remainingTarget) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // test case: 1
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(-2, -1, 1, 2));
        list.add(Arrays.asList(-2, 0, 0, 2));
        list.add(Arrays.asList(-1, 0, 0, 1));
        assertEquals(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0), list);

        // test case: 2
        List<List<Integer>> list2 = new ArrayList<>();
        list2.add(Arrays.asList(-1, 0, 1, 2));
        assertEquals(fourSum(new int[]{2, 1, 0, -1}, 2), list2);

        // test case: 2
        List<List<Integer>> list3 = new ArrayList<>();
        list3.add(Arrays.asList(-5, -4, -1, 1));
        list3.add(Arrays.asList(-5, -4, 0, 0));
        list3.add(Arrays.asList(-5, -2, -2, 0));
        list3.add(Arrays.asList(-4, -2, -2, -1));
        assertEquals(fourSum(new int[]{-1, 0, -5, -2, -2, -4, 0, 1, -2}, -9), list3);
    }
}