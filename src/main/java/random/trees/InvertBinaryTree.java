package random.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 3/13/19.
 * Invert Binary Tree - Recursive and Iterative
 */
public class InvertBinaryTree {

    private BinaryTreeNode invertBinaryTree(BinaryTreeNode root) {
        if (root != null) {
            invertBinaryTreeHelper(root);
        }
        return root;
    }

    private BinaryTreeNode invertBinaryTreeHelper(BinaryTreeNode root) {
        BinaryTreeNode temp = root.llink;
        root.llink = root.rlink;
        root.rlink = temp;

        if (root.llink != null) {
            invertBinaryTreeHelper(root.llink);
        }

        if (root.rlink != null) {
            invertBinaryTreeHelper(root.rlink);
        }
        return root;
    }

    private BinaryTreeNode invertBinaryTreeIterative(BinaryTreeNode root) {
        if (root == null) {
            return null;
        } else {
            Queue<BinaryTreeNode> nodeStoreQueue = new LinkedList<>();
            nodeStoreQueue.offer(root);

            while (!nodeStoreQueue.isEmpty()) {
                BinaryTreeNode treeNode = nodeStoreQueue.poll();

                if (treeNode.llink != null) {
                    nodeStoreQueue.offer(treeNode.llink);
                }
                if (treeNode.rlink != null) {
                    nodeStoreQueue.offer(treeNode.rlink);
                }

                BinaryTreeNode temp = treeNode.llink;
                treeNode.llink = treeNode.rlink;
                treeNode.rlink = temp;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(3);
        BinaryTreeNode node1 = new BinaryTreeNode(4);
        BinaryTreeNode node2 = new BinaryTreeNode(5);
        BinaryTreeNode node3 = new BinaryTreeNode(6);
        BinaryTreeNode node4 = new BinaryTreeNode(7);
        BinaryTreeNode node5 = new BinaryTreeNode(8);
        BinaryTreeNode node6 = new BinaryTreeNode(9);

        root.llink = node1;
        root.rlink = node2;

        node1.llink = node3;
        node1.rlink = node4;

        node2.llink = node5;
        node2.rlink = node6;

        System.out.println("Before inverting: ");
        LevelOrderTraversal.printLevelOrderTraversal(root);

        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
        BinaryTreeNode headAfterInverting = invertBinaryTree.invertBinaryTree(root);
        System.out.println("\nAfter inverting: ");
        LevelOrderTraversal.printLevelOrderTraversal(headAfterInverting);

        /* TESTING - ITERATIVE METHOD
        BinaryTreeNode headAfterInvertingIterative = invertBinaryTree.invertBinaryTreeIterative(root);
        System.out.println("\nAfter inverting. Iterative Method:");
        LevelOrderTraversal.printLevelOrderTraversal(headAfterInvertingIterative);*/
    }
}
