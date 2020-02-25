package hard;

/**
 * Created by udaythota on 2/24/20.
 * <p>
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * </p>
 */
public class _25_Reverse_Nodes_K_Groups {
    // core logic: find the prev and next nodes for the every k group of nodes and reverse all the nodes between prev and next. better understood on paper.
    // see more explanation here: https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11440/Non-recursive-Java-solution-and-idea
    // TC: O(n)
    private static LinkedListUtils.ListNode reverseKGroup(LinkedListUtils.ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }
        LinkedListUtils.ListNode dummyNode = new LinkedListUtils.ListNode(-1);
        dummyNode.next = head;
        LinkedListUtils.ListNode begin = dummyNode;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverseNodes(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyNode.next;
    }

    // simple function to reverse all the nodes between begin and end (both begin and end exclusive)
    private static LinkedListUtils.ListNode reverseNodes(LinkedListUtils.ListNode begin, LinkedListUtils.ListNode end) {
        LinkedListUtils.ListNode curr = begin.next;
        LinkedListUtils.ListNode next, first;
        LinkedListUtils.ListNode prev = begin;
        first = curr;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
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
        LinkedListUtils.ListNode newHead = reverseKGroup(head, 3);
        LinkedListUtils.printList(newHead);
    }
}