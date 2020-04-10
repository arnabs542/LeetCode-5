package med;

import java.util.Random;

/**
 * Created by udaythota on 4/10/20.
 * <p>
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * </p>
 */
public class _398_Random_Pick_Index {

    private int[] nums;
    private Random random;

    private _398_Random_Pick_Index(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    // reservoir sampling: whenever we encounter a target, we increment count (which means that many times we encountered the target till that point) and randomly pick an index between 0 and that number. if that picked index = 0, we select i, else we keep result as is.
    // randomly select an int from 0 to the nums of target. If x equals 0, set the res as the current index. The probability is always 1/nums for the latest appeared number. For example, 1 for 1st num, 1/2 for 2nd num, 1/3 for 3nd num (1/2 * 2/3 for each of the first 2 nums).
    // NOTE: better understood running the example
    // TC: O(n), SC: O(1)
    private int pick(int target) {
        int count = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int x = random.nextInt(++count);
                result = x == 0 ? i : result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 3};
        _398_Random_Pick_Index random_pick_index = new _398_Random_Pick_Index(nums);
        System.out.println(random_pick_index.pick(3));
        System.out.println(random_pick_index.pick(1));
    }
}
