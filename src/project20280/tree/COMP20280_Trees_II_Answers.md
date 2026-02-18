# COMP20280 Trees II – Exercises Answers

## Q1

Ensure LinkedBinaryTree is complete. (Already done in Trees I: addRoot, addLeft, addRight, remove, set, attach, createLevelOrder, inorder, positions, toString, depth, height, diameter, etc.)

## Q2

Create a binary tree from level-order array. **Implemented:** `createLevelOrder(E[] arr)` and `createLevelOrder(ArrayList<E> l)`.  
Example in `LinkedBinaryTree.main()`:

```java
String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
bt.createLevelOrder(arr);
System.out.println(bt.toBinaryTreeString());
```

Produces the required tree (A root, B/C children, D/E under B, F under C, G/H under D).

## Q3

Create a binary tree from **inorder** and **preorder** traversals. **Implemented:** `construct(E[] inorder, E[] preorder)`.

- Preorder’s first element is the root.
- Find that root in inorder; left part = left subtree, right part = right subtree.
- Recursively build left and right subtrees from the corresponding ranges in both arrays.

Example in `LinkedBinaryTree.main()` uses the given 30-node inorder/preorder and prints the tree.

## Q4

Print all root-to-leaf paths. **Implemented:** `rootToLeafPaths()` returns `List<List<E>>`.  
Unit test: `LinkedBinaryTreeTest.testRootToLeafPaths()` — expects  
`"[[5, 1, 0], [5, 1, 4, 2, 3], [5, 7, 6], [5, 7, 8]]"`.

## Q5 – Diameter/Width (pseudocode and method)

**Definitions (PDF):** The **width** of a binary tree is the number of **nodes** on the longest path from the left-most to the right-most node (this path may or may not pass through the root). In the handout this is also called “diameter”.

**Pseudocode (diameter = longest path in nodes):**

```
function DIAMETER_NODES(T):
  if T.isEmpty() then return 0
  return DIAMETER_NODES_SUB(T.root())

function DIAMETER_NODES_SUB(p):
  if p = null then return (0, -1)   // (diameter, height)
  (leftDiam, leftH) ← DIAMETER_NODES_SUB(T.left(p))
  (rightDiam, rightH) ← DIAMETER_NODES_SUB(T.right(p))
  height ← 1 + max(leftH, rightH)
  throughP ← (leftH + 1) + (rightH + 1)   // nodes on path through p: left path + right path
  diameter ← max(throughP, leftDiam, rightDiam)
  return (diameter, height)
```

Then **width** = value returned by DIAMETER_NODES(T).  
(Equivalently: width = diameter_in_edges + 1, so we implement `width()` as `diameter() + 1`.)

**Implemented:** `width()` in `LinkedBinaryTree` (uses existing `diameter()` in edges, then +1).  
Test tree from PDF (given inorder/preorder) should give width 13. Use `LinkedBinaryTreeTest.testWidth()`.

## Q6

**Implemented:** `LinkedBinaryTree.runHeightExperiment()`.

- For each n ∈ {50, 100, 150, …, 5000}, generates 100 random binary trees of size n.
- Computes average height and prints one line per n: `n,avgHeight` (CSV).
- Run from code, e.g. in `main`: `LinkedBinaryTree.runHeightExperiment();`
- Copy the output into Excel or Google Sheets, plot “avgHeight” vs “n”, and add a trendline to check scaling (e.g. O(log n)).
