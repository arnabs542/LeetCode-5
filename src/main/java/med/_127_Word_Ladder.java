package med;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/8/20.
 * <p>
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * </p>
 */
public class _127_Word_Ladder {
    // core logic: classic BFS: starting from the begin word, do a BFS till you encounter the end word. if you don't find end word by the end of the process, return 0
    // level 1: hit  level 2: hot  level 3: dot, lot   level 4: dog, log   level 5: cog  (this is for example 1)
    // TC: O(m * n) -> where m is the length of word list and n is the length of each word
    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        int level = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>(wordList);
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return level + 1;
                }
                wordMatch(queue, visited, word);
            }
            level++;
        }
        return 0;
    }

    // helper method to transform all the letters of the given word and generate the possibilities of new words (eg: hit will be matched to hot and hot will be matched to dot and lot from the first example). only one letter will be replaced from the given word to generate all the possibilities.
    private static void wordMatch(Queue<String> queue, Set<String> visited, String word) {
        for (int i = 0; i < word.length(); i++) {
            char[] wordArray = word.toCharArray();
            for (char ch = 'a'; ch < 'z'; ch++) {
                wordArray[i] = ch;

                String newWord = String.valueOf(wordArray);
                if (visited.contains(newWord)) {
                    visited.remove(newWord);
                    queue.offer(newWord);
                }
            }
        }
    }

    public static void main(String[] args) {
        assertEquals(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")), 5);
        assertEquals(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")), 0);
    }
}