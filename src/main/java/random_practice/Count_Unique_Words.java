package random_practice;

import java.util.Arrays;
import java.util.HashSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/13/19.
 * Count of unique words in a string or an array
 */
public class Count_Unique_Words {
    private static int countOfUniqueWords(String input) {
        if (input == null || input.length() == 0) {
            return -1;
        }

        String[] tokens = input.trim().split(" ");
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(tokens));
        return set.size();
    }

    private static int countOfUniqueWords2(String[] input) {
        if (input == null || input.length == 0) {
            return -1;
        }
        return new HashSet<>(Arrays.asList(input)).size();
    }

    public static void main(String[] args) {
        assertEquals(countOfUniqueWords("this is test string to test the string test and see if the test passes"), 10);
        assertEquals(countOfUniqueWords2(new String[]{"this", "is", "test", "string", "to", "test", "and", "see", "if", "the", "test", "passes"}), 10);
    }
}
