// some tweaking and cleanup from the original version

class Node {

  Node(Object v, Node n) {
    this.value = v
    this.next = n
  }

  Object value
  Node next

  String toString() { value as String }
}


def printNode
printNode = { Node node ->
  print "${node}[${node?.next}] "
  if ( node?.next ) printNode( node.next )
  else println "\n"
}.trampoline()

public void reverse(Node first) {
  // if empty input or only a single node, nothing to do
  if ( ! first?.next ) return

  // first node is a special case because .next will be null when reversing
  def second = first.next
  first.next = null

  def third = second?.next
  // if we have more nodes, then process the rest of the list
  if ( third ) {
    recurse( first, second, third )
  }
}

// tail recursive method
public void recurse( Node previous, Node current, Node next ) {
  def third = next?.next
  // swap the pointers as we go
  next?.next = current
  current.next = previous
  if ( third )
    recurse( current, next, third )
}


// keep a reference to the tail node so we can verify later it's "next" node has been mutated
def tailNode = new Node(5, null)

def rootNode = new Node(1,
                new Node(2,
                    new Node(3,
                        new Node(4,
                           tailNode))))

println "original linked list: "
printNode( rootNode )

println "reversing linked list in place (which isn't very functional or treating the data as immutable...)\n"
reverse( rootNode )

println "reversed linked list: "
printNode( tailNode )