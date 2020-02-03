package med;

import java.util.HashMap;
import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/2/20.
 * <p>
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * </p>
 */
public class _739_Daily_Temperatures {
    // core logic: similar to LC: 496 (next greater element). use stack and map to manipulate it accordingly
    // push to stack all the elements in the decreasing sequence. when you encounter an element greater than the stack peek (which means current element is greater than last element), calculate its index, save it in map and record the current index back to stack
    // see https://leetcode.com/problems/daily-temperatures/discuss/109862/O(n)-Java-Stack for more explanation
    private static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        if (T.length == 0) {
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();  // key: current index, value: index of next greater element  (if any key doesn't have next greater element, that key will not be present in this map)

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                map.put(stack.pop(), i);
            }
            stack.push(i);
        }

        for (int i = 0; i < T.length; i++) {
            result[i] = map.get(i) == null ? 0 : map.get(i) - i;  // find the difference as the values in the map are indexes of next greater elements, but what we need is the difference between the current index and next greater element index
        }
        return result;
    }

    // core logic: similar to above logic, but here we get rid of map operations and directly put in to the result array since we are handling indexes instead of temperatures
    private static int[] dailyTemperatures2(int[] T) {
        int[] result = new int[T.length];
        if (T.length == 0) {
            return result;
        }
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                result[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}), new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        assertEquals(dailyTemperatures(new int[]{73, 74, 75}), new int[]{1, 1, 0});

        assertEquals(dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73}), new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        assertEquals(dailyTemperatures2(new int[]{73, 74, 75}), new int[]{1, 1, 0});
    }
}