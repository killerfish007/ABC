package leetcode;

public class P6_ZigZagConversion {

	public static void main(String[] args) {
		System.out.println(convert("ABCDE", 4));
	}

	public static String convert(String s, int numRows) {
		int length = s.length();
		if (numRows <= 1 || numRows > length) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int interval = 2 * numRows - 2;
		for (int i = 0; i < numRows; i++) {
			int step = interval - 2 * i;
			for (int j = i; j < length; j += interval) {
				sb.append(s.charAt(j));
				if (i != 0 && i != numRows - 1 && j + step < length) {
					sb.append(s.charAt(j + step));
				}
			}
		}
		return sb.toString();
	}
}
