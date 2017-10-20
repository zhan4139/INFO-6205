import java.util.*;
import java.lang.*;

class Lecture6 {
	public static void main(String[] args) {
		//lecture version
		binarySequence2(3);
		
		//my version
		List<List<Integer>> res = binarySequence(2);

		for (List<Integer> l1 : res) {
		   for (Integer n : l1) {
		       System.out.print(n + " "); 
		   }
		   System.out.println();
		} 

		//combination
		int[] arr = {1, 2, 3};
		printCombination(arr, 2);

		//subsets
		generateSubset("ABC");

		//sumK
		int[] arr2 = {1, 2, 3, 4, 5};
		generateSubsetSumK(arr2, 8);

		//permutation
		permutations("ABC", 2);
		
	}

	//backtracking
	//1. Binary sequence generation
	public static List<List<Integer>> binarySequence(int n) {
		List<List<Integer>> res = new ArrayList<>();
		if (n <= 0) return res;
		backtracking(res, new ArrayList<Integer> (), n, 0);
		return res;
	}

	//my version, need test
	private static void backtracking(List<List<Integer>> res, List<Integer> temp, int n, int start) {
		if (start == n) 
			res.add(new ArrayList<Integer>(temp));

		for (int i = start; i < 2; i++) {
			temp.add(i);
			backtracking(res, temp, n, i+1);
			temp.remove(temp.size() - 1);
		}
	}

	//lecture version
	private static void binarySequence2(int n) {
		if (n <=0 ) return;
		int[] res = new int [n];
		int current = 0;
		binarySequenceHelper(res, current); 
	}

	private static void binarySequenceHelper(int[] res, int current) {
		if (current == res.length) {
			System.out.print(Arrays.toString(res));
			System.out.println();
			return;
		}

		for (int i = 0; i < 2; i++) {
			res[current] = i;
			binarySequenceHelper(res, current+1);
		}
	}

	//combinations[0, 1, 2] return combination of size(with repitition)
	public static void printCombination(int[] arr, int size) {
		if (size <= 0 || arr.length <= 0) return;
		int[] res = new int [size];
		int current = 0;
		combinationBackTrack(res, arr, current, size);
	}

	private static void combinationBackTrack(int[] res, int[] arr, int current, int m) {
		if (current == res.length) {
			System.out.print(Arrays.toString(res));
			System.out.println();
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			res[current] = arr[i];
			combinationBackTrack(res, arr, current+1, m);
		}
	}

	//subsets
	//my version
	public static void subsets(char[] arr) {
		if (arr.length <= 0) return;
		printSubsets(arr, 0);
	}

	private static void printSubsets(char[] arr, int index) {
		if (index > arr.length) return;

		System.out.println();
		for (int i = index; i < arr.length; i++) {
			System.out.print(arr[i]);
			printSubsets(arr, i+1);
		}
	}

	//subsets
	//lecture version
	//
	public static void generateSubset(String str) {
		if (str.length() == 0) return;

		int[] res = new int[str.length()];
		int current = 0;
		generateSubset(str, res, current);
	}

	private static void generateSubset(String str, int[] res, int current) {
		if (current == res.length) {
			printSubset(res, str);
			return; 
		}

		for (int i = 0; i < 2; i++) {
			res[current] = i;
			generateSubset(str, res, current+1);
		}
	}

	private static void printSubset(int[] res, String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < res.length; i++) {
			if (res[i] == 1) {
				sb.append(str.charAt(i)).append(" ");
			}
		}
		sb.append("}");
		System.out.println(sb.toString());
	}


	//subset sum equals to k
	public static void generateSubsetSumK(int[] arr, int k) {
		if (arr.length == 0 || k <=0) return;
		int[] res = new int[arr.length];
		int current = 0;
		generateSubsetSumK(arr, res, current, k);
	}

	private static void generateSubsetSumK(int[] arr, int[] res, int current, int k) {
		if (current == res.length) {
			//print
			printSubsetSumK(arr, res, k);
			return;
		}

		for (int i = 0; i < 2; i++) {
			res[current] = i;
			generateSubsetSumK(arr, res, current+1, k);
		}
	}

	private static void printSubsetSumK(int[] arr, int[] res, int k) {
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for (int i = 0; i < res.length; i++) {
			if (res[i] == 1) {
				sb.append(arr[i] + " ");
				sum += Integer.valueOf(arr[i]);
			}
			
		}
		sb.append("}");

		if (sum == k) System.out.println(sb.toString());
	}

	//permutations  --- n is the size of output
	public static void permutations(String str, int n) {
		if (str.length() == 0 || n <= 0) return;
		int[] res = new int[n];
		int current = 0;
		permutations(res, current, n, str);
	}

	private static void permutations(int[] res, int current, int n, String str) {
		if (current == n) {
			//print
			printCombination(res, n);
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			if (isValid(res, current, i)) {
				res[current] = i;
				permutations(res, current+1, n, str);
			}
		}
	}

	private static boolean isValid(int[] res, int current, int num) {//find it is not chosen before
		for (int i = 0; i < current; i ++) {
			if (res[i] == num) {
				return false;
			}
		}
		return true;
	}

}