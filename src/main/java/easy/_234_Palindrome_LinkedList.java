package easy;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 7/13/19.
 * <p>
 * Given a singly linked list, determine if it is a palindrome.
 * </p>
 */
public class _234_Palindrome_LinkedList {

    // core logic: reverse the 2nd half of list and compare them with the elements in first half. when they are unequal return false, else return true
    private static boolean isPalindrome(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        LinkedListUtils.ListNode slow = head;
        LinkedListUtils.ListNode fast = head;

        while (fast != null && fast.next != null) {   // navigate to the middle of the list using 2 pointers approach
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {   // this means there are odd number of nodes in the list, so move the slow pointer by one more step (eg: when list: 1->2->3->2->1, and when slow pointer is at 3, move it one step forward to 2 so you can reverse and compare the 2 elements)
            slow = slow.next;
        }

        slow = reverse(slow);
        fast = head;

        while (slow != null) {   // compare elements in the first and second half's
            if (fast.val != slow.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    // simple utility function to reverse the linked list
    private static LinkedListUtils.ListNode reverse(LinkedListUtils.ListNode head) {
        LinkedListUtils.ListNode prev = null;
        while (head != null) {
            LinkedListUtils.ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(1);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);

        assertTrue(isPalindrome(head));
    }
}