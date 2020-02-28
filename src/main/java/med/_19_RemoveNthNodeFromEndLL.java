package med;

/**
 * Created by udaythota on 1/17/19.
 * Given a linked list, remove the n-th node from the end of list and return its head.
 */
public class _19_RemoveNthNodeFromEndLL {
    private Node head;

    private static class Node {
        Node next;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    private void addToLast(Node node) {
        if (head == null)
            head = node;
        else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    public void printList(Node head) {
        if (head == null)
            System.out.println("No elements in the linked list");
        else {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }
    }

    // see the below method for the slightly simplified version
    private static Node removeNthNodeFromEnd(Node head, int n) {
        boolean moveHead = false;
        if (n <= 0 || head == null) {
            return null;
        }

        if (head.next == null)
            return null;

        Node current = head;
        Node temp = head;
        for (int i = 0; i < n; i++) {
            if (temp.next != null) {
                temp = temp.next;
            } else {
                moveHead = true;   // to handle the special case of moving the head (when n is equal to size of the list)
            }
        }

        while (temp.next != null) {
            temp = temp.next;
            current = current.next;
        }

        if (current == head && moveHead) {
            head = current.next;
        } else {
            current.next = current.next.next;
        }
        return head;
    }

    // alternate solution: little simplified than the above solution
    private static Node removeNthNodeFromEnd2(Node head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        Node newHead = new Node(0);
        Node slow = newHead;
        Node fast = newHead;
        newHead.next = head;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return newHead.next;
    }

    public static void main(String[] args) {
        // CASE 1:
        Node head = new Node(1);
        _19_RemoveNthNodeFromEndLL removeNthNodeFromEndLL = new _19_RemoveNthNodeFromEndLL();
        removeNthNodeFromEndLL.addToLast(head);
        removeNthNodeFromEndLL.addToLast(new Node(2));
        removeNthNodeFromEndLL.addToLast(new Node(3));
        removeNthNodeFromEndLL.addToLast(new Node(4));
        removeNthNodeFromEndLL.addToLast(new Node(5));

        Node newHeadAfterDeletion = removeNthNodeFromEnd2(head, 3);
        System.out.println("CASE 1: ");
        removeNthNodeFromEndLL.printList(newHeadAfterDeletion);

        // CASE 2:
        Node head1 = new Node(1);
        _19_RemoveNthNodeFromEndLL removeNthNodeFromEndLL2 = new _19_RemoveNthNodeFromEndLL();
        removeNthNodeFromEndLL2.addToLast(head1);
        removeNthNodeFromEndLL2.addToLast(new Node(2));

        Node newHeadAfterDeletion2 = removeNthNodeFromEnd(head1, 2);
        System.out.println("\nCASE 2: ");
        removeNthNodeFromEndLL.printList(newHeadAfterDeletion2);

        // CASE 3:
        Node head3 = new Node(1);
        _19_RemoveNthNodeFromEndLL removeNthNodeFromEndLL3 = new _19_RemoveNthNodeFromEndLL();
        removeNthNodeFromEndLL3.addToLast(head3);
        removeNthNodeFromEndLL3.addToLast(new Node(2));

        Node newHeadAfterDeletion3 = removeNthNodeFromEnd(head1, 1);
        System.out.println("\nCASE 3: ");
        removeNthNodeFromEndLL.printList(newHeadAfterDeletion3);

        // CASE 4:
        Node head4 = new Node(1);
        _19_RemoveNthNodeFromEndLL removeNthNodeFromEndLL4 = new _19_RemoveNthNodeFromEndLL();
        removeNthNodeFromEndLL4.addToLast(head4);
        removeNthNodeFromEndLL4.addToLast(new Node(2));
        removeNthNodeFromEndLL4.addToLast(new Node(3));

        Node newHeadAfterDeletion4 = removeNthNodeFromEnd(head4, 2);
        System.out.println("\nCASE 4: ");
        removeNthNodeFromEndLL.printList(newHeadAfterDeletion4);

        // CASE 5:
        Node head5 = new Node(1);
        _19_RemoveNthNodeFromEndLL removeNthNodeFromEndLL5 = new _19_RemoveNthNodeFromEndLL();
        removeNthNodeFromEndLL5.addToLast(head5);
        removeNthNodeFromEndLL5.addToLast(new Node(2));
        removeNthNodeFromEndLL5.addToLast(new Node(3));

        Node newHeadAfterDeletion5 = removeNthNodeFromEnd(head5, 3);
        System.out.println("\nCASE 5: ");
        removeNthNodeFromEndLL.printList(newHeadAfterDeletion5);
    }
}