package med;

/**
 * Created by udaythota on 5/26/19.
 * Commonly used linked list utility methods so as to reuse
 */
public class LinkedListUtils {

    private ListNode head;

    protected static class ListNode {
        protected int val;
        protected ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    protected void addToTheLast(ListNode listNode) {
        if (head == null) {
            head = listNode;
        } else {
            ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = listNode;
        }
    }

    protected void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
}
