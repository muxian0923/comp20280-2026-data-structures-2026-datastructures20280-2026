# COMP20280 Trees I – Exercises Answers

## Q1 (Implementation)

- **Tree.java, BinaryTree.java, Position.java** – already in `project20280.interfaces`.
- **LinkedBinaryTree.java** – implemented with `Node<E>`, `addRoot`, `addLeft`, `addRight`, `set`, `remove`, `attach`, `createLevelOrder(E[])`, `toString()` (uses `positions().toString()`), `positions()` (uses `inorder()` from AbstractBinaryTree).
- **LinkedBinaryTreeTest.java** – unit tests for size, addRoot, addLeft, addRight, remove, toString, createLevelOrder, inorder, depth, height, height recursive call count, diameter, countExternalNodes, countLeftExternalNodes.

### Q1(h) Height and recursive call count

- For the tree built from the given level-order array (1..31 and 35 with nulls), **height = 5**.
- **Recursive calls**: one call per node. So for a tree with \(n\) nodes, `height_recursive` is called **\(n\) times** (each node visited exactly once). Use `getHeightRecursiveCallCount()` to verify.

### Q1(i) Diameter

- **Diameter** = length (in edges) of the longest path between any two nodes (may or may not pass through the root).
- Implemented in `LinkedBinaryTree.diameter()`. For each node we compute the longest path through that node as `(1 + leftHeight) + (1 + rightHeight)` and take the maximum over all nodes.

---

## Q2: Count external nodes (pseudocode + Java)

**Pseudocode (only Binary Tree ADT: root, left, right, isExternal):**

```
function COUNT_EXTERNAL(T):
  if T.isEmpty() then return 0
  return COUNT_EXTERNAL_SUB(T, T.root())

function COUNT_EXTERNAL_SUB(T, p):
  if p = null then return 0
  if T.isExternal(p) then return 1
  return COUNT_EXTERNAL_SUB(T, T.left(p)) + COUNT_EXTERNAL_SUB(T, T.right(p))
```

**Java:** See `LinkedBinaryTree.countExternalNodes()` and private helper `countExternalNodes(Position<E> p)`.

---

## Q3: Count only left external nodes

**Idea:** An external node is **left external** if it is a leaf and it is the **left child** of its parent. Use ADT: `parent(p)`, `left(parent(p)) == p`, `isExternal(p)`, `isRoot(p)`.

**Pseudocode:**

```
function COUNT_LEFT_EXTERNAL(T):
  if T.isEmpty() then return 0
  return COUNT_LEFT_EXTERNAL_SUB(T, T.root())

function COUNT_LEFT_EXTERNAL_SUB(T, p):
  if p = null then return 0
  if T.isExternal(p) and not T.isRoot(p) and T.left(T.parent(p)) = p then
    return 1 + COUNT_LEFT_EXTERNAL_SUB(T, T.left(p)) + COUNT_LEFT_EXTERNAL_SUB(T, T.right(p))
  return COUNT_LEFT_EXTERNAL_SUB(T, T.left(p)) + COUNT_LEFT_EXTERNAL_SUB(T, T.right(p))
```

**Java:** See `LinkedBinaryTree.countLeftExternalNodes()`.

---

## Q4: Binary tree with characters so that traversal gives "EXAMFUN"

Nodes: A, E, F, M, N, U, X (7 nodes).

- **Preorder gives "EXAMFUN"**  
  Preorder = root, then left subtree, then right subtree. So first letter is root: **E**.  
  Then left subtree preorder = "XAM" → root of left = **X**; then "A" and "M" (e.g. A left, M right of X).  
  Then right subtree preorder = "FUN" → root of right = **F**; then "U" and "N" (e.g. U left, N right of F).

  ```
        E
       / \
      X   F
     / \ / \
    A  M U  N
  ```

  Preorder: E, X, A, M, F, U, N → "EXAMFUN" ✓

- **Inorder gives "EXAMFUN"**  
  Inorder = left, root, right. So order of nodes is E, X, A, M, F, U, N.  
  In a BST-like layout with that inorder: the “middle” of E,X,A,M,F,U,N for root could be **M** (4th). Left inorder "EXA", right inorder "FUN". So root **M**; left subtree has inorder "EXA" (root e.g. **X**, left E, right A); right subtree inorder "FUN" (root **U**, left F, right N).

  ```
        M
       / \
      X   U
     / \ / \
    E  A F  N
  ```

  Inorder: E, X, A, M, F, U, N → "EXAMFUN" ✓

- **Postorder gives "EXAMFUN"**  
  Postorder = left, right, root. So last letter is root: **N**.  
  Then right subtree postorder = "FUN" without N → last of that is root of right: **U**; then "FU" and "F" (e.g. F left, U right).  
  Then left subtree postorder = "EXAM" (e.g. root **M**; left "EXA", right "A"; so root M, left X with E and A as children).

  One possibility:

  ```
        N
       / \
      M   U
     / \ / \
    X  A F  (single node)
    |
    E
  ```

  Postorder: E, X, A, M, F, U, N → "EXAMFUN" ✓  
  (Other shapes are possible; the important part is that the **order** of nodes in the traversal is E, X, A, M, F, U, N.)

---

## Q5: Total number of descendants of a node (pseudocode)

**Descendants** of a node \(p\) = all nodes in the subtree rooted at \(p\) (excluding \(p\) itself), or equivalently the number of children + descendants of children.

**Pseudocode (Binary Tree ADT: left, right, children / numChildren):**

```
function COUNT_DESCENDANTS(T, p):
  if p = null then return 0
  n ← 0
  for each child c of p in T do
    n ← n + 1 + COUNT_DESCENDANTS(T, c)
  return n
```

Or using left/right only:

```
function COUNT_DESCENDANTS(T, p):
  if p = null then return 0
  leftDesc  ← COUNT_DESCENDANTS(T, T.left(p))
  rightDesc ← COUNT_DESCENDANTS(T, T.right(p))
  leftCount ← 1 if T.left(p) ≠ null else 0
  rightCount ← 1 if T.right(p) ≠ null else 0
  return leftCount + rightCount + leftDesc + rightDesc
```

So total descendants = number of children + descendants in left subtree + descendants in right subtree.
