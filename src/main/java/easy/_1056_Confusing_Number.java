package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 5/16/20.
 * <p>
 * Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 * </p>
 */
public class _1056_Confusing_Number {
    // simple math
    // this is used as helper method in LC: 1088
    private static boolean confusingNumber(int num) {
        String numString = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for (char ch : numString.toCharArray()) {
            if (ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '7') {
                return false;
            } else if (ch == '6') {
                sb.append('9');
            } else if (ch == '9') {
                sb.append('6');
            } else {
                sb.append(ch);
            }
        }
        return !sb.reverse().toString().equals(numString);
    }

    public static void main(String[] args) {
        Assert.assertTrue(confusingNumber(6));
        Assert.assertTrue(confusingNumber(89));
        Assert.assertFalse(confusingNumber(11));
    }
}
