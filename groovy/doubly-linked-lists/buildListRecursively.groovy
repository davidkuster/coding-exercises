// create doubly linked list recursively

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


def recurse
recurse = { List values, int size, int iter, Node head ->
    if (iter >= size) {
        // all values have been processed
        return
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


def buildList = { List values ->
    if (! values) return null
    def first = new Node(value: values[0])
    recurse(values, values.size(), 1, first)
    return first
}


Node head = buildList([1,2,3,4,5])
printNode(head)

Node hundo = buildList((1..100).toList())
printNode(hundo)

printNode(buildList([]))

printNode(buildList(['x']))

printNode(buildList(null))

Node allTheBenjamins = buildList((1..10000).toList())
printNode(allTheBenjamins)