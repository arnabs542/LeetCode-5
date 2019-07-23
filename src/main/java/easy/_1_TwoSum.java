package easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by udaythota on 4/16/19.
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 * </p>
 */
public class _1_TwoSum {
    private static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> storeNumMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (storeNumMap.containsKey(target - nums[i])) {
                return new int[]{i, storeNumMap.get(target - nums[i])};
            } else {
                storeNumMap.put(nums[i], i);
            }
        }
        return null;
    }

    private static int[] twoSumAlternate(int[] nums, int target) {
        int[] resultArray = new int[2];

        if (nums == null || nums.length == 0) {
            return resultArray;
        }

        HashMap<Integer, Integer> storeNumMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (storeNumMap.containsKey(target - nums[i])) {
                resultArray[0] = i;
                resultArray[1] = storeNumMap.get(target - nums[i]);
            } else {
                storeNumMap.put(nums[i], i);
            }
        }
        return resultArray;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSumAlternate(new int[]{2, 7, 11, 15}, 9)));
    }
}
