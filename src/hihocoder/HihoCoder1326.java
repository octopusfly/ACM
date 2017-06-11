package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1326.
 * 
 * @version 2017-05-29
 * @author Zhang Yufei.
 */
public class HihoCoder1326 {
	/**
	 * Input data;
	 */
	private static int T;

	/**
	 * For input.
	 */
	private static Scanner scan;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		T = scan.nextInt();

		for (int i = 0; i < T; i++) {
			function();
		}

		scan.close();
	}

	/**
	 * This function deal with one test case.
	 */
	private static void function() {
		String s = scan.next();
		char[] array = s.toCharArray();

		/*
		 * dp[1][i] means the number of 1 in sequence array[0...i]; 
		 * dp[0][i] means the number of 0 in sequence 
		 * 			array[i...array.length - 1].
		 */
		int[][] dp = new int[2][array.length];
		
		int cnt1 = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == '1') {
				cnt1++;
			}
			dp[1][i] = cnt1;
		}
		
		int cnt0 = 0;
		for(int i = array.length - 1; i >= 0; i--) {
			if(array[i] == '0') {
				cnt0++;
			}
			dp[0][i] = cnt0;
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < array.length - 1; i++) {
			if(result > dp[1][i] + dp[0][i + 1]) {
				result = dp[1][i] + dp[0][i + 1];
			}
		}
		
		if(result > dp[0][0]) {
			result = dp[0][0];
		}
		
		if(result > dp[1][array.length - 1]) {
			result = dp[1][array.length - 1];
		}
		
		System.out.println(result);
	}
}

