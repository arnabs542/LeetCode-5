package easy;

import java.util.Arrays;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/6/19.
 * <p>
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * </p>
 */
public class _169_MajorityElement {

    // core logic: as the majority element always exists, sort the array and take the n/2 the element
    // TC: O(nlogn), SC: O(1)
    private static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // core logic: keep a count of all the elements and whenever you encounter an element whose count is > nums.length / 2, return that element (as it is given that there will be only one majority element, we can assume this)
    // TC: O(n), SC: O(n)
    private static int majorityElement2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }

    // boyer-moore algorithm
    // TC: O(n), SC: O(1)
    // core logic: see https://leetcode.com/problems/majority-element/solution/ for the algorithm details
    // as it is guaranteed that there will ALWAYS be ONLY ONE majority element in the sequence, this algorithm works
    // if it says majority element may or may not exist, the last candidate we get may not be the majority element. to confirm that, iterate through the array again and get the count of that candidate and if its count > nums.length / 2, return it, else return -1
    private static int majorityElement3(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else {
                if (candidate == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        // test case: 1
        assertEquals(majorityElement(new int[]{3, 2, 3}), 3);
        assertEquals(majorityElement2(new int[]{3, 2, 3}), 3);
        assertEquals(majorityElement3(new int[]{3, 2, 3}), 3);

        // test case: 2
        assertEquals(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}), 2);
        assertEquals(majorityElement2(new int[]{2, 2, 1, 1, 1, 2, 2}), 2);
        assertEquals(majorityElement3(new int[]{3, 2, 3}), 3);
    }
}
