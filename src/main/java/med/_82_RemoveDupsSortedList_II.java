package _5_Longest_Palindromic_Substring;

/**
 * Created by udaythota on 5/26/19.
 * <p>
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * <p>
 * Input: 1->1->1->2->3
 * Output: 2->3
 * </p>
 */
public class _82_RemoveDupsSortedList_II {

    private static LinkedListUtils.ListNode deleteDuplicates(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedListUtils.ListNode dummyNode = new LinkedListUtils.ListNode(0);  // this avoids lots of corner cases like duplicates for the first element, etc
        dummyNode.next = head;
        LinkedListUtils.ListNode prev = dummyNode;
        LinkedListUtils.ListNode current = head;

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {  // duplicates encountered : navigate till they are gone
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                current = current.next;
                prev.next = current;
            } else {   // when no duplicates: just keep incrementing prev and current
                current = current.next;
                prev = prev.next;
            }
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();

        // test 1
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node5 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node6 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node7 = new LinkedListUtils.ListNode(5);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);
        linkedListUtils.addToTheLast(node5);
        linkedListUtils.addToTheLast(node6);
        linkedListUtils.addToTheLast(node7);

        LinkedListUtils.ListNode headAfterRemovingDups = deleteDuplicates(head);
        linkedListUtils.printList(headAfterRemovingDups);

        // test 2
        LinkedListUtils.ListNode head2 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node8 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node9 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node10 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node11 = new LinkedListUtils.ListNode(3);

        linkedListUtils.addToTheLast(head2);
        linkedListUtils.addToTheLast(node8);
        linkedListUtils.addToTheLast(node9);
        linkedListUtils.addToTheLast(node10);
        linkedListUtils.addToTheLast(node11);
        System.out.println();

        LinkedListUtils.ListNode head2AfterRemovingDups = deleteDuplicates(head2);
        linkedListUtils.printList(head2AfterRemovingDups);

        // test 3
        LinkedListUtils.ListNode head3 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node12 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node13 = new LinkedListUtils.ListNode(2);

        linkedListUtils.addToTheLast(head3);
        linkedListUtils.addToTheLast(node12);
        linkedListUtils.addToTheLast(node13);
        System.out.println();

        LinkedListUtils.ListNode head3AfterRemovingDups = deleteDuplicates(head3);
        linkedListUtils.printList(head3AfterRemovingDups);
    }
}
