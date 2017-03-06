package hihocoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1273
 * 
 * @version 2017-03-04
 * @author Zhang Yufei
 */
public class HihoCoder1273 {
	/**
	 * Input data.
	 */
	private static int N;

	/**
	 * The poster list.
	 */
	private static Poster[] posters;

	/**
	 * Record the result.
	 */
	private static int maxNum, maxIndex;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		scan.nextInt();
		scan.nextInt();
		N = scan.nextInt();

		posters = new Poster[N];

		for (int i = 0; i < N; i++) {
			int x1 = scan.nextInt();
			int y1 = scan.nextInt();
			int x2 = scan.nextInt();
			int y2 = scan.nextInt();

			posters[i] = new Poster(x1, y1, x2, y2);
			posters[i].index = i + 1;

			for (int j = 0; j < i; j++) {
				cover(posters[i], posters[j]);
			}
		}

		scan.close();

		maxNum = -1;
		maxIndex = -1;

		for (int i = 0; i < N; i++) {
		// if (posters[i].visited == -1) {
			compute(posters[i], i);
		// }
		}

		System.out.println(maxNum + " " + maxIndex);
	}

	/**
	 * Check if the poster <code>p1</code> has covered the poster
	 * <code> p2 </code> and update the information of these two posters.
	 * 
	 * @param p1
	 *            a poster.
	 * @param p2
	 *            Another poster.
	 */
	private static void cover(Poster p1, Poster p2) {
		if (p1.x1 <= p2.x1 && p1.x2 > p2.x1 && p1.y1 <= p2.y1 && p1.y2 > p2.y1) {
			p2.isCovered[0] = true;
		}

		if (p1.x1 <= p2.x1 && p1.x2 > p2.x1 && p1.y1 < p2.y2 && p1.y2 >= p2.y2) {
			p2.isCovered[1] = true;
		}

		if (p1.x1 < p2.x2 && p1.x2 >= p2.x2 && p1.y1 <= p2.y1 && p1.y2 > p2.y1) {
			p2.isCovered[2] = true;
		}

		if (p1.x1 < p2.x2 && p1.x2 >= p2.x2 && p1.y1 < p2.y2 && p1.y2 >= p2.y2) {
			p2.isCovered[3] = true;
		}

		if (!(p1.x2 <= p2.x1 || p2.x2 <= p1.x1 || p1.y2 <= p2.y1 || p2.y2 <= p1.y1)) {
			p2.aboves.add(p1);
		}
	}

	/**
	 * Compute the number of posters will be pulled when the given poster is
	 * been pulled, using DFS compute the result.
	 * 
	 * @param p
	 *            The current poster.
	 * @param tag
	 *            Mark if the poster has been visited in this iterater.
	 * @return The number of posters will be pulled.
	 */
	public static int compute(Poster p, int tag) {
		int count = 1;
		p.visited = tag;
		for (Poster poster : p.aboves) {
			count += compute(poster, tag);
		}

		if (p.isCovered[0] && p.isCovered[1] && p.isCovered[2]
				&& p.isCovered[3]) {
			return count;
		}

		if (maxNum == -1 || maxNum < count) {
			maxNum = count;
			maxIndex = p.index;
		} else if (maxNum == count) {
			if (maxIndex > p.index) {
				maxIndex = p.index;
			}
		}

		return count;
	}
}

/**
 * Record the poster information.
 */
class Poster {
	/**
	 * The position of the poster.
	 */
	int x1, y1, x2, y2;

	/**
	 * All the posters covered on this poster.
	 */
	ArrayList<Poster> aboves;

	/**
	 * Mark if the angle has been covered by other poster.
	 */
	boolean[] isCovered;

	/**
	 * Used in DPS, mark if the node has been visited.
	 */
	int visited;

	/**
	 * The id of this poster.
	 */
	int index;

	Poster(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		aboves = new ArrayList<>();
		isCovered = new boolean[] { false, false, false, false };
		visited = -1;
		index = -1;
	}
}

/*
 * Testing case: 
 * Input: 
 * 		10 10 5
 * 		2 3 5 6
 * 		0 1 3 7
 * 		4 1 7 7
 * 		2 0 5 2
 * 		6 3 8 6 
 * Output:
 * 		3 3
 */
