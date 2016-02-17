// comparing my thoughts on how to answer this common question, vs what was found at the following link:
// http://www.programmerinterview.com/index.php/data-structures/reverse-a-linked-list/

// In the link above, the recursive solution works in an interview to prove you can think recursively,
// but doesn't work in a real-world scenario because it's prone to stack overflow errors.

class Node {
  Node next
  Integer i
  String toString() { i as String }
}

def a = new Node(i:1)
def b = new Node(i:2)
def c = new Node(i:3)
def d = new Node(i:4)
def e = new Node(i:5)

a.next = b
b.next = c
c.next = d
d.next = e

def printNode
printNode = { Node node ->
  print "${node}[${node?.next}] "
  if ( node?.next ) printNode( node.next )
  else println "\n"
}.trampoline()

println "original linked list: "
printNode( a )


public void reverse(Node first) {
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

// tail recursive method
public void recurse( Node previous, Node current, Node next ) {
  def third = next?.next
  // swap the pointers as we go
  next?.next = current
  current.next = previous
  if ( third )
    recurse( current, next, third )
}

reverse( a )

println "reversed linked list: "
printNode( e )