import java.util.Arrays;

class Assignment1 {
	public static void main (String[] args) {
		int[] arr = {9, 5, 8, 6, 3, 7, 4};

		//find 3rd largest number in an unsorted array
		int res = new Assignment1().findKthLargest(arr, 0, arr.length - 1, 3);
		System.out.println(res);

	}

	public int Partition(int[] arr, int i, int j) {
		int pivot = arr[j];
        int wall = i - 1;
        for(int k = i; k < j; k ++){
            if(arr[k] <= pivot){
                wall ++;
                Swap(arr, k, wall);
            }
        }
        Swap (arr, j, wall + 1);
        return wall + 1;
	}

	public int findKthLargest(int[] arr, int i, int j, int k) {
		if (k < 0 || k > j - i + 1)
			return Integer.MIN_VALUE;
		int pos = Partition(arr, i, j);

		//find if pos is equal to k
		if (j - pos == k - 1)
			return arr[pos];
		//if pos is greater than kth largest, then in the right part
		//find not kth largest in the right subarray, but k - (pos - i)
		else if (j - pos > k - 1)
			return findKthLargest(arr, pos + 1, j, k);
		//if pos is smaller than kth largest
		//in the left part subarray
		else return findKthLargest(arr, i, pos - 1, (k - 1) - (j - pos));
	}

	//helper function
	private void Swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
}
