package easy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by udaythota on 2/5/20.
 */
class MedianFinder {
    //maxHeap on left, minHeap on right
    private PriorityQueue<Integer> maxHeap = null;
    private PriorityQueue<Integer> minHeap = null;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    public void addNum(int num) {
        maxHeap.add(num);
        if(maxHeap.size() > minHeap.size())
            minHeap.add(maxHeap.remove());
        else if(maxHeap.peek() > minHeap.peek()){
            int n1 = maxHeap.remove(), n2= minHeap.remove();
            maxHeap.add(n2);
            minHeap.add(n1);
        }
    }
    public double findMedian() {
        if(maxHeap.size() == minHeap.size())
            return (maxHeap.peek()+minHeap.peek())/2.0;
        else
            return minHeap.peek();
    }

    public static void main(String[] args) {

        MedianFinder medianFinder = new MedianFinder() ;
        medianFinder.addNum(3);
        medianFinder.addNum(5);
        medianFinder.addNum(2);
        medianFinder.addNum(10);
        medianFinder.addNum(7);
    }
}
