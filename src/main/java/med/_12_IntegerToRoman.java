package _5_Longest_Palindromic_Substring;

import java.util.*;

/**
 * Created by udaythota on 1/24/19.
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Example 1:
 * <p>
 * Input: 3
 * Output: "III"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "IV"
 * <p/>
 */
public class _12_IntegerToRoman {

    private static String intToRoman(int num) {

        StringBuilder resultString = new StringBuilder();

        HashMap<Integer, String> romanNumberMap = new HashMap<Integer, String>();
        romanNumberMap.put(1, "I");
        romanNumberMap.put(4, "IV");
        romanNumberMap.put(5, "V");
        romanNumberMap.put(9, "IX");
        romanNumberMap.put(10, "X");
        romanNumberMap.put(40, "XL");
        romanNumberMap.put(50, "L");
        romanNumberMap.put(90, "XC");
        romanNumberMap.put(100, "C");
        romanNumberMap.put(400, "CD");
        romanNumberMap.put(500, "D");
        romanNumberMap.put(900, "CM");
        romanNumberMap.put(1000, "M");

        if (num <= 0) {
            return resultString.toString();
        }

        if (romanNumberMap.containsKey(num)) {
            return romanNumberMap.get(num);
        }

        Set<Integer> keySetTemp = romanNumberMap.keySet();
        Set<Integer> keySet = new TreeSet<Integer>(keySetTemp);

        List<Integer> numberList = new ArrayList<Integer>(keySet);

        int i = 0;
        while (num != 0) {
            while (num >= 1000) {
                resultString.append("M");
                num -= 1000;
            }

            if (num == 0) {
                return resultString.toString();
            }

            while (num >= numberList.get(i)) {
                i++;
            }

            resultString.append(num == numberList.get(i) ? romanNumberMap.get(numberList.get(i)) : romanNumberMap.get(numberList.get(i - 1)));
            num -= num == numberList.get(i) ? numberList.get(i) : numberList.get(i - 1);
            i = 0;
        }
        return resultString.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(2000));
    }
}
