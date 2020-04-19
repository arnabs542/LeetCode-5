package med;

import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/19/20.
 * <p>
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * </p>
 */
public class _658_Find_K_Closest_Elements {
    // brute force: simple priority queue (min heap) solution: add elements to the queue based on its absolute distance and remove the first K elements from heap and add to the result
    // see the below alternate solution for the optimal approach
    // TC: O(nlogn), SC: O(n)
    private static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (x - a != b - x) ? Math.abs(a - x) - Math.abs(b - x) : a - b);  // when the absolute distances are equal, add the lowest element (to the left) first
        for (int num : arr) {
            queue.offer(num);
        }
        while (k-- > 0) {
            result.add(queue.poll());
        }
        Collections.sort(result);
        return result;
    }

    // core logic: binary search: we try to find the left most index which can be closest to x and the next k elements from that index are the k closest elements
    // keep adjusting the start and end pointers based on the absolute distance of left and right elements from x
    // TC: O(logN + k) -> +k because of the for loop
    private static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int start = 0, end = arr.length - k;   // important: end pointer defines the max left pointer the index could start from (eg: if all the closest elements are to left then the interval is: [0,arr.length - k). if all the elements are to the right, then the interval is: [arr.length - k, arr.length))
        while (start < end) {
            int mid = (start + end) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {   // compare the elements to left and right and adjust the start and end pointers accordingly
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = start; i < start + k; i++) {  // k elements from the start index - add to the result
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        // solution 1
        Assert.assertEquals(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3), Arrays.asList(1, 2, 3, 4));
        Assert.assertEquals(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1), Arrays.asList(1, 2, 3, 4));
        Assert.assertEquals(findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 1, 9), Arrays.asList(10));

        // solution 2
        Assert.assertEquals(findClosestElements2(new int[]{1, 2, 3, 4, 5}, 4, 3), Arrays.asList(1, 2, 3, 4));
        Assert.assertEquals(findClosestElements2(new int[]{1, 2, 3, 4, 5}, 4, -1), Arrays.asList(1, 2, 3, 4));
        Assert.assertEquals(findClosestElements2(new int[]{1, 1, 1, 10, 10, 10}, 1, 9), Arrays.asList(10));
    }
}
