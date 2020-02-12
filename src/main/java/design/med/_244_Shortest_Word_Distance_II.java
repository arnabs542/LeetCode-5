package design.med;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/11/20.
 * <p>
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.
 * </p>
 */
public class _244_Shortest_Word_Distance_II {
    static HashMap<String, List<Integer>> map = new HashMap<>();

    // core logic: this is an extension / follow up for LC 243. the same method can be used here, but that method uses lot of comparisons and given the fact that this question explicitly says the shortest method would be called multiple times, we need some sort of cache (hash table) which we can lookup to find the shortest distance
    // iterate through words array and create a look up map. later use the look up map to find the shortest distance
    // TC: O(n) + O(1) ??
    private _244_Shortest_Word_Distance_II(String[] words) {
        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }

    // finds the shortest distance between elements in lists given 2 sorted lists. merge sorted lists approach.
    private static int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int shortest = Integer.MAX_VALUE;

        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int index1 = list1.get(i);
            int index2 = list2.get(j);

            if (index1 < index2) {
                shortest = Math.min(shortest, index2 - index1);
                i++;
            } else {
                shortest = Math.min(shortest, index1 - index2);
                j++;
            }
        }

        return shortest;
    }

    public static void main(String[] args) {
        _244_Shortest_Word_Distance_II shortest_word_distance_ii = new _244_Shortest_Word_Distance_II(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        assertEquals(shortest("makes", "coding"), 1);
        assertEquals(shortest("perfect", "practice"), 2);
    }
}