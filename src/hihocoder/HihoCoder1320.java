package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1320.
 * 
 * @version 2017-04-29
 * @author Zhang Yufei.
 */
public class HihoCoder1320 {
	/**
	 * Input Data.
	 */
	private static int T;

	/**
	 * Input string.
	 */
	private static String string;

	/**
	 * Used for dp.
	 */
	private static String[][] codes;

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
	 * Deals with one test case.
	 */
	private static void function() {
		string = scan.next();
		int length = string.length();
		codes = new String[length][length];

		for (int i = 0; i < length; i++) {
			codes[i][i] = "" + string.charAt(i);
		}

		for (int i = 1; i < length; i++) {
			for (int j = 0; j < length - i; j++) {
				int length1 = i + 1;

				int length2 = Integer.MAX_VALUE;
				int minIndex2 = -1;
				for (int k = j; k < i + j; k++) {
					int tmpLen = codes[j][k].length()
							+ codes[k + 1][i + j].length();
					if (length2 > tmpLen) {
						length2 = tmpLen;
						minIndex2 = k;
					}
				}

				int length3 = Integer.MAX_VALUE;
				int minIndex3 = -1;

				for (int subLen = 1; subLen < i + 1; subLen++) {
					if ((i + 1) % subLen != 0) {
						continue;
					}

					String substring = codes[j][j + subLen
							- 1];
					boolean tag = true;
					for (int k = j + subLen; k <= i + j; k += subLen) {
						if (!substring.equals(codes[k][k
								+ subLen - 1])) {
							tag = false;
							break;
						}
					}

					if (tag) {
						int digitLen = ("" + (i + 1)
								/ subLen).length();
						int tmpLen = digitLen
								+ substring.length() + 2;
						if (length3 > tmpLen) {
							length3 = tmpLen;
							minIndex3 = subLen;
						}
					}
				}

				if (length1 <= length2 && length1 <= length3) {
					StringBuilder builder = new StringBuilder();
					for (int k = j; k <= i + j; k++) {
						builder.append(string.charAt(k));
					}

					codes[j][i + j] = builder.toString();
				} else if (length2 <= length1
						&& length2 <= length3) {
					codes[j][i + j] = codes[j][minIndex2]
							+ codes[minIndex2 + 1][i + j];
				} else {
					int times = (i + 1) / minIndex3;
					codes[j][i + j] = times + "("
							+ codes[j][j + minIndex3 - 1]
							+ ")";
				}
			}
		}
		
		System.out.println(codes[0][length - 1].length());
//		System.out.println(codes[0][length - 1]);
	}
}
