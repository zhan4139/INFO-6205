//TINGYUAN ZHANG
//NUID: 001838788

import java.util.Arrays;
import java.util.HashSet;

public class Assignment3 {
	public static void main(String[] args) {
		//Problem: tend to have mid value first if it is a peak when
		//there is a duplicate in array
		int[] arr = {1, 2, 3, 1, 1}; 
		int res = findPeak(arr, 0, arr.length - 1);
		System.out.println(res);

		int[] arr2 = {-1, 2, 3, -1, 5};
		findPairSumCloseToX(arr2, 0);
	}

	//return the index of peak value
	static int findPeak(int[] arr, int low, int high) {
		if (low > high) return -1;
		int mid = (low + high)/2;

		//check increasing or decreasing arrays and single elements
		//check mid-1 with 0 and mid+1 with arr.length-1
		if ((mid == 0 || arr[mid] >= arr[mid - 1]) 
			&& (mid == arr.length -1 || arr[mid] >= arr[mid + 1]))
			return mid;
		//right half
		else if (arr[mid] < arr[mid + 1]) 
			return findPeak(arr, mid + 1, high);
		//left half
		else 
			return findPeak(arr, low, mid - 1);
	}

	static void findPairSumCloseToX(int[] arr, int x) {
		Arrays.sort(arr);

		int start = 0; 
		int end = arr.length - 1;
		int finalLeft = 0;
		int finalRight = arr.length - 1;
		int sum = Integer.MAX_VALUE;
		//check input
		if (end < 1) return;

		while (start < end) {
			int diff = Math.abs(arr[start] + arr[end] - x);
			if(diff < sum){
				finalLeft = start;
				finalRight = end;
				sum = diff;    
			} else if (arr[start] + arr[end] < x) {
				start ++;
			} else {
				end --;
			}
		}

		System.out.println("Two elements sum close to x are " + 
			arr[finalLeft] + " " + arr[finalRight]);
	}
}