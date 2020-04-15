package med;

import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/14/20.
 * <p>
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * </p>
 */
public class _1268_Search_Suggestions_System {
    // core logic: keep adding possible suggestions to the min heap for every prefix in the search word, and later use the min heap to get the top 3 words for every prefix
    // Tc: O(n * p) -> where n is the length of the search word, while p is size of the products array
    private static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        if (products == null || products.length == 0 || searchWord == null || searchWord.length() == 0) {
            return result;
        }

        for (int i = 0; i < searchWord.length(); i++) {
            int k = 3;
            List<String> list = new ArrayList<>();
            PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));   // min heap: we don't need to give the comparator logic (optional) as the min heap by itself stores the strings in lexicographic order
            String prefix = searchWord.substring(0, i + 1);
            for (String product : products) {
                if (product.startsWith(prefix)) {
                    queue.offer(product);
                }
            }
            while (!queue.isEmpty() && k-- > 0) {
                list.add(queue.poll());
            }
            result.add(list);
        }
        return result;
    }

    // core logic: use custom trie (every node will also have the suggestions list which will be helpful for faster look ups during searching). first build the trie using all the products and in the process of building it, also save 3 suggestions at every trie node, so the look ups could be much faster when searching
    // TC: see here: https://leetcode.com/problems/search-suggestions-system/discuss/440474/Java-trie-explained-clean-code-14ms
    private static List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        Arrays.sort(products);
        TrieNode root = new TrieNode();
        for (String product : products) {
            TrieNode curr = root;
            for (int i = 0; i < product.length(); i++) {
                char ch = product.charAt(i);
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode();
                    curr.value = ch;   // optional
                }
                curr = curr.children[ch - 'a'];
                if (curr.suggestions.size() < 3) {
                    curr.suggestions.add(product);
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        TrieNode prefix = root;
        for (int i = 0; i < searchWord.length(); i++) {
            prefix = prefix.children[searchWord.charAt(i) - 'a'];   // eg: search word = "mouse". we navigate from m -> o -> u -> s -> e
            if (prefix == null) {
                for (int j = i; j < searchWord.length(); j++) {   // if the prefix is null, no point in searching the tree further, so just add the empty lists for the rest of the string and return
                    result.add(new ArrayList<>());
                }
                break;
            } else {
                result.add(prefix.suggestions);
            }
        }
        return result;
    }

    static class TrieNode {
        TrieNode[] children;
        char value;
        List<String> suggestions;

        TrieNode() {
            suggestions = new ArrayList<>();
            children = new TrieNode[26];
        }
    }


    public static void main(String[] args) {
        // solution 1
        Assert.assertEquals(suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"), Arrays.asList(Arrays.asList("mobile", "moneypot", "monitor"), Arrays.asList("mobile", "moneypot", "monitor"), Arrays.asList("mouse", "mousepad"), Arrays.asList("mouse", "mousepad"), Arrays.asList("mouse", "mousepad")));
        Assert.assertEquals(suggestedProducts(new String[]{"havana"}, "havana"), Arrays.asList(Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana")));
        Assert.assertEquals(suggestedProducts(new String[]{"bags", "baggage", "banner", "box", "cloths"}, "bags"), Arrays.asList(Arrays.asList("baggage", "bags", "banner"), Arrays.asList("baggage", "bags", "banner"), Arrays.asList("baggage", "bags"), Arrays.asList("bags")));
        Assert.assertEquals(suggestedProducts(new String[]{"havana"}, "tatiana"), Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList()));

        // solution 2
        Assert.assertEquals(suggestedProducts2(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"), Arrays.asList(Arrays.asList("mobile", "moneypot", "monitor"), Arrays.asList("mobile", "moneypot", "monitor"), Arrays.asList("mouse", "mousepad"), Arrays.asList("mouse", "mousepad"), Arrays.asList("mouse", "mousepad")));
        Assert.assertEquals(suggestedProducts2(new String[]{"havana"}, "havana"), Arrays.asList(Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana"), Arrays.asList("havana")));
        Assert.assertEquals(suggestedProducts2(new String[]{"bags", "baggage", "banner", "box", "cloths"}, "bags"), Arrays.asList(Arrays.asList("baggage", "bags", "banner"), Arrays.asList("baggage", "bags", "banner"), Arrays.asList("baggage", "bags"), Arrays.asList("bags")));
        Assert.assertEquals(suggestedProducts2(new String[]{"havana"}, "tatiana"), Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList()));
    }
}
