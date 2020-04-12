package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * </p>
 */
public class _345_Reverse_Vowels_String {
    private static String reverseVowels(String s) {
        char[] charArray = s.toCharArray();
        String vowels = "aeiouAEIOU";
        int start = 0, end = s.length() - 1;
        while (start < end) {
            while (start < end && vowels.indexOf(charArray[start]) == -1) {
                start++;
            }
            while (start < end && vowels.indexOf(charArray[end]) == -1) {
                end--;
            }
            swap(charArray, start, end);  // swap when we encountered both the vowels
            start++;
            end--;
        }
        return new String(charArray);
    }

    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Assert.assertEquals(reverseVowels("hello"), "holle");
        Assert.assertEquals(reverseVowels("leetcode"), "leotcede");
    }
}
