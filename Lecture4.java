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

		char[] c = {'a', 'b', 'a', 'c', 'b'};
		System.out.println(canBePalindrome2(c));
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

	//7. check all characters are unique
	//
	public static boolean isUnique(char[] arr) {
		HashSet<Character> set = new HashSet<>();

		for (int i = 0; i < arr.length; i++) {
			if (set.contains(arr[i]))
				return false;
			else
				set.add(arr[i]);
		}

		return true;
	}

	//8. can string can be arranged to palindrome
	// need work
	public static boolean canBePalindrome(char[] arr) {
		Arrays.sort(arr);

		boolean alone = false;
		int count = 0;
		char curr = arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == curr) {
				count ++;
			} else {
				if (count % 2 != 0) {
					if (alone) alone = false;
					else {
						alone = true;
						curr = arr[i];
						count = 1;
					}
				} else {
					count = 1;
				}
			}
		}
		return alone;
	}


	//O(n) extra space
	public static boolean canBePalindrome2(char[] arr) {
		HashMap<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], 1+ map.get(arr[i]));
			}
		}

		boolean odd = false;
		for (Character c : map.keySet()) {
			if (map.get(c) % 2 != 0) {
				if (odd) odd = false;
				else odd = true;
			}
		}

		return odd;
	}

	//9. compress string 
	//need test
	public static String stringCompress(char[] arr) {
		if (arr.length == 0 || arr.length == 1) return new String(arr);

		char curr = arr[0];
		int count = 1;

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < arr.length i ++) {
			if (arr[i] == curr) {
				count ++;
			} else {
				sb.append(String.valueOf(curr));
				sb.append(String.valueOf(count));
				curr = arr[i];
				count = 1;
			}
		}

		sb.append(String.valueOf(curr));
		sb.append(String.valueOf(count));

		return sb.toString();
	}

	//10. 
	//need work
	public static boolean isRotate() {}

	//11. rotate matrix to right 90 degree size n*n or not n*n
	//need work
	public static void matrixRotation(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = i; j < matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i = 0 ; i < matrix.length; i++){
            for(int j = 0; j < matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
	}

	//12. replace space with %20
	public static String replaceSpace(char[] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < arr.length; i ++) {
			if (arr[i] == ' ') {
				sb.append("%20");
			} else {
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	//13. reverse a sentense 
	//reverse whole sentence and then reverse words
	public static String reverseSentence() {
		
	}
}