// friendlier tree creation

class Node {

    Node() { }

    Node(Object value, Node leftChild = null, Node rightChild = null) {
        this.value = value
        if (leftChild) setLeftChild(leftChild)
        if (rightChild) setRightChild(rightChild)
    }

    Node parent
    Node leftChild
    Node rightChild
    Object value

    String toString() { value as String }

    void setLeftChild(Node c) {
        leftChild = c
        c.parent = this
    }
    void setRightChild(Node c) {
        rightChild = c
        c.parent = this
    }
}

def preOrderPrint
preOrderPrint = { Node node, int depth=0 ->
    if (depth > 1) print("  " * depth)
    if (depth > 0) print "|---"
    println node
    ++depth
    if (node?.leftChild) preOrderPrint( node.leftChild, depth )
    if (node?.rightChild) preOrderPrint( node.rightChild, depth )
}.trampoline()


def tree = new Node(1,
                    new Node(2,
                            new Node(4),
                            new Node(5)),
                    new Node(3,
                            new Node(6),
                            new Node(7)))

preOrderPrint(tree)
