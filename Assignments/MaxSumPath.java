import java.util.*;
public class MaxSumPath {
	
	public static void main(String[] args) {
		Tree tree = new Tree();
		maxSumPath(tree.root);
		
	}

	static int max = 0;
	public static void maxSumPath(TreeNode root) {
		if (root == null) return;
		List<Integer> res = new ArrayList<>();
		maxSumPath(root, res, new ArrayList<>(), 0);
		for (Integer i : res) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}

	public static void maxSumPath(TreeNode root, List<Integer> res, List<Integer> temp, int sum) {
		if (root == null) return;
		
		sum += root.val;
		temp.add(root.val);

		if (root.left == null && root.right == null) {
			if (sum > max) {
				res.clear();
				for (Integer i : temp) {
					res.add(i);
				}
				max = sum;
			}
		}

		maxSumPath(root.left, res, temp, sum);
		maxSumPath(root.right, res, temp, sum);
		sum -= root.val;
		temp.remove(temp.size() - 1);
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
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