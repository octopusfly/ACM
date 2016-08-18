
import static java.lang.Math.*;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1237.
 * 
 * @version 2016-08-18
 * @author Zhang Yufei.
 */
public class Main {
	// input data.
	private static double x, y;
	private static double r;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command line parameters.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		x = scan.nextDouble();
		y = scan.nextDouble();
		r = scan.nextDouble();

		double max = -1;
		int max_x = -1, max_y = -1;
		double eps = 1e-6;

		for (int i = (int) floor(x + r); i >= (int) ceil(x - r); i--) {
			int top = (int) floor(y + sqrt(pow(r, 2) - pow(i - x, 2)));
			int bottom = (int) ceil(y - sqrt(pow(r, 2) - pow(i - x, 2)));

			double dist_top = pow(i - x, 2) + pow(top - y, 2);
			double dist_bottom = pow(i - x, 2) + pow(bottom - y, 2);

			if (max - dist_top < -eps) {
				max = dist_top;
				max_x = i;
				max_y = top;
			}

			if (max - dist_bottom < -eps) {
				max = dist_bottom;
				max_x = i;
				max_y = bottom;
			}
		}

		System.out.println(max_x + " " + max_y);

		scan.close();
	}
}
