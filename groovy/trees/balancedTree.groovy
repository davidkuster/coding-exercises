// implement a function to check if a binary tree is balanced

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

public void preOrderPrint( Node node, int depth=0 ) {
    print "${ depth ? '    ' * depth : '    ' }"
    if (depth > 0) print "|---"
    println node

    ++depth
    if (node?.leftChild) preOrderPrint( node.leftChild, depth )
    if (node?.rightChild) preOrderPrint( node.rightChild, depth )
}


def balanced = new Node(1,
                    new Node(2,
                            new Node(4),
                            new Node(5,
                                new Node(55),
                                null)),
                    new Node(3,
                            new Node(6),
                            new Node(7)))


def unbalanced = new Node(1,
                    new Node(2,
                            new Node(4),
                            new Node(5,
                                new Node(55, new Node(555, new Node(5555)), null),
                                null)),
                    new Node(3,
                            new Node(6),
                            new Node(7)))

println "balanced: "
preOrderPrint(balanced)

println "\n\nunbalanced:"
preOrderPrint(unbalanced)
println "\n\n"

// "for the purposes of this question" a balanced tree is defined to be one such that
// the heights of the two subtrees of any node never differ by more than one.

// pseudocode

// breadth-first search
// keep a count of the minimum depth reached (x)
// as soon as any depths are reached that are > x + 1, tree is not balanced - stop the process
// otherwise once all nodes have been visited and max depth <= x + 1, tree is balanced

// count depth of each subtree
// if difference in depth of any is > 1, tree is not balanced


// slightly modified solution from the book.

int getHeightInvocations = 0

Closure<Integer> getHeight
getHeight = { Node n ->
  ++getHeightInvocations
  if (! n) return 0
  return 1 + Math.max(getHeight(n.leftChild), getHeight(n.rightChild))
}

Closure<Boolean> isBalanced
isBalanced = { Node root ->
    if (! root) return true

    int diff = getHeight(root.leftChild) - getHeight(root.rightChild)
    if (Math.abs(diff) > 1) {
        return false
    }
    else {
        return isBalanced(root.leftChild) && isBalanced(root.rightChild)
    }
}


assert isBalanced(balanced) == true
println "non-memoized invocations (balanced) = $getHeightInvocations"

getHeightInvocations = 0

assert isBalanced(unbalanced) == false
println "non-memoized invocations (unbalanced) = $getHeightInvocations"



// same solution, but memoized to avoid the repeated calls to getHeight()

int getHeightMemoizedInvocations = 0

Closure<Integer> getHeightMemoized
getHeightMemoized = { Node n ->
  ++getHeightMemoizedInvocations
  if (! n) return 0
  return 1 + Math.max(getHeightMemoized(n.leftChild), getHeightMemoized(n.rightChild))
}.memoizeAtMost(1000)

Closure<Boolean> isBalancedMemoized
isBalancedMemoized = { Node root ->
    if (! root) return true

    int diff = getHeightMemoized(root.leftChild) - getHeightMemoized(root.rightChild)
    if (Math.abs(diff) > 1) {
        return false
    }
    else {
        return isBalancedMemoized(root.leftChild) && isBalancedMemoized(root.rightChild)
    }
}

assert isBalancedMemoized(balanced) == true
println "memoized invocations (balanced) = $getHeightMemoizedInvocations"

getHeightMemoizedInvocations = 0

assert isBalancedMemoized(unbalanced) == false
println "memoized invocations (unbalanced) = $getHeightMemoizedInvocations"