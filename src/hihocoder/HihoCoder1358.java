package hihocoder;

import java.util.Scanner;
import static java.lang.Math.*;

/**
 * This is the ACM problem solving program for hihoCoder 1358.
 * 
 * @version 2016-08-20
 * @author Zhang Yufei
 */
public class HihoCoder1358 {
	// Input data.
	private static int N, K;
	private static int[] A, B;

	/*
	 * The matrix used in dp. dp[i] means the maximum result of C1^(1/B1) *
	 * C2^(1/B2) * ... * Ck^(1/Bk). given that there is i drugs, in the k-th
	 * iterates.
	 */
	private static double[] dp;

	/**
	 * The main program.
	 * 
	 * @param The
	 *            command line parameters.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		K = scan.nextInt();

		A = new int[K];
		B = new int[K];

		for (int i = 0; i < K; i++) {
			A[i] = scan.nextInt();
		}

		for (int i = 0; i < K; i++) {
			B[i] = scan.nextInt();
		}

		dp = new double[N + 1];

		for (int i = 0; i <= N; i++) {
			dp[i] = pow((double) (A[0] + i), (double) 1 / B[0]);
		}

		for (int i = 1; i < K; i++) {
			for (int j = N; j > 0; j--) {
				double max = dp[j] * pow((double) A[i], (double) 1 / B[i]);
				for (int k = 1; k <= j; k++) {
					double result = dp[j - k] * pow((double) (A[i] + k), (double) 1 / B[i]);
					if (max - result < -1e-6) {
						max = result;
					}
				}
				dp[j] = max;
			}

			dp[0] *= pow((double) A[i], (double) 1 / B[i]);
		}

		System.out.printf("%.3f\n", dp[N]);

		scan.close();
	}
}