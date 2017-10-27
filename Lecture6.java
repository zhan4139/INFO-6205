import java.util.*;
import java.lang.*;

class Lecture6 {
	public static void main(String[] args) {
		//lecture version
		binarySequence2(2);
		
		//my version
		List<List<Integer>> res = binarySequence(2);
		//System.out.println("Length of res is " + res.size());
		for (List<Integer> l1 : res) {
		   for (Integer n : l1) {
		       System.out.print(n + " "); 
		   }
		   System.out.println();
		}

		//place 0/1 at 3 postions
		MarySequence(3, 2); 
		System.out.println("---");

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
		backtracking(res, new ArrayList<Integer> (), n, 0, 0);
		return res;
	}

	//my version - it is combination with reptition(Could be used for combination sum)
	private static void backtracking(List<List<Integer>> res, List<Integer> temp, int n, int start, int index) {
		if (index == n) {
			res.add(new ArrayList<Integer>(temp));
			return;
		}

		for (int i = start; i < n; i++) {
			temp.add(i);
			//System.out.println("Add " + i);
			backtracking(res, temp, n, i, index + 1);
			//System.out.println("Remove " + temp.get(temp.size() - 1));
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

	//Mary Sequence m^n (size is n, permutation with repetition)
	public static void MarySequence(int n, int m) {
        if (n <= 0)
            return;

        int[] result = new int[n];
        int current = 0;

        MarySequence(result, current, m);
    }

    private static void MarySequence(int[] result, int current, int m) {
        if (current == result.length) {
            System.out.print(Arrays.toString(result));
			System.out.println();
            return;
        }

        for (int i = 0; i < m; i++) {
            result[current] = i;
            MarySequence(result, current + 1, m);
        }
    }




	//combinations[0, 1, 2] return combination of size(with repitition)
	//!!!!Note: This is permutation with repetition!!!! n^size
	public static void printCombination(int[] arr, int size) {
		if (size <= 0 || arr.length <= 0) return;
		int[] res = new int [size];
		int current = 0;
		combinationBackTrack(res, arr, current, size);
	}

	private static void combinationBackTrack(int[] res, int[] arr, int current, int size) {
		if (current == size) {
			System.out.print(Arrays.toString(res));
			System.out.println();
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			res[current] = arr[i];
			combinationBackTrack(res, arr, current+1, size);
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
	//permutation with repetition 2^n (select 0 or 1 at n postions, size is n)
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

	//permutations w/o repetition --- n is the size of output 
	public static void permutations(String str, int n) {
		if (str.length() == 0 || n <= 0) return;
		int[] res = new int[n];
		int current = 0;
		permutations(res, current, n, str);
	}

	private static void permutations(int[] res, int current, int n, String str) {
		if (current == n) {
			//print
			System.out.println(Arrays.toString(res));
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