package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1240.
 * 
 * @version 2016-09-06
 * @author Zhang Yufei
 */
public class HihoCoder1240 {
	// input data.
	private static int N, T;

	// image matrix.
	private static int[][] imageA, imageB;

	// input.
	private static Scanner scan = new Scanner(System.in);

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters.
	 */
	public static void main(String[] args) {
		T = scan.nextInt();
		for (int i = 0; i < T; i++) {
			function();
		}
		scan.close();
	}

	/**
	 * This function deals with one test case;
	 */
	private static void function() {
		N = scan.nextInt();

		imageA = new int[N][N];
		imageB = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				imageA[i][j] = scan.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				imageB[i][j] = scan.nextInt();
			}
		}

		if (check(0, 0, 0, 0, N)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	/**
	 * This function check if the given two sub-images can transfer each other.
	 * 
	 * @param x1
	 *            The x of the left-top position of the image A.
	 * @param y1
	 *            The y of the left-top position of the image A.
	 * @param x2
	 *            The x of the left-top position of the image B.
	 * @param y2
	 *            The y of the left-top position of the image B.
	 * @param size
	 * @return If the two images can transfer each other, returns
	 *         <code>true</code>, or returns <code>false</code>.
	 */
	private static boolean check(int x1, int y1, int x2, int y2, int size) {
		if (size % 2 == 0) {
			int[][] area = new int[][] { { 0, 0 }, { 0, size / 2 }, { size / 2, 0 }, { size / 2, size / 2 } };
			
			// original.
			if (check(x1 + area[0][0], y1 + area[0][1], x2 + area[0][0], y2 + area[0][1], size / 2)
			 && check(x1 + area[1][0], y1 + area[1][1], x2 + area[1][0], y2 + area[1][1], size / 2)
			 && check(x1 + area[2][0], y1 + area[2][1], x2 + area[2][0], y2 + area[2][1], size / 2)
			 && check(x1 + area[3][0], y1 + area[3][1], x2 + area[3][0], y2 + area[3][1], size / 2)) {
				return true;
			}

			// 90 degree.
			if (check(x1 + area[0][0], y1 + area[0][1], x2 + area[1][0], y2 + area[1][1], size / 2)
			 && check(x1 + area[1][0], y1 + area[1][1], x2 + area[3][0], y2 + area[3][1], size / 2)
			 && check(x1 + area[2][0], y1 + area[2][1], x2 + area[0][0], y2 + area[0][1], size / 2)
			 && check(x1 + area[3][0], y1 + area[3][1], x2 + area[2][0], y2 + area[2][1], size / 2)) {
				return true;
			}

			// 180 degree.
			if (check(x1 + area[0][0], y1 + area[0][1], x2 + area[3][0], y2 + area[3][1], size / 2)
			 && check(x1 + area[1][0], y1 + area[1][1], x2 + area[2][0], y2 + area[2][1], size / 2)
			 && check(x1 + area[2][0], y1 + area[2][1], x2 + area[1][0], y2 + area[1][1], size / 2)
			 && check(x1 + area[3][0], y1 + area[3][1], x2 + area[0][0], y2 + area[0][1], size / 2)) {
				return true;
			}

			// 270 degree.
			if (check(x1 + area[0][0], y1 + area[0][1], x2 + area[2][0], y2 + area[2][1], size / 2)
			 && check(x1 + area[1][0], y1 + area[1][1], x2 + area[0][0], y2 + area[0][1], size / 2)
			 && check(x1 + area[2][0], y1 + area[2][1], x2 + area[3][0], y2 + area[3][1], size / 2)
			 && check(x1 + area[3][0], y1 + area[3][1], x2 + area[1][0], y2 + area[1][1], size / 2)) {
				return true;
			}
		} else {
			boolean result = true;
			
			// original.
			for (int xa = x1, xb = x2; xa < x1 + size; xa++, xb++) {
				for (int ya = y1, yb = y2; ya < y1 + size; ya++, yb++) {
					if (imageA[xa][ya] != imageB[xb][yb]) {
						result = false;
					}
				}
			}

			if (result) {
				return true;
			}

			// 90 degree.
			result = true;
			for (int xa = x1, yb = y2 + size - 1; xa < x1 + size; xa++, yb--) {
				for (int ya = y1, xb = x2; ya < y1 + size; ya++, xb++) {
					if (imageA[xa][ya] != imageB[xb][yb]) {
						result = false;
					}
				}
			}

			if (result) {
				return true;
			}

			// 180 degree.
			result = true;
			for (int xa = x1, xb = x2 + size - 1; xa < x1 + size; xa++, xb--) {
				for (int ya = y1, yb = y2 + size - 1; ya < y1 + size; ya++, yb--) {
					if (imageA[xa][ya] != imageB[xb][yb]) {
						result = false;
					}
				}
			}

			if (result) {
				return true;
			}

			// 270 degree.
			result = true;
			for (int xa = x1, yb = y2; xa < x1 + size; xa++, yb++) {
				for (int ya = y1, xb = x2 + size - 1; ya < y1 + size; ya++, xb--) {
					if (imageA[xa][ya] != imageB[xb][yb]) {
						result = false;
					}
				}
			}

			if (result) {
				return true;
			}
		}

		return false;
	}
}