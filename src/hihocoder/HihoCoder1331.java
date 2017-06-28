package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1331.
 * 
 * @version 2017-06-12
 * @author Zhang Yufei.
 */
public class HihoCoder1331 {
	/**
	 * Input data.
	 */
	private static int N;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		scan.close();

		while (N > 0 && N % 2 == 1) {
			N /= 2;
		}
		int cnt0 = 0;
		int cnt1 = 0;

		while (N > 0 && N % 2 == 0) {
			N /= 2;
			cnt0++;
		}

		while (N > 0 && N % 2 == 1) {
			cnt1++;
			N /= 2;
		}

		int result, result0, result1;
		result = cnt0 * cnt1 + 1;
		result0 = cnt0;
		result1 = cnt0 * (cnt1 - 1) + 1;
		while (N > 0) {
			cnt0 = 0;
			while (N > 0 && N % 2 == 0) {
				cnt0++;
				N /= 2;
			}
			cnt1 = 0;
			while (N > 0 && N % 2 == 1) {
				cnt1++;
				N /= 2;
			}

			int newResult = result0
					* (cnt1 * (cnt0 + 1) + 1) + result1
					* (cnt1 * cnt0 + 1);
			int newResult0 = result0 * (cnt0 + 1)
					+ result1 * cnt0;
			int newResult1 = result0
					* ((cnt1 - 1) * (cnt0 + 1) + 1)
					+ result1 * ((cnt1 - 1) * cnt0 + 1);

			result = newResult;
			result0 = newResult0;
			result1 = newResult1;
		}
		System.out.println(result);
	}
}

