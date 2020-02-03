package med;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/2/20.
 * <p>
 * Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
 * A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
 * The CPU is single threaded which means that only one function is being executed at a given time unit.
 * Return the exclusive time of each function, sorted by their function id.
 * </p>
 */
public class _636_Exclusive_Time_Functions {
    // core logic: use stack to record the function id's and calculate the time accordingly for each function id
    // some more explanation: https://leetcode.com/problems/exclusive-time-of-functions/discuss/244322/Java-solution-with-explanation
    private static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        if (n < 0 || logs == null || logs.size() == 0) {
            return null;
        }
        int lastTime = 0;
        Stack<Integer> stack = new Stack<>();

        for (String log : logs) {
            String[] logSplit = log.split(":");
            int functionId = Integer.valueOf(logSplit[0]);
            int timeStamp = Integer.valueOf(logSplit[2]);

            if (logSplit[1].equals("start")) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += timeStamp - lastTime;
                }
                stack.push(functionId);
                lastTime = timeStamp;
            } else {
                result[stack.pop()] += timeStamp - lastTime + 1;   // add one to account for end time (if the time started at 2 and ended at 5, means its 4 units of time and not 3)
                lastTime = timeStamp + 1;   // to account for end time
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("0:start:0");
        input.add("1:start:2");
        input.add("1:end:5");
        input.add("0:end:6");
        assertEquals(exclusiveTime(2, input), new int[]{3, 4});
    }
}