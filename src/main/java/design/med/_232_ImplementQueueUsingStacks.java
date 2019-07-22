package design.med;

import java.util.Stack;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Created by udaythota on 6/19/19.
 * <p>
 * Implement the following operations of a queue using stacks.
 * <p>
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * </p>
 */
public class _232_ImplementQueueUsingStacks {

    // core logic: swap elements as needed between 2 stacks so as to get the appropriate order of elements (make sure the order of elements gets adjusted while pushing itself)
    // TC: O(n): Each element, with the exception of the newly arrived, is pushed and popped twice. The last inserted element is popped and pushed once. Therefore this gives 4n+2 operations where n is the queue size. The push and pop operations have O(1) time complexity.
    static class QueueUsingStacks {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;
        private int front;

        private QueueUsingStacks() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            if (stack1.isEmpty()) {
                front = x;
            }

            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack2.push(x);

            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        public int pop() {
            int pop = stack1.pop();
            if (!stack1.isEmpty()) {
                front = stack1.peek();
            }
            return pop;
        }

        public int peek() {
            return stack1.peek();
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }

    // TC: O(1) - see https://leetcode.com/problems/implement-queue-using-stacks/solution/ (3rd solution) for detailed amortized analysis
    // core logic: you always keep pushing to input stack and only when needed (peek / pop operations), you move the elements from input to output stack as needed to get the right ordering of elements
    // move elements from input stack to output stack when needed, i.e., when I need to peek/pop but the output stack is empty. When that happens, I move all elements from input to output stack, thereby reversing the order so it's the correct order for peek/pop.
    // The loop in peek does the moving from input to output stack. Each element only ever gets moved like that once, though, and only after we already spent time pushing it, so the overall amortized cost for each operation is O(1).
    static class QueueUsingStacksAmortized {
        private Stack<Integer> input;
        private Stack<Integer> output;

        private QueueUsingStacksAmortized() {
            input = new Stack<>();
            output = new Stack<>();
        }

        public void push(int x) {
            input.push(x);
        }

        public int pop() {
            peek();
            return output.pop();
        }

        public int peek() {
            if (output.empty()) {    // move the elements from input to output stack only when output stack is empty as irrespective of number of elements in input, the next element in the output stack is next element of the queue (first come first serve)
                while (!input.empty()) {
                    output.push(input.pop());
                }
            }
            return output.peek();
        }

        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }
    }

    public static void main(String[] args) {
        // test method 1
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.push(1);
        queue.push(2);
        assertEquals(queue.peek(), 1);
        assertEquals(queue.pop(), 1);
        assertFalse(queue.empty());

        // test method 2
        QueueUsingStacksAmortized queue1 = new QueueUsingStacksAmortized();
        queue1.push(1);
        queue1.push(2);
        queue1.push(3);
        assertEquals(queue1.peek(), 1);
        assertEquals(queue1.pop(), 1);
        assertFalse(queue1.empty());
        queue1.push(4);
        assertEquals(queue1.peek(), 2);
        assertEquals(queue1.pop(), 2);
    }
}