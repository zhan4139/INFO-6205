import java.util.*;
public class Lecture8 {
	public static void main(String[] args) {
		Tree tree = new Tree();
		System.out.println("1.-------");
		printNodesKdistance(tree.root, 2);

		System.out.println("2.-------");
		System.out.println(areSiblings(tree.root, tree.test, tree.test2));
		
		System.out.println("4.-------");
		System.out.println(hasSum(tree.root, 10));

		System.out.println("6.-------");
		printPath(tree.root);

		System.out.println("8.-------");
		System.out.println(levelOfNode(tree.root, tree.test, 1));

		System.out.println("12.-------");
		printAtLevel(tree.root, 3);

		System.out.println("13.-------");
		System.out.println(areAllLeaveSameLevel(tree.root, maxLevel, 1));

		System.out.println("14.-------");
		getMaxSum(tree.root);

		BST bst = new BST();
		System.out.println("15.-------");
		inorder(bst.root);
		System.out.println();

		System.out.println("16.-------");
		System.out.println(countNodeInRange(bst.root, 7, 13));
	}

	//1. find distance k nodes of target node
	public static void printNodesKdistance(TreeNode node, int k) {
		if (node == null) return;
		if (k == 0) {
			System.out.println(node.val);
			return;
		}

		printNodesKdistance(node.left, k - 1);
		printNodesKdistance(node.right, k - 1);
	}

	//2. find siblings : need testing
	public static boolean areSiblings(TreeNode node, TreeNode a, TreeNode b) {
		if (node == null) return false;
		return (node.left == a && node.right == b || 
				node.right == a && node.left == b ||
				areSiblings(node.left, a, b) ||
				areSiblings(node.right, a, b));
	}

