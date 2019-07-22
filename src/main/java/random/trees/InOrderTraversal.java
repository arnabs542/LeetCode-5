package random.trees;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by udaythota on 3/6/19.
 */
public class InOrderTraversal {

    static class BinaryTreeNode {
        BinaryTreeNode llink;
        BinaryTreeNode rlink;
        int data;

        BinaryTreeNode(int data) {
            this.data = data;
            this.llink = null;
            this.rlink = null;
        }
    }

    private void inOrderTraversal(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.llink);
        System.out.print(root.data + ",");
        inOrderTraversal(root.rlink);
    }

    private ArrayList<Integer> inOrderTraversalIterative(BinaryTreeNode root) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (root == null) {
            System.out.println("Empty tree. Can't process..");
            return resultList;
        }

        Stack<BinaryTreeNode> nodeStoreStack = new Stack<BinaryTreeNode>();
        BinaryTreeNode current = root;

        while (!nodeStoreStack.isEmpty() || current != null) {
            if (current != null) {
                nodeStoreStack.push(current);
                current = current.llink;
            } else {
                current = nodeStoreStack.pop();
                resultList.add(current.data);
                current = current.rlink;
            }
        }
        return resultList;
    }

    // simple and works but sometimes less counter intuitive
    private ArrayList<Integer> inOrderTraversalIterative2(BinaryTreeNode root) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (root == null) {
            System.out.println("Empty tree. Can't process..");
            return resultList;
        }

        Stack<BinaryTreeNode> nodeStoreStack = new Stack<BinaryTreeNode>();
        BinaryTreeNode current = root;

        while (current != null) {
            nodeStoreStack.push(current);
            current = current.llink;
        }

        while (!nodeStoreStack.isEmpty()) {
            current = nodeStoreStack.pop();
            resultList.add(current.data);

            current = current.rlink;
            while (current != null) {
                nodeStoreStack.push(current);
                current = current.llink;
            }
        }
        return resultList;
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

        InOrderTraversal inOrderTraversal = new InOrderTraversal();
        inOrderTraversal.inOrderTraversal(root);

        ArrayList<Integer> outputList = inOrderTraversal.inOrderTraversalIterative(root);
        System.out.println("\n" + outputList);

        ArrayList<Integer> outputList2 = inOrderTraversal.inOrderTraversalIterative2(root);
        System.out.println("\n" + outputList2);

    }
}
