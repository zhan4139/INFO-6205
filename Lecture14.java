public class Lecture14 {
	public static void main(String[] args) {
		int[] coins = {2, 5, 6, 8};
		System.out.println(getMinCoins(coins, 11));

		int[] coins2 = {1, 2, 3};
		System.out.println(getMinCoins(coins2, 5));
	}
	//DP
	//Minimum coins to make sum to target num
	//2d table for recording the current minimim coins required
	//need test
	//row = 4, col = 12
	//  0 1 2 3 4 5 6 7 8 9 10 11
	//2 0 0 1 
	//5
	//6
	//8
	public static int getMinCoins(int[] coins, int total) {
		int[][] matrix = new int[coins.length][total + 1];

		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (coins[i] > j) {
					if (i > 0) {
						matrix[i][j] = matrix[i - 1][j];
					} else {
						matrix[i][j] = 0;
					}
 				} else {
					if (i > 0) {
						matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j - coins[i]]) + 1;
					} else {
						matrix[i][j] = matrix[i][j - coins[i]] + 1;
					}
				}
			}
		}
		return matrix[coins.length - 1][total];
	}

	public static int getNumberWaysCoinTotal(int[] coins, int total) {
		int[][] matrix = new int[coins.length][total + 1];

		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (coins[i] > j) {
					if (i > 0) {
						matrix[i][j] = matrix[i - 1][j];
					} else {
						matrix[i][j] = 1;
					}
				} else {
					if (i > 0) {
						matrix[i][j] = matrix[i - 1][j] + matrix[i][j - coins[i]] + 1;
					} else {
						matrix[i][j] = matrix[i][j - coins[i]] + 1;
					}
				}
			}
		}
		return matrix[coins.length - 1][total];
	}

	public static int longestCommonSubstring(char[] arr1, char[] arr2) {
		int[][] matrix = new int[arr1.length + 1][arr2.length + 1];
		int max = 0;
		for (int i = 1; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				if (arr1[i - 1] == arr2[j - 1]) {
					matrix[i][j] = matrix[i - 1][j - 1] + 1;
					max = Math.max(max, matrix[i][j]);
				}
			}
		}
		return max;
	}

	public static int longestCommonSubsequence(char[] arr1, char[] arr2) {
		return 0;
	}

	public static int minCostMatrix(int[][] arr) {
		int[][] dp = new int[arr.length][arr[0].length];

		dp[0][0] = arr[0][0];
		for (int i = 1; i < arr.length; i++) {
			dp[0][i] = dp[0][i - 1] + arr[0][i];
		}

		for (int i = 1; i < arr[0].length; i++) {
			dp[j][0] = dp[j - 1][0] + arr[j][0];
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {
				dp[i][j] = Math.min(dp[i - 1][j] + arr[i][j], dp[i][j - 1] + arr[i][j]);
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + arr[i][j]);
			}
		}
		return dp[i - 1][j - 1];
	}

	public int cutRod(int[] price, int total) {
		int[][] matrix = new int[price.length][total + 1];

		for (int i = 0; i < price.length; i++) {
			for (int j = 0; j < total + 1; j++) {
				if (j == 0) {
					matrix[i][j] = 0;
					continue;
				}

				if (j <= i) {
					matrix[i][j] = matrix[i - 1][j];
				} else {
					if (i > 0) {
						matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - i - 1] + price[i]);
					} else {
						matrix[i][j] = matrix[i - 1][j - i - 1] + price[i];
					}
				}
			}
		}
		return matrix[price.length - 1][total];
	}

	//0-1 Knapsack Problem
	public static int Knapsack(int total, int[] price, int weight) {
		
	}
}