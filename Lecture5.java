import java.lang.*;
import java.util.*;
import java.util.LinkedList;
public class Lecture5 {
	public static void main(String[] args) {
		// TreeNode temp = new TreeNode();
		// System.out.println(temp.val);
		// 
		Tree tree = new Tree();
		preorder(tree.root);
		System.out.println();
		inorder(tree.root);
		System.out.println();
		postorder(tree.root);
		System.out.println();

		System.out.println(treeSize(tree.root));
		System.out.println(treeHeight(tree.root));

		levelOrder(tree.root);
		System.out.println();
		levelOrder2(tree.root);
		levelOrderReverse(tree.root);

		printAllLeaves(tree.root);
		System.out.println();

		System.out.println("----------------");
		printPerimeter(tree.root);
		System.out.println();

		printRightView(tree.root);
		System.out.println();

		printLeftView(tree.root);
		System.out.println();

		printTopView(tree.root);
		System.out.println();

		printTopView2(tree.root);
		System.out.println();
	}

	//1. Binary Tree three order of traversal, pre-order in-order post-order
	//preorder
	//
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

	public static void postorder(TreeNode root) {
		if (root != null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.val + " ");
		}
	}

	public static int treeSize(TreeNode root) {
		if (root == null) return 0;
		//preorder
		return 1 + treeSize(root.left) + treeSize(root.right);	
		//inorder	treeSize(root.left) + 1 + treeSize(root.right)
		//postorder treeSize(root.left) + treeSize(root.right) + 1
	}

	public static int treeHeight(TreeNode root) {
		if (root == null) return 0;
		int left = treeHeight(root.left);
		int right = treeHeight(root.right);

		//return left > right ? treeHeight(root.left) + 1 : treeHeight(root.right) + 1;
		//same as below
		return 1 + Math.max(left, right);
	}

	//need test, self version, did not change order
	public static void levelOrder(TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode temp = queue.poll();
			if (temp == null) {
				//System.out.println();
				System.out.println("  ");
				if (queue.size() == 0) break;
				else queue.offer(null);
				//System.out.println();
			} else {
				System.out.print(temp.val + " ");
				if (temp.left != null) queue.offer(temp.left);
				if (temp.right != null) queue.offer(temp.right);
			}
		}
		System.out.println();
	}
	//lecturer version (use a null as a new level flag)
	public static void levelOrder2(TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		queue.offer(null);

		while(queue.size() != 0) {
			TreeNode temp = queue.poll();
			if (temp != null) {
				System.out.print(temp.val + " ");
				if (temp.left != null) queue.offer(temp.left);
				if (temp.right != null) queue.offer(temp.right);
			} else {
				System.out.println();
				if (queue.size() == 0) break;
				else queue.offer(null);
			}
		}
	}

	//print level order in reverse order (put them in a stack) 
	//when it hits a null, check if stack is not empty print it out // zigzag
	public static void levelOrderReverse(TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		queue.offer(null);

		boolean print = true;
		Stack<TreeNode> stack = new Stack<>();

		 while(queue.size() != 0) {
			TreeNode temp = queue.poll();
			if (temp != null) {
				
				if (print)
					System.out.print(temp.val + " ");
				else stack.push(temp);

				if (temp.left != null) queue.offer(temp.left);
				if (temp.right != null) queue.offer(temp.right);
			} else {
				//if (stack.isEmpty())

				if (queue.size() == 0) break;
				else queue.offer(null);

				print = !print;
				while (stack.size() != 0) {
					System.out.print(stack.pop().val + " ");
				}

				System.out.println();
			}
		}

		while (stack.size() != 0) {
			System.out.print(stack.pop().val + " ");
		}

		System.out.println();
	}

	//
	public static void printAllLeaves(TreeNode root) {
		if (root != null) {
			printAllLeaves(root.left);
			printAllLeaves(root.right);
			if (root.left == null && root.right == null) {
				System.out.print(root.val + " ");
			}
		}
	}

	//print out side of a tree, left-side + leavesnode + right-side
	//use some 2D counter to record the if goes left or right, then
	//print out all right counter 0's and left counter 0's and leaves nodes

	public static void printPerimeter(TreeNode root) {
		if (root == null) return;
		// System.out.print(root.val + " ");
		// int left = 0, right = 0;
		System.out.print(root.val + " ");
		perimeterHelper(root, 0, 0);
	}

	//need test here
	public static void perimeterHelper(TreeNode node, int left, int right) {
		if (node != null) {
			if (right == 0 && left != 0) {
				System.out.print(node.val + " ");
			} else if (left == 0 && right != 0) {
				System.out.print(node.val + " ");
			} else if (node.left == null && node.right == null) {
				System.out.print(node.val + " ");
			}

			//left ++;
			perimeterHelper(node.left, left + 1, right);
			//right ++;
			perimeterHelper(node.right, left, right + 1);
		}
	}

	public static void printLeft(TreeNode root) {
		if (root == null) return;
		TreeNode temp = root;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.left;
		}
		System.out.println();
	}

	public static void printRight(TreeNode root) {
		if (root == null) return;
		TreeNode temp = root;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.right;
		}
		System.out.println();
	}

	//Homework 1, print preimeter top->left->bottom->right->top
	//
	
	//modify level order traversal, when it hit a null, print the previous value
	public static void printRightView(TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		queue.offer(null);
		int lastValue = root.val;
		while(queue.size() != 0) {
			TreeNode temp = queue.poll();
			if (temp != null) {
				lastValue = temp.val;
				//System.out.print(temp.val + " ");
				if (temp.left != null) queue.offer(temp.left);
				if (temp.right != null) queue.offer(temp.right);
			} else {
				System.out.print(lastValue + " ");
				//System.out.println();
				if (queue.size() == 0) break;
				else queue.offer(null);
			}
		}
	}
	
	//use a boolean flag once goto a new level, make it true and print the first element
	//in the new level 
	public static void printLeftView(TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		queue.offer(null);
		boolean printed = false;

		while(queue.size() != 0) {
			TreeNode temp = queue.poll();
			if (temp != null) {
				if (!printed) {
					System.out.print(temp.val + " ");
					printed = !printed;
				}
				//System.out.print(temp.val + " ");
				if (temp.left != null) queue.offer(temp.left);
				if (temp.right != null) queue.offer(temp.right);
			} else {
				//System.out.print(lastValue + " ");
				//System.out.println();
				if (queue.size() == 0) break;
				else {
					printed = !printed;
					queue.offer(null);
				}
			}
		}
	}

	//print top view || (using a pre-order traversal, then use a hashmap to record the the column number as a key, if the value's 
	//column is already in the key, we skip it. otherwise we add it and print)
	//HOMEWORK: fix this! : need one more parameter to record the level order, if found one
	//with smaller level, update the result (See homwork solution)
	public static void printTopView(TreeNode root) {
		HashMap<Integer, Integer> map = new HashMap<>();
		printTopViewHelper(root, 0, map);
	}
	public static void printTopViewHelper(TreeNode root, int key, HashMap<Integer, Integer> map) {
		if (root != null) {
			if (!map.containsKey(key)) {
				map.put(key, root.val);
				System.out.print(root.val + " ");
			}
			printTopViewHelper(root.left, key - 1, map);
			//root.val = map.get(0);
			printTopViewHelper(root.right, key + 1, map);
		}
	}

	public static void printTopView2(TreeNode root) {
        Map<Integer, Integer> data = new TreeMap<Integer, Integer>();
        printTopViewRecursive(data, root, 0);
        for(int key : data.keySet()) {
            System.out.print(data.get(key) +" ");
        }
    }


	private static void printTopViewRecursive(Map<Integer, Integer> hDMap, TreeNode root, int hD) {
	    if(root == null)
	        return;
	    if(!hDMap.containsKey(hD)) {
	        hDMap.put(hD, root.val);
	    }
	    printTopViewRecursive(hDMap, root.left,hD - 1);
	    printTopViewRecursive(hDMap, root.right, hD + 1);
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
		//root.left.left.right.right = new TreeNode(12);
		
		root.left.right.right = new TreeNode(10);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		root.right.right.right = new TreeNode(11);
	}
}
