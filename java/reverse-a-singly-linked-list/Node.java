// package...

public class Node {

    public Node() {}

    public Node(Integer value) {
        this.i = value;
    }

    public Node(Integer value, Node next) {
        this.i = value;
        this.next = next;
    }

    Node next;
    Integer i;

    @Override
    public String toString() {
        return Integer.toString(i);
    }


    public static void printNode(Node node) {
        String next = (node != null && node.next != null) ? node.next.toString() : "";
        System.out.println("{"+ node +"}["+ next +"]");

        if (node != null && node.next != null) {
            Node.printNode(node.next);
        }
    }


    public static Node createList(Node n, int listLength, int step) {
        if (step >= listLength) return n;

        Node next = new Node(++step);
        n.next = next;

        return Node.createList(next, listLength, step);
    }

}