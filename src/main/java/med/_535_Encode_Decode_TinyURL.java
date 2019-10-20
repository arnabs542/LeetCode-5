package _5_Longest_Palindromic_Substring;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/10/19.
 * <p>
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * </p>
 */
public class _535_Encode_Decode_TinyURL {

    private static final String BASE_HOST = "http://tinyurl.com/";
    private static final HashMap<String, String> longToShort = new HashMap<>();
    private static final HashMap<String, String> shortToLong = new HashMap<>();
    private static int counter = 1;

    // Encodes a URL to a shortened URL.
    // for every request, get its id (incrementing sequence) and convert that to base62 format
    // TC: O(logn) (to be more precise logn to the base 62), SC: O(1)
    private static String encode(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return longToShort.get(longUrl);
        }
        String shortURL = BASE_HOST + convertDecimalToBase62(counter++);
        longToShort.put(longUrl, shortURL);
        shortToLong.put(shortURL, longUrl);
        return shortURL;
    }

    // Decodes a shortened URL to its original URL.
    private static String decode(String shortUrl) {
        return shortToLong.get(shortUrl);
    }

    // convert the integer to a base62 format
    private static String convertDecimalToBase62(int id) {
        final char[] base62Array = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        while (id > 0) {
            stringBuilder.append(base62Array[id % 62]);
            id = id / 62;
        }
        return stringBuilder.reverse().toString();  // don't forget to reverse it
    }

    private static final HashMap<String, String> longToShort2 = new HashMap<>();
    private static final HashMap<String, String> shortToLong2 = new HashMap<>();

    // Encodes a URL to a shortened URL.
    // convert every new long url to a 6 digit random string (using base 62 notion) and when collision occur, repeat the same process till it is unique
    private static String encode2(String longUrl) {
        if (longToShort2.containsKey(longUrl)) {
            return longToShort2.get(longUrl);
        }

        String shortURL = generateRandomURL(longUrl);
        while (shortToLong2.containsKey(shortURL)) {   // to avoid collisions
            shortURL = generateRandomURL(longUrl);
        }
        longToShort2.put(longUrl, shortURL);
        shortToLong2.put(shortURL, longUrl);
        return shortURL;
    }

    // Decodes a shortened URL to its original URL.
    private static String decode2(String shortUrl) {
        return shortToLong2.get(shortUrl);
    }

    private static String generateRandomURL(String longURL) {
        final String base62Array = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        int k = 6;
        for (int i = 0; i < k; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, base62Array.length());
            stringBuilder.append(base62Array.charAt(randomIndex));
        }
        return BASE_HOST + stringBuilder.toString();
    }

    public static void main(String[] args) {
        String input = "https://leetcode.com/problems/design-tinyurl";
        String tinyUrl = encode(input);
        System.out.println(tinyUrl);
        assertEquals(input, decode(tinyUrl));

        String input2 = "https://leetcode.com/problems/design-tinyurl";
        String tinyUrl2 = encode2(input);
        System.out.println(tinyUrl2);
        assertEquals(input2, decode2(tinyUrl2));
    }
}