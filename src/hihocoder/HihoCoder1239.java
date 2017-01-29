package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1239.
 * 
 * @version 2016-08-28
 * @author Zhang Yufei
 */
public class HihoCoder1239 {
	/*
	 * The mod value.
	 */
	private static final int MOD = 1000000007;

	/*
	 * Record the fibonacci array.
	 */
	private static int[] fibonacci = new int[26];

	/*
	 * The array used in dp.
	 */
	private static int[] dp = new int[26];

	/**
	 * The main program.
	 */
	public static void main(String[] args) {
		/*
		 * Compute the fibonacci list.
		 */
		fibonacci[1] = fibonacci[2] = 1;

		for (int i = 3; i < 26; i++) {
			fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
		}

		/*
		 * Init DP array.
		 */
		dp[0] = 1;

		/*
		 * Input and compute result.
		 */

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			int a = scan.nextInt();
			int index = check(a);
			if(index != -1) {
				if(index == 1) {
					dp[2] = (dp[2] + dp[1]) % MOD;
					dp[1] = (dp[1] + 1) % MOD;
				} else {
					dp[index] = (dp[index] + dp[index - 1]) % MOD;
				}
			}
		}
		
		int sum = 0;
		for(int i = 1; i < 26; i++) {
			sum = (sum + dp[i]) % MOD;
		}
		
		System.out.println(sum);
		
		scan.close();
	}

	/**
	 * This function checks if the value x is the element in fibonacci list.
	 * 
	 * @param x
	 *            The value to check.
	 * @return If <b>x</b> is an element in fibonacci, returns the index in the
	 *         fibonacci, or returns -1.
	 */
	private static int check(int x) {
		for (int i = 1; i <= 25; i++) {
			if (fibonacci[i] == x) {
				return i;
			}
		}

		return -1;
	}
}