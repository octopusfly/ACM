package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1324.
 * Result: WA(data overflow) -> AC.
 * 
 * @version 2017-05-10
 * @author Zhang Yufei.
 */
public class HihoCoder1324 {
	/**
	 * Input data.
	 */
	private static int n, x, y;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		x = scan.nextInt();
		y = scan.nextInt();
		scan.close();
		
		System.out.println(search((int) Math.pow(2, n), x,
				y));
	}

	/**
	 * Find the sequence number for position (x, y);
	 * 
	 * @param n
	 *            The size of the matrix.
	 * @param x
	 *            The x coordinate;
	 * @param y
	 *            The y coordinate;
	 * @return
	 */
	private static long search(int n, int x, int y) {
		if (n == 1) {
			return 1L;
		}

		if (x <= n / 2 && y <= n / 2) {
			return search(n / 2, y, x);
		} else if (x <= n / 2 && y > n / 2) {
			return (long) (n / 2) * (n / 2)
					+ search(n / 2, x, y - n / 2);
		} else if (x > n / 2 && y > n / 2) {
			return (long) 2 * (n / 2) * (n / 2)
					+ search(n / 2, x - n / 2, y - n / 2);
		} else {
			return (long) 3 * (n / 2) * (n / 2) +
					search(n / 2, n / 2 + 1 - y, n - x + 1);
		}
	}
}
