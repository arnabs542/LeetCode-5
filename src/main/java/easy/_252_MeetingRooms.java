package easy;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 8/4/19.
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * </p>
 */
public class _252_MeetingRooms {
    // core logic: sort the array based on its start times. iterate through the sorted array, and whenever you encounter the current meeting start time before the previous meeting end time, return false
    // similar logic as 252 and 253 (meeting rooms)
    private static boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {   // overlapping meetings found (current meeting start is before previous meeting end)
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assertFalse(canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        assertTrue(canAttendMeetings(new int[][]{{7, 10}, {2, 4}}));
        assertTrue(canAttendMeetings(new int[][]{{13, 15}, {1, 13}}));
    }
}