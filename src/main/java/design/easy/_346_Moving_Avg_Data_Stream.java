package design.easy;

import org.testng.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 4/9/20.
 * <p>
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * </p>
 */
public class _346_Moving_Avg_Data_Stream {
    private Queue<Integer> queue;
    private int size;
    private int previousSum = 0;

    private _346_Moving_Avg_Data_Stream(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size) {
            previousSum -= queue.poll();  // make sure we remove the element being removed from queue from the calculated sum
        }
        previousSum += val;
        queue.offer(val);
        return previousSum / (double) queue.size();
    }

    public static void main(String[] args) {
        _346_Moving_Avg_Data_Stream moving_avg_data_stream = new _346_Moving_Avg_Data_Stream(3);
        Assert.assertEquals(moving_avg_data_stream.next(1), 1.0);
        Assert.assertEquals(moving_avg_data_stream.next(10), 5.5);
        Assert.assertEquals(moving_avg_data_stream.next(3), 4.666666666666667);
        Assert.assertEquals(moving_avg_data_stream.next(5), 6.0);
    }
}
