package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1318.
 * 
 * @version 2017-04-29
 * @author Zhang Yufei.
 */
public class HihoCoder1318 {
	/**
	 * Mod Constant.
	 */
	private static final int MOD = 1000000007;

	/**
	 * Used for dp.
	 */
	private static int[][] dp;

	/**
	 * Used for dp.
	 */
	private static int[] dp2;

	/**
	 * Input data;
	 */
	private static int n;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The Command line parameters list.
	 */
	public static void main(String[] args) {
		dp = new int[101][2];
		dp2 = new int[101];
		
		dp[1][0] = 1;
		dp[1][1] = 1;
		
		for(int i = 2; i <= 100; i++) {
			long data = (long) dp[i - 1][0] + dp[i - 1][1];
			data %= MOD;
			dp[i][0] = (int) data;
			dp[i][1] = dp[i - 1][0];
		}
		
		dp2[1] = 0;
		for(int i = 2; i <= 100; i++) {
			long data = (long) dp2[i - 1] * 2 + dp[i - 1][1];
			data %= MOD;
			dp2[i] = (int) data;
		}
		
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		scan.close();
		
		System.out.println(dp2[n]);
	}
}
