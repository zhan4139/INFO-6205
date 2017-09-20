public class Lecture2 {
	public static void main(String[] args) {

	}

	//n1: 1 -> 3 -> 5 -> 7
	//n2: 2 -> 3 -> 6
	//res: 1->2->3->3->5->6->7
	public Node sortedMerge(Node n1, Node n2) {
		Node res = null;
		if (n1 == null) return n2;
		if (n2 == null) return n1;

		if (n1.data < n2.data) {
			res = n1;
			res.next = sortedMerge(n1.next, n2);
		} else {
			res = n2;
			res.next = sortedMerge(n1, n2.next);
		}
		return res;
	}

	

	class Node {
		public int data;
		public Node next;
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	class LinkedList {
		public Node head;
		public LinkedList() {
			head = null;
		}

		public void addToHead(int data) {
			Node n = new Node (data);
			n.next = head;
			head = n;
		}

		public void addToTail(int data) {
			Node n = new Node (data);
			if (head == null) {
				head = n;
				return;
			} 
				
			Node temp = head;
	
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = n;
		}

		public void reverseLinkedList() {
			if (head == null || head.next == null) {
				return;
			}

			Node currNode = head;
			Node nextNode = null;
			Node prevNode = null;

			while (currNode != null) {
				nextNode = currNode.next;
				currNode.next = prevNode;
				prevNode = currNode;
				currNode = nextNode;
			}
			head = prevNode;
		}

		public int linkedListLength() {
			if (head == null)
				return 0;

			Node temp = head;
			int count = 1;

			while (temp != null) {
				temp = temp.next;
				count++;
			} 
			return count;
		}

		public Node findNthFromEnd(int n) {
			if (head == null || n <= 0)
				return Integer.MIN_VALUE;

			//front tends to get two moves once a time while back moves one 
			Node front = head;
			Node back = head;

			//move front first
			for (int i = 0 ; i < n; i++) {
				//check if n is larger than length
				if (front == null)
					return;
				front = front.next;
			}

			//move front and back
			while (front != null) {
				front = front.next;
				back = back.next;
			}

			return back;

		}

		public boolean isCyclic() {
			if (head == null || head.next == null)
				return false;

			// two node starting from head, front moves 2 nodes, back moves 1
			Node front = head.next.next;
			Node back = head;

			while (front != null && front != back) {
				front = front.next;
				if (front != null) {
					front = front.next;
					back = back.next
				}
			}

			if (front == null)
				return false;
			return true;

		}

		// two node starting from head, front moves 2 nodes, back moves 1
		// if front.next is null, then back is in mid
		public int findMidOfLinkedList() {
			if (head == null)
				return null;

			if (head.next == null)
				return head;

			Node front = head;
			Node back = head;

			while (front != null) {
				front = front.next;
				if (front != null) {
					front = front.next;
					back = back.next
				}
			}

			return back;
		}

		public Node splitLinkedList() {
			if (head == null || head.next == null) 
				return null;

		}

		public boolean isPalindrome() {
			if (head == null || head.next == null)
				return true;
			Node secondHalf = head.splitLinkedList();

			second = reverseLinkedList();

			Node temp1 = head;
			Node temp2 = second;
			while (temp1 != null && temp2 != null) {
				if (temp1.data != temp2.data) {
					return false;
				}
				temp1 = temp1.next;
				temp2 = temp2.next;
			}

			//find leftovers next

			return true;

		}

		//input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
		//output: 1 -> 7 -> 2 -> 3 -> 6 -> 4 -> 5
		//sol: splid and reverse and sortedMerge(but not compare)
		public void zipMerge () {
			Node secondHalf = head.splid();
			Node firstHalf = head;
			secondHalf = secondHalf.reverseLinkedList();

			head = zipMerge(firstHalf, secondHalf, true);

			//Homework1: Iteration version of zipMerge
			Node test = null;
			while (firstHalf != null && secondHalf != null) {

			}
			
		}

		public Node zipMerge (Node n1, Node n2, boolean bSwitch) {
			Node res = null;
			if (n1 == null) return n2;
			if (n2 == null) return n1;

			if (bSwitch) {
				res = n1;
				res.next = sortedMerge(n1.next, n2, false);
			} else {
				res = n2;
				res.next = sortedMerge(n1, n2.next, true);
			}
			return res;
		}
	}


	private int nodeLength(Node node) {
		if (node == null) {
			return 0;
		}

		Node temp = node;
		int count = 0;
		while (temp != null) {
			count ++;
			temp = temp.next;
		}
		return count;
	}

	public Node findIntersection(Node n1, Node n2) {
		int len1 = nodeLength(n1);
		int len2 = nodeLength(n2);
		int diff = Math.Abs(len1 = len2);

		if (len1 > len2) {
			for (int i = 0; i < diff; i++)
				n1 = n1.next;
		} else {
			for (int i = 0; i < diff; i++)
				n2 = n2.next;
		}

		while (n1 != null && n2 != null) {
			if (n1 == n2)
				return n1;
			n1 = n1.next;
			n2 = n2.next;
		}
	}

	//Clone a linked list with next and random pointer | Set 1
	//1. create the duplicts between original list
	//Key: clone.random = origin.random.next
	//get the cloned out and delete the original one.
	//
	//Homework2: Merge Sort for LinkedList
}