import java.util.*;
public class Lecture9 {
	public static void main(String[] args) {
		BST bst = new BST();
		bst.createTree();
		//1.
		/*System.out.println("1. ");
		for (int i = 1; i < 8; i++) 
			bst.insert(i);

		preorder(bst.root);
		System.out.println();*/


		//2.
		System.out.println("2.");
		AVLTree tree = new AVLTree();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(7);
		tree.insert(1);
		//tree.insert(50);
		preorder(tree.root);
		System.out.println();
		inorder(tree.root);
		System.out.println();

		//3. convert a sorted array to bst
		System.out.println("4.");
		int[] a = {1, 2, 3, 4, 5, 6};
		preorder(sortedArrayToBST(a));
		System.out.println();
		
		//4. 
		System.out.println(isBST(bst.root));

		//5.
		System.out.println("5.");
		int[] preorder = {3, 1, 2, 5, 4, 6};
		TreeNode m = constructBSTFromPreorder(preorder);
		inorder(m);
		System.out.println();
	}

	public static void preorder(TreeNode root) {
		if (root != null) {
			System.out.print(root.val + " ");
			preorder(root.left);
			preorder(root.right);
		}
	}

	public static void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.val + " ");
			inorder(root.right);
		}
	}

	//3.
	public static TreeNode sortedArrayToBST(int[] a) {
		TreeNode root = sortedArrayToBST(a, 0, a.length - 1);
		return root;
	}
	public static TreeNode sortedArrayToBST(int[] a, int start, int end) {
		if (a == null || a.length == 0)
			return null;

		if (start > end)
			return null;

		int mid = start + (end - start)/2;
		TreeNode root = new TreeNode(a[mid]);
		//int[] l = Arrays.copyOfRange(a, start, endIndex);
		root.left = sortedArrayToBST(a, start, mid - 1);
		root.right = sortedArrayToBST(a, mid + 1, end);
		return root;
	}
	
	//4. validate bst
	public static boolean isBST(TreeNode root) {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		return isBST(root, min, max);
	}

	public static boolean isBST(TreeNode root, int min, int max) {
		if (root == null)
			return true;
		if (root.val > max || root.val < min)
			return false;

		return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);

	}

	//5. 
	static int preIndex = 0;
	public static TreeNode constructBSTFromPreorder(int[] arr) {
		return constructBSTFromPreorder(arr, 0, arr.length - 1);
	}

	public static TreeNode constructBSTFromPreorder(int[] arr, int low, int high) {
		if (preIndex >= arr.length || low > high) {
			return null;
		}

		TreeNode node = new TreeNode(arr[preIndex]);
		preIndex++;

		if (low == high) {
			return node;
		}

		int i = 0;
		for (i = low; i <= high; i++) {
			if (arr[i] > node.val)
				break;
		}

		//should not pass preindex as another pramater because line 111 will increase the one in scoop instead of global one
		//System.out.println("preindex is " + preIndex + " and i, low, high is " + i + "," + low +","+ high);
		node.left = constructBSTFromPreorder(arr, preIndex, i - 1);
		node.right = constructBSTFromPreorder(arr, i, high);
		return node;
	} 

	//6. 
	public boolean areMirror(TreeNode a, TreeNode b) {
		if (a == null && b == null)
			return true;
		if (a == null || b == null)
			return false;
		return a.val == b.val && 
			areMirror(a.left, b.right) &&
			areMirror(a.right, b.left);
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode parent;//for test
	int height;

	public TreeNode() {
		this.val = 0;
		left = null;
		right = null;
	}

	public TreeNode(int val) {
		this.height = 1;
		this.val = val;
		left = null;
		right = null;
	}
}


class AVLTree {
	TreeNode root;
	public AVLTree() {}
	public int height(TreeNode node) {
		if (node == null) return 0;
		return node.height;
	}

	private TreeNode rotateRight(TreeNode z) {
		TreeNode y = z.left;

		//ROTATE
		z.left = y.right;
		y.right = z;

		z.height = Math.max(height(z.left), height(z.right)) + 1; 
		y.height = Math.max(height(y.left), height(y.right)) + 1; 

		return y;
	}

	private TreeNode rotateLeft(TreeNode z) {
		TreeNode y = z.right;

		//ROTATE
		z.right = y.left;
		y.left = z;

		z.height = Math.max(height(z.left), height(z.right)) + 1; 
		y.height = Math.max(height(y.left), height(y.right)) + 1; 

		return y;
	}

	private int getDiff(TreeNode node) {
		if (node == null)
			return 0;
		return height(node.right) - height(node.left);
	}

	public void insert(int data) {
		root = addNode(root, data);
	}

	private TreeNode addNode(TreeNode node, int data) {
		if (node == null)
			return new TreeNode(data);

		if (node.val > data) {
			node.left = addNode(node.left, data);
		} else {
			node.right = addNode(node.right, data);
		}

		node.height = 1 + Math.max(height(node.left), height(root.right));


		int balance = getDiff(node);
		//left left
		if (balance < -1 && node.val > data)
			return rotateRight(node);

		//left right
		if (balance < -1 && node.val < data) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}

		//right right
		if (balance > 1 && node.val < data) {
			return rotateLeft(node);
		}

		//right left
		if (balance > 1 && node.val > data) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node;
	}



}

class BST {
	TreeNode root;

	public BST() {
		root = null;
	}

	public void createTree() {
		root = new TreeNode(8);
		root.left = new TreeNode(6);
		root.right = new TreeNode(11);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);

		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(4);

		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(14);
	}


	//1. iterative
	public void insert(int data) {
		TreeNode add = new TreeNode(data);
		if (root == null) {
			root = add;
			return;
		}

		TreeNode parent = null;
		TreeNode current = root;

		while (current != null) {
			parent = current;
			if (current.val > data) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		//parent is the leaf node
		if (parent.val > data) {
			parent.left = add;
		} else {
			parent.right = add;
		}
	}

	//2. recursive
	public void addNode(int data) {
		root = addNode(root, data);
	}

	private TreeNode addNode(TreeNode node, int data) {
		if (node == null)
			return new TreeNode(data);

		if (node.val > data) {
			node.left = addNode(node.left, data);
		} else {
			node.right = addNode(node.right, data);
		}

		return node;
	}

}
