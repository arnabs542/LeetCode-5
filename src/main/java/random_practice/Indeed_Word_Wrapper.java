package random_practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota on 2/1/20.
 * We are building a word processor and we would like to implement a "word-wrap" functionality.
 * Given a maximum number of characters in a line followed by a list of words, return a collection of strings where each string element represents a line that contains as many words as possible, with the words in each line being concatenated with a single '-' (representing a space, but easier to see for testing). The length of each string must not exceed the maximum character length per line.
 * Your function should take in the maximum characters per line and return a data structure representing all lines in the indicated max length.
 * Note: built-in functions like Python textwrap module should not be used as solutions to this problem.
 */
public class Indeed_Word_Wrapper {
    private static List<String> wordWrap(String[] words, int length) {
        if (words == null | words.length == 0) {
            return null;
        }

        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            if (stringBuilder.length() + word.length() <= length) {
                stringBuilder.append(word);
                stringBuilder.append("-");
            } else {
                stringBuilder.setLength(stringBuilder.length() - 1);
                result.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                stringBuilder.append(word);
                stringBuilder.append("-");
            }
        }

        if (stringBuilder.length() != 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
            result.add(stringBuilder.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        // example 1:
        System.out.println(wordWrap(new String[]{"The", "day", "began", "as", "still", "as", "the", "night", "abruptly", "lighted", "with", "brilliant", "flame"}, 13));
        System.out.println(wordWrap(new String[]{"The", "day", "began", "as", "still", "as", "the", "night", "abruptly", "lighted", "with", "brilliant", "flame"}, 20));

        // example 2:
        System.out.println(wordWrap(new String[]{"Hello"}, 5));

        // example 3:
        System.out.println(wordWrap(new String[]{"Hello", "world"}, 5));
    }
}