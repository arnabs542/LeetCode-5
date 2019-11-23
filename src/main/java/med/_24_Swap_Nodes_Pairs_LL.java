package med;

/**
 * Created by udaythota on 7/23/19.
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * </p>
 */
public class _24_Swap_Nodes_Pairs_LL {
    // core logic: move the first pointer to third node, second to first and save the first (pre)
    // once you shift the 2nd set of nodes, attach the pre to 2nd new head. continue the process till end of the nodes
    // better understood with an example
    private static LinkedListUtils.ListNode swapPairs(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListUtils.ListNode pre = new LinkedListUtils.ListNode(0);  // dummy pointer
        LinkedListUtils.ListNode first = head;
        LinkedListUtils.ListNode ret = head.next;   // to return the new head

        while (first != null && first.next != null) {
            LinkedListUtils.ListNode second = first.next;
            first.next = second.next;
            second.next = first;
            pre.next = second;
            pre = first;
            first = first.next;
        }
        return ret;
    }

    // simple recursive process
    private static LinkedListUtils.ListNode swapPairs2(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListUtils.ListNode newHead = head.next;
        head.next = swapPairs2(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        // test method: 1
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(4);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);

        LinkedListUtils.ListNode newHead = swapPairs(head);
        linkedListUtils.printList(newHead);

        // test method: 2
        LinkedListUtils.ListNode head2 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node5 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node6 = new LinkedListUtils.ListNode(4);

        linkedListUtils.addToTheLast(head2);
        linkedListUtils.addToTheLast(node4);
        linkedListUtils.addToTheLast(node5);
        linkedListUtils.addToTheLast(node6);

        LinkedListUtils.ListNode newHead2 = swapPairs(head2);
        System.out.println();
        linkedListUtils.printList(newHead2);
    }
}