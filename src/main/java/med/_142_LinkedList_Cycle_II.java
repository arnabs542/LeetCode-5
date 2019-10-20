package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/25/19.
 * <p>
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * </p>
 */
public class _142_LinkedList_Cycle_II {
    // core logic: first find if the cycle exists in the loop
    // if the cycle exists, move the head and meeting pointer (slow / fast) by one step till they reach same node. that point is the start node of the loop
    // m + k + n(i) = 2m + 2n + n(j) => m = n(i-j) + k. this means if we move the head by m steps, the fast pointer would have made (i-j) cycles and in the last cycle, it would have made k steps lesser which would be the beginning node in the list
    // see https://www.youtube.com/watch?v=-YiQZi3mLq0 and https://www.geeksforgeeks.org/find-first-node-of-loop-in-a-linked-list/ for detailed proof
    private static LinkedListUtils.ListNode detectCycle(LinkedListUtils.ListNode head) {
        if (head == null) {
            return null;
        }
        LinkedListUtils.ListNode slow = head;
        LinkedListUtils.ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {   // cycle encountered
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(0);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        assertEquals(detectCycle(head).val, 2);
    }
}