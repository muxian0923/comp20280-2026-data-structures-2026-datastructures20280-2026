package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        bt.size = n;
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String[] args) {
        // Q2: level-order construction
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());

        // Q3: construct from inorder + preorder
        Integer[] inorder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer[] preorder = {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16, 17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};
        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<>();
        bt2.construct(inorder, preorder);
        System.out.println(bt2.toBinaryTreeString());
        System.out.println("Width (nodes on longest path): " + bt2.width());

        // Q6: 需要做高度实验时取消下面注释，再运行本 main；会输出 n,avgHeight 到控制台，可复制到 Excel 绘图
        // runHeightExperiment();
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (root != null) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        // TODO

    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        return null;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = validate(p);
        if (n.getLeft() != null) throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, n, null, null);
        n.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = validate(p);
        if (n.getRight() != null) throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, n, null, null);
        n.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = validate(p);
        E old = n.getElement();
        n.setElement(e);
        return old;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> n = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        if (!t1.isEmpty()) {
            t1.root.setParent(n);
            n.setLeft(t1.root);
            size += t1.size;
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(n);
            n.setRight(t2.root);
            size += t2.size;
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<E> child = (n.getLeft() != null ? n.getLeft() : n.getRight());
        if (n == root) {
            root = child;
            if (child != null) child.setParent(null);
        } else {
            Node<E> parent = n.getParent();
            if (parent.getLeft() == n) parent.setLeft(child);
            else parent.setRight(child);
            if (child != null) child.setParent(parent);
        }
        size--;
        n.setParent(n);   // defunct convention
        return n.getElement();
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        E[] arr = (E[]) l.toArray();
        createLevelOrder(arr);
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, null, 0);
        size = 0;
        for (E e : arr) if (e != null) size++;
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> parent, int i) {
        if (i >= arr.length || arr[i] == null) return null;
        Node<E> n = createNode(arr[i], parent, null, null);
        n.setLeft(createLevelOrderHelper(arr, n, 2 * i + 1));
        n.setRight(createLevelOrderHelper(arr, n, 2 * i + 2));
        return n;
    }

    /**
     * Q3: Builds a binary tree from inorder and preorder traversals.
     * Preorder gives the root; inorder splits left/right subtrees.
     */
    public void construct(E[] inorder, E[] preorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length || inorder.length == 0) {
            root = null;
            size = 0;
            return;
        }
        root = buildFromInPre(inorder, preorder, 0, inorder.length - 1, 0, preorder.length - 1);
        size = inorder.length;
    }

    private Node<E> buildFromInPre(E[] in, E[] pre, int inLo, int inHi, int preLo, int preHi) {
        if (inLo > inHi) return null;
        E rootVal = pre[preLo];
        int inRoot = -1;
        for (int i = inLo; i <= inHi; i++) {
            if ((in[i] == null && rootVal == null) || (in[i] != null && in[i].equals(rootVal))) {
                inRoot = i;
                break;
            }
        }
        if (inRoot < 0) throw new IllegalArgumentException("Root from preorder not found in inorder");
        int leftSize = inRoot - inLo;
        Node<E> n = createNode(rootVal, null, null, null);
        n.setLeft(buildFromInPre(in, pre, inLo, inRoot - 1, preLo + 1, preLo + leftSize));
        n.setRight(buildFromInPre(in, pre, inRoot + 1, inHi, preLo + leftSize + 1, preHi));
        if (n.getLeft() != null) n.getLeft().setParent(n);
        if (n.getRight() != null) n.getRight().setParent(n);
        return n;
    }

    /**
     * Q4: Returns all root-to-leaf paths (each path is a list of elements from root to leaf).
     */
    public List<List<E>> rootToLeafPaths() {
        List<List<E>> result = new ArrayList<>();
        if (isEmpty()) return result;
        List<E> path = new ArrayList<>();
        rootToLeafPathsHelper(root(), path, result);
        return result;
    }

    private void rootToLeafPathsHelper(Position<E> p, List<E> path, List<List<E>> result) {
        path.add(p.getElement());
        if (isExternal(p))
            result.add(new ArrayList<>(path));
        else {
            if (left(p) != null) rootToLeafPathsHelper(left(p), path, result);
            if (right(p) != null) rootToLeafPathsHelper(right(p), path, result);
        }
        path.remove(path.size() - 1);
    }

    /**
     * Q5: Width = number of nodes on the longest path between any two nodes (may not pass through root).
     * Equals diameter (in edges) + 1.
     */
    public int width() {
        if (isEmpty()) return 0;
        return diameter() + 1;
    }

    /**
     * Q6: Runs the height experiment: for each n in [50, 5000] step 50, generates 100 random
     * binary trees of size n and prints (n, average height) as CSV for plotting.
     * Call from main or a test to produce data for Excel/Sheets; then fit trendline to check O(log n).
     */
    public static void runHeightExperiment() {
        System.out.println("n,avgHeight");
        for (int n = 50; n <= 5000; n += 50) {
            long sumHeight = 0;
            for (int run = 0; run < 100; run++) {
                LinkedBinaryTree<Integer> bt = makeRandom(n);
                sumHeight += bt.height();
            }
            double avg = sumHeight / 100.0;
            System.out.println(n + "," + avg);
        }
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Returns the number of recursive calls made during the last height() computation.
     * (For Q1(h) - use this to verify expected call count.)
     */
    public int getHeightRecursiveCallCount() {
        if (isEmpty()) return 0;
        int[] callCount = new int[1];
        height_recursive(root(), callCount);
        return callCount[0];
    }

    /**
     * Diameter of the binary tree: length (in edges) of the longest path between any two nodes.
     * (Q1(i)) The path may or may not pass through the root.
     */
    public int diameter() {
        if (isEmpty()) return 0;
        int[] result = diameterAndHeight(root());
        return result[0];
    }

    /** Returns [diameter, height] for the subtree rooted at p. Height of null = -1. */
    private int[] diameterAndHeight(Position<E> p) {
        if (p == null) return new int[]{0, -1};
        Node<E> n = (Node<E>) p;
        int[] left = diameterAndHeight(n.getLeft());
        int[] right = diameterAndHeight(n.getRight());
        int height = 1 + Math.max(left[1], right[1]);
        int throughP = (left[1] + 1) + (right[1] + 1);  // edges from left leaf through p to right leaf
        int diameter = Math.max(throughP, Math.max(left[0], right[0]));
        return new int[]{diameter, height};
    }

    /**
     * Counts the number of external (leaf) nodes in the tree. Uses only Binary Tree ADT.
     * (Q2) Recursive: external node contributes 1; internal node sums counts from left and right subtrees.
     */
    public int countExternalNodes() {
        if (isEmpty()) return 0;
        return countExternalNodes(root());
    }

    private int countExternalNodes(Position<E> p) {
        if (p == null) return 0;
        if (isExternal(p)) return 1;
        return countExternalNodes(left(p)) + countExternalNodes(right(p));
    }

    /**
     * Counts only the left external nodes (leaves that are left children). Uses only Binary Tree ADT.
     * (Q3)
     */
    public int countLeftExternalNodes() {
        if (isEmpty()) return 0;
        return countLeftExternalNodes(root());
    }

    private int countLeftExternalNodes(Position<E> p) {
        if (p == null) return 0;
        if (isExternal(p) && !isRoot(p) && left(parent(p)) == p) return 1;
        return countLeftExternalNodes(left(p)) + countLeftExternalNodes(right(p));
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}
