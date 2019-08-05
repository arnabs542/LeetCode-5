package hard;

import java.util.PriorityQueue;

/**
 * Created by udaythota on 8/4/19.
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * </p>
 */
public class _23_Merge_K_Sorted_Lists {
    // core logic: use min heap and add the all the lists (heads) to heap in the increasing order of head values
    // iterate through the heap and add the next elements and process them appropriately
    // TC: O(n), SC: O(n)
    private static LinkedListUtils.ListNode mergeKLists(LinkedListUtils.ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<LinkedListUtils.ListNode> queue = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);   // min heap
        for (LinkedListUtils.ListNode listNode : lists) {
            if (listNode != null) {
                queue.offer(listNode);
            }
        }

        LinkedListUtils.ListNode dummy = new LinkedListUtils.ListNode(0);
        LinkedListUtils.ListNode current = dummy;
        while (queue.size() != 0) {
            current.next = queue.poll();
            current = current.next;

            if (current.next != null) {
                queue.offer(current.next);    // as queue only contains heads. when you process the element at head, add the next element to heap, and keep continuing till all elements are done
            }
        }
        return dummy.next;
    }


    // core logic: divide and conquer. keep dividing the lists and merge them recursively using the merge 2 lists helper method
    // TC: O(nlogk)
    private static LinkedListUtils.ListNode mergeKLists2(LinkedListUtils.ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return helper(lists, 0, lists.length - 1);
    }

    private static LinkedListUtils.ListNode helper(LinkedListUtils.ListNode[] lists, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            LinkedListUtils.ListNode head1 = helper(lists, low, mid);
            LinkedListUtils.ListNode head2 = helper(lists, mid + 1, high);
            return mergeTwoLists(head1, head2);
        } else {
            return lists[low];
        }
    }

    // utility method to merge 2 lists
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

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head1 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(5);
        head1.next = node1;
        node1.next = node2;

        LinkedListUtils.ListNode head2 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(4);
        head2.next = node3;
        node3.next = node4;

        LinkedListUtils.ListNode head3 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node5 = new LinkedListUtils.ListNode(6);
        head3.next = node5;

        LinkedListUtils.ListNode[] input = new LinkedListUtils.ListNode[3];
        input[0] = head1;
        input[1] = head2;
        input[2] = head3;
        LinkedListUtils.ListNode newHead = mergeKLists(input);
        linkedListUtils.printList(newHead);

        /*LinkedListUtils.ListNode newHead2 = mergeKLists2(input);
        linkedListUtils.printList(newHead2);*/
    }
}