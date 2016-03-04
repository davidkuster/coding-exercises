// package...

public class Reverse {

    public static void main(String[] args) {
        reverseSmallList();
        reverseLargeList();
    }

    public static void reverseSmallList() {
        Node tail = new Node(5);
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, tail))));

        System.out.println("Original list:");
        Node.printNode(head);

        Reverse r = new Reverse();
        r.reverse(head);

        System.out.println("Reversed list:");
        Node.printNode(tail);
    }

    public static void reverseLargeList() {
        Node head = new Node(0);
        //Node tail = Node.createList(head, 10000, 0);
        // hits a stack overflow ~5500

        Node tail = Node.createList(head, 2000, 0);

        System.out.println("Original list:");
        Node.printNode(head);

        Reverse r = new Reverse();
        r.reverse(head);

        System.out.println("Reversed list:");
        Node.printNode(tail);
    }


    public void reverse(Node first) {
        // if empty input or only a single node, nothing to do
        if (first == null || first.next == null) return;

        // first node is a special case because .next is null
        Node second = first.next;
        first.next = null;

        Node third = (second != null && second.next != null) ? second.next : null;

        if (third != null) {
            recurse(first, second, third);
        }
    }


    private void recurse(Node previous, Node current, Node next) {
        Node third = (next != null && next.next != null) ? next.next : null;

        // swap the pointers as we go
        next.next = current;
        current.next = previous;

        if (third != null) {
            this.recurse(current, next, third);
        }
    }

}