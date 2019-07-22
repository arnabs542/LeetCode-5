package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/8/19.
 * <p>
 * Count the number of prime numbers less than a non-negative number, n.
 * </p>
 */
public class _204_CountPrimes {

    // core logic: for every number (starting 2), if the index of corresponding number in boolean array is false (which means it is a prime), increment the count, else set all the positions of number multiples to false
    // algorithm: Sieve of Eratosthenes
    private static int countPrimes(int n) {
        int count = 0;
        boolean[] isMultipleOfPrime = new boolean[n];  // isMultipleOfPrime[i] store whether num i is dividable by a prime num < i
        for (int i = 2; i < n; i++) {
            if (!isMultipleOfPrime[i]) {   // if i not dividable by a previous num, it's a prime
                count++;
            }
            for (int j = i; j < n; j = j + i) {    // mark all multiples of i as non-prime: for i = 2, mark boolean[4], boolean[6], boolean[8], boolean[10], boolean[12], boolean[14], ... as false
                isMultipleOfPrime[j] = true;
            }
        }
        return count;
    }

    // core logic: similar to above approach, but with some optimization: we only iterate till sqrt(n)
    private static int countPrimes2(int n) {
        int count = 0;
        boolean[] notPrime = new boolean[n];
        for (int i = 2; i < Math.sqrt(n); i++) {   // NOTE: iterate only till sqrt(n). for i=2, fill 4, 6, 8, 10, 12, ..  for i=3, fill 6, 9, 12, 15, 18, ..
            if (!notPrime[i]) {
                for (int j = 2; j * i < n; j++) {
                    notPrime[j * i] = true;    // fill all the multiples of the number to true
                }
            }
        }

        // count all the primes (with 'false' values) in the boolean array
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assertEquals(countPrimes(10), 4);
        assertEquals(countPrimes2(10), 4);
    }
}