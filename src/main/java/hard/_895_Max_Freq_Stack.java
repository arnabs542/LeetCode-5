package hard;

import java.util.HashMap;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/7/19.
 * <p>
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 * FreqStack has two functions:
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 * </p>
 */
public class _895_Max_Freq_Stack {
    private PriorityQueue<int[]> maxHeap;
    private HashMap<Integer, Integer> map;
    private static int count;

    // the meat of the problem lies in defining the priority queue (max heap). it would be much simpler to implement this solution if there was no constraint of 'tie -> take the element closest to the stack'. in that case, you just need to save the freq and the element in the priority queue
    // but to handle the tie situation, we introduce the third variable in the heap, which is count (we increment the count whenever new element is pushed). when there is a tie, we use this count to decide which element should be placed first in the queue. the one with highest count should be placed first as that would be closest to top of the stack
    private _895_Max_Freq_Stack() {
        maxHeap = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        map = new HashMap<>();
        count = 0;
    }

    private void push(int x) {
        count++;
        map.put(x, map.getOrDefault(x, 0) + 1);
        maxHeap.offer(new int[]{map.get(x), count, x});
    }

    private int pop() {
        int[] array = maxHeap.poll();
        map.put(array[2], map.get(array[2]) - 1);
        return array[2];
    }

    public static void main(String[] args) {
        _895_Max_Freq_Stack max_freq_stack = new _895_Max_Freq_Stack();
        max_freq_stack.push(5);
        max_freq_stack.push(7);
        max_freq_stack.push(5);
        max_freq_stack.push(7);
        max_freq_stack.push(4);
        max_freq_stack.push(5);

        assertEquals(max_freq_stack.pop(), 5);
        assertEquals(max_freq_stack.pop(), 7);
        assertEquals(max_freq_stack.pop(), 5);
        assertEquals(max_freq_stack.pop(), 4);
    }
}