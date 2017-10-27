import java.util.HashMap;
public class TopView {
	public static void main(String[] args) {

		/*	    1
			   / \
			  2   3
			 / \  / \
			4   5 6  7
		   / \   \    \
		  8   9  10   11
		  
		 */

		Tree tree = new Tree();
		printTopView(tree.root); //return 1,2,4,3,8,7,11
	}

	public static void printTopView(TreeNode root) {
		HashMap<Integer, int[]> map = new HashMap<>();
		printTopView(root, map, 0, 0);
		for (Integer k : map.keySet()) {
			//System.out.println(k);
			System.out.print(map.get(k)[0] + " ");
		}
	}

	//Do pre-order traversal and fill map with horizontal distance as key,
	//int array has size 2, first is the val of tree node and second is the vertical level
	private static void printTopView(TreeNode root, HashMap<Integer, int[]> map, int dist, int level) {
		if (root == null) return;

		//if there is no key dist or the dist's current level is smaller than the one
		//in map, we update the map (This guarantee the most top value with distinct dist will be in the map)
		int[] arr = new int[] {root.val, level};
		if (!map.containsKey(dist) || level < map.get(dist)[1]) {
			//System.out.println("dist is " + dist);
			map.put(dist, arr);
		}

		//recursively call left and right, if go left, dist -1, if right, dist + 1, but the level is 
		//always + 1 because we go one level deeper
		printTopView(root.left, map, dist - 1, level + 1);
		printTopView(root.right, map, dist + 1, level + 1);
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

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
		//root.left.left.right.right = new TreeNode(12);
		
		root.left.right.right = new TreeNode(10);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		root.right.right.right = new TreeNode(11);
	}
}