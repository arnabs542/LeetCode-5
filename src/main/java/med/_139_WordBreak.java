package med;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 7/5/19.
 * <p>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * </p>
 */
public class _139_WordBreak {
    // core logic: dp[i] represents whether s[0...i] can be formed by dict
    // see the below solution for easier understanding
    // TC: O(n^2)
    private static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int length = s.length();
        boolean dp[] = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // exactly same as the above solution except that indexes start from 0 and end at length - 1 so as for easier understanding
    // TODO: pursue BFS and DFS later: https://leetcode.com/problems/word-break/discuss/43908/4-different-ways-to-solve-this-with-detailed-explanation.
    // TODO: comments on the link: https://leetcode.com/problems/word-break/discuss/43819/DFS-with-Path-Memorizing-Java-Solution
    private static boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int length = s.length();
        boolean dp[] = new boolean[length];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (wordDict.contains(s.substring(j, i + 1)) && (j == 0 || dp[j - 1])) {   // when j==0, by default assume dp as true and see if dp till previous element is true
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        assertTrue(wordBreak("leetcode", wordDict));
        assertTrue(wordBreak2("leetcode", wordDict));

        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("apple");
        wordDict2.add("pen");
        assertTrue(wordBreak("applepenapple", wordDict2));
        assertTrue(wordBreak2("applepenapple", wordDict2));

        List<String> wordDict3 = new ArrayList<>();
        wordDict3.add("cats");
        wordDict3.add("dog");
        wordDict3.add("sand");
        assertFalse(wordBreak("catsandog", wordDict3));
        assertFalse(wordBreak2("catsandog", wordDict3));
    }
}
