package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/14/19.
 * <p>
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * </p>
 */
public class _287_Find_Duplicate_Number {
    // core logic: similar to finding a loop in linked list
    private static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;
        while (slow != fast) {  // once you enter the loop, find the starting node of the loop
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        assertEquals(findDuplicate(new int[]{1, 3, 4, 2, 2}), 2);
        assertEquals(findDuplicate(new int[]{3, 1, 3, 4, 2}), 3);
        assertEquals(findDuplicate(new int[]{2, 4, 6, 5, 3, 5, 1}), 5);
    }
}