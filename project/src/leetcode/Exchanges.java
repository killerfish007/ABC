package leetcode;

import java.util.Scanner;

public class Exchanges {

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in);) {
			int t = s.nextInt();
			for (int i = 0; i < t; i++) {
				int n = s.nextInt();
				int k = s.nextInt();
				int[] ar = new int[n];
				for (int j = 0; j < n; j++) {
					ar[j] = s.nextInt();
				}
				System.out.println(exchange(n, k, ar));
			}
		}
	}

	private static long exchange(int n, int k, int[] ar) {
		int l = 0;
		for (int i = 0; i < n - 1; i++) {
			l += (k / ar[i]) * (n - 1 - i);
		}
		int r = 0;
		for (int j = 1; j < n; j++) {
			r += (k / ar[j]) * j;
		}
		return Math.abs(l - r);
	}

}
