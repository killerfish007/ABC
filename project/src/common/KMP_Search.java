package common;

public class KMP_Search {
	/**
	 * Slow method of pattern matching
	 */
	public void strSearch(char[] text, char[] pattern) {

		for (int i = 0; i <= text.length - pattern.length; i++) {
			int k = 0;
			while (k < pattern.length && text[i + k] == pattern[k]) {
				k++;
			}
			if (k == pattern.length)
				System.out.println(i);
		}
	}

	/**
	 * Compute temporary array to maintain size of suffix which is same as prefix
	 * Time/space complexity is O(size of pattern)
	 */
	private int[] computeTemporaryArray(char pattern[]) {
		int[] lps = new int[pattern.length];
		for (int i = 1, j=0; i < pattern.length;) {
			if (pattern[i] == pattern[j]) {
				lps[i] = j + 1;
				j++;
				i++;
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	/**
	 * KMP algorithm of pattern matching.
	 */
	public void KMP(char[] text, char[] pattern) {

		int lps[] = computeTemporaryArray(pattern);
		int i = 0;
		int j = 0;
		while (i < text.length) {
			if (text[i] == pattern[j]) {
				i++;
				j++;
			}
			if (j == pattern.length) {
				System.out.println("Pattern Found At : " + (i - j));
				j = lps[j - 1];
			}
			if (i < text.length && text[i] != pattern[j]) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
	}

	public static void main(String args[]) {

		String str = "ABABDABACDABABCABAB";
		String subString = "AB";
		KMP_Search ss = new KMP_Search();
		ss.strSearch(str.toCharArray(), subString.toCharArray());
		ss.KMP(str.toCharArray(), subString.toCharArray());

	}
}
