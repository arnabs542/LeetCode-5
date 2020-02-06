package hard;

import java.util.Collections;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;

/**
 * Created by z002jsf on 2/5/20.
 * <p>
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * </p>
 */
public class _295_Find_Median_Data_Stream {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    private _295_Find_Median_Data_Stream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // core logic: max heap maintains the elements in the first half (with the top elements of heap being highest elements in the first half), min heap maintains the elements in the second half (with the top elements of heap being lowest elements in the second half)
    // make sure to add elements in the appropriate heaps to satisfy the above condition
    // better understood with an example
    // TC: O(logN) -> for the priority queue operations, SC: O(1)
    private void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.remove());

        if (maxHeap.size() < minHeap.size()) {  // to make sure max heap always have the highest number of elements
            maxHeap.add(minHeap.remove());
        }
    }

    // TC: O(1), SC: O(1)
    private double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            int m1 = minHeap.peek();
            int m2 = maxHeap.peek();
            return (double) (m1 + m2) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        _295_Find_Median_Data_Stream median_data_stream = new _295_Find_Median_Data_Stream();
        median_data_stream.addNum(1);
        median_data_stream.addNum(2);
        assertEquals(median_data_stream.findMedian(), 1.5);
    }
}