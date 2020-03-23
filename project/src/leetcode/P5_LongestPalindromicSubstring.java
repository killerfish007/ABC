package leetcode;

public class P5_LongestPalindromicSubstring {

	public static void main(String[] args) {
		System.out.println(longestPalindrome("babad"));

	}

	public static String longestPalindrome(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		int length = s.length();

		boolean[][] isPalindrome = new boolean[length][length];
		int left = 0;
		int right = 0;

		for (int j = 0; j < length; j++) {
			for (int i = 0; i <= j; i++) {
				boolean isInnerwordPalindrome = j - i <= 2 || isPalindrome[i + 1][j - 1];
				if (s.charAt(i) == s.charAt(j) && isInnerwordPalindrome) {
					isPalindrome[i][j] = true;
					if (j - i > right - left) {
						left = i;
						right = j;
					}
				}
			}
		}

		return s.substring(left, right + 1);
	}
}
