import java.util.Arrays;

class Lecture1 {
	public static void main (String[] args) {
		int[] arr = {9, 5, 8, 6, 3, 7, 6};

		new Lecture1().CountSort(arr);
		System.out.println(Arrays.toString(arr));
		//System.out.println(arr.length);

	}


	//Lecture version
	public void BubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					Swap(arr, i, j);
				}
			} 
		}
	}

	//This is the one that is the same as what lecturer said
	public void BubbleSort2(int[] arr) {
		for (int i = 0; i < arr.length - i; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j+1]) {
					Swap(arr, j, j+1);
				}
			} 
		}
	}

	public void SelectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min])
					min = j;
			}
			if (min != i) 
				Swap(arr, i, min);
		}
	}

	public void InsertionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int j = i + 1;
			int temp = arr[j];
			while (j > 0 && temp < arr[j-1]) {
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = temp;
		}
	}

	//O(nlgn)
	public void MergeSort(int[] arr, int low, int high) {
		if (low >= high)
			return;
		int mid = (low + high)/2;
		MergeSort(arr, low, mid);
		MergeSort(arr, mid+1, high);
		Merge(arr, low, high); 
	}

	public void Merge(int[] arr, int low, int high) {
		int mid = (low + high)/2;
		int[] temp = new int [high - low + 1]; //new array for merged array
		int i = low;
		int j = mid + 1;
		int index = 0;//for new array

		System.out.println(arr[i]);

		//begin merging process
		while (i <= mid && j <= high) {
			if (arr[i] < arr[j]) 
				temp[index++] = arr[i++];
			else temp[index++] = arr[j++];
		}

		//deal with left-overs
		while (i <= mid) temp[index++] = arr[i++];
		while (j <= high) temp[index++] = arr[j++];

		//copy temp arr to original array
		i = low;
		for (int k = 0; k < temp.length; k++)
			arr[i++] = temp[k];

	}

	//O(nlgn), best case O(n), worst case O(n^2)
	public void QuickSort(int[] arr, int i, int j) {
		if(i < j) {
            int pos = Partition(arr, i, j);
            QuickSort(arr, i, pos - 1);
            QuickSort(arr, pos + 1, j);
        }
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

	//only if we know the range of integers
	public void CountSort(int[] arr) {
		int[] count = new int [10];

		for (int i : arr) {
			count[i] ++;
		}

		int index = 0;
		for (int j = 0; j < count.length; j++) {
			while (count[j] > 0) {
				arr[index++] = j;
				count[j] --;
			}
		}
	}


	//helper function
	private void Swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
}