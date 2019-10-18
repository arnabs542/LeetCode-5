package random_practice.microsoftassessment;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/17/19.
 * <p>
 * Return the max possible value after inserting '5' digit inside the decimal representation
 * </p>
 */
public class MaxPossibleValue {
    private static int solution(int N) {
        boolean isNegative = N < 0;
        boolean isAdded = false;
        N = Math.abs(N);
        int result = 0;
        Stack<Integer> stack = new Stack<>();

        if (N == 0) {
            stack.push(0);  // corner case: to handle the input '0'
        }

        while (N != 0) {   // first push the digits to stack
            stack.push(N % 10);
            N = N / 10;
        }

        while (!stack.isEmpty()) {
            if (!isAdded && !stack.isEmpty() && (isNegative ? 5 < stack.peek() : 5 > stack.peek())) {   // push to stack appropriately based on if the number is negative or not
                stack.push(5);
                isAdded = true;
            }
            result = result * 10 + stack.pop();   // calculate the number
        }

        if (!isAdded) {
            result = result * 10 + 5;
        }

        return isNegative ? -result : result;  // return the negative result if the input is a negative number
    }

    public static void main(String[] args) {
        assertEquals(solution(268), 5268);
        assertEquals(solution(670), 6750);
        assertEquals(solution(0), 50);
        assertEquals(solution(-999), -5999);
    }
}