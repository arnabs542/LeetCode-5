package easy;

/**
 * Created by z002jsf on 2/25/20.
 * <p>
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * </p>
 */
public class _83_Remove_Duplicates_Sorted_List {
    private static LinkedListUtils.ListNode deleteDuplicates(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedListUtils.ListNode dummyHead = new LinkedListUtils.ListNode(-1);
        dummyHead.next = head;
        LinkedListUtils.ListNode current = head;

        while (current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        head.next = node1;
        node1.next = node2;

        LinkedListUtils.ListNode newHead = deleteDuplicates(head);
        LinkedListUtils.printList(newHead);
    }
}