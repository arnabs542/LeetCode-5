package med;

import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/13/20.
 * <p>
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 * </p>
 */
public class _692_Top_K_Frequent_Words {
    // core logic: map + priority queue (max heap). the only tricky part is to come up with a right comparator for priority queue
    // TC: O(nlogk), SC: O(n)
    private static List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> maxHeap = new PriorityQueue<>((o1, o2) -> map.get(o1) == map.get(o2) ? o1.compareTo(o2) : map.get(o2) - map.get(o1)); // to make sure when counts are equal, we output the word which lower alphabetical order first
        for (String word : map.keySet()) {
            maxHeap.offer(word);
        }

        while (k-- > 0) {
            result.add(maxHeap.poll());
        }
        return result;
    }

    // core logic: map + priority queue (min heap). the only tricky part is to come up with a right comparator for priority queue
    // the only difference when compared to above solution is this uses min heap instead of max heap
    // TC: O(nlogk), SC: O(n)
    private static List<String> topKFrequent2(String[] words, int k) {
        List<String> result = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>((o1, o2) -> map.get(o1) == map.get(o2) ? o2.compareTo(o1) : map.get(o1) - map.get(o2)); // to make sure when counts are equal, we output the word which lower alphabetical order first
        for (String word : map.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {   // min heap always contains the kth highest frequent elements
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll());   // as we need the highest count elements first, and this is min heap, we need to add the elements from the heap in the reverse order
        }
        return result;
    }

    public static void main(String[] args) {
        // solution 1
        Assert.assertEquals(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2), Arrays.asList("i", "love"));
        Assert.assertEquals(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4), Arrays.asList("the", "is", "sunny", "day"));

        // solution 2
        Assert.assertEquals(topKFrequent2(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2), Arrays.asList("i", "love"));
        Assert.assertEquals(topKFrequent2(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4), Arrays.asList("the", "is", "sunny", "day"));
    }
}
