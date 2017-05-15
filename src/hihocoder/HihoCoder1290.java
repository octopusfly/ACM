package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1290.
 * 
 * @version 2017-05-15
 * @author Zhang Yufei.
 */
public class HihoCoder1290 {
	/**
	 * Record the information of each grid.
	 */
	private static class Grid {
		boolean isBlocked;
		/**
		 * The minimum blocks to remove when move through this grid in right
		 * direction.
		 */
		int right;
		/**
		 * The minimum blocks to remove when move through this grid in down
		 * direction.
		 */
		int down;
	}

	/**
	 * Input data.
	 */
	private static int N, M;

	/**
	 * Grids list.
	 */
	private static Grid[][] grids;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		// Input.
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();

		grids = new Grid[N][M];

		for (int i = 0; i < N; i++) {
			char[] array = scan.next().toCharArray();
			for (int j = 0; j < M; j++) {
				grids[i][j] = new Grid();
				if (array[j] == '.') {
					grids[i][j].isBlocked = false;
				} else {
					grids[i][j].isBlocked = true;
				}
			}
		}

		scan.close();

		// Compute
		if (grids[N - 1][M - 1].isBlocked) {
			grids[N - 1][M - 1].right = 1;
			grids[N - 1][M - 1].down = 1;
		} else {
			grids[N - 1][M - 1].right = 0;
			grids[N - 1][M - 1].down = 0;
		}

		for (int j = M - 2; j >= 0; j--) {
			grids[N - 1][j].right = grids[N - 1][j + 1].right;
			if (grids[N - 1][j].isBlocked) {
				grids[N - 1][j].right++;
			}
			grids[N - 1][j].down = grids[N - 1][j].right;
		}

		for (int i = N - 2; i >= 0; i--) {
			grids[i][M - 1].down = grids[i + 1][M - 1].down;
			if (grids[i][M - 1].isBlocked) {
				grids[i][M - 1].down++;
			}
			grids[i][M - 1].right = grids[i][M - 1].down;
		}

		for (int i = N - 2; i >= 0; i--) {
			for (int j = M - 2; j >= 0; j--) {
				int result1 = grids[i][j + 1].right;
				;
				int result2 = grids[i + 1][j].down;
				if (grids[i + 1][j].isBlocked
						&& grids[i][j + 1].isBlocked) {
					grids[i][j].right = grids[i][j].down = result1 < result2 ? result1
							: result2;
				} else if (grids[i + 1][j].isBlocked) {
					grids[i][j].down = result1 < result2 ? result1
							: result2;
					grids[i][j].right = result1 < result2 + 1 ? result1
							: result2 + 1;
				} else if (grids[i][j + 1].isBlocked) {
					grids[i][j].right = result1 < result2 ? result1
							: result2;
					grids[i][j].down = result2 < result1 + 1 ? result2
							: result1 + 1;
				} else {
					grids[i][j].right = result1 < result2 + 1 ? result1
							: result2 + 1;
					grids[i][j].down = result2 < result1 + 1 ? result2
							: result1 + 1;
				}

				if (grids[i][j].isBlocked) {
					grids[i][j].right++;
					grids[i][j].down++;
				}
			}
		}

		// Print.
		System.out.println(grids[0][0].right);
	}
}
