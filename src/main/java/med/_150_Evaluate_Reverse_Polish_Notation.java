package med;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/25/19.
 * <p>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * </p>
 */
public class _150_Evaluate_Reverse_Polish_Notation {

    // core logic: when it a number: push to stack, else pop the last 2 integers, calculate the result and push the result back to stack
    private static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return -1;
        }
        int num1, num2;
        Stack<Integer> stack = new Stack<>();   // NOTE: stack with integers is more OPTIMAL here than stack with strings

        for (String token : tokens) {
            switch (token) {
                case "+":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 + num1);
                    break;

                case "-":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 - num1);
                    break;

                case "*":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 * num1);
                    break;

                case "/":
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 / num1);
                    break;

                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        assertEquals(evalRPN(new String[]{"2", "1", "+", "3", "*"}), 9);
        assertEquals(evalRPN(new String[]{"4", "13", "5", "/", "+"}), 6);
        assertEquals(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}), 22);
    }
}