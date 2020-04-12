package med;

import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/12/20.
 * <p>
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * </p>
 */
public class _249_Group_Shifted_Strings {
    // core logic: for every word, generate a unique key remembering the distance shifts between each character in the word. save the key and corresponding value in the map. if the same key in encountered again (which means same distance shift for the word), add the value to the result list. finally retrieve all the values from the map and insert in to the result list
    // logic is similar to group anagrams
    // TC: O(n)
    private static List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        if (strings == null || strings.length == 0) {
            return result;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String word : strings) {
            String key = "";
            for (int j = 0; j < word.length(); j++) {
                key += (word.charAt(j) - word.charAt(0) + 26) % 26 + ",";   // this is needed to take care of words with negative offset (eg: for 'ba', if we just subtract a from b, the offset is negative (-1). so to handle all these cases, we add and mod by 26. in this case the offset for 'b' in 'ab' would be 25: (-1 + 26)%26)
            }
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }
        for (String key : map.keySet()) {   // add all the values for the same keys to the result list
            List<String> values = map.get(key);
            Collections.sort(values);
            result.add(values);
        }
        return result;
    }

    public static void main(String[] args) {
        Assert.assertEquals(groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}), Arrays.asList(Arrays.asList("acef"), Arrays.asList("az", "ba"), Arrays.asList("abc", "bcd", "xyz"), Arrays.asList("a", "z")));
        Assert.assertEquals(groupStrings(new String[]{"abc", "am"}), Arrays.asList(Arrays.asList("am"), Arrays.asList("abc")));
    }
}
