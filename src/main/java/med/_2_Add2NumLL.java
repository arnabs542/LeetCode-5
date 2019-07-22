package med;

/**
 * Created by udaythota on 1/17/19.
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * </p>
 */
public class _2_Add2NumLL {

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

    private static void printList(Node head) {
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

    // NOTE:
    // 1) take care of left over carry in the end
    // 2) reset the first and second numbers (to zero) after each iteration so, when one of them is missing, it defaults to zero
    private static Node add2Numbers(Node head1, Node head2) {
        if (head1 == null && head2 == null)
            return null;

        if (head1 == null)
            return null;

        if (head2 == null)
            return null;

        Node temp1 = head1;
        Node temp2 = head2;
        Node dummyNode = new Node(0);
        Node temp = dummyNode;
        int firstNumber = 0, secondNumber = 0, carry = 0, sum = 0;

        while (temp1 != null || temp2 != null) {
            if (temp1 != null) {
                firstNumber = temp1.data;
                temp1 = temp1.next;
            }

            if (temp2 != null) {
                secondNumber = temp2.data;
                temp2 = temp2.next;
            }

            sum = (firstNumber + secondNumber + carry) % 10;
            carry = (firstNumber + secondNumber + carry) / 10;

            temp.next = new Node(sum);
            temp = temp.next;

            firstNumber = secondNumber = 0;  //  reset all the values back to zero
        }
        if (carry != 0) {       // NOTE: tricky part: handle the left over carry (if any) in the end
            temp.next = new Node(carry);
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {

        // CASE 1:
        Node head = new Node(2);
        Node head2 = new Node(5);

        _2_Add2NumLL add2NumLL = new _2_Add2NumLL();
        add2NumLL.addToLast(head);
        add2NumLL.addToLast(new Node(4));
        add2NumLL.addToLast(new Node(3));

        _2_Add2NumLL add2NumLL2 = new _2_Add2NumLL();
        add2NumLL2.addToLast(head2);
        add2NumLL2.addToLast(new Node(6));
        add2NumLL2.addToLast(new Node(4));

        printList(head);
        printList(head2);

        Node resultHead = _2_Add2NumLL.add2Numbers(head, head2);
        System.out.println();
        printList(resultHead);


        // CASE 2:
        Node head3 = new Node(5);
        Node head4 = new Node(5);

        add2NumLL.addToLast(head3);
        add2NumLL2.addToLast(head4);

        printList(head3);
        printList(head4);

        Node resultHead2 = _2_Add2NumLL.add2Numbers(head3, head4);
        System.out.println();
        printList(resultHead2);


        // CASE 3:
        Node head5 = new Node(9);
        Node head6 = new Node(1);

        add2NumLL.addToLast(head5);
        add2NumLL.addToLast(new Node(8));
        add2NumLL2.addToLast(head6);

        printList(head5);
        printList(head6);

        Node resultHead3 = _2_Add2NumLL.add2Numbers(head5, head6);
        System.out.println();
        printList(resultHead3);
    }
}
