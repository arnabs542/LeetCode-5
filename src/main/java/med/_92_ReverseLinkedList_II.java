package med;

/**
 * Created by udaythota on 5/26/19.
 * <p>
 * Reverse a linked list from position m to n. Do it in one-pass.
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 * <p>
 * Input: 1->2->3->4->5->6->7, m = 3, n=7
 * Output: 1->2->7->6->5->4->3
 * </p>
 */
public class _92_ReverseLinkedList_II {
    // logic: keep swapping next elements and change the pointers accordingly
    private static LinkedListUtils.ListNode reverseBetween(LinkedListUtils.ListNode head, int m, int n) {
        if (head == null || head.next == null || m <= 0 || n <= 0) {
            return head;
        }
        LinkedListUtils.ListNode dummyNode = new LinkedListUtils.ListNode(0);
        dummyNode.next = head;
        LinkedListUtils.ListNode pre = dummyNode;
        LinkedListUtils.ListNode start = head;

        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
            start = start.next;
        }

        // flow of execution in each iteration (for the 2nd input): 1->2->3->4->5->6->7  |  1->2->4->3->5->6->7 | 1->2->5->4->3->6->7 | 1->2->6->5->4->3->7 | 1->2->7->6->5->4->3
        for (int i = 0; i < n - m; i++) {
            LinkedListUtils.ListNode current = start.next;
            start.next = current.next;
            current.next = pre.next;
            pre.next = current;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();

        // test 1
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node5 = new LinkedListUtils.ListNode(5);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);
        linkedListUtils.addToTheLast(node5);

        LinkedListUtils.ListNode headAfterRemovingDups = reverseBetween(head, 2, 4);
        linkedListUtils.printList(headAfterRemovingDups);

        // test 2
        LinkedListUtils.ListNode head2 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node6 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node7 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node8 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node9 = new LinkedListUtils.ListNode(5);
        LinkedListUtils.ListNode node10 = new LinkedListUtils.ListNode(6);
        LinkedListUtils.ListNode node11 = new LinkedListUtils.ListNode(7);

        linkedListUtils.addToTheLast(head2);
        linkedListUtils.addToTheLast(node6);
        linkedListUtils.addToTheLast(node7);
        linkedListUtils.addToTheLast(node8);
        linkedListUtils.addToTheLast(node9);
        linkedListUtils.addToTheLast(node10);
        linkedListUtils.addToTheLast(node11);

        LinkedListUtils.ListNode head2AfterRemovingDups = reverseBetween(head2, 3, 7);
        System.out.println();
        linkedListUtils.printList(head2AfterRemovingDups);
    }
}
