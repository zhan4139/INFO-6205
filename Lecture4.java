import java.util.*;
import java.lang.*;

public class Lecture4 {
	public static void main(String[] args) {
		int[] a2 = {-1, 2, 0, 3, -2, 0};
		dutchFlag(a2);
		System.out.println(Arrays.toString(a2));
		replaceWithNextGreatest(a2);
		System.out.println(Arrays.toString(a2));

		String s1 = "123", s2 = "321";
		System.out.println(isAnagram(s1, s2));
	}

	//1.
	//majority element, occurs more than 50%
	//(1) hashmap extra space O(n); use a candidate element and a count as pivot,
	//if it is the one left candidate, then loop through the array to see if it is
	//really the majority(because there is a 1, 2, 3, 4 case)
	public static int getMajorityElement(int[] arr) {
		if (arr.length == 0) return Integer.MIN_VALUE;
		int candidate = arr[0];
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == candidate) {
				count ++;
			} else {
				candidate = arr[i];
				count = 1;
			}
		}

		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == candidate) {
				cnt ++;
			}
		}

		if (cnt > arr.length / 2) return candidate;
		return Integer.MIN_VALUE;
	}
	

	//2. move smaller element than 0 to the front and large than 0 to the back
	//
	public static void dutchFlag(int[] arr) {
		int back = 0;
		int mid = 0;
		int front = arr.length - 1;

		while (mid <= front) {
			if (arr[mid] < 0) {
				swap(arr, back, mid);
				mid++;
				back++;
			} else if (arr[mid] == 0) {
				mid++;
			} else {
				swap(arr, mid, front);
				front--;
			}
		}
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	//HW: find the index of maximux subsequence
	//3. 
	//
	public static void kadane(int[] arr) {
		int maxSoFar = 0; // assume there r negatives and positives
		int sum = 0;

		for (int i = 0; i < arr.length; i ++) {
			sum = sum  + arr[i];

			if (maxSoFar < sum) {
				maxSoFar = sum;
			}

			if (sum < 0) {
				sum = 0;
			}
		}
	}

	//4. move all zeros to the end
	//need to be tested
	public static void moveAllZeroToEnd(int[] arr) {
		int start =  0;
		int end = arr.length - 1;

		while (start <= end) {
			if (arr[end] != 0) {
				if (arr[start] == 0) {
					swap(arr, start, end);
				} else {
					start ++;
				}
			} else {
				end --;
			}
		}
	}

	//5. find the greatest number in the rest of array and replace with it
	//Sol: start from end
	public static void replaceWithNextGreatest(int[] arr) {
		int maxSoFar = arr[arr.length - 1];

		arr[arr.length - 1] = Integer.MIN_VALUE;

		for (int i = arr.length - 2; i >= 0; i--) {
			int temp = arr[i];
			arr[i] = maxSoFar;
			if (temp > maxSoFar)
				maxSoFar = temp;
		}

	}

	//6. Check string anagrams
	public static boolean isAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) return false;

		HashMap<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if (map.containsKey(c1)) {
				map.put(c1, map.get(c1) + 1);
			} else {
				map.put(c1, 1);
			}

			if (map.containsKey(c2)) {
				map.put(c2, map.get(c2) - 1);
			} else {
				map.put(c2, -1);
			}
		}

		for (Character s : map.keySet()) {
			if (map.get(s) != 0)
				return false;
		}

		return true;

	}
}