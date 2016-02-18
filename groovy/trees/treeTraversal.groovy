// tree traversal


class Node {
    Node parent
    Node leftChild
    Node rightChild
    Object value

    String toString() { value as String }

    void setRightChild(Node c) {
        rightChild = c
        c.parent = this
    }
    void setLeftChild(Node c) {
        leftChild = c
        c.parent = this
    }
}

def preOrderPrint
preOrderPrint = { Node node, int depth=0 ->
    if (node.parent)  print ("--" * depth)
    println node
    ++depth
    if (node?.leftChild) preOrderPrint( node.leftChild, depth )
    if (node?.rightChild) preOrderPrint( node.rightChild, depth )
}.trampoline()


def inOrderPrint
inOrderPrint = { Node node ->
    if (node?.leftChild) inOrderPrint( node.leftChild )
    println node
    if (node?.rightChild) inOrderPrint( node.rightChild )
}.trampoline()


def postOrderPrint
postOrderPrint = { Node node ->
    if (node?.leftChild) postOrderPrint( node.leftChild )
    if (node?.rightChild) postOrderPrint( node.rightChild )
    println node
}.trampoline()


def a = new Node(value: 1)
def b = new Node(value: 2)
def c = new Node(value: 3)
def d = new Node(value: 4)
def e = new Node(value: 5)
def f = new Node(value: 6)
def g = new Node(value: 7)

a.leftChild = b
a.rightChild = c

b.leftChild = d
b.rightChild = e

c.leftChild = f
c.rightChild = g


println "pre-order"
preOrderPrint(a)

println "\nin order"
inOrderPrint(a)

println "\npost order"
postOrderPrint(a)