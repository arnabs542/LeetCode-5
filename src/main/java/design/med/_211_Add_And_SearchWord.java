package design.med;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 6/18/19.
 * <p>
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * </p>
 */
public class _211_Add_And_SearchWord {

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

        private void addWord(String word) {
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

        // FIXME: this iterative approach is broken for few corner cases. fix it later
        private boolean search(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch != '.') {
                    if (current.children[ch - 'a'] == null) {
                        return false;
                    }
                    current = current.children[ch - 'a'];
                } else {
                    int j;
                    for (j = 0; j < 26; j++) {
                        if (current.children[j] != null) {
                            break;
                        }
                    }
                    current = current.children[j];
                }
            }
            return current.isWord;
        }

        private boolean searchRecursive(String word) {
            return searchRecursive(word, 0, root);
        }

        private boolean searchRecursive(String word, int index, TrieNode root) {
            if (index == word.length()) {
                return root.isWord;
            }
            char ch = word.charAt(index);
            if (ch == '.') {
                for (int i = 0; i < 26; i++) {   // as max possible children for the given parent node is 26
                    if (root.children[i] != null) {
                        if (searchRecursive(word, index + 1, root.children[i])) {
                            return true;
                        }
                    }
                }
            } else {
                return root.children[ch - 'a'] != null && searchRecursive(word, index + 1, root.children[ch - 'a']);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new _211_Add_And_SearchWord.Trie();
        trie.addWord("bad");
        trie.addWord("dad");
        trie.addWord("mad");

        /*assertFalse(trie.search("pad"));
        assertTrue(trie.search("bad"));
        assertTrue(trie.search(".ad"));
        assertTrue(trie.search("b.."));*/

        assertFalse(trie.searchRecursive("pad"));
        assertTrue(trie.searchRecursive("bad"));
        assertTrue(trie.searchRecursive(".ad"));
        assertTrue(trie.searchRecursive("b.."));
    }
}
