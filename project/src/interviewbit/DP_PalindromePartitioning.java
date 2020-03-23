package interviewbit;

public class DP_PalindromePartitioning {
	public static void main(String[] args) {
		System.out.println(minCut("abcde"));
		System.out.println(minCutFast("abcde"));
	}

	public static int minCut(String s) {
		int[][] dp = new int[s.length()][s.length()];
		for (int len = 1; len <= s.length(); len++) {
			for (int i = 0; i + len < s.length(); i++) {
				int j = i + len;
				if (!isPalindrome(s, i, j)) {
					int min = Integer.MAX_VALUE;
					for (int k = i; k <= j - 1; k++) {
						min = Math.min(min, 1 + dp[i][k] + dp[k + 1][j]);
					}
					dp[i][j] = min;
				}
			}
		}
		return dp[0][s.length() - 1];

	}

	public static boolean isPalindrome(String str, int l, int r) {
		while (l < r) {
			if (str.charAt(l) != str.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}

	public static int minCutFast(String s) {
		boolean[][] isPalin = new boolean[s.length()][s.length()];
		int[] cuts = new int[s.length()];
		for (int len = 1; len <= s.length(); len++) {
			for (int i = 0; i + len < s.length(); i++) {
				int j = i + len;
				boolean isInnerwordPalin = j-i <2 || isPalin[i+1][j-1];
				if(s.charAt(i) == s.charAt(j) && isInnerwordPalin) {
					isPalin[i][j] = true;
				}
			}
		}
		return cuts[s.length() - 1];
	}

}
