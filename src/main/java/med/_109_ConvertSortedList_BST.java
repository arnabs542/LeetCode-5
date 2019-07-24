package med;

/**
 * Created by udaythota on 7/24/19.
 * <p>
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * </p>
 */
public class _109_ConvertSortedList_BST {
    // find the root (centre of the list) for the tree and recursively balance the left and right sub trees
    private static TreeNode sortedListToBST(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        return helper(head, null);
    }

    // simple helper method to balance the left and right sub trees
    private static TreeNode helper(LinkedListUtils.ListNode head, LinkedListUtils.ListNode tail) {
        LinkedListUtils.ListNode slow = head;
        LinkedListUtils.ListNode fast = head;

        if (head == tail) {  // base case: return when head reaches to tail
            return null;
        }

        while (fast != tail && fast.next != tail) {   // navigate to centre of the list (which will be the root)
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);  // left sub tree
        root.right = helper(slow.next, tail); // right sub tree
        return root;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(-10);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(-3);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(0);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(5);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(9);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);

        TreeNode root = sortedListToBST(head);
        BinaryTreeUtils.printLevelOrder(root);
    }
}