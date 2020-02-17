package med;

import java.util.Random;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * Shuffle a set of numbers without duplicates.
 * </p>
 */
public class _384_Shuffle_Array {
    private Random random;
    private int[] nums;
    private int[] original;

    public _384_Shuffle_Array(int[] nums) {
        original = nums.clone();
        this.nums = nums;
        random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return original;
    }

    /**
     * Returns a random shuffling of the array.
     * This swap ensures randomness in 2 ways:
     * That each element in the array from [0, i] has an equal chance of being the ith element.
     * That the original ith element has an equal chance of being anywhere in the array in [0, i]
     */
    public int[] shuffle() {
        for (int i = 1; i < nums.length; i++) {
            int rand = random.nextInt(i + 1);  // get the random number from [0, i+1). i+1 as last element is exclusive in random
            swap(nums, i, rand);
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}