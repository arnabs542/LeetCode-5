package hard;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/17/20.
 * <p>
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * </p>
 */
public class _269_Alien_Dictionary {
    // core logic: build the graph + topological sort. similar to course schedule I and II approach
    // TC: O(V+E) -> where V represents the number of vertices while E represents the number of edges
    private static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        HashMap<Character, Set<Character>> graph = new HashMap<>();
        int[] inDegree = new int[26];
        buildGraph(graph, words, inDegree);
        return topologicalSort(graph, inDegree);
    }

    // build the dependency graph (edges from each node) from the given words. while building the graph, make sure to keep the in degree count (incoming connections to a node) for every vertex. this helps us to process the vertices with no in degree connections before the ones with connections in the later steps .
    private static void buildGraph(HashMap<Character, Set<Character>> map, String[] words, int[] inDegree) {
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                map.putIfAbsent(ch, new HashSet<>());    // create entries for all the keys
            }
        }

        // compare ith and i+1th word and create the dependency graph when you encounter first different characters in both the strings
        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            int length = Math.min(word1.length(), word2.length());

            for (int j = 0; j < length; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    char out = word1.charAt(j);
                    char in = word2.charAt(j);

                    if (!map.get(out).contains(in)) {
                        map.get(out).add(in);
                        inDegree[in - 'a']++;    // increment the in degree connections for the incoming node
                    }
                    break;
                }
            }
        }
    }

    // classic topological sort / BFS: first process the vertices with no in degree connections, and as we process them unblock the rest of the vertices and process them in order
    private static String topologicalSort(HashMap<Character, Set<Character>> map, int[] inDegree) {
        StringBuilder stringBuilder = new StringBuilder();
        int totalChars = map.size();
        Queue<Character> queue = new LinkedList<>();
        for (char ch : map.keySet()) {
            if (inDegree[ch - 'a'] == 0) {
                queue.offer(ch);
            }
        }

        while (!queue.isEmpty()) {
            char ch = queue.poll();
            stringBuilder.append(ch);

            if (map.get(ch) == null || map.get(ch).size() == 0) {
                continue;
            }
            for (char neighbor : map.get(ch)) {
                inDegree[neighbor - 'a']--;

                if (inDegree[neighbor - 'a'] == 0) {   // means the the in degree connections to this vertex is zero, which means we can process it (add to the queue)
                    queue.offer(neighbor);
                }
            }
        }
        return stringBuilder.length() == totalChars ? stringBuilder.toString() : "";   // the fact that the string builder didn't contain all the chars (!=totalChars) from the words / key set, there was a cyclic dependency and hence we cannot process
    }

    public static void main(String[] args) {
        assertEquals(alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}), "wertf");
        assertEquals(alienOrder(new String[]{"z", "x"}), "zx");
        assertEquals(alienOrder(new String[]{"z", "x", "z"}), "");
    }
}