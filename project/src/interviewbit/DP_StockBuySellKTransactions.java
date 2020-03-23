package interviewbit;

import java.util.Stack;

public class DP_StockBuySellKTransactions {
	public static void main(String[] args) {
		int[] prices = { 10, 22, 5, 75, 65, 80 };
		System.out.println("Max Profit :: " + maxProfitSlowSolution(2, prices));
		System.out.println("Max Profit :: " + maxProfitFastSolution(2, prices));
	}

	public static int maxProfitSlowSolution(int k, int[] prices) {
		int[][] dp = new int[k + 1][prices.length];
		for (int i = 1; i <= k; i++) {
			for (int j = 1; j < prices.length; j++) {
				int maxV = dp[i][j - 1];
				for (int m = 0; m < j; m++) {
					maxV = Math.max(maxV, prices[j] - prices[m] + dp[i - 1][m]);
				}
				dp[i][j] = maxV;
			}
		}
		printActualSolution(dp, prices);
		return dp[k][prices.length - 1];
	}

	public static int maxProfitFastSolution(int k, int[] prices) {
		int[][] dp = new int[k + 1][prices.length];
		for (int i = 1; i <= k; i++) {
			int maxD = Integer.MIN_VALUE;
			for (int j = 1; j < prices.length; j++) {
				maxD = Math.max(maxD, dp[i - 1][j - 1] - prices[j - 1]);
				dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxD);
			}
		}
		printActualSolution(dp, prices);
		return dp[k][prices.length - 1];
	}

	public static void printActualSolution(int[][] dp, int[] prices) {
		int i = dp.length - 1;
		int j = dp[0].length - 1;
		Stack<Integer> s = new Stack<>();
		while (i != 0 || j != 0) {
			if (dp[i][j] == dp[i][j - 1]) {
				j = j - 1;
			} else {
				s.add(prices[j]);
				for (int k = j - 1; k >= 0; k--) {
					if (dp[i][j] - prices[j] == dp[i - 1][k] - prices[k]) {
						i = i - 1;
						j = k;
						s.add(prices[k]);
						break;
					}
				}
			}
		}
		while (!s.isEmpty()) {
			System.out.println("Sell at price :: " + s.pop());
			System.out.println("Buy at price :: " + s.pop());
		}
	}
}