	//3. check trees if they have same structure
	public static boolean isomorphic(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null)
			return true;
		if (node1 == null || node2 == null) {
			return false;
		}
		return isomorphic(node1.left, node2.left) && isomorphic(node1.right, node2.right);
	}
	
	//4. check if they are the same tree
	public static boolean isSameTree(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null)
			return true;
		if (node1 == null || node2 == null) {
			return false;
		}
		return node1.val == node2.val && isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
	}



	//5. Sum root to leaf value is target sum
	public static boolean hasSum(TreeNode node, int sum) {
		if (node == null && sum != 0) return false;
		if (node.left == null && node.right == null) return sum - node.val == 0;

		return hasSum(node.left, sum - node.val) || hasSum(node.right, sum - node.val);
	}

	//6. Print the root to leaf path (using pre-order)
	//if use the list we have to remove last element, OW pos keep track of postion of the array
	public static void printPath(TreeNode node) {
		int[] arr = new int[1000];
		printPath(node, arr, 0);
	}
	public static void printPath(TreeNode node, int[] arr, int pos) {
		if (node == null) return;
		arr[pos++] = node.val;
		if (node.left == null && node.right == null) {
			//print array
			printArray(arr, pos);
		}

		printPath(node.left, arr, pos);
		printPath(node.right, arr, pos);

	}

	private static void printArray(int[] arr, int pos) {
		for (int i = 0; i < pos; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	//7. find clostest leaf of root
	int minDis = Integer.MAX_VALUE;
	public static int clostestDistance(TreeNode node) {
		return 0;
	}

	private static void clostestDistance(TreeNode node, int level) {
		return;
	}

	//8. find level the node belong to
	public static int levelOfNode(TreeNode root, TreeNode node, int level) {
		if (root == null) return 0;
		if (node == root) return level;

		int left = levelOfNode(root.left, node, level + 1);
		if (left != 0) return left;
		return levelOfNode(root.right, node, level + 1);
	}

	//9. check if cousins (parent of two nodes are siblings)
	public static boolean areCousins(TreeNode root, TreeNode a, TreeNode b) {
		if (!areSiblings(root, a, b) && areSiblings(root, a.parent, b.parent))
			return true;
		return false;
	}

	//10. least common ancestors
	public static TreeNode lcAncestors(TreeNode root, TreeNode a, TreeNode b) {
		if (a == b) return a;
		HashSet<TreeNode> set = new HashSet<>();
		set.add(a);
		set.add(b);

		TreeNode currA = a;
		TreeNode currB = b;

		while (currA.parent != null || currB.parent != null) {
			if (set.contains(currA.parent))
				return currA.parent;
			if (set.contains(currB.parent))
				return currB.parent;

			set.add(currA.parent);
			set.add(currB.parent);

			currA = currA.parent;
			currB = currB.parent;
		}

		return null;
	}

	//11. sum of root is sum of all childrens
	public static boolean isChildSum(TreeNode node) {
		if (node == null || node.left == null && node.right == null) 
			return true;

		int left = 0;
		int right = 0;

		if (node.left != null)
			left = node.left.val;
		if (node.right != null)
			right = node.right.val;
		if (node.val == left + right && isChildSum(node.left) && isChildSum(node.right))
			return true;
		return false;
	}

	//12. print node at particular level
	//(1) level order travseral till particular level
	//(2) pre-order till target level
	public static void printAtLevel(TreeNode node, int level) {
		if (node == null) return;
		if (level == 1) {
			System.out.println(node.val + " ");
		}

		printAtLevel(node.left, level - 1);
		printAtLevel(node.right, level - 1);
	}

	//13. check if all leaves are at same level // need test
	static int maxLevel = 0;
	public static boolean areAllLeaveSameLevel(TreeNode node, int maxLevel, int level) {
		if (node == null) return true;
		if (node.left == null && node.right == null) {
			if (maxLevel == 0) //first leaf node
				maxLevel = level;
			else {
				if (level != maxLevel)
					return false;
				else return true;
			}
		}
		return areAllLeaveSameLevel(node.left, maxLevel, level + 1) &&
			areAllLeaveSameLevel(node.right, maxLevel, level + 1);
	}

	//14. find max sum of root to path sum // need test
	public static void getMaxSum(TreeNode node) {
		TreeNode ln = new TreeNode();
		getMaxSum(node, maxSum, 0, ln);
		System.out.println(maxSum);
		System.out.println(ln.val);
	}
	static int maxSum = 0;
	public static void getMaxSum(TreeNode node, int maxSum, int currSum, TreeNode leafNode) {
		if (node == null) return;

		currSum += node.val;

		if (node.left == null && node.right == null) {
			if (currSum > maxSum) {
				maxSum = currSum;
				leafNode = node;
			}
		}

		getMaxSum(node.left, maxSum, currSum, leafNode);
		getMaxSum(node.right, maxSum, currSum, leafNode);
	}

	//Another Topic. Binary Search Tree
	//15. bst in order travseral
	//will return sorted array
	public static void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.val + " ");
			inorder(root.right);
		}
	}
	
	public static void posorder(TreeNode root) {
		if (root != null) {
			posorder(root.left);
			posorder(root.right);
			System.out.print(root.val + " ");
		}
	}

	public static void preorder(TreeNode root) {
		if (root != null) {
			System.out.print(root.val + " ");
			preorder(root.left);
			preorder(root.right);
		}
	}

	//16. kth smallest of bst
	//in order traversal //need test
	static int count = 0;
	public static void kthSmallest(TreeNode node, int k, int count) {
		if (node != null) {
			if (count > k) return;
			kthSmallest(node.left, k, count + 1);//need test
			count ++;
			if (count == k)
				System.out.print("4 smallest value is " + node.val);
			kthSmallest(node.right, k, count);
		}
	}

	//17. find number in certain range of bst
	public static int countNodeInRange(TreeNode node, int low, int high) {
		if (node == null) return 0;
		if (node.val <= high && node.val >= low)
			return 1 + countNodeInRange(node.left, low, high) + countNodeInRange(node.right, low, high);
		else if (node.val > high)
			return countNodeInRange(node.left, low, high);
		else
			return countNodeInRange(node.right, low, high);
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode parent;//for test

	public TreeNode() {
		this.val = 0;
		left = null;
		right = null;
	}

	public TreeNode(int val) {
		this.val = val;
		left = null;
		right = null;
	}
}

class Tree {
	TreeNode root;

	TreeNode test;
	TreeNode test2;

	public Tree() {
		createTree();
	}

	public void createTree() {
		root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		test = root.left.right;
		test2 = root.left.left;

		root.left.left.left = new TreeNode(8);
		root.left.left.right = new TreeNode(9);
		//root.left.left.right.right = new TreeNode(12);
		
		root.left.right.right = new TreeNode(10);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		root.right.right.right = new TreeNode(11);
	}
}

class BST {
	TreeNode root;

	public BST() {
		createTree();
	}

	public void createTree() {
		root = new TreeNode(8);
		root.left = new TreeNode(6);
		root.right = new TreeNode(13);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);

		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(4);

		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(14);
	}

}
