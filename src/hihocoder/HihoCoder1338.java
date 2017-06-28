package hihocoder;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1338.
 * 
 * @version 2017-06-19
 * @author Zhang Yufei.
 */
public class HihoCoder1338 {
	/**
	 * The input.
	 */
	private static Scanner scan;

	/**
	 * The output.
	 */
	private static PrintWriter writer;

	/**
	 * Input data. The length of array.
	 */
	private static int N;

	/**
	 * Input data. The array.
	 */
	private static int[] array;

	/**
	 * Record the sum of the array[i...j].
	 */
	private static int[][] sum;

	/**
	 * Used for DP. given the number array[i...j], dp1[i][j] means the maximum
	 * score the one can get when he move the number first.
	 */
	private static int[][] dp;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		initIO();

		// Input.
		N = scan.nextInt();

		array = new int[N];
		sum = new int[N][N];

		for (int i = 0; i < N; i++) {
			array[i] = scan.nextInt();
		}

		// Compute sum.
		for (int i = 0; i < N; i++) {
			sum[i][i] = array[i];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; i + j < N; j++) {
				sum[j][j + i] = sum[j][j + i - 1]
						+ array[j + i];
			}
		}

		// DP.
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			dp[i][i] = array[i];
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j + i < N; j++) {
				int r1 = array[j] + sum[j + 1][j + i]
						- dp[j + 1][j + i];
				int r2 = array[j + i] + sum[j][j + i - 1]
						- dp[j][j + i - 1];

				dp[j][j + i] = r1 > r2 ? r1 : r2;
			}
		}

		// Print.
		writer.println(dp[0][N - 1]);

		// Close IO.
		scan.close();
		writer.close();
	}

	/**
	 * Init the input and output.
	 */
	private static void initIO() {
		try {
			scan = new Scanner(System.in);
			// scan = new Scanner(new File(
			// "E:\\workspace\\ACM\\src\\data.txt"));
			// writer = new PrintWriter(new File(
			// "E:\\workspace\\ACM\\src\\test.txt"));
			writer = new PrintWriter(System.out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

