package easy;

/**
 * Created by udaythota on 7/13/19.
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 */
public class _237_DeleteNode_LL {

    // core logic: just copy the value of next element to current element and adjust the current pointer
    private static void deleteNode(LinkedListUtils.ListNode node) {
        node.val = node.next.val;    // we need not check if next node is null as given that the node to be deleted will not be a last node
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(5);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(9);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);

        deleteNode(head);
        linkedListUtils.printList(head);
    }
}