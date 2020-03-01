package med;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/4/19.
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * </p>
 */
public class _253_MeetingRooms_II {
    // core logic: sort all the start times. instantiate a mean heap (which stores end times).
    // when iterating, see if the start time of the new interval is greater than or equal to minimum of the end time available in heap :
    // if yes, this means we have a free room and we can use that. if no, we need to use a new room
    // in the end, the size of heap is the number of min rooms used
    // similar logic as 252 and 253 (meeting rooms)
    private static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // sort the start times using java 8: lambdas
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);   // sort by start times

        // alternate version of sorting java 7: using comparators
        /*Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });*/

        PriorityQueue<Integer> ends = new PriorityQueue<>();   // min heap of end times
        ends.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= ends.peek()) {
                ends.poll();   // min room available, so remove this end time and add the end time of the new interval to the heap
            }
            ends.add(intervals[i][1]);   // add the end time to min heap
        }
        return ends.size();
    }

    public static void main(String[] args) {
        assertEquals(minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}), 2);
        assertEquals(minMeetingRooms(new int[][]{{7, 10}, {2, 4}}), 1);
    }
}