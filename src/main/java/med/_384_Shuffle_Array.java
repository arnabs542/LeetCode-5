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

    // we basically chose a random index between i and nums.length and swap the elements in i and random index and continue this process
    // this swap ensures us that we don't pick the same element again as i increments in the next cycle and the same index is not chosen again, which guarantees us the same probability for all the elements in the array
    public int[] shuffle2() {
        for (int i = 0; i < nums.length; i++) {
            int rand = randInRange(i, nums.length);
            swap(nums, i, rand);
        }
        return nums;
    }

    private int randInRange(int min, int max) {
        return random.nextInt(max - min) + min;   // min here is the offset (ith index). this ensures we don't want to pick the same element again in the process
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}