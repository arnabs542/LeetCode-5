package design.easy;

import java.util.LinkedList;
import java.util.Queue;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Created by udaythota on 6/19/19.
 * <p>
 * Implement the following operations of a stack using queues.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * </p>
 */
public class _225_ImplementStackUsingQueues {

    // core logic: when inserting an element, add it to the queue and remove all the elements before it (size - 1) and add them back to the queue: we are basically making sure the new element gets added to the front instead of back of the queue
    // Just use a queue where you "push to front" by pushing to back and then rotating the queue until the new element is at the front (i.e., size-1 times move the front element to the back).
    // Time complexity : O(n). The algorithm removes n elements and inserts n+1 elements to queue , where n is the stack size. This gives 2n+1 operations. The operations add and remove in linked lists has O(1) complexity.
    // see the alternate approach below for stack using 2 queues
    static class Stack {
        Queue<Integer> queue;

        private Stack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            for (int i = 0; i < queue.size() - 1; i++) {   // NOTE: size -1 as you don't move the newly added element
                queue.offer(queue.remove());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    // stack using 2 queues
    // TC: O(n). The algorithm removes n elements from q1 and inserts n+1 elements to q2, where n is the stack size. This gives 2n+1 operations.
    static class StackUsing2Queues {
        Queue<Integer> queue1;
        Queue<Integer> queue2;
        int top;

        private StackUsing2Queues() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        // push new element to q2 and update top. move all the elements from q1 to q2. swap q1 and q2
        public void push(int x) {
            queue2.offer(x);
            top = x;

            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }

            // swap q1 and q2  (alternate approach: else simple thing is to assign q2 to q1 and instantiate new queue to q2)
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        public int pop() {
            int pop = queue1.poll();
            if (!queue1.isEmpty()) {   // NOTE: update top when you pop an element
                top = queue1.peek();
            }
            return pop;
        }

        public int top() {
            return top;
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    public static void main(String[] args) {

        // method 1: stack using one queue
        Stack stack = new _225_ImplementStackUsingQueues.Stack();
        stack.push(1);
        stack.push(2);
        assertEquals(stack.top(), 2);
        assertEquals(stack.pop(), 2);
        assertFalse(stack.empty());

        // method 2: stack using 2 queues
        StackUsing2Queues stackUsing2Queues = new _225_ImplementStackUsingQueues.StackUsing2Queues();
        stackUsing2Queues.push(1);
        stackUsing2Queues.push(2);
        assertEquals(stackUsing2Queues.top(), 2);
        assertEquals(stackUsing2Queues.pop(), 2);
        assertFalse(stackUsing2Queues.empty());
    }
}
