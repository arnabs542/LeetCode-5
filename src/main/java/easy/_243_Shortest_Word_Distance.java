package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/29/20.
 * <p>
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * </p>
 */
public class _243_Shortest_Word_Distance {
    // core logic: iterate through all the words. maintain 2 indexes and whenever you encounter word1 and word2 update the indexes.
    // calculate min distances at every step (only when both indexes are not -1 which means both words in the array are visited)
    // TC: O(n)
    private static int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length < 2) {
            return 0;
        }

        int index1 = -1, index2 = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            } else if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {    // update min distance only after you have visited both the words
                minDistance = Math.min(minDistance, Math.abs(index1 - index2));
            }
        }
        return minDistance;
    }

    // slightly optimized: same as above logic, but uses only 1 index instead of 2
    // TC: O(n)
    private static int shortestDistance2(String[] words, String word1, String word2) {
        if (words == null || words.length < 2) {
            return 0;
        }

        int index = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (index != -1 && !words[index].equals(words[i])) {
                    minDistance = Math.min(minDistance, Math.abs(i - index));  // update the min index only when both are different words (which means prev index word and current index word are different)
                }
                index = i;   // update the index whenever you encounter one of the word1 or word2
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        assertEquals(shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"), 3);
        assertEquals(shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"), 1);

        assertEquals(shortestDistance2(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"), 3);
        assertEquals(shortestDistance2(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"), 1);
    }
}