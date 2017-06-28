package hihocoder;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1339.
 * 
 * @version 2017-06-19
 * @author Zhang Yufei.
 */
public class HihoCoder1339 {
	/**
	 * The input.
	 */
	private static Scanner scan;

	/**
	 * The output.
	 */
	private static PrintWriter writer;

	/**
	 * Input data. The amount of the dices
	 * and the sum value.
	 */
	private static int N, M;
	
	/**
	 * Used for DP.
	 */
	private static double[] dp;
	
	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		initIO();

		N = scan.nextInt();
		M = scan.nextInt();
		
		dp = new double[6 * N + 1];
		
		for(int i = 0; i < 6 * N + 1; i++) {
			dp[i] = 0.0;
		}
		
		dp[0] = 1.0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 6 * N; j >= 0; j--) {
				dp[j] = 0.0;
				for(int k = 1; k <= 6; k++) {
					if(j - k >= 0) {
						dp[j] += dp[j - k];
					}
				}
				dp[j] /= 6.0;
			}
		}
		
		writer.printf("%.2f\n", dp[M] * 100);
		
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

