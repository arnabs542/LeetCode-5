package random_practice.microsoftassessment;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/18/19.
 * <p>
 * Given a string s consisting of n lowercase letters, you have to delete the minimum number of characters from s so that every letter in s appears a unique number of times.
 * We only care about the occurrences of letters that appear at least once in result.
 * </p>
 */
public class Min_Deletions_Unique_Frequency {

    // good idea, but getting a concurrent modification exception. deal with it later
    private static int minDeletions(String input) {
        if (input == null || input.length() <= 2) {
            return 0;
        }

        int minDeletions = 0;
        Integer[] charCount = new Integer[26];

        for (int i = 0; i < 26; i++) {
            charCount[i] = 0;
        }
        for (char ch : input.toCharArray()) {
            charCount[ch - 'a']++;
        }

        Map<Integer, Integer> freqCountMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                freqCountMap.put(charCount[i], freqCountMap.getOrDefault(charCount[i], 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : freqCountMap.entrySet()) {
            if (entry.getKey() == 0) {
                break;
            }
            while (entry.getValue() > 1) {
                freqCountMap.put(entry.getKey(), entry.getValue() - 1);
                minDeletions++;
                freqCountMap.put(entry.getKey() - 1, freqCountMap.getOrDefault(entry.getKey() - 1, 0) + 1);
            }
        }
        return minDeletions;
    }

    // taken from the leet code discussions. check it out later
    private static int minDeletions2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (char c : map.keySet()) {
            cnt.put(map.get(c), cnt.getOrDefault(map.get(c), 0) + 1);
        }
        Queue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getKey() - a.getKey());
        maxHeap.addAll(cnt.entrySet());
        int res = 0;
        while (maxHeap.size() > 1) {
            Map.Entry<Integer, Integer> e1 = maxHeap.poll();
            Map.Entry<Integer, Integer> e2 = maxHeap.poll();
            res += e1.getValue() - 1;
            e2.setValue(e2.getValue() + e1.getValue() - 1);
            maxHeap.offer(e2);
        }
        Map.Entry<Integer, Integer> lastEntry = maxHeap.poll();
        if (lastEntry.getValue() > lastEntry.getKey()) {
            res += lastEntry.getKey() * (lastEntry.getValue() - lastEntry.getKey());
            lastEntry.setValue(lastEntry.getKey());
        }
        res += getSum(lastEntry.getValue() - 1);
        return res;
    }

    private static int getSum(int n) {
        if (n <= 1)
            return n;
        return n + getSum(n - 1);
    }

    public static void main(String[] args) {
        assertEquals(minDeletions("eeeeffff"), 1);
        assertEquals(minDeletions("aabbffddeaee"), 6);
        assertEquals(minDeletions("llll"), 0);
        assertEquals(minDeletions("example"), 4);

        assertEquals(minDeletions2("eeeeffff"), 1);
        assertEquals(minDeletions2("aabbffddeaee"), 6);
        assertEquals(minDeletions2("llll"), 0);
        assertEquals(minDeletions2("example"), 4);
    }
}