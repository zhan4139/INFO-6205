import java.lang.Math;

public class Lecture2 {
	public static void main(String[] args) {
		System.out.println("Testing...");
		LinkedList list = new LinkedList();
		for (int i = 0; i < 6; i++) {
			list.addToTail(i+1);
		}
		System.out.println(list.linkedListLength());
		list.printLinkedList();
		new Lecture2().zipMerge(list.head);
		
		list.printLinkedList();
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

	public int nodeLength(Node node) {
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
		int diff = Math.abs(len1 = len2);

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

		return null;
	}
	//Clone a linked list with next and random pointer | Set 1
	//1. create the duplicts between original list
	//Key: clone.random = origin.random.next
	//get the cloned out and delete the original one.
	//
	//Homework2: Merge Sort for LinkedList
	

	//input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
	//output: 1 -> 7 -> 2 -> 3 -> 6 -> 4 -> 5
	//sol: splid and reverse and sortedMerge(but not compare)
	public void zipMerge(Node head) {
		
		LinkedList first = new LinkedList(null);
		LinkedList second = new LinkedList(null);;

		splitLinkedList(head, first, second);

		//System.out.println(second.head.data);

		//LinkedList flist = new LinkedList(first);
		//LinkedList slist = new LinkedList(second);
		//System.out.println(first.data);
		//flist.printLinkedList();
		//slist.printLinkedList();
		//Node firstHalf = head;
		
		second.reverseLinkedList();

		head = zipMerge(first.head, second.head, true);

		//Homework1: Iteration version of zipMerge
		// Node test = null;
		// while (firstHalf != null && secondHalf != null) {

		// }
		
	}

	public Node zipMerge(Node n1, Node n2, boolean bSwitch) {
		Node res = null;
		if (n1 == null) return n2;
		if (n2 == null) return n1;

		if (bSwitch) {
			res = n1;
			res.next = zipMerge(n1.next, n2, false);
		} else {
			res = n2;
			res.next = zipMerge(n1, n2.next, true);
		}
		return res;
	}

	public void splitLinkedList(Node head, LinkedList first, LinkedList second) {
		if (head == null || head.next == null) 
			return;

		first.head = head;

		Node slow = head;
		Node fast = head.next;

		while (fast != null) {
			if (fast.next != null) 
				fast = fast.next.next;
			else
				break;
			slow = slow.next;
		}

		second.head = slow.next;
		slow.next = null;

		//System.out.println("first is " + first.head.data);
		//System.out.println("second is " + second.head.data);
	}
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

	public LinkedList(Node head) {
		this.head = head;
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

	public void printLinkedList() {
		Node temp = head;
		for (int i = 0; i < linkedListLength(); i++) {
			System.out.print(temp.data);
			temp = temp.next;
			if (temp != null)
				System.out.print(" -> ");
			if (temp == null)
				System.out.println(" -> null");
		}
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
		int count = 0;

		while (temp != null) {
			temp = temp.next;
			count++;
		} 
		return count;
	}


	//Not finished
	public int findNthFromEnd(int n) {
		if (head == null || n <= 0)
			return Integer.MIN_VALUE;

		return Integer.MIN_VALUE;

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
				back = back.next;
			}
		}

		if (front == null)
			return false;
		return true;

	}

	// two node starting from head, front moves 2 nodes, back moves 1
	// if front.next is null, then back is in mid
	public Node findMidOfLinkedList() {
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
				back = back.next;
			}
		}

		return back;
	}

	public boolean isPalindrome() {
		if (head == null || head.next == null)
			return true;
		LinkedList first = null;
		LinkedList second = null;
		new Lecture2().splitLinkedList(head, first, second);

		second.reverseLinkedList();

		Node temp1 = head;
		Node temp2 = second.head;
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

	
}
