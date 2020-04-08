package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/8/20.
 * <p>
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 * The rules of Goat Latin are as follows:
 * <p>
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 * <p>
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 * <p>
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 * </p>
 */
public class _824_Goat_Latin {
    // core logic: just modify the string builder based on the given conditions
    private static String toGoatLatin(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        if (input == null || input.length() == 0) {
            return stringBuilder.toString();
        }

        String vowels = "aeiouAEIOU";   // if indexOf functions seems a bit complex, add all these characters to a set and use set contains method
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (vowels.indexOf(words[i].charAt(0)) != -1) {
                stringBuilder.append(words[i]);
            } else {
                stringBuilder.append(words[i].substring(1));
                stringBuilder.append(words[i].charAt(0));
            }

            stringBuilder.append("ma");
            for (int j = 0; j <= i; j++) {
                stringBuilder.append("a");
            }
            stringBuilder.append(" ");  // add space at end of each word
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();  // to make sure we delete the extra space in the last word
    }

    public static void main(String[] args) {
        Assert.assertEquals(toGoatLatin("I speak Goat Latin"), "Imaa peaksmaaa oatGmaaaa atinLmaaaaa");
        Assert.assertEquals(toGoatLatin("The quick brown fox jumped over the lazy dog"), "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa");
    }
}
