package common;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		playTicTacToe();
	}

	public static int COMPUTER = 1;
	public static int HUMAN = 2;
	public static char COMPUTERMOVE = 'O';
	public static char HUMANMOVE = 'X';

	public static void showBoard(char board[][]) {
		System.out.printf("\n\n");
		System.out.printf("\t\t\t  %c | %c  | %c  \n", board[0][0], board[0][1], board[0][2]);
		System.out.printf("\t\t\t--------------\n");
		System.out.printf("\t\t\t  %c | %c  | %c  \n", board[1][0], board[1][1], board[1][2]);
		System.out.printf("\t\t\t--------------\n");
		System.out.printf("\t\t\t  %c | %c  | %c  \n\n", board[2][0], board[2][1], board[2][2]);
	}

	public static void showInstructions() {
		System.out.printf("\t\t\t  Tic-Tac-Toe\n\n");
		System.out.printf("Choose a cell numbered from 1 to 9 as below and play\n\n");

		System.out.printf("\t\t\t  1 | 2  | 3  \n");
		System.out.printf("\t\t\t--------------\n");
		System.out.printf("\t\t\t  4 | 5  | 6  \n");
		System.out.printf("\t\t\t--------------\n");
		System.out.printf("\t\t\t  7 | 8  | 9  \n\n");

		System.out.printf("-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n");
	}

	public static void initialise(char board[][]) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public static void declareWinner(int whoseTurn) {
		if (whoseTurn == COMPUTER)
			System.out.printf("COMPUTER has won\n");
		else
			System.out.printf("HUMAN has won\n");
	}

	public static boolean rowCrossed(char board[][]) {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ')
				return true;
		}
		return false;
	}

	public static boolean columnCrossed(char board[][]) {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')
				return true;
		}
		return false;
	}

	public static boolean diagonalCrossed(char board[][]) {
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
			return true;

		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')
			return true;
		return false;
	}

	public static boolean gameOver(char board[][]) {
		return rowCrossed(board) || columnCrossed(board) || diagonalCrossed(board);
	}

	public static boolean isMoveLeft(char board[][]) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ')
					return true;
			}
		}
		return false;
	}

	public static void playTicTacToe() {
		char board[][] = new char[3][3];
		int moves[] = new int[3 * 3];
		initialise(board);
		showInstructions();
		int whoseTurn = COMPUTER;
		Scanner s = new Scanner(System.in);
		System.out.println("Are you going to start the game?(y/n) :");
		String str = s.nextLine();
		if(str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes")) {
			whoseTurn = HUMAN;
		}
		
		int x, y;
		while (!gameOver(board) && isMoveLeft(board)) {
			if (whoseTurn == COMPUTER) {
				Random r = new Random();
				int m = r.nextInt(9);
				while (moves[m] != 0) {
					m = r.nextInt(9);
				}
				moves[m] = 1;
				x = m / 3;
				y = m % 3;
				board[x][y] = COMPUTERMOVE;
				System.out.printf("COMPUTER has put a %c in cell %d\n", COMPUTERMOVE, m + 1);
				showBoard(board);
				whoseTurn = HUMAN;
			} else if (whoseTurn == HUMAN) {
				System.out.print("Choose a valid cell numbered from 1 to 9:");
				int m = s.nextInt();
				m = m - 1;
				while (moves[m] != 0) {
					System.out.println("Choose a valid cell numbered from 1 to 9:");
					m = s.nextInt();
				}
				moves[m] = 1;
				x = m / 3;
				y = m % 3;
				board[x][y] = HUMANMOVE;
				System.out.printf("HUMAN has put a %c in cell %d\n", HUMANMOVE, m + 1);
				showBoard(board);
				whoseTurn = COMPUTER;
			}
		}

		if (!gameOver(board))
			System.out.printf("It's a draw\n");
		else {
			if (whoseTurn == COMPUTER)
				whoseTurn = HUMAN;
			else if (whoseTurn == HUMAN)
				whoseTurn = COMPUTER;

			declareWinner(whoseTurn);
		}

	}
}
