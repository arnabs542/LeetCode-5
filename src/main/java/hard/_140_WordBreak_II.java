package hard;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/19/20.
 * <p>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * </p>
 */
public class _140_WordBreak_II {

    private static HashMap<String, List<String>> cache = new HashMap<>();

    // core logic: DFS + DP (as we are using memoization for sub strings)
    // iterate through the string and for all the combination of sub strings, check if the left and right sub strings are present in the dict and accordingly add them to the result
    // may be see the 2nd solution for easier understanding
    // FIXME: not working for few corner cases, investigate later
    private static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        if (wordDict.contains(s)) {
            result.add(s);
        }

        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i, s.length());
            if (wordDict.contains(left) && containsSuffix(right, wordDict)) {
                for (String rightString : wordBreak(right, wordDict)) {
                    result.add(left + " " + rightString);
                }
            }
        }
        cache.put(s, result);
        return result;
    }

    // helper method to check if at least one of the sub string in s is present in word dict
    private static boolean containsSuffix(String s, List<String> wordDict) {
        for (int i = 0; i < s.length(); i++) {
            if (wordDict.contains(s.substring(i))) {
                return true;
            }
        }
        return false;
    }

    // using DFS + memoization
    // good solution, but think more on this to get a deeper understanding
    // TC: O(n^3) -> n^2 for the recursion (every word could be checking with every other word) and creation of list takes O(n) time
    private static List<String> wordBreak2(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    private static List<String> DFS(String s, List<String> wordDict, Map<String, List<String>> cache) {
        if (cache.containsKey(s)) return cache.get(s);
        List<String> res = new LinkedList<>();
        if (s.length() == 0) { // found an answer
            res.add("");
            //   return res;    equivalent to having it. we are just retuning back the result here
        } else {
            for (String word : wordDict) {
                if (s.startsWith(word)) {
                    List<String> subList = DFS(s.substring(word.length()), wordDict, cache);
                    for (String sub : subList) {
                        if (sub.isEmpty())
                            res.add(word);
                        else
                            res.add(word + " " + sub);
                    }
                }
            }
        }
        cache.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        // solution 1
        assertEquals(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")), Arrays.asList("cat sand dog", "cats and dog"));
        assertEquals(wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")), Arrays.asList("pine apple pen apple",
                "pine applepen apple",
                "pineapple pen apple"));

        // solution 2
        assertEquals(wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")), Arrays.asList("cat sand dog", "cats and dog"));
        assertEquals(wordBreak2("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")), Arrays.asList("pine apple pen apple",
                "pine applepen apple",
                "pineapple pen apple"));
    }
}