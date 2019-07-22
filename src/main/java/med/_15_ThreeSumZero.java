package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by udaythota on 1/23/19.
 * <p>
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * The solution set must not contain duplicate triplets.
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * </p>
 */
public class _15_ThreeSumZero {

    // NOTE: the trick is to avoid the duplicates by incrementing and decrementing the pointers as necessary
    private static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return resultList;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;  // don't repeat the loop for the same number (in order to avoid duplicates)

            int firstPointer = i + 1;
            int endPointer = nums.length - 1;

            while (firstPointer < endPointer) {
                if (nums[i] + nums[firstPointer] + nums[endPointer] == 0) {
                    resultList.add(new ArrayList<>(Arrays.asList(nums[i], nums[firstPointer], nums[endPointer])));
                    firstPointer++;
                    endPointer--;
                    while (firstPointer < endPointer && nums[firstPointer] == nums[firstPointer - 1]) {   // increment the first pointer till it encounters a different element (in order to avoid duplicates)
                        firstPointer++;
                    }
                    while (endPointer > firstPointer && nums[endPointer] == nums[endPointer + 1]) {  // decrement the end pointer till it encounters a different element (in order to avoid duplicates)
                        endPointer--;
                    }
                } else if (nums[i] + nums[firstPointer] + nums[endPointer] < 0) {
                    firstPointer++;
                    while (firstPointer < endPointer && nums[firstPointer] == nums[firstPointer - 1]) {
                        firstPointer++;
                    }
                } else {
                    endPointer--;
                    while (endPointer > firstPointer && nums[endPointer] == nums[endPointer + 1]) {
                        endPointer--;
                    }
                }
            }
        }
        return resultList;
    }


    public static void main(String[] args) {
        List<List<Integer>> resultList = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(resultList);

        List<List<Integer>> resultList1 = threeSum(new int[]{0, 0, 0, 0});
        System.out.println(resultList1);
    }
}
