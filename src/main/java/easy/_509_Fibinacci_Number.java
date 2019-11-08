package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/31/19.
 * <p>
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1.
 * </p>
 */
public class _509_Fibinacci_Number {
    // simple iterative approach
    private static int fib(int N) {
        if (N <= 1) {
            return N;
        }
        int fib1 = 0, fib2 = 1;
        int result = 0;

        for (int i = 0; i <= N - 2; i++) {
            result = fib1 + fib2;
            fib1 = fib2;
            fib2 = result;
        }
        return result;
    }

    // simple recursion. TC: O(2^n), SC: O(N) -- stack call space
    private static int fib2(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return fib2(N - 2) + fib2(N - 1);
        }
    }

    // memoization: TC: O(n), SC: O(n)
    static int[] memo = new int[31];  // max int possible. if you want you can chose it to N as well
    private static int fib3(int N) {
        if (N <= 1) {
            return N;
        } else if (memo[N] != 0) {
            return memo[N];
        } else {
            memo[N] = fib3(N - 2) + fib3(N - 1);
            return memo[N];
        }
    }

    public static void main(String[] args) {
        assertEquals(fib(1), 1);
        assertEquals(fib(2), 1);
        assertEquals(fib(3), 2);
        assertEquals(fib(5), 5);

        assertEquals(fib2(1), 1);
        assertEquals(fib2(2), 1);
        assertEquals(fib2(3), 2);
        assertEquals(fib2(5), 5);

        assertEquals(fib3(1), 1);
        assertEquals(fib3(2), 1);
        assertEquals(fib3(3), 2);
        assertEquals(fib3(5), 5);
    }
}