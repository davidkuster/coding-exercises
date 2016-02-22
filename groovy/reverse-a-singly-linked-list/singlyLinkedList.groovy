// comparing my thoughts on how to answer this common question, vs what was found at the following link:
// http://www.programmerinterview.com/index.php/data-structures/reverse-a-linked-list/

// In the link above, the recursive solution works in an interview to prove you can think recursively,
// but doesn't work in a real-world scenario because it's prone to stack overflow errors.

class Node {
  Node() { }
  Node(Integer value, Node next = null) {
    this.i = value
    this.next = next
  }

  Node next
  Integer i
  String toString() { i as String }
}

def tail = new Node(5)
def head = new Node(1, new Node(2, new Node(3, new Node(4, tail))))


def printNode
printNode = { Node node ->
  print "${node}[${node?.next}] "
  if ( node?.next ) printNode.trampoline( node.next )
  else println "\n"
}.trampoline()

println "original linked list: "
printNode( head )


// tail recursive method
def recurse
recurse = { Node previous, Node current, Node next ->
  def third = next?.next
  // swap the pointers as we go
  next?.next = current
  current.next = previous
  if ( third )
    recurse.trampoline( current, next, third )
}.trampoline()

def reverse = { Node first ->
  // if empty input or only a single node, nothing to do
  if ( ! first?.next ) return

  // first node is a special case because .next is null
  def second = first.next
  first.next = null

  def third = second?.next
  // if we have more nodes, then process the rest of the list
  if ( third )
    recurse( first, second, third )
}

reverse( head )

println "reversed linked list: "
printNode( tail )


// create a big list then reverse it to verify we're avoiding stack overflows

def createLinkedList
createLinkedList = { Node n, int listLength, int step = 0 ->
  if (step >= listLength) return n

  Node next = new Node(++step)
  n.next = next

  createLinkedList.trampoline(next, listLength, step)
}.trampoline()


def head2 = new Node(0)
Node tail2 = createLinkedList(head2, 10000)

//printNode(head2)

reverse( head2 )

printNode( tail2 )