package interviewbit;

import java.util.HashSet;

public class ColorfulNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int colorful(int A) {
		HashSet<Integer> set = new HashSet<>();
		while (A > 0) {
			int t = A;
			int prod = 1;
			while (t > 0) {
				int d = t % 10;
				t /= 10;
				prod *= d;
				if (!set.add(prod))
					return 0;
			}
			A /= 10;
		}
		return 1;
	}

}
