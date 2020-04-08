package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/8/20.
 * <p>
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 * If there are two middle nodes, return the second middle node.
 * </p>
 */
public class _876_Middle_LinkedList {
    // core logic: keep 2 pointers slow and fast. slow moves one pointer while fast moves 2 pointers at a time. by the time, fast would have reached to end, slow would be at the middle of the linked list
    // TC: O(n)
    private static LinkedListUtils.ListNode middleNode(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedListUtils.ListNode slow = head;
        LinkedListUtils.ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node5 = new LinkedListUtils.ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Assert.assertEquals(middleNode(head), node3);
    }
}
