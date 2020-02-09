package hard;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by udaythota on 2/9/20.
 * <p>
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * </p>
 */
public class _212_Word_Search_II {
    class Trie {
        class TrieNode {
            private Trie.TrieNode[] children;
            private boolean isWord;
            private char ch;

            TrieNode(char ch) {
                children = new Trie.TrieNode[26];
                isWord = false;
                this.ch = ch;
            }
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode(' ');
        }

        // ITERATIVE methods
        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.children[ch - 'a'] == null) {
                    current.children[ch - 'a'] = new TrieNode(ch);
                }
                current = current.children[ch - 'a'];
            }
            current.isWord = true;
        }

        public boolean search(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.children[ch - 'a'] == null) {
                    return false;
                }
                current = current.children[ch - 'a'];
            }
            return current.isWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode current = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (current.children[ch - 'a'] == null) {
                    return false;
                }
                current = current.children[ch - 'a'];
            }
            return true;
        }
    }

    Set<String> res = new HashSet<String>();

    // core logic: classic DFS. first add the words to trie so they can be checked (if the sub string is part words) when performing DFS and decide if we need to further continue DFS or not.
    // TC: O(m * n * wl * 4^wl) where m*n is the size of the board and wl is the average length of the words in the list.
    private List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<String>(res);
    }

    // dfs helper method: visited array is helpful to track the visited nodes in the grid when performing DFS
    private void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {   // grid conditions exceeded
            return;
        }
        if (visited[x][y]) {   // already visited node
            return;
        }

        str += board[x][y];
        if (!trie.startsWith(str)) {  // if trie doesn't contain this sub string, then there is no use proceeding further, so return
            return;
        }

        if (trie.search(str)) {   // add to result as this word is present in the trie
            res.add(str);
        }

        visited[x][y] = true;   // mark the node as visited
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        visited[x][y] = false;   // mark the node as unvisited after DFS: backtracking
    }

    public static void main(String[] args) {
        _212_Word_Search_II word_search_ii = new _212_Word_Search_II();
        assertEquals(word_search_ii.findWords(new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}}, new String[]{"oath", "pea", "eat", "rain"}), Arrays.asList("oath", "eat"));
        // assertEquals(word_search_ii.findWords(new char[][]{{'a', 'a'}}, new String[]{"aaa"}), Arrays.asList());
    }
}