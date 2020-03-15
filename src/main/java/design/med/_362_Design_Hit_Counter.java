package design.med;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 3/14/20.
 * <p>
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 * It is possible that several hits arrive roughly at the same time.
 * </p>
 */
public class _362_Design_Hit_Counter {

    private HashMap<Integer, Integer> map;

    /**
     * Initialize your data structure here.
     */
    private _362_Design_Hit_Counter() {
        map = new HashMap<>();
    }

    /**
     * Record a hit.
     * TC: O(1): due to map operation
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    private void hit(int timestamp) {
        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * TC: O(n) -> where n is number of keys in the map
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    private int getHits(int timestamp) {
        int count = 0;
        for (Integer time : map.keySet()) {
            if (timestamp - time < 300) {
                count += map.get(time);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        _362_Design_Hit_Counter design_hit_counter = new _362_Design_Hit_Counter();
        design_hit_counter.hit(1);
        design_hit_counter.hit(2);
        design_hit_counter.hit(3);
        assertEquals(design_hit_counter.getHits(4), 3);
        design_hit_counter.hit(300);
        assertEquals(design_hit_counter.getHits(300), 4);
        assertEquals(design_hit_counter.getHits(301), 3);
    }
}