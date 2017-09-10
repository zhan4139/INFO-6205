import java.util.Arrays;

class Lecture1 {
	public static void main (String[] args) {
		int[] arr = {10, 5, 8, 6, 3, 7, 5};

		//new Lecture1().BubbleSort2(arr);
		new Lecture1().InsertionSort(arr);
		System.out.println(Arrays.toString(arr));
		//System.out.println(arr.length);

	}


	//Lecture version
	public void BubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					Swap(arr, i, j);
					//System.out.println(i);
				}
			} 
		}
	}

	//This is the one that is the same as what lecturer said
	public void BubbleSort2(int[] arr) {
		for (int i = 0; i < arr.length - i; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j+1]) {
					//System.out.println(arr[j]);
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
			//System.out.println(arr[i]);
		}
	}

	public void InsertionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int j = i + 1;
			int temp = arr[j];
			while (j > 0 && temp < arr[j-1]) {
				//Swap(arr, j-1, j);
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = temp;
		}

	}

	public void MergeSort(int[] arr) {

	}

	public void QuickSort(int[] arr) {

	}
	//helper function
	private void Swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
}