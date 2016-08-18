package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1357.
 * 
 * @version 2016-08-18
 * @author Zhang Yufei.
 */
public class HihoCoder1357 {
	// Input data.
	private static int N, M, K;
	private static int[] A;

	/**
	 * The main function
	 * 
	 * @param args
	 *            The command line parameters.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();
		K = scan.nextInt();

		A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = scan.nextInt();
		}

		long min = 1;
		long max = Integer.MAX_VALUE - 1;
		long ans = -1;

		while (true) {
			long mid = (min + max) / 2;
			int count = 0;
			int shield = M;
			for (int i = 0; i < N; i++) {
				if (A[i] >= shield) {
					count++;
					shield = M;
					continue;
				} else {
					shield -= A[i];
					shield += mid;
					if (shield > M) {
						shield = M;
					}
				}
			}

			if (count < K) {
				ans = mid;
			}
			
			if(min + 1 == max) {
				break;
			}
			
			if (count < K) {
				max = mid;
			} else {
				min = mid;
			}
			
		}

		System.out.println(ans);

		scan.close();
	}
}
