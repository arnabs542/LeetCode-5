package med;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by udaythota on 4/18/20.
 * <p>
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration, return the earliest time slot that works for both of them and is of duration duration.
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * </p>
 */
public class _1229_Meeting_Scheduler {
    // similar to merge intervals. first sort both the arrays and keep checking the duration for all the common intervals and whenever it matches the given duration, return it, else keep navigating forward num1 or nums2 array accordingly
    // TC: O(nlogn) - where n is the length of slot1 + slots2
    private static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(slots1, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(slots2, (o1, o2) -> o1[0] - o2[0]);
        int i = 0, j = 0;

        while (i < slots1.length && j < slots2.length) {
            int commonStart = Math.max(slots1[i][0], slots2[j][0]);
            int commonEnd = Math.min(slots1[i][1], slots2[j][1]);

            if (commonStart + duration <= commonEnd) {
                result.add(commonStart);
                result.add(commonStart + duration);
                break;  // we encountered our first duration common to both the slots, so break the loop and return the result
            } else if (slots1[i][0] < slots2[j][0]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // priority queue approach: add all slots to the queue based on the start times. poll one by one and return the slot where it matches the given duration
    // as the problem states that the slots doesn't intersect each other, we can add all the slots (from 2 arrays) to the same queue and keep them processing together
    // TC: O(nlogn) -> where n is the length of slot1 + slots2
    private static List<Integer> minAvailableDuration2(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);     // min heap
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) {
                queue.offer(slot);
            }
        }
        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) {
                queue.offer(slot);
            }
        }

        int[] prev = queue.poll();
        while (!queue.isEmpty()) {   // keep comparing current and prev slots till we encounter the slots that matches the given duration
            int[] current = queue.poll();
            if (current[0] + duration <= prev[1]) {    // this is because the queue is sorted by start times. so compare the current + duration with prev (as curr[0] ALWAYS >= prev[0])
                result.add(current[0]);
                result.add(current[0] + duration);
                break;   // break the loop when we encounter the given condition
            }
            prev = current;
        }
        return result;
    }

    public static void main(String[] args) {
        // solution 1
        Assert.assertEquals(minAvailableDuration(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 8), Arrays.asList(60, 68));
        Assert.assertEquals(minAvailableDuration(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 12), Arrays.asList());

        // solution 2
        Assert.assertEquals(minAvailableDuration2(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 8), Arrays.asList(60, 68));
        Assert.assertEquals(minAvailableDuration2(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 12), Arrays.asList());
    }
}
