
import java.util.List;

// bst is not sorted at first. it will make sure to be sorted as node inserts.

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class BinarySearchTree {
    TreeNode root;

    BinarySearchTree(TreeNode root) {
        this.root = root;
    }

    public void insert(int value) {
        TreeNode newNode = new TreeNode(value);

        if (root == null) {
            this.root = newNode;
            return;
        }

        TreeNode leafNode = root;


        while (true) {
            if (leafNode.val == value) {
                return;
            } else if (leafNode.val > value) {
                if (leafNode.left == null) {
                    leafNode.left = newNode;
                    return;
                }
                leafNode = leafNode.left;
            } else if (leafNode.val < value) {
                if (leafNode.right == null) {
                    leafNode.right = newNode;
                    return;
                }
                leafNode = leafNode.right;
            }
        }
    }

    public TreeNode deleteNode(TreeNode node, int value){
        if (node == null) return null;

        if (value < node.val) {
            node.left = deleteNode(node.left, value);
        } else if (value > node.val) {
            node.right = deleteNode(node.right, value);
        } else {
            //when child is 0 or 1
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            TreeNode min = findMin(node.right);
            node.val= min.val;
            node.right = deleteNode(node.right, min.val);
        }
        return node;
    }

    public TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    //Given: [A, B, C, D, E, F]
    //[D, B, E, A, C, F]
    //Left → Root → Right
    public void inorderTraversal(TreeNode node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.println(node.val + " ");
        inorderTraversal(node.right);

    }

    //[A, B, D, E, C, F]
    //Root → Left → Right
    public void preOrderTraversal(TreeNode node) {
        if (node == null) return;
        System.out.println(node.val + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }
    //[D, E, B, F, C, A]
    //Left → Right → Root
    public void postOrderTraversal(TreeNode node){
        if (node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.val + " ");
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(3);
        node.right = new TreeNode(7);
        node.left.left = null;
        node.left.right = new TreeNode(2);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(9);

        BinarySearchTree bst = new BinarySearchTree(node);
        bst.insert(8);
        bst.inorderTraversal(bst.root);
        //bst.preOrderTraversal(answer);
        //bst.postOrderTraversal(answer);
        bst.deleteNode(bst.root, 8);
        System.out.println("deleted 8");
        bst.inorderTraversal(bst.root);

    }
}

