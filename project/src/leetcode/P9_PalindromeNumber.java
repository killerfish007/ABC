package leetcode;

public class P9_PalindromeNumber {

	public static void main(String[] args) {
		System.out.println(isPalindrome(10));

	}

	public static boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int originalInt = x;
		double rev = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			rev = rev * 10 + pop;
		}
		if (rev >= Integer.MIN_VALUE && rev <= Integer.MAX_VALUE && originalInt == (int) rev) {
			return true;
		}
		return false;
	}

}
