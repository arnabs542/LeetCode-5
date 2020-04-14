package hard;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/14/20.
 * <p>
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * </p>
 */
public class _472_Concatenated_Words {
    // DFS + memoization (adding valid suffixes to set acts as memoization).
    // for every word, check all the word prefixes and for all the valid prefixes, check if the corresponding suffix is valid. if both of them are valid (part of the word set), it means that we have both prefix and suffix of this word in the word set and this hence can be added to the result list
    // TC: O(N^2) or O(N^3) ??
    // a similar and an interesting approach using trie: https://leetcode.com/problems/concatenated-words/discuss/541520/Java-DFS-%2B-Memoization-Clean-code. pursue it later
    private static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (dfsHelper(word, wordSet)) {
                result.add(word);
            }
        }
        return result;
    }

    // dfs helper method
    private static boolean dfsHelper(String word, Set<String> wordSet) {
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if (wordSet.contains(prefix)) {
                String suffix = word.substring(i);
                if (wordSet.contains(suffix) || dfsHelper(suffix, wordSet)) {
                    wordSet.add(suffix);   // optimization: just to make sure we don't do dfs on the same suffix again. this helps in faster look ups in the further calls
                    return true;
                }
            }
        }
        return false;
    }

    // DFS + memoization: same logic as above solution except that we here are using cache to make our looks ups much faster and reduce the total number of dfs operations
    private static List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        HashMap<String, Boolean> cache = new HashMap<>();

        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (dfsHelper2(word, wordSet, cache)) {
                result.add(word);
            }
        }
        return result;
    }

    // dfs helper method
    private static boolean dfsHelper2(String word, Set<String> wordSet, HashMap<String, Boolean> cache) {
        if (cache.containsKey(word)) {  // if the cache already has the value, just return it
            return cache.get(word);
        }
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if (wordSet.contains(prefix)) {
                String suffix = word.substring(i);
                if (wordSet.contains(suffix) || dfsHelper2(suffix, wordSet, cache)) {
                    cache.put(word, true);   // update the cache
                    return true;
                }
            }
        }
        cache.put(word, false);   // update the cache: this word can't be part of result
        return false;
    }

    public static void main(String[] args) {
        Assert.assertEquals(findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}), Arrays.asList("catsdogcats", "dogcatsdog", "ratcatdogcat"));
        Assert.assertEquals(findAllConcatenatedWordsInADict2(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}), Arrays.asList("catsdogcats", "dogcatsdog", "ratcatdogcat"));
    }
}
