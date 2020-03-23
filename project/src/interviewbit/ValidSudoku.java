package interviewbit;

public class ValidSudoku {

	public static void main(String[] args) {
		String[] A = { "..5.....6", "....14...", ".........", ".....92..", "5....2...", ".......3.", "...54....",
				"3.....42.", "...27.6.." };
		System.out.println(isValidSudoku(A));

	}

	public static int isValidSudoku(final String[] A) {
		boolean[][] row = new boolean[9][9];
		boolean[][] col = new boolean[9][9];
		boolean[][] square = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = A[i].charAt(j);
				if (c == '.') {
					continue;
				}
				int digit = (int) (c - '1');
				int s = 3 * (i / 3) + (j / 3);
				if (row[i][digit] || col[j][digit] || square[s][digit]) {
					return 0;
				}
				row[i][digit] = col[j][digit] = square[s][digit] = true;
			}
		}

		return 1;

	}
}
