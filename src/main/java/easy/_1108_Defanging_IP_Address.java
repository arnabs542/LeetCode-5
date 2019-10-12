package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/12/19.
 * <p>
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 * A defanged IP address replaces every period "." with "[.]".
 * </p>
 */
public class _1108_Defanging_IP_Address {
    // though this looks like one liner, it is not performance efficient: as internally strings are immutable, for each and every replace call, it will create new string making it O(n^2)
    // see the below solution for optimal approach
    private static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    // more optimal approach. use string builder, parse the string and whenever, you encounter the desired character, replace it with target character
    private static String defangIPaddr2(String address) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : address.toCharArray()) {
            if (ch == '.') {
                stringBuilder.append("[.]");
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        assertEquals(defangIPaddr("1.1.1.1"), "1[.]1[.]1[.]1");
        assertEquals(defangIPaddr("255.100.50.0"), "255[.]100[.]50[.]0");

        assertEquals(defangIPaddr2("1.1.1.1"), "1[.]1[.]1[.]1");
        assertEquals(defangIPaddr2("255.100.50.0"), "255[.]100[.]50[.]0");
    }
}