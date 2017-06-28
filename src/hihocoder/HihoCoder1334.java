package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1334.
 * 
 * @version 2017-06-13
 * @author Zhang Yufei.
 */
public class HihoCoder1334 {
	/**
	 * Input data, The number of words.
	 */
	private static int N;

	/**
	 * The code list, the i-th code mark the letters the i-th word contains,
	 * using the lower 26 bits of an integer to mark the existance of every 26
	 * words.
	 */
	private static int[] codes;

	/**
	 * Record the result;
	 */
	private static int max;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();

		codes = new int[N];

		for (int i = 0; i < N; i++) {
			codes[i] = setCode(scan.next());
		}

		scan.close();

		max = Integer.MIN_VALUE;

		search(0, 0, 0);

		System.out.println(max);
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start + 1));
	}

	/**
	 * Convert the word into code.
	 * 
	 * @param word
	 *            The word.
	 * @return The code integer which mark if every letter is exist in this
	 *         word.
	 */
	private static int setCode(String word) {
		int code = 0;
		for (int i = 0; i < word.length(); i++) {
			code |= 1 << word.charAt(i) - 'a';
		}

		return code;
	}

	/**
	 * Search the maximum word we can choose to ensure every 2 chosen word
	 * dosen't share every letter.
	 * 
	 * @param index
	 *            The index of the code in current iterator.
	 * @param code
	 *            The code of all words which has been chosen. Its value is the
	 *            or result of the codes of every chosen words.
	 * @param count
	 *            The number of chosen words.
	 */
	private static void search(int index, int code,
			int count) {
		if (max < count) {
			max = count;
		}
		if (index == N) {
			return;
		}
		search(index + 1, code, count);
		if ((codes[index] & code) == 0) {
			search(index + 1, code | codes[index],
					count + 1);
		}
	}
}

