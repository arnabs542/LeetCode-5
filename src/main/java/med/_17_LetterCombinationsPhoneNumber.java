package med;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by udaythota on 1/22/19.
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * Example:
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * </p>
 */
public class _17_LetterCombinationsPhoneNumber {

    // NOTE: the key here is that, all the elements in the result set will have the length equal to the input string.
    // so in the first iteration, add all the elements corresponding to the first digit, and in each of the next corresponding iterations, keep adding each character of the new digit array to each element of the previous result array
    private static List<String> letterCombinations(String digits) {

        List<String> resultList = new ArrayList<String>();
        if (digits == null || digits.equals("") || digits.contains("1"))
            return resultList;

        String[] dataArray = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for (int i = 0; i < digits.length(); i++) {
            List<String> tempList = new ArrayList<String>();
            char[] numCharArray = dataArray[digits.charAt(i) - '0'].toCharArray();

            // so as the first iteration to make it succeed (when the result array is empty)
            if (resultList.isEmpty()) {
                resultList.add("");
            }

            // add all the new characters (from the current digit array) to all the elements of the current result array
            for (String str : resultList) {
                for (Character ch : numCharArray) {
                    tempList.add(str + ch);
                }
            }
            resultList = tempList;
        }
        return resultList;
    }

    // backtracking - dfs approach. similar to problem: 22
    private static List<String> letterCombinationsRecursive(String digits) {
        String[] dataArray = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        helper(digits, 0, new StringBuilder(), dataArray, result);
        return result;
    }

    private static void helper(String digits, int index, StringBuilder sb, String[] dataArray, List<String> result) {
        if (digits.length() == index) {
            result.add(sb.toString());
            return;
        }

        String number = dataArray[digits.charAt(index) - '0'];
        for (Character ch : number.toCharArray()) {
            sb.append(ch);
            helper(digits, index + 1, sb, dataArray, result);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        // test - iterative approach
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations("456"));
        System.out.println(letterCombinations("2"));
        System.out.println(letterCombinations("231"));  // should return empty list as it contains '1'
        System.out.println(letterCombinations(""));

        // test - recursive approach
        System.out.println(letterCombinationsRecursive("23"));
        System.out.println(letterCombinationsRecursive("456"));
        System.out.println(letterCombinationsRecursive("2"));
        System.out.println(letterCombinationsRecursive("231"));  // should return empty list as it contains '1'
        System.out.println(letterCombinationsRecursive(""));
    }
}