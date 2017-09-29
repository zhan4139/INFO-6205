public class Assignment2 {
	public static void main(String[] args) {

		System.out.println("Testing...");
		LinkedList list = new LinkedList();
		
		for (int i = 0; i < 6; i++) {
			list.addToTail(i+1);
		}
		//Original Linkedlist
		System.out.println(list.linkedListLength());
		list.printLinkedList();
		System.out.println(list.findMidOfLinkedList().data);

		//Linkedlist after zipMerge
		new Assignment2().zipMerge(list);	
		list.printLinkedList();

		System.out.println(list.findMidOfLinkedList().data);

		//Homework P2: merge sort for linkedlist
		LinkedList list2 = new LinkedList();
		list2.addToTail(5);
		list2.addToTail(6);
		list2.addToTail(2);
		list2.addToTail(1);
		Node sortedListNode = new Assignment2().mergeSort(list2.head);
		LinkedList sortedList2 = new LinkedList(sortedListNode);
		sortedList2.printLinkedList();

		//Homework P3:

	}


	//Homework P1: Iterative Version of ZipMerge
	public void zipMerge(LinkedList list) {

		LinkedList first = new LinkedList();
		LinkedList second = new LinkedList();

		splitLinkedList(list.head, first, second);
		second.reverseLinkedList();

		list.head = zipMergePartition(first.head, second.head);
	}

	public Node zipMergePartition(Node n1, Node n2) {
		LinkedList result = new LinkedList();

		while (n1 != null || n2 != null) {
			// System.out.println(n2.data);
			if (n1 != null) {
				result.addToTail(n1.data);
				n1 = n1.next;
			}

			if (n2 != null) {
				result.addToTail(n2.data);
				n2 = n2.next;
			}
		}

		return result.head; 
	}


	//Homework P2: merge sort for linkedlist
	public Node mergeSort(Node head) {
		if (head == null || head.next == null)
			return head;
		
		LinkedList first = new LinkedList();
		LinkedList second = new LinkedList();

		splitLinkedList(head, first, second);

		Node left = mergeSort(first.head);
		Node right = mergeSort(second.head);

		Node list = sortedMerge(left, right);
		return list;
	}

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

}