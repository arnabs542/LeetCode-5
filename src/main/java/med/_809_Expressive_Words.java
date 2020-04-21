package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/20/20.
 * <p>
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
 * Given a list of query words, return the number of words that are stretchy.
 * </p>
 */
public class _809_Expressive_Words {
    // core logic: straight forward 2 pointers approach
    // TC: O(m * n) - where m is length of the words array and n is length of the longest word in the array or the given string S
    private static int expressiveWords(String S, String[] words) {
        if (S == null || S.length() == 0 || words == null || words.length == 0) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            if (isWordStretchy(S, word)) {
                count++;
            }
        }
        return count;
    }

    // to find if the given word is stretchy. just make sure to include all the given conditions carefully
    private static boolean isWordStretchy(String S, String word) {
        int i = 0, j = 0;
        while (i < S.length() && j < word.length()) {
            if (S.charAt(i) == word.charAt(j)) {
                int count1 = countRepeatChars(S, i);
                int count2 = countRepeatChars(word, j);
                if ((count1 < 3 && count1 != count2) || (count1 >= 3 && count1 < count2)) {
                    return false;
                }
                i += count1;
                j += count2;
            } else {
                return false;
            }
        }
        return i == S.length() && j == word.length();
    }

    // simple helper method to get the count of repeated characters from the given index
    private static int countRepeatChars(String word, int index) {
        int count = 1;
        char ch = word.charAt(index++);
        while (index < word.length() && word.charAt(index) == ch) {
            index++;
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        Assert.assertEquals(expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}), 1);
        Assert.assertEquals(expressiveWords("abcd", new String[]{"abc"}), 0);
    }
}
