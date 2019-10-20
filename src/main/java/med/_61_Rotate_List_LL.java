package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 7/23/19.
 * <p>
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * </p>
 */
public class _61_Rotate_List_LL {
    // core logic: connect the list into a circle:
    // first, count the length of list while going through the list to find the end of it. connect the tail to head.
    // The problem asked to rotate k nodes, however, now the tail is at the end of the list and its difficult to move backward, so move (k - len) nodes along the list instead.
    // k = k % length is to get the absolute k to handle the cases where k > length
    // TC: O(n), SC: O(1)
    private static LinkedListUtils.ListNode rotateRight(LinkedListUtils.ListNode head, int k) {
        if (head == null || head.next == null || k < 0) {
            return head;
        }

        LinkedListUtils.ListNode fast = head;
        int length = 1;
        while (fast.next != null) {   // get length of the list
            length++;
            fast = fast.next;
        }

        fast.next = head;  // make it a loop

        k = k % length;  // get absolute k (to handle k > length)
        for (int i = 0; i < length - k; i++) {   // move the fast pointer to length - k steps
            fast = fast.next;
        }

        head = fast.next;  // set the new head
        fast.next = null;  // fast will be the new last node in the list. so set that to null
        return head;
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
        LinkedListUtils.ListNode newHead = rotateRight(head, 2);
        linkedListUtils.printList(newHead);
    }
}