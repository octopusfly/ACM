package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1473
 * 
 * @version 2017-03-10
 * @author Zhang Yufei
 */
public class HihoCoder1473 {
	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line Parameters.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int T = scan.nextInt();

		for (int i = 0; i < T; i++) {
			int L = scan.nextInt();
			int F = scan.nextInt();
			int D = scan.nextInt();

			if (gcd(L, D) >= F) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		
		scan.close();
	}

	/**
	 * Compute the greatest common division.
	 * 
	 * @param a
	 *            A number.
	 * @param b
	 *            Another number.
	 * @return The greatest common division of <code>a</code> and <code>b</code>
	 *         .
	 */
	private static int gcd(int a, int b) {
		if (a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}

		if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}
}

