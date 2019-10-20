package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 7/25/19.
 * <p>
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * </p>
 */
public class _143_Reorder_LL {
    // core logic: follow below 3 steps:
    // 1) split the list in to half
    // 2) reverse the second list
    // 3) merge both the lists
    private static void reorderList(LinkedListUtils.ListNode head) {
        if (head == null) {
            return;
        }
        LinkedListUtils.ListNode slow = head;
        LinkedListUtils.ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        LinkedListUtils.ListNode head2 = reverseList(slow);  // reverse the 2nd list
        slow.next = null; // split the list: point the last node of first list to null
        merge2Lists(head, head2);
    }

    private static LinkedListUtils.ListNode reverseList(LinkedListUtils.ListNode head) {
        LinkedListUtils.ListNode prev = null;
        LinkedListUtils.ListNode current = head;
        while (current != null) {
            LinkedListUtils.ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // this logic would slightly change if the split was made in such a way that l2.length >= l1.length, but in this specific case, l1.length is always >= l2.length
    private static void merge2Lists(LinkedListUtils.ListNode head1, LinkedListUtils.ListNode head2) {
        while (head2 != null) {  // as first list will always be greater than equal to second list after splitting (equal length when number of nodes are even, first list is bigger when number of nodes are odd)
            LinkedListUtils.ListNode n1 = head1.next;
            LinkedListUtils.ListNode n2 = head2.next;
            head1.next = head2;
            head2.next = n1;
            head1 = n1;
            head2 = n2;
        }
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

        // original list
        linkedListUtils.printList(head);
        reorderList(head);
        System.out.println();
        // list after modification
        linkedListUtils.printList(head);
    }
}