package easy;

import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 7/6/19.
 * <p>
 * Given a linked list, determine if it has a cycle in it.
 * </p>
 */
public class _141_LinkedListCycle {

    // core logic: 2 pointers approach using floyd's algorithm
    // if slow and fast pointers enter in to a cycle they should eventually meet
    // TC: O(n+k) ~ O(n) -> where k is the cyclic length
    private static boolean hasCycle(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        LinkedListUtils.ListNode slowPointer = head;
        LinkedListUtils.ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();

        // test 1
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(0);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(-4);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);
        linkedListUtils.addToTheLast(node2);
        assertTrue(hasCycle(head));
    }
}
