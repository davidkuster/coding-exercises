// finding the middle node of a doubly linked list

class DoublyLinkedList {
    Node head
    Node tail
}

class Node {
    Node prev
    Node next
    Object value

    String toString() { value as String }
}

def printNode
printNode = { Node node ->
  print "[${node?.prev}]${node}[${node?.next}] "
  if (node?.next) printNode.trampoline( node.next )
  else println "\n"
}.trampoline()

def printNodeReverse
printNodeReverse = { Node node ->
  print "[${node?.prev}]${node}[${node?.next}] "
  if (node?.prev) printNodeReverse.trampoline( node.prev )
  else println "\n"
}.trampoline()


def recurse
recurse = { List values, int size, int iter, Node head ->
    if (iter >= size) {
        // all values have been processed
        return head
    }
    else if (size - iter >= 1) {
        // adding next node
        def n = new Node(value: values[iter])
        head.next = n
        n.prev = head

        // continue working through the list
        recurse.trampoline(values, size, iter+1, n)
    }
}.trampoline()

Closure<DoublyLinkedList> buildList = { List values ->
    if (! values) return null
    def head = new Node(value: values[0])
    def tail = recurse(values, values.size(), 1, head)
    return new DoublyLinkedList(head: head, tail: tail)
}



def count
count = { Node n, int size=0 ->
    if (!n) return size
    else count.trampoline(n.next, ++size)
}.trampoline()


Closure<Node> findMiddleElement = { DoublyLinkedList list ->
    if (count(list.head) %2 != 1) {
        println "There are not an odd number of elements in this list. FAIL."
        return
    }

    println "There are an odd number of elements in this list. Finding the middle one..."

    Node head = list.head
    Node tail = list.tail

    while (head != tail) {
        head = head.next
        tail = tail.prev
    }

    println "Found the middle element, it is: ${head.value}"
}

DoublyLinkedList hundo = buildList((0..100).toList())
printNode(hundo.head)
printNodeReverse(hundo.tail)

findMiddleElement(hundo)


DoublyLinkedList allTheBenjamins = buildList((0..10000).toList())
//printNode(allTheBenjamins.head)
//printNodeReverse(allTheBenjamins.tail)

findMiddleElement(allTheBenjamins)