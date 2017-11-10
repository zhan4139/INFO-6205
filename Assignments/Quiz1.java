public class Quiz1 {
	public static void main(String[] args) {
		//1.
		int[] a = {1, 2, 3, 5, 6, 6, 7, 12};
		findPair(a, 12); // return 5, 7

		//2. Note that root level is 0;
		Tree tree = new Tree();
		/*
		0			1
				/		\
		1		2		3	
			   / \      / \
		2	  4   5    6   7
			 / \   \        \
		3	8  9   10       11
                \
        4       12
		 */
		prinrtKdistance(tree.root, 3);//return 4 5 6 7

		//Bonus
		int[] a2 = {1, 2, 3, 4, 5, 6, 7};
		findTriple(a2, 10); //return 4, 5, 6
	}

	//1. find a pair in sorted array which sum to target
	public static void findPair(int[] arr, int target) {
		int start = 0, end = arr.length - 1;

		boolean found = false;
		while (start < end) {
			if (arr[start] + arr[end] == target) {
				found = true;
				System.out.println("Found pair value " + arr[start] + "," + arr[end]);
				break;
			} else if (arr[start] + arr[end] < target) {
				start++;
			} else {
				end--;
			}
		}
		if (!found) {
			System.out.println("Pair not found!");
		}
	}

	//2. Create a class of Nodes and Tree and Print all Nodes K distance from root. 
	//(Print at level K)
	public static void prinrtKdistance(TreeNode root, int k) {
		System.out.print("Nodes at level " +  k + " are: ");
		prinrtKdistance(root, k, 0);
		System.out.println();
	}

	private static void prinrtKdistance(TreeNode root, int k, int curr) {
		if (root == null || curr > k)
			return;
		
		if (curr == k) {
			System.out.print(root.val + " ");
			return;
		}
		prinrtKdistance(root.left, k, curr + 1);
		prinrtKdistance(root.right, k, curr + 1);
	}

	//Bonus
	public static void findTriple(int[] arr, int target) {
		boolean found = false;
		for (int i = 0; i < arr.length; i++) {
			int left = 0, right = i - 1;
			while (left < right) {
				if (arr[left] + arr[right] + arr[i] == target) {
					found = true;
					System.out.println("Found triple " + arr[left] + "," + arr[right] + "," + arr[i]);
					break;
				} else if (arr[left] + arr[right] + arr[i] < target) {
					left++;
				} else {
					right--;
				}
			}
			if (found) break;
		}

		if (!found)
			System.out.println("Triple not found!");
	}
}

class TreeNode {

	int val;
	TreeNode left;
	TreeNode right;

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

	public Tree() {
		createTree();
	}

	public void createTree() {
		root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.left.left.left = new TreeNode(8);
		root.left.left.right = new TreeNode(9);
		root.left.left.right.right = new TreeNode(12);
		
		root.left.right.right = new TreeNode(10);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		root.right.right.right = new TreeNode(11);
	}
}