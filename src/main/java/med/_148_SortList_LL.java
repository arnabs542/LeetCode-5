package med;

/**
 * Created by udaythota on 7/25/19.
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class _148_SortList_LL {
    // core logic: perform below 3 steps:
    // 1) split the lists
    // 2) sort both the lists individually using recursion
    // 3) merge both the lists
    // TC: O(nlogn)
    private static LinkedListUtils.ListNode sortList(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListUtils.ListNode slow = head;
        LinkedListUtils.ListNode fast = head;
        LinkedListUtils.ListNode pre = head;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;

        LinkedListUtils.ListNode head1 = sortList(head);
        LinkedListUtils.ListNode head2 = sortList(slow);
        return merge2Lists(head1, head2);
    }

    // simple utility function to merge the lists
    private static LinkedListUtils.ListNode merge2Lists(LinkedListUtils.ListNode head1, LinkedListUtils.ListNode head2) {
        LinkedListUtils.ListNode dummy = new LinkedListUtils.ListNode(0);
        LinkedListUtils.ListNode current = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;
        }
        if (head1 != null) {
            current.next = head1;
        }
        if (head2 != null) {
            current.next = head2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(5);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);
        linkedListUtils.printList(head);

        LinkedListUtils.ListNode newHead = sortList(head);
        System.out.println();

        // new sorted list
        linkedListUtils.printList(newHead);
    }
}