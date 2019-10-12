package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/11/19.
 * <p>
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
 * </p>
 */
public class _299_Bulls_Cows {
    // core logic: when secret char matches the guess char, increment the bull count
    // when it doesn't match, count logic: increment the count (hash) for secret char and decrement the count (hash) for the guess char
    // cows logic: for secret char, if count < 0, which means it was already guessed, so increment it and for guess char, if count > 0, which means it was already in secret, so again increment the count
    private static String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int hash[] = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bulls++;
            } else {
                if (hash[s] < 0) {  // means already guessed it (as guess decrements the char count)
                    cows++;
                }
                if (hash[g] > 0) {  // means already in secret (as secret increment the char count)
                    cows++;
                }

                hash[s]++;  // increment the secret
                hash[g]--;  // decrement the guess
            }
        }
        return bulls + "A" + cows + "B";
    }

    // uses more space but more easier to understand: save secret and guess counts separately when they don't match
    // when computing the cows count, take the minimum of secret and guess counts available (count of min common digits in both secret and guess)
    private static String getHint2(String secret, String guess) {
        int bulls = 0, cows = 0;
        int secretHash[] = new int[10];
        int guessHash[] = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bulls++;
            } else {
                secretHash[s]++;
                guessHash[g]++;
            }
        }

        for (int i = 0; i < secretHash.length; i++) {
            cows += Math.min(secretHash[i], guessHash[i]);
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        assertEquals(getHint("1807", "7810"), "1A3B");
        assertEquals(getHint("1123", "0111"), "1A1B");

        assertEquals(getHint2("1807", "7810"), "1A3B");
        assertEquals(getHint2("1123", "0111"), "1A1B");
    }
}