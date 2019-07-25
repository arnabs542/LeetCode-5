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
    // TC: O(nlogn): the recursive part is O(n), because T(n)=2T(n/2)+O(1). and in each recursive call, faster pointer traverse full list of logn, which leads to O(nlogn). so the total Time Complexity is O(nlogn)
    // NOTE: the below solution is probably more intuitive and readable
    private static TreeNode sortedListToBST(LinkedListUtils.ListNode head) {
        if (head == null) {
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

    // similar to above approach, but more intuitive and readable
    // find the root (centre of the list) and set the last node (pre: before root) of the first list to NULL. now that we have 2 lists, repeat the same process for left and right sub lists to generate the left and right sub trees
    // TC: O(logn) - see the detailed explanation here: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/solution/
    private static TreeNode sortedListToBST2(LinkedListUtils.ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        LinkedListUtils.ListNode slow = head;
        LinkedListUtils.ListNode fast = head;
        LinkedListUtils.ListNode pre = head;  // pointer to the node before slow (this is useful to set the last node the first list to NULL)

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST2(head);
        root.right = sortedListToBST2(slow.next);
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

        // test method: 1
        TreeNode root = sortedListToBST(head);
        BinaryTreeUtils.printLevelOrder(root);

        // test method: 2
        System.out.println();
        TreeNode root2 = sortedListToBST2(head);
        BinaryTreeUtils.printLevelOrder(root2);
    }
}