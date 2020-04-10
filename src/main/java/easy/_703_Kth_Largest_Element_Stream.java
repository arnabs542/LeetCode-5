package easy;

import org.testng.Assert;

import java.util.PriorityQueue;

/**
 * Created by udaythota on 4/9/20.
 * <p>
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * </p>
 */
public class _703_Kth_Largest_Element_Stream {

    PriorityQueue<Integer> queue;
    private int k;

    private _703_Kth_Largest_Element_Stream(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
    }

    // core logic: basic understanding of how pq works. we always make sure that we have k highest elements (till that point) in the queue. hence peek element of the queue would always be the result.
    // when queue size grows > k, poll the smaller elements from queue to readjust the queue to only have k highest elements
    public int add(int val) {
        queue.offer(val);
        while (queue.size() > k) {
            queue.poll();
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4, 5, 8, 2};
        _703_Kth_Largest_Element_Stream kth_largest_element_stream = new _703_Kth_Largest_Element_Stream(k, arr);
        Assert.assertEquals(kth_largest_element_stream.add(3), 4);
        Assert.assertEquals(kth_largest_element_stream.add(5), 5);
        Assert.assertEquals(kth_largest_element_stream.add(10), 5);
        Assert.assertEquals(kth_largest_element_stream.add(9), 8);
        Assert.assertEquals(kth_largest_element_stream.add(4), 8);
    }
}
