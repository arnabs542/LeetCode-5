package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/6/19.
 * <p>
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * </p>
 */
public class _160_Intersection_Two_LL {

    // core logic: find the length of 2 lists, navigate the head of the higher length list to length1 - length2. from there, move both the lists by one step and see if you encounter the same next node
    private static LinkedListUtils.ListNode getIntersectionNode(LinkedListUtils.ListNode headA, LinkedListUtils.ListNode headB) {
        int length1 = length(headA);
        int length2 = length(headB);

        // if length1 > length2 , move headA to length1-length2 steps (till both lengths are equal)
        while (length1 > length2) {
            headA = headA.next;
            length1--;
        }

        // if length2 > length1 , move headB to length2-length1 steps (till both lengths are equal)
        while (length2 > length1) {
            headB = headB.next;
            length2--;
        }

        // either encounters the same node or encounters the last node (which will be NULL)
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private static int length(LinkedListUtils.ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils linkedListUtils1 = new LinkedListUtils();

        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(8);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(5);

        LinkedListUtils.ListNode head2 = new LinkedListUtils.ListNode(5);
        LinkedListUtils.ListNode node7 = new LinkedListUtils.ListNode(0);
        LinkedListUtils.ListNode node8 = new LinkedListUtils.ListNode(1);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);

        linkedListUtils1.addToTheLast(head2);
        linkedListUtils1.addToTheLast(node7);
        linkedListUtils1.addToTheLast(node8);
        linkedListUtils1.addToTheLast(node2);

        assertEquals(getIntersectionNode(head, head2), node2);
    }
}
