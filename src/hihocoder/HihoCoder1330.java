package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1330.
 * 
 * @version 2017-06-11
 * @author Zhang Yufei.
 */
public class HihoCoder1330 {
	/**
	 * Input data, the amount of numbers.
	 */
	private static int N;

	/**
	 * The number list.
	 */
	private static int[] num;

	/**
	 * Mark if the number has been visited.
	 */
	private static boolean[] visited;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();

		num = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			num[i] = scan.nextInt();
			num[i]--;
			visited[i] = false;
		}

		scan.close();
		
		int result = 1;

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			int count = 1;
			int j = i;
			while (num[j] != i) {
				visited[num[j]] = true;
				j = num[j];
				count++;
			}

			result = lcm(result, count);
//			System.out.println(count + " " + result);
		}
		
		System.out.println(result);
	}
	
	/**
	 * Compute the Least Common Multiple of 2 number <code>a</code> and
	 * <code>b</code>.
	 * 
	 * @param a
	 *            A number.
	 * @param b
	 *            Another number.
	 * @return The least common multiple of number <code>a</code> and
	 *         <code>b</code>
	 */
	private static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	/**
	 * Compute the Greatest Common Divisor of 2 number <code>a</code> and
	 * <code>b</code>.
	 * 
	 * @param a
	 *            A number.
	 * @param b
	 *            Another number.
	 * @return The greatest common divisor of number <code>a</code> and
	 *         <code>b</code>
	 */
	private static int gcd(int a, int b) {
		if (a < b) {
			return gcd(b, a);
		}

		if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}
}

