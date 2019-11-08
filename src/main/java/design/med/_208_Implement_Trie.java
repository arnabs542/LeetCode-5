package design.med;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 6/16/19.
 * <p>
 * Implement a trie with insert, search, and startsWith methods.
 * </p>
 */
public class _208_Implement_Trie {

    static class TrieNode {
        private TrieNode[] children;
        private boolean isWord;
        private char ch;

        TrieNode(char ch) {
            children = new TrieNode[26];
            isWord = false;
            this.ch = ch;
        }
    }

    static class Trie {
        TrieNode root;

        private Trie() {
            root = new TrieNode(' ');
        }

        // ITERATIVE methods
        private void insert(String word) {
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

        private boolean search(String word) {
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

        private boolean startsWith(String prefix) {
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

        // RECURSIVE methods
        private void insertRecursive(String word) {
            insertRecursive(word, 0, root);
        }

        private void insertRecursive(String word, int index, TrieNode root) {
            if (index == word.length()) {
                root.isWord = true;
                return;
            }

            char ch = word.charAt(index);
            if (root.children[ch - 'a'] == null) {
                root.children[ch - 'a'] = new TrieNode(ch);
            }
            insertRecursive(word, index + 1, root.children[ch - 'a']);
        }

        private void searchRecursive(String word) {
            searchRecursive(word, 0, root);
        }

        private boolean searchRecursive(String word, int index, TrieNode root) {
            if (index == word.length()) {
                return root.isWord;
            }
            char ch = word.charAt(index);
            return root.children[ch - 'a'] != null && searchRecursive(word, index + 1, root.children[ch - 'a']);
        }

        private boolean startsWithRecursive(String word) {
            return startsWithRecursive(word, 0, root);
        }

        private boolean startsWithRecursive(String word, int index, TrieNode root) {
            if (index == word.length()) {
                return true;
            }
            char ch = word.charAt(index);
            return root.children[ch - 'a'] != null && startsWithRecursive(word, index + 1, root.children[ch - 'a']);
        }
    }

    public static void main(String[] args) {
        Trie trie = new _208_Implement_Trie.Trie();

        // test case: 1
        trie.insert("test");
        assertTrue(trie.search("test"));
        trie.insert("testing");
        assertTrue(trie.startsWith("testi"));
        assertFalse(trie.startsWith("abc"));

        // test case: 2
        trie.insert("str");
        assertTrue(trie.search("str"));
        trie.insert("stri");
        assertTrue(trie.startsWith("stri"));
        trie.insert("string");
        assertTrue(trie.search("string"));

        // test case: 3
        trie.insertRecursive("target");
        assertTrue(trie.search("target"));
        assertTrue(trie.startsWithRecursive("tar"));
        assertFalse(trie.startsWithRecursive("targets"));
    }
}