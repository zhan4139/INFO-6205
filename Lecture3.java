//Searching
import java.util.Arrays;
import java.lang.Math;
public class Lecture3 {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 5, 7, 10, 11, 13, 15, 17, 20, 45};
		boolean found = new Lecture3().binarySearch(arr, 13);
		System.out.println(found);

		int[] arr2 = {1, 2, 3, 4, 5, 6};
		new Lecture3().rotate(arr2, 2);
		for (int i = 0; i < arr2.length; i++) {
			System.out.println(arr2[i]);
		}

		int[] arr3 = {1,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4, 6,6,6};
		int res = new Lecture3().getOccurances(arr3, 3);
		System.out.println(res);

		int res2 = new Lecture3().findFirstOccurance(arr3, 3, 0, arr3.length - 1);
		System.out.println(res2);

		int[] arr4 = {1, 2, 3, 4, 5, 7, 8, 10};
		int res3 = new Lecture3().findCeiling(arr4, 9);
		System.out.println(res3);
	}

	//(1)6, 3, 5, 1, 7, 2, 8
	//(2)1, 5, 3, 6, 7, 2, 8
	public void pancakeSort() {
		return;
	}

	public boolean binarySearch(int[] arr, int x) {
		//sorted array, iteration
		
		int low = 0;
		int high = arr.length - 1;
		while (low < high) {
			//int mid = arr.length/2;
			int mid = (low + high)/2;
			if (arr[mid] == x)
				return true;
			else if (arr[mid] > x) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return false;
	}

	public boolean binarySearch2(int[] arr, int x) {
		//recursion
		return binarySearch2(arr, x, 0, arr.length - 1);
	}

	//need to be tested
	public boolean binarySearch2(int[] arr, int x, int low, int high) {
		if (low >= high) return false;
		//int mid = arr.length/2;
		int mid = low/2 + high/2;
		if (arr[mid] == x) return true;
		else if (arr[mid] < x) return binarySearch2(arr, x, mid + 1, high);
		else return binarySearch2(arr, x, low, mid - 1);
	}

	//rotate array 
	//Homework 
	//
	public void swap(int[] arr, int start, int end) {
		int temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
	}
	
	public void reverse(int[] arr, int start, int end) {
		// int start = 0;
		// int end = arr.length - 1;
		// 
		while(start <= end) {
			swap(arr, start++, end--);
		}
	}

	public void rotate(int[] arr, int x) {
		x = x % arr.length;

		reverse(arr, 0, x - 1);
		reverse(arr, x, arr.length - 1);
	}


	//find occurance 
	//recursive need test
	public int getOccurances(int[] arr, int x) {
		return getOccurances(arr, x, 0, arr.length-1);
	}

	public int getOccurances(int[] arr, int x, int start, int end) {
		if (arr[start] > x || arr[end] < x) return 0;
		if (arr[start] == x && arr[end] == x) return end - start +1;

		//int mid = arr.length/2;
		int mid = (start + end)/2;
		if (arr[mid] == x) 
			return (1 + getOccurances(arr, x, start, mid - 1) + getOccurances(arr, x, mid + 1, end));
		else if (arr[mid] > x) {
			return getOccurances(arr, x, start, mid - 1);
		} else {
			return getOccurances(arr, x, mid + 1, end);
		}
	}

	//find first occurence
	//
	public int findFirstOccurance(int[] arr, int x, int start, int end) {
		if (arr[start] > x || arr[end] < x) return -1;
		if (arr[start] == x) return start;

		int mid = (start + end)/2;
		if (arr[mid] >= x) return findFirstOccurance(arr, x, start, mid - 1);
		else return findFirstOccurance(arr, x, mid + 1, end);
		//else return findFirstOccurance(arr, x, )
	}

	//HW: find last occurance
	//
	public int findLastOccurance(int[] arr, int x, int start, int end) {
		if (arr[start] > x || arr[end] < x) return -1;
		if (arr[end] == x) return end;

		int mid = (start + end)/2;
		if (arr[mid] <= x) return findLastOccurance(arr, x, mid + 1, end);
		else return findLastOccurance(arr, x, start, mid - 1);
		//else return findFirstOccurance(arr, x, )
	}
	
	
	//find first value bigger than some value
	//sorted array, return index
	public int findFirstBiggerThanK(int[] arr, int k) {
		int res = -1;
		int start = 0;
		int end = arr.length - 1;

		if (arr[arr.length - 1] < k) return -1;

		while (start <= end) {
			int mid = (start + end)/2;
			if (arr[mid] > k) {
				res = mid;
				end = mid - 1;
			} else start = mid + 1;
		}

		return res;
	}

	//find ceiling/floor of some value in sorted array
	//if found, return, otherwise, return prev or next one's index
	public int findCeiling(int[] arr, int k) {
		if (arr[arr.length -1] < k)
			return -1;
		int start = 0;
		int end = arr.length - 1;
		int res = -1;

		while (start <= end) {
			int mid = (start + end)/2;
			if (arr[mid] == k) {
				return mid;
			} else if (arr[mid] > k) {
				res = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		return res;
	}

	public int findFloor(int[] arr, int k) {
		if (arr[arr.length -1] < k)
			return -1;
		int start = 0;
		int end = arr.length - 1;
		int res = -1;

		while (start <= end) {
			int mid = (start + end)/2;
			if (arr[mid] == k) {
				return mid;
			} else if (arr[mid] < k) {
				res = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return res;
	}

	//HW2: fix the following function
	public int findPeak(int[] arr, int low, int high) {
		if (low > high) return -1;
		int mid = (low + high)/2;

		if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
			return mid;

		if (arr[mid] < arr[mid + 1]) 
			return findPeak(arr, mid + 1, high);
		else if (arr[mid] < arr[mid + 1])
			return findPeak(arr, low, mid - 1);

		return -1;
	}

	//sorted array, re sort it to the wave shape
	//O(nlgn) because we are sorting the array
	public void sortArrayIntoWave(int[] arr) {
		Arrays.sort(arr);
		for (int i = 0; i < arr.length -1; i+=2) {
			swap(arr, i, i+1);
		}
	}

	//without sorting
	public void sortArrayIntoWave2(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i > 0 && arr[i - 1] > arr[i]) {
				swap(arr, i - 1, i);
			}
			if (i < arr.length - 1 && arr[i] < arr[i + 1]) {
				swap(arr, i, i + 1);
			}
		}
	}

	//
	public void findSumEqualToX(int[] arr, int x) {
		Arrays.sort(arr);

		int start = 0; 
		int end = arr.length - 1;

		while (start < end) {
			if (arr[start] + arr[end] == x) {
				System.out.println("Found");
				return;
			} else if (arr[start] + arr[end] < x) {
				start ++;
			} else {
				end --;
			}
		}
	}

	public int findCloseToX(int[] arr, int x) {
		Arrays.sort(arr);

		int start = 0; 
		int end = arr.length - 1;
		int finalLeft = 0;
		int finalRight = arr.length - 1;

		int sum = Integer.MAX_VALUE;
		int diff = Math.abs(arr[start] + arr[end] - x);

		while (start < end) {
			if (diff == 0) {
				System.out.println("Found " + start + " " + end);
				return 1;
			} else if (arr[start] + arr[end] < x) {
				start ++;
			} else {
				end --;
			}
		}

		return sum;
	}

	//2, 11, 4, 1, 4, 7
	//find a pair that sum to the rest numbers
	//sum all numbers, then find pair equals half of the sum
	
	public void findPairSumEqualRest(int[] arr) {
		Arrays.sort(arr);
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		sum /= 2;

		int start = 0;
		int end = arr.length - 1;

		while (start < end) {
			if (arr[start] + arr[end] == sum) {
				System.out.println("Found");
				return;
			}
				
			else if (arr[start] + arr[end] > sum)
				end --;
			else start ++;
		}
		
		System.out.println("Not found");
		return;

	}
}