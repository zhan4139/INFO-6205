import java.util.*;
public class Lecture11 {
	//Dynamic Programming
	public static void main(String[] args) {
		System.out.println(findFibTabular(5));
	}

	//Fib
	static int findFib(int n) {
		if (n <= 1) 
			return n;
		return findFib(n - 1) + findFib(n - 2);
	}

	static int findFibTabular(int n) {
		if (n <= 1) 
			return n;
		int[] res = new int[n + 1];
		res[0] = 0;
		res[1] = 1;

		for (int i = 2; i <= n; i++) {
			res[i] = res[i - 1] + res[i - 2];
		}

		return res[n];
	}

	static int findFibMemo(int n) {
		HashMap<Integer, Integer> map = new HashMap<>();
		return findFibMemo(n, map);
	}

	static int findFibMemo(int n, HashMap<Integer, Integer> map) {
		if (!map.containsKey(n)) {
			if (n <= 1)
				return n;
			int first = findFibMemo(n - 1, map);
			int second = findFibMemo(n - 2, map);

			map.put(n, first + second);
		}

		return map.get(n);
	}

	//climb stairs 2 way to climb, 1 and 2 staircase /time -> Fib
	//Check climb stairs 3 way! need test
	
	//Longest Increasing Subsequence
	//
	static int LIS(int[] arr) {
		int[] lis = new int[arr.length];

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
					if (max < lis[i]) {
						max = lis[i];
					}
				}
			}
		}

		return max;
	}

	//LDS
	static int LDS(int[] arr) {

	}

	static int bitTonic(int[] arr) {
		int[] lis = new int[arr.length];
		int[] lds = new int[arr.length];

		for(int i = 0; i < arr.length; i++) {
			lis[i] = 1;
			lds[i] = 1;
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j ++) {
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
					lis[i] = lis[j] + 1;
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
					lds[i] = lds[j] + 1；
			}
		}

		int max = 0;
		for (int i = 0; i < ) {
			if (max < lis[i] + lds[i] - 1) {
				max = lis[i] + lds[i] - 1
			}
		}

		return max;
	}

	static int jumpsNeeded(int[] arr) {
		int[] jumps = new int[arr.length];

		int[] res = new int[arr.length];

		jumps[0] = 0;

		for (int i = 0; i < arr.length; i++) {
			jumps[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < arr.length; i ++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] + j > i) {
					if (jumps[j] + 1 < jumps[i]) {
						jumps[i] = jumps[j] + 1;
						res[i] = j;
					}
				}
			}
		}

		return jumps[arr.length - 1];
	}

	//HW: print path of increasing subarray
}
