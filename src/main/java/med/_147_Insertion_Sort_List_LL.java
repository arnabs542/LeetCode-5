package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 7/25/19.
 * <p>
 * Sort a linked list using insertion sort.
 * </p>
 */
public class _147_Insertion_Sort_List_LL {
    // core logic: have a new list initiated with dummy head. iterate through the input list, and for every node in the list, iterate through the new list and start inserting the current node at the right position in the new list. repeat this till all the nodes in the input list are done
    private static LinkedListUtils.ListNode insertionSortList(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListUtils.ListNode dummy = new LinkedListUtils.ListNode(0);
        LinkedListUtils.ListNode pre = dummy;
        LinkedListUtils.ListNode current = head;

        while (current != null) {
            LinkedListUtils.ListNode nextNode = current.next;
            while (pre.next != null && pre.next.val < current.val) {
                pre = pre.next;
            }
            LinkedListUtils.ListNode temp = pre.next;
            pre.next = current;
            current.next = temp;
            pre = dummy;  // reset the pre to new head (dummy) for every iteration as each element in the input list should be compared from start of the new list
            current = nextNode;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.printList(head);
        System.out.println();

        insertionSortList(head);
        linkedListUtils.printList(head);  // new sorted list
    }
}