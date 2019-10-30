package easy;

/**
 * Created by udaythota on 6/23/19.
 * <p>
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * </p>
 */
public class _21_Merge_Two_Sorted_Lists {

    // core logic: compare the first node of 1st list with 1st node of 2nd list, if l1<=l2, attach current (for the new list) to l1 and increment l1, else attach current to l2 and increment l2
    private static LinkedListUtils.ListNode mergeTwoLists(LinkedListUtils.ListNode l1, LinkedListUtils.ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        LinkedListUtils.ListNode newHead = new LinkedListUtils.ListNode(-1);
        LinkedListUtils.ListNode current = newHead;

        while (l1 != null && l2 != null) {   // till both the lists are not empty
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // unequal number of nodes in the input lists: if l1 is null (already reached end of the list), attach current to l2, else attach current to l1
        current.next = l1 == null ? l2 : l1;
        return newHead.next;
    }

    // simple recursive approach
    private static LinkedListUtils.ListNode mergeTwoListsRecursive(LinkedListUtils.ListNode l1, LinkedListUtils.ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head1 = new LinkedListUtils.ListNode(1);
        linkedListUtils.addToTheLast(head1);
        linkedListUtils.addToTheLast(new LinkedListUtils.ListNode(2));
        linkedListUtils.addToTheLast(new LinkedListUtils.ListNode(4));

        LinkedListUtils linkedListUtils2 = new LinkedListUtils();
        LinkedListUtils.ListNode head2 = new LinkedListUtils.ListNode(1);
        linkedListUtils2.addToTheLast(head2);
        linkedListUtils2.addToTheLast(new LinkedListUtils.ListNode(3));
        linkedListUtils2.addToTheLast(new LinkedListUtils.ListNode(4));

        linkedListUtils.printList(head1);
        System.out.println();
        linkedListUtils2.printList(head2);

        // test method: 1
        System.out.println("\n" + "New list after merging using iterative approach is: ");
        LinkedListUtils.ListNode newHead = mergeTwoLists(head1, head2);
        linkedListUtils.printList(newHead);

        // test method: 2  (FIXME: make sure you only uncomment one of the iterative / recursive approach and test it. Some bug in linked list utils in running both of it together. Fix it later)
        /*System.out.println("\n" + "New list after merging using recursive approach is: ");
        LinkedListUtils.ListNode newHead1 = mergeTwoListsRecursive(head1, head2);
        linkedListUtils.printList(newHead1);*/
    }
}
