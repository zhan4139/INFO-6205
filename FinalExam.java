import java.util.*;
public class FinalExam {
	public static void main(String[] args) {
		//Final Exam content

		//1. 
		CircularLinkedList list = new CircularLinkedList();
		list.insert(new Node(4));
		list.insert(new Node(2));
		list.insert(new Node(3));
		list.insert(new Node(3));
		list.insert(new Node(5));
		System.out.println(list); //2, 3, 3, 4, 5


		//2.       A
		//         1
		//        / \
		//       2   3
		//      / \
		//     4   5
		//    / \
		//   6   7
		//   
		//         B
		//         2
		//        / \
		//       5   4
		TreeNode A = new TreeNode(1);
		A.left = new TreeNode(2);
		A.right = new TreeNode(3);
		A.left.left = new TreeNode(4);
		A.left.right = new TreeNode(5);
		A.left.left.left = new TreeNode(6);
		A.left.left.right = new TreeNode(7);

		TreeNode B = new TreeNode(2);
		B.left = new TreeNode(5);
		B.right = new TreeNode(4);

		System.out.println(checkMirror(A, B));//true

		//3.
		int[][] image = {
			{1, 2, 3, 4},
			{1, 1, 2, 3},
			{1, 3, 5, 1},
			{0, 1, 0, 0}
		};

		int[][] res = floodFill(image, 1, 1, 5);
		for(int i = 0; i < image.length; i++) {
			System.out.println(Arrays.toString(res[i]));
		}
		/* res:
		[5, 2, 3, 4]
		[5, 5, 2, 3]
		[5, 3, 5, 1]
		[0, 5, 0, 0]
		 */
	}
	
	

	//2. tree, another(subtree), check if mirror of subtree B exists in the tree A
	//could be part of the tree instead of strictly whole part, return true
	public static boolean checkMirror(TreeNode A, TreeNode B) {
		if (A == null || B == null)
			return A == B;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(A);
		boolean res = false;
		while (!stack.isEmpty()) {
			TreeNode temp = stack.pop();
			if (temp.val == B.val) {
				res = isMirror(temp, B);
				if (res) return res;
			}

			if (temp.right != null) stack.push(temp.right);
			if (temp.right != null) stack.push(temp.left);
		}

		return res;
	}
	
	private static boolean isMirror(TreeNode left, TreeNode right){
		if(left == null && right == null) return true;
	    if(left==null) return false;
	    if(right == null) return true;
	    if(left.val!=right.val) return false;
    return isMirror(left.left, right.right) && isMirror(left.right, right.left);
}

	//3. Flood Fill, horizental and vertical and diagonal 
	// change the connected 1 to change value (only the connected 1s)
	public static int[][] floodFill(int[][] image, int i, int j, int newColor) {
		if (image == null || image.length == 0) return image;
		int val = image[i][j];
		fill(image, newColor, i, j, val);
		
		return image;
	}

	private static void fill(int[][] image, int newColor, int i, int j, int val) {
		if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != val) return;

		image[i][j] = newColor;
		for (int m = -1; m <= 1; m++) {
			for (int n = -1; n <= 1; n++) {
				int ii = i + m;
				int jj = j + n;
				fill(image, newColor, ii, jj, val);
			}
		}
	}
	
}

//1.
//create a class, whenever it inserts a value, it becomes
//sorted linkedlist and it is circular
//function: insert(if it is null, point to itself, dummy node required)
class CircularLinkedList {
	Node head;
	public CircularLinkedList() {
		head = null;
	}

	public void insert(Node node) {
		if (head == null) {
			head = node;
			head.next = head;
		} else {
			Node temp = head;
			if (temp.val > node.val) {
				node.next = head;
				while (temp.next != head) {
					temp = temp.next;
				}
				temp.next = node;
				head = node;
			} else {
				while (node.val >= temp.val) {
					if (temp.next == head || temp.next.val >= node.val) break;
					temp = temp.next;
				}
				node.next = temp.next;
				temp.next = node;
			}
		}
	}

	@Override
	public String toString() {
		Node temp = head;
		String res = "";
		if (temp.next == temp) 
			res += (temp.val + ", ");
		else {
			while (temp.next != head) {
				res += (temp.val + ", ");
				temp = temp.next;
			}
			res += temp.val;
		}
		
		return res;
	}
}

class Node {
	int val;
	Node next;
	public Node(int val) {
		this.val = val;
		next = null;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
