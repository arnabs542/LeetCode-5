package easy;

/**
 * Created by udaythota on 2/14/20.
 * <p>
 * Remove all elements from a linked list of integers that have value val.
 * </p>
 */
public class _203_Remove_LL_Elements {
    // core logic: whenever you encounter the value to be deleted, adjust the prev and curr pointers accordingly
    // NOTE: see the above solution which is more clear and easy to understand
    private static LinkedListUtils.ListNode removeElements(LinkedListUtils.ListNode head, int val) {
        LinkedListUtils.ListNode dummyHead = new LinkedListUtils.ListNode(0);
        dummyHead.next = head;
        LinkedListUtils.ListNode current = head;
        LinkedListUtils.ListNode prev = dummyHead;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
            } else {
                prev = prev.next;
            }
            current = current.next;
        }
        return dummyHead.next;
    }

    // same logic as above but uses only one pointer here. no prev pointer is needed
    // TC: O(n)
    private static LinkedListUtils.ListNode removeElements2(LinkedListUtils.ListNode head, int val) {
        LinkedListUtils.ListNode dummyHead = new LinkedListUtils.ListNode(0);
        dummyHead.next = head;
        LinkedListUtils.ListNode current = dummyHead;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node6 = new LinkedListUtils.ListNode(6);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node5 = new LinkedListUtils.ListNode(5);
        LinkedListUtils.ListNode node6Dup = new LinkedListUtils.ListNode(6);
        node1.next = node2;
        node2.next = node6;
        node6.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6Dup;
        LinkedListUtils.ListNode newHead = removeElements(node1, 6);
        LinkedListUtils.printList(newHead);
    }
}