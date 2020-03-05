package med;

import java.util.Stack;

/**
 * Created by udaythota on 10/30/19.
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * </p>
 */
public class _445_Add_Two_Numbers_II {
    // push both of the linked list numbers to stack and then process them accordingly
    private static LinkedListUtils.ListNode addTwoNumbers(LinkedListUtils.ListNode l1, LinkedListUtils.ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        LinkedListUtils.ListNode prev = null;
        int sum = 0, carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int num1 = 0, num2 = 0;
            if (!stack1.isEmpty()) {
                num1 = stack1.pop();
            }

            if (!stack2.isEmpty()) {
                num2 = stack2.pop();
            }

            sum = (carry + num1 + num2) % 10;
            carry = (carry + num1 + num2) / 10;
            LinkedListUtils.ListNode temp = new LinkedListUtils.ListNode(sum);
            temp.next = prev;
            prev = temp;
        }
        if (carry != 0) {
            LinkedListUtils.ListNode carryNode = new LinkedListUtils.ListNode(carry);
            carryNode.next = prev;
            prev = carryNode;
        }
        return prev;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head1 = new LinkedListUtils.ListNode(7);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);
        head1.next = node2;
        node2.next = node4;
        node4.next = node3;

        LinkedListUtils.ListNode head2 = new LinkedListUtils.ListNode(5);
        LinkedListUtils.ListNode node6 = new LinkedListUtils.ListNode(6);
        LinkedListUtils.ListNode node42 = new LinkedListUtils.ListNode(4);
        head2.next = node6;
        node6.next = node42;

        LinkedListUtils.ListNode newHead = addTwoNumbers(head1, head2);
        linkedListUtils.printList(newHead);
    }
}
