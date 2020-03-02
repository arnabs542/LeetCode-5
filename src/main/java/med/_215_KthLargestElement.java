package med;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/11/19.
 * <p>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * </p>
 */
public class _215_KthLargestElement {
    // core logic: just sort the array and return the index of kth largest element
    // TC: O(nlogn)
    private static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // core logic: use a priority queue which always maintains k largest elements till that point
    private static int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);

            if (priorityQueue.size() > k) {   // when size of the queue is greater than k, remove the smallest available element in the queue
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2), 5);
        assertEquals(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);

        // test method: 2
        assertEquals(findKthLargest2(new int[]{3, 2, 1, 5, 6, 4}, 2), 5);
        assertEquals(findKthLargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
        assertEquals(findKthLargest2(new int[]{3, 2, 4, 5, 6, 1, 8}, 2), 6);
    }
}
