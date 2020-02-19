package med;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 2/18/20.
 * <p>
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * </p>
 */
public class _116_Populating_Next_Right_Pointers {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    // core logic: simple BFS / level order traversal. uses prev and curr to adjust the pointers as needed
    // this method uses extra space (queue). see the dfs method below which doesn't use any extra space
    private static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            Node prev = null, curr;
            for (int i = 0; i < queueSize; i++) {
                curr = queue.poll();
                if (prev != null) {
                    prev.next = curr;
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                prev = curr;
            }
        }
        return root;
    }

    // same as above logic, but it doesn't use curr and prev pointers
    private static Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (temp != null) {
                if (temp.right != null) {
                    temp.left.next = temp.right;
                    if (temp.next != null) {
                        temp.right.next = temp.next.left;   // adjust the right leaf next to left node of the adjacent neighbor when needed (eg: 5->6 is one such case in the below example)
                    }
                }
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
        }
        return root;
    }

    // same as above approach, but uses DFS.
    // no extra space involved, so much efficient
    private static Node connect3(Node root) {
        if (root != null) {
            if (root.right != null) {
                root.left.next = root.right;
                if (root.next != null) {
                    root.right.next = root.next.left;
                }
            }
            connect3(root.left);
            connect3(root.right);
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Node newHead = connect2(root);
    }
}