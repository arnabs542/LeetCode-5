package easy;

/**
 * Created by udaythota on 7/9/19.
 * <p>
 * Reverse a singly linked list.
 * </p>
 */
public class _206_Reverse_LinkedList {
    // simple iterative process
    private static LinkedListUtils.ListNode reverseList(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedListUtils.ListNode prev = null;
        LinkedListUtils.ListNode current = head;
        while (current != null) {
            LinkedListUtils.ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    // simple recursive process
    // TC: O(n)
    // SC: O(n) - due to recursion function stack call
    private static LinkedListUtils.ListNode reverseList2(LinkedListUtils.ListNode head) {
        return helper(head, null);
    }

    private static LinkedListUtils.ListNode helper(LinkedListUtils.ListNode current, LinkedListUtils.ListNode prev) {
        if (current == null) {
            return prev;
        }
        LinkedListUtils.ListNode nextNode = current.next;
        current.next = prev;
        return helper(nextNode, current);
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();

        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(5);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);

        LinkedListUtils.ListNode headAfterReversing = reverseList(head);
        linkedListUtils.printList(headAfterReversing);

        LinkedListUtils.ListNode headAfterReversing2 = reverseList2(head);
        linkedListUtils.printList(headAfterReversing2);
    }
}