package med;

/**
 * Created by udaythota on 2/17/20.
 * <p>
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * </p>
 */
public class _138_Copy_List_Random_Pointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        // added a new constructor to define a node
        public Node(int val) {
            this.val = val;
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    // core logic: 3 step process: step 1: iterate through the list and create duplicate / copy nodes. the duplicate nodes immediately follows the original node
    // step 2: create the random pointers for copy nodes whenever there is a random node pointer for the corresponding original node
    // step 3: separate the original and copy lists from the merged list and return the copy head
    // TC: O(n), SC: O(1)
    private Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node current = head;
        while (current != null) {    // step 1: create copy nodes (each copy node will be placed adjacent to its original node)
            Node nextNode = current.next;
            Node newNode = new Node(current.val);
            current.next = newNode;
            newNode.next = nextNode;
            current = nextNode;
        }

        current = head;
        while (current != null) {    // step 2: create random pointers for copy nodes
            if (current.random != null) {
                current.next.random = current.random.next;
            } else {
                current.next.random = null;
            }
            current = current.next.next;   // move current to next original node
        }

        current = head;
        Node copyHeadReturn = current.next;
        Node copy = copyHeadReturn;
        // the reason this is not current.next!=null is because last copy node will be adjacent to last original node
        while (copy.next != null) {    // step 3: separate the original and copy lists
            current.next = current.next.next;
            copy.next = copy.next.next;

            current = current.next;  //  move to next original node
            copy = copy.next;       // move to next copy node
        }
        current.next = current.next.next;  // to adjust for the last node
        return copyHeadReturn;
    }
}