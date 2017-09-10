import java.util.Arrays;

class Lecture1 {
	public static void main (String[] args) {
		int[] arr = {10, 5, 8, 6, 3, 7, 5};

		new Lecture1().BubbleSort2(arr);
		System.out.println(Arrays.toString(arr));
		//System.out.println(arr.length);

	}



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

	//helper function
	private void Swap(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
}