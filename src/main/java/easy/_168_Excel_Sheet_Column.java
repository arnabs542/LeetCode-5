package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * </p>
 */
public class _168_Excel_Sheet_Column {
    // simple math. just make sure to decrement 'n' appropriately
    // TC: O(logN)
    private static String convertToTitle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            n--;   // as we are adding 'A' to the result, we need to decrement n by one to get the right result
            stringBuilder.append((char) (n % 26 + 'A'));
            n /= 26;
        }

        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        Assert.assertEquals(convertToTitle(1), "A");
        Assert.assertEquals(convertToTitle(28), "AB");
        Assert.assertEquals(convertToTitle(701), "ZY");
    }
}
