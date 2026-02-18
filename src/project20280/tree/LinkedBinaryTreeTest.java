package project20280.tree;

import org.junit.jupiter.api.Test;
import project20280.interfaces.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedBinaryTreeTest {

    @Test
    void testSize() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
        Position<Integer> root = bt.addRoot(1);
        assertEquals(1, bt.size());

        bt.addLeft(root, 2);

        bt.remove(bt.root());
        assertEquals(1, bt.size());
    }

    @Test
    void testAddRoot() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        assertEquals(c, bt.root().getElement(), "root not added correctly");
    }

    @Test
    void testAddLeft() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addLeft(bt.root(), 1);
        assertEquals(1, bt.left(bt.root()).getElement());
    }

    @Test
    void testAddRight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        assertEquals(1, bt.right(bt.root()).getElement());
    }

    @Test
    void testRemove() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        Integer old = bt.remove(bt.right(bt.root()));
        assertEquals(old, 1);
        assertEquals(1, bt.size());
    }

    @Test
    void testToString() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
    }

    @Test
    void testCreateLevelOrder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
    }

    @Test
    void testInorder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.inorder().toString());
    }

    @Test
    void testDepth() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(0, bt.depth(bt.root()));
    }

    @Test
    void testHeight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(3, bt.height());
    }

    @Test
    void testHeightRecursiveCallCount() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        int calls = bt.getHeightRecursiveCallCount();
        assertEquals(3, bt.height());
        // Each node visited once: 12 nodes => 12 recursive calls
        assertEquals(12, calls);
    }

    @Test
    void testTreeFromPDF() {
        // Q1(h) and Q1(i): tree from the exercise sheet level-order array
        Integer[] arr = new Integer[]{
                1,
                2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
                null, null, null, 35
        };
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.createLevelOrder(arr);
        assertEquals(32, bt.size());
        int h = bt.height();
        int calls = bt.getHeightRecursiveCallCount();
        int d = bt.diameter();
        assertEquals(5, h);  // height of this tree
        assertEquals(32, calls);  // one call per node
        assertTrue(d >= 1);
    }

    @Test
    void testDiameter() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        int d = bt.diameter();
        assertEquals(6, d);  // longest path in this complete tree
    }

    @Test
    void testCountExternalNodes() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        bt.createLevelOrder(arr);
        assertEquals(4, bt.countExternalNodes());  // leaves: 4,5,6,7
    }

    @Test
    void testCountLeftExternalNodes() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.addRoot(1);
        bt.addLeft(bt.root(), 2);
        bt.addRight(bt.root(), 3);
        bt.addLeft(bt.left(bt.root()), 4);
        assertEquals(1, bt.countLeftExternalNodes());  // only node 4 is left external
    }

    // ---------- Trees II (Q4) ----------
    @Test
    void testRootToLeafPaths() {
        Integer[] inorder = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] preorder = {5, 1, 0, 4, 2, 3, 7, 6, 8};
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.construct(inorder, preorder);
        assertEquals("[[5, 1, 0], [5, 1, 4, 2, 3], [5, 7, 6], [5, 7, 8]]", bt.rootToLeafPaths().toString());
    }

    // ---------- Trees II (Q5) ----------
    @Test
    void testConstructFromInPre() {
        Integer[] inorder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer[] preorder = {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16, 17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.construct(inorder, preorder);
        assertEquals(30, bt.size());
        assertTrue(bt.width() >= 1);
    }

    @Test
    void testWidth() {
        Integer[] inorder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        Integer[] preorder = {6, 5, 3, 2, 1, 0, 4, 17, 10, 9, 8, 7, 16, 14, 13, 12, 11, 15, 21, 20, 19, 18, 22};
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.construct(inorder, preorder);
        assertEquals(13, bt.width());  // path [0,1,2,3,5,6,17,10,16,14,13,12,11] has 13 nodes
    }
}
