package easy;

import java.util.HashMap;
import java.util.HashSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/23/20.
 * <p>
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 * Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
 * </p>
 */
public class _819_Most_Common_Word {
    // core logic: split the words, keep the count of non banned words and whenever its greater than count of other non banned words, replace the result word. finally return the max non banned word encountered
    // NOTE: the meat of the problem lies in coming up with the right regex to do the job
    private static String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) {
            return null;
        }
        int count = 0;
        String result = "";

        String[] words = paragraph.toLowerCase().split("\\W+"); // IMPORTANT: \\W means replace all the non word chars with "". CHECK all the regex patterns again
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for (String word : banned) {
            set.add(word);
        }
        for (String word : words) {
            if (!set.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
                if (map.get(word) > count) {
                    count = map.get(word);
                    result = word;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}), "ball");
    }
}