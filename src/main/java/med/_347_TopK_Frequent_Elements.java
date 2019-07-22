package med;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/15/19.
 * <p>
 * Given a non-empty array of integers, return the k most frequent elements.
 * </p>
 */
public class _347_TopK_Frequent_Elements {

    // step 1: add element and its corresponding count to the hash map
    // step 2: add entries from hash map to priority queue based on the count value (max heap -> highest count values will have the highest priority)
    // step 3: poll from priority queue and add first K elements to the result
    private static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) == null) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());   // add the entries to the priority queue based on the counts for keys
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {   // add k elements from priority queue to the result list
            Map.Entry<Integer, Integer> entry = pq.poll();
            res.add(entry.getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        assertEquals(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2), Arrays.asList(1, 2));
        assertEquals(topKFrequent(new int[]{1}, 1), Arrays.asList(1));
    }
}