package med;

import java.util.*;

/**
 * Created by udaythota on 5/11/19.
 * <p>
 * Given an array of strings, group anagrams together.
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * </p>
 */
public class _49_GroupAnagrams {

    // TC: O(n klogk) -> where n is the number of elements in the input array and k is the max length of an element in the input array
    // SC: O(nk)
    private static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {  // empty list
            return new ArrayList<>();
        }

        HashMap<String, List<String>> resultMap = new HashMap<>();
        for (String input : strs) {
            char[] charArray = input.toCharArray();
            Arrays.sort(charArray);
            String sortedInput = new String(charArray);   // or String.valueOf(charArray)

            if (resultMap.containsKey(sortedInput)) {
                resultMap.get(sortedInput).add(input);
            } else {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(input);
                resultMap.put(sortedInput, newList);
            }
        }
        return new ArrayList<>(resultMap.values());    // map.values() returns a collection view of values contained in the map. so there is no need to iterate over the key set and put them back to the result list
    }

    // same as above solution but we are avoiding sorting here which reduces the overall TC
    // TC: O(nk), where 'n' is the length of strs, and k is the maximum length of a string in strs
    // SC: O(nk), same as above solution
    private static List<List<String>> groupAnagramsOptimal(String[] strs) {
        if (strs.length == 0) {  // empty list
            return new ArrayList<>();
        }

        HashMap<String, List<String>> resultMap = new HashMap<>();
        for (String input : strs) {
            StringBuilder stringBuilder = new StringBuilder();
            int[] alphabetCountArray = new int[26];   // to keep the count of each character in the input string
            char[] charArray = input.toCharArray();
            for (Character ch : charArray) {
                alphabetCountArray[ch - 'a']++;
            }

            for (int i = 0; i < 26; i++) {   // constant time
                stringBuilder.append("#");
                stringBuilder.append(alphabetCountArray[i]);
            }
            String sortedUniqueString = stringBuilder.toString();   // this will be unique for all the anagram pairs that exist in the input array (eg: aabc: #2#1#1)

            if (resultMap.containsKey(sortedUniqueString)) {
                resultMap.get(sortedUniqueString).add(input);
            } else {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(input);
                resultMap.put(sortedUniqueString, newList);
            }
        }
        return new ArrayList<>(resultMap.values());    // map.values() returns a collection view of values contained in the map. so there is no need to iterate over the key set and put them back to the result list
    }

    // one of the java solution from the LC accepted solutions: clean and short
    // minor modification in modifying the map (if-else can be replaced with if statement)
    private static List<List<String>> groupAnagramsAlternate(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"}));
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

        System.out.println(groupAnagramsAlternate(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagramsAlternate(new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"}));
        System.out.println(groupAnagramsAlternate(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

        System.out.println(groupAnagramsOptimal(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagramsOptimal(new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"}));
        System.out.println(groupAnagramsOptimal(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
