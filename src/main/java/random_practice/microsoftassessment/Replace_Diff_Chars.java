package random_practice.microsoftassessment;

/**
 * Created by udaythota on 10/20/19.
 * <p>
 * when there is '?' in the string replace the chars with other char which is not same as adjacent chars
 * </p>
 */
public class Replace_Diff_Chars {
    private static String replaceChars(String input) {
        if (input == null || input.length() == 0) {
            return null;
        }

        char prev = input.charAt(0), next = input.charAt(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '?') {
                if (i > 0) {
                    prev = stringBuilder.charAt(i - 1);
                }
                if (i < input.length() - 1) {
                    next = input.charAt(i + 1);
                }

                char startChar = 'a';
                while (prev == startChar || next == startChar) {
                    startChar++;
                }
                stringBuilder.append(startChar);
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceChars("te??ee?da"));
        System.out.println(replaceChars("abc?d?e?f"));
    }
}