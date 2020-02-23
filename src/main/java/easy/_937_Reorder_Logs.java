package easy;

import java.util.Arrays;
import java.util.Comparator;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/5/20.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * Each word after the identifier will consist only of lowercase letters, or; Each word after the identifier will consist only of digits.
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 * </p>
 */
public class _937_Reorder_Logs {
    // core logic: split the string and use a comparator to define sorting logic for digit and letter logs
    // case 1: when there are both digit logs, return 0, which means both are equal and let the compareTo built in function sort it accordingly
    // case 2: when you have a digit log and a letter log, order the letter log first irrespective of any condition and vice versa
    // case 3: when both letter logs are equal, sort them by identifier
    private static String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return null;
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] str1 = s1.split(" ");
                String[] str2 = s2.split(" ");
                if (Character.isDigit(str1[1].charAt(0)) && Character.isDigit(str2[1].charAt(0))) {  // case 1
                    return 0;
                }
                if (Character.isLetter(str1[1].charAt(0)) && Character.isDigit(str2[1].charAt(0))) {  // case 2
                    return -1;
                }
                if (Character.isDigit(str1[1].charAt(0)) && Character.isLetter(str2[1].charAt(0))) {   // case 1
                    return 1;
                }
                String logs1 = s1.substring(s1.indexOf(" ") + 1);   // indexOf returns the first occurrence of " "
                String logs2 = s2.substring(s2.indexOf(" ") + 1);

                if (logs1.equals(logs2)) {   // case 3
                    return str1[0].compareTo(str2[0]);
                }
                return logs1.compareTo(logs2);   // after all this compare the the letter logs as per the default compareTo logic
            }
        };
        Arrays.sort(logs, comparator);
        return logs;
    }

    public static void main(String[] args) {
        assertEquals(reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"}), new String[]{"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"});
    }
}