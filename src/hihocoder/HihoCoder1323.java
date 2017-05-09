package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1323.
 * 
 * @version 2017-05-09
 * @author Zhang Yufei.
 */
public class HihoCoder1323 {
	/**
	 * Input data.
	 */
	private static String string;

	/**
	 * Used for DP.
	 */
	private static int[][] dp;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		string = scan.next();
		scan.close();

		int length = string.length();

		dp = new int[length][length];

		for (int i = 0; i < length; i++) {
			dp[i][i] = 0;
		}

		for (int i = 1; i < length; i++) {
			for (int j = 0; j + i < length; j++) {
				int r1 = dp[j][j + i - 1] + 1;
				int r2 = dp[j + 1][j + i] + 1;
				int r3 = 0;
				if (string.charAt(j) != string
						.charAt(i + j)) {
					r3 = 1;
				}
				if (i > 1) {
					r3 += dp[j + 1][i + j - 1];
				}
				dp[j][j + i] = r1;
				if (dp[j][j + i] > r2) {
					dp[j][j + i] = r2;
				}
				if (dp[j][j + i] > r3) {
					dp[j][j + i] = r3;
				}

			}
		}

		System.out.println(dp[0][length - 1]);
	}

}
