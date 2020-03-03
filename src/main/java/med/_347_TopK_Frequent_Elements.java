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
    // TC: O(NlogK) -> building the map takes O(N) time while adding to heap takes O(NlogK) times [adding / removing from heap is an O(logK) operation and we do this N times - so it is O(NlogK)]. Total TC: O(N + NlogK) ~ O(NlogK)
    private static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
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