package random.trees;

/**
 * Created by udaythota on 3/14/19.
 * Simple Recursive Approach
 */
public class ValidateBST {
    private static boolean validateBST(BinaryTreeNode root) {
        return validateBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean validateBSTHelper(BinaryTreeNode root, int leftMin, int rightMax) {
        if (root == null) {
            return true;
        }

        if (root.data < leftMin || root.data > rightMax) {
            return false;
        } else {
            return validateBSTHelper(root.llink, leftMin, root.data) && validateBSTHelper(root.rlink, root.data, rightMax);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(7);
        BinaryTreeNode node1 = new BinaryTreeNode(4);
        BinaryTreeNode node2 = new BinaryTreeNode(9);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(5);
        BinaryTreeNode node5 = new BinaryTreeNode(8);
        BinaryTreeNode node6 = new BinaryTreeNode(10);

        root.llink = node1;
        root.rlink = node2;

        node1.llink = node3;
        node1.rlink = node4;

        node2.llink = node5;
        node2.rlink = node6;

        System.out.println(ValidateBST.validateBST(root));
    }
}
