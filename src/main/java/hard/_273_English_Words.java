package hard;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/5/19.
 * <p>
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 * </p>
 */
public class _273_English_Words {
    private static final String[] belowTen = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] belowTwenty = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] belowHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private static String numberToWords(int num) {
        if (num < 0) {
            return "";
        } else if (num == 0) {
            return "Zero";
        } else {
            return helper(num);
        }
    }

    private static String helper(int num) {
        String result;
        if (num < 10) {
            result = belowTen[num];
        } else if (num < 20) {
            result = belowTwenty[num % 10];
        } else if (num < 100) {
            result = belowHundred[num / 10] + " " + helper(num % 10);
        } else if (num < 1000) {
            result = helper(num / 100) + " Hundred " + helper(num % 100);
        } else if (num < 1000000) {
            result = helper(num / 1000) + " Thousand " + helper(num % 1000);
        } else if (num < 1000000000) {
            result = helper(num / 1000000) + " Million " + helper(num % 1000000);
        } else {
            result = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
        }
        return result.trim();
    }

    public static void main(String[] args) {
        assertEquals(numberToWords(123), "One Hundred Twenty Three");
        assertEquals(numberToWords(12345), "Twelve Thousand Three Hundred Forty Five");
        assertEquals(numberToWords(1234567), "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven");
        assertEquals(numberToWords(1234567891), "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");
    }
}