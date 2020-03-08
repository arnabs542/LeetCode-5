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

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());   // max heap: add the entries to the priority queue based on the counts for keys
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

    // same as above logic except that we use min heap here instead of max heap
    // size of min heap here will always be <=k. the above solution looks more straight forward though
    // TODO: execute later and see if it is working
    private static List<Integer> topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());   // min heap: always keep K elements in the heap and when it exceeds K remove the min freq element as we don't need that any more¬
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        Integer[] res = new Integer[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll().getKey();
        }
        return Arrays.asList(res);
    }

    public static void main(String[] args) {
        assertEquals(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2), Arrays.asList(1, 2));
        assertEquals(topKFrequent(new int[]{1}, 1), Arrays.asList(1));

        assertEquals(topKFrequent2(new int[]{1, 1, 1, 2, 2, 3}, 2), Arrays.asList(1, 2));
        assertEquals(topKFrequent2(new int[]{1}, 1), Arrays.asList(1));
    }
}