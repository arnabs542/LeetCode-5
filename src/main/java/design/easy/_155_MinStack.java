package design.easy;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/19/19.
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * </p>
 */
public class _155_MinStack {
    // trivial: using 2 stacks (one to maintain min values and other is a main stack which stores all all the values)
    // see alternate method below for slightly better approach (using one stack)
    static class MinStackUsing2Stacks {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        MinStackUsing2Stacks() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
            stack.push(x);
        }

        public void pop() {
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // uses only ONE stack (but still the core logic and memory used will be ~ same as above approach)
    // core logic: the trick here is how do we maintain the min and previous min values so min returns the right value
    // when pushing to the stack, whenever you encounter a value less than current min, push the current min and then the current value (which is new min) to the stack
    // when popping from the stack, if the value to be popped is equal to current min, pop the current value and pop it again and assign that value to the new min (as the immediate next value in the stack would be prev min)
    static class MinStack {
        Stack<Integer> stack;
        int min = Integer.MAX_VALUE;

        MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (x <= min) {   // push both the current min and new min to stack
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if (stack.peek() == min) {
                stack.pop();
                min = stack.pop();   // as the immediate next value in the stack would be prev min
            } else {
                stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public static void main(String[] args) {

        // test method: 1 (using 2 stacks)
        MinStackUsing2Stacks minStackUsing2Stacks = new MinStackUsing2Stacks();
        minStackUsing2Stacks.push(-2);
        minStackUsing2Stacks.push(0);
        minStackUsing2Stacks.push(-3);
        assertEquals(minStackUsing2Stacks.getMin(), -3);
        minStackUsing2Stacks.pop();
        assertEquals(minStackUsing2Stacks.top(), 0);
        assertEquals(minStackUsing2Stacks.getMin(), -2);

        // test method: 2 (using 1 stack)
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(minStack.getMin(), -3);
        minStack.pop();
        assertEquals(minStack.top(), 0);
        assertEquals(minStack.getMin(), -2);
    }
}