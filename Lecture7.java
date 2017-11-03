import java.util.*;
import java.util.ArrayList;
import java.lang.*;
public class Lecture7 {
	public static void main(String[] args) {
		MinHeap mheap = new MinHeap(20);
		mheap.insert(6);
		mheap.insert(3);
		mheap.insert(5);
		mheap.insert(1);
		mheap.insert(9);
		mheap.insert(8);
		mheap.insert(-2);

		mheap.removeMin();
		// mheap.removeMin();
		// mheap.removeMin();
		// mheap.removeMin();
		// mheap.removeMin();
		// mheap.removeMin();
		// mheap.removeMin();
		System.out.println(Arrays.toString(mheap.getData()));



		Trie trie = new Trie();
		trie.insert("ball");
		trie.insert("bat");
		trie.insert("bad");

		System.out.println();

		trie.createSuffixTrie("abaaba");
	}

//HW: 1. findsubstring, 2. number of time str occurs, 3. longest repeated substring
}

//1.Max/Min Heap
//left child =  2*i + 1
//right child = 2 *i + 2
//parent = (i - 1)/2
class MinHeap {
	private int[] data;
	private int heapSize;

	public MinHeap(int heapSize) {
		data = new int[heapSize];
		this.heapSize = 0;
	}

	public int getMin() {
		if (isEmpty()) 
			throw new IndexOutOfBoundsException("Heap is empty!");
		else {
			//...
			return data[0];
		}
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	private int getLeftChild(int index) {
		return index * 2 + 1;
	}

	private int getRightChild(int index) {
		return index * 2 + 2;
	}

	private int getParent(int index) {
		return (index - 1) / 2;
	}

	public void insert(int value) {
		if (heapSize == data.length)
			throw new IndexOutOfBoundsException("Heap is full!");
		else {
			heapSize ++;
			data[heapSize - 1] = value;
			shiftUp(heapSize - 1);
		}
	}

	private void shiftUp(int index) {
		int parentIndex, tmp;
		if (index != 0) {
			parentIndex = getParent(index);
			if (data[parentIndex] > data[index]) {
				tmp = data[index];
				data[index] = data[parentIndex];
				data[parentIndex] = tmp;
				shiftUp(parentIndex);
			}
		}
	}

	public int[] getData() {
		return data;
	}

	public void removeMin() {
		if (isEmpty())
			throw new IndexOutOfBoundsException("Heap is empty!");
		data[0] = data[heapSize - 1];//copy the last value to the first
		data[heapSize - 1] = 0; // added???
		heapSize --;
		if (heapSize > 0) 
			shiftDown(0);
	}

	private void shiftDown(int index) {
		//int parentIndex, tmp;
		//find left and right index and find the smaller one
		int leftIndex, rightIndex, minIndex, tmp;
		leftIndex = getLeftChild(index);
		rightIndex = getRightChild(index);

		if (rightIndex >= heapSize) {
			if (leftIndex >= heapSize)
				return;
			else {
				minIndex = leftIndex;
			}
		} else {
			if (data[leftIndex] <= data[rightIndex])
				minIndex = leftIndex;
			else
				minIndex = rightIndex;
		}

		if (data[minIndex] < data[index]) {
			tmp = data[index];
			data[index] = data[minIndex];
			data[minIndex] = tmp;

			shiftDown(minIndex);
		}	
	}
}

class Node {
	public char val;
	public boolean isTerminalChar;
	public List<Node> children;

	public Node(char c) {
		val = c;
		isTerminalChar = false;
		children = new ArrayList<Node>();
	}
}

class Trie {
	private Node root;
	public Trie() {
		root = new Node('\0');//?
	}

	public void insert(String str) {
		char[] arr = str.toCharArray();
		Node curr = root;
		for (int i = 0; i < arr.length; i ++) {
			Node child = findChild(curr, arr[i]);
			if (child == null) {//create new node
				child = new Node (arr[i]);
				curr.children.add(child);
			}
			curr = child;
			if (i == arr.length - 1)
				curr.isTerminalChar = true;
		}
	}

	private Node findChild(Node node, char ch) {
		for (int i = 0; i < node.children.size(); i++) {
			if (node.children.get(i).val == ch)
				return node.children.get(i);
		}
		return null;
	}

	public void createSuffixTrie(String str) {
		str = str + "$";

		for (int i = 0; i < str.length(); i ++) {
			String sub = str.substring(str.length() - 1 - i, i + 1);
			insert(sub);
			System.out.println(sub);
		}
		
	}
}

// suffix trie, (1. find substring~O(n), 2. how many times of substring occurs(check $ of substrings))
// 3. Longest repeated substring (find longest node that has branched)
// suffix tree (group chars together when there is a '$')
