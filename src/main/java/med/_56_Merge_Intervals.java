package med;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/4/19.
 * <p>
 * Given a collection of intervals, merge all overlapping intervals.
 * </p>
 */
public class _56_Merge_Intervals {
    // core logic: insert to priority queue in the increasing order (sorted) of start times
    // start iterating through priority queue and when the merge condition matches (prev end > current start, which means we can merge intervals), merge the prev and current intervals and add it to the result, else add the new interval as is to the result
    // similar logic as 252 and 253 (meeting rooms)
    // TC: O(nlogn)
    private static int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);   // ascending order of start times
        for (int[] interval : intervals) {
            queue.add(interval);
        }

        res.add(queue.poll());
        while (queue.size() != 0) {
            int[] current = queue.poll();
            int[] prev = res.get(res.size() - 1);

            if (prev[1] >= current[0]) {    // this means merge current and prev interval
                res.remove(res.get(res.size() - 1));
                res.add(new int[]{prev[0], Math.max(prev[1], current[1])});
            } else {
                res.add(new int[]{current[0], current[1]});   // put the current interval as it is
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        assertEquals(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}), new int[][]{{1, 6}, {8, 10}, {15, 18}});
        assertEquals(merge(new int[][]{{1, 4}, {4, 5}}), new int[][]{{1, 5}});
    }
}