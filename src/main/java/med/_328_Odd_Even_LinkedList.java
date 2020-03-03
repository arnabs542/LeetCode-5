package med;

/**
 * Created by udaythota on 7/15/19.
 * <p>
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * </p>
 */
public class _328_Odd_Even_LinkedList {
    // core logic: save the odd (first node) and even head (second node) in the beginning. iterate through the list and start arranging the odd nodes and even nodes separately. finally attach the end of odd node to the even head (which we saved in the beginning)
    // eg: original list: 1->2->3->4->5. arrange the odd nodes: 1->3->5 and the even nodes: 2->4 as you iterate through the list. finally (after end of the loop), attach the end of odd node list 5 to the beginning of even nodes list 2
    private static LinkedListUtils.ListNode oddEvenList(LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListUtils.ListNode odd = head;
        LinkedListUtils.ListNode even = head.next;
        LinkedListUtils.ListNode evenHead = even;  // this is useful to attach the end of odd node

        while (even != null && even.next != null) {   // NOTE: this is an important observation (equivalent to: odd.next != null && odd.next.next != null)
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;   // end of odd nodes should be linked to beginning of even nodes
        return head;
    }

    public static void main(String[] args) {
        LinkedListUtils linkedListUtils = new LinkedListUtils();
        LinkedListUtils.ListNode head = new LinkedListUtils.ListNode(1);
        LinkedListUtils.ListNode node1 = new LinkedListUtils.ListNode(2);
        LinkedListUtils.ListNode node2 = new LinkedListUtils.ListNode(3);
        LinkedListUtils.ListNode node3 = new LinkedListUtils.ListNode(4);
        LinkedListUtils.ListNode node4 = new LinkedListUtils.ListNode(5);

        linkedListUtils.addToTheLast(head);
        linkedListUtils.addToTheLast(node1);
        linkedListUtils.addToTheLast(node2);
        linkedListUtils.addToTheLast(node3);
        linkedListUtils.addToTheLast(node4);

        System.out.println("Actual linked list:");
        linkedListUtils.printList(head);
        LinkedListUtils.ListNode newHead = oddEvenList(head);
        System.out.println("\nLinked list after re-arranging the nodes:");
        linkedListUtils.printList(newHead);
    }
}