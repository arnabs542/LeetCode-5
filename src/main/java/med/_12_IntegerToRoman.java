package med;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.testng.Assert.assertEquals;

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
    // check the below 2nd solution for the optimal one
    private static String intToRoman(int num) {
        StringBuilder resultString = new StringBuilder();
        TreeMap<Integer, String> romanNumberMap = new TreeMap<Integer, String>();  // to make sure keys are sorted

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

        List<Integer> numberList = new ArrayList<Integer>(romanNumberMap.keySet());

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

    // core logic: the meat of the problem lies in assuming the roman and values arrays properly
    // the roman number is formed by appending (or adding) the higher letters first and then the lower letters. this is true for all the cases except 6 cases (4, 9, 40, 90, 400, 900) where subtraction is needed (or lower numbers are appended first)
    // to handle this special case, just create this 6 numbers as constants in the array and keep the logic as is
    private static String intToRoman2(int num) {
        if (num < 1 || num > 3999) {
            return "";
        }

        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        while (num > 0) {
            while (num >= values[i]) {
                stringBuilder.append(roman[i]);
                num -= values[i];
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        assertEquals(intToRoman(2000), "MM");
        assertEquals(intToRoman2(2000), "MM");
        assertEquals(intToRoman2(2548), "MMDXLVIII");
    }
}