package hihocoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1271.
 * 
 * @version 2017-02-19
 * @author Zhang Yufei.
 */
public class HihoCoder1271 {
	/**
	 * Used for input.
	 */
	private static Scanner scan;

	/**
	 * The input data.
	 */
	private static int N, M, T, S;

	/**
	 * plane list.
	 */
	private static ArrayList<Plane> planes, planesAir, planesShip;

	/**
	 * position list. This array will be used in DP.
	 */
	private static ArrayList<Position> positions;

	/**
	 * Record the result.
	 */
	private static int maxPower;
	private static boolean result;

	/**
	 * The class records the information of plane.
	 */
	static class Plane implements Comparable<Plane> {
		/**
		 * The input data.
		 */
		int B, C;

		@Override
		public int compareTo(Plane o) {
			// TODO Auto-generated method stub
			if (B != 0) {
				return B - o.B;
			} else if (C != 0) {
				return C - o.C;
			} else {
				return 0;
			}
		}
	}

	static class Position implements Comparable<Position> {
		/**
		 * The payload of this position.
		 */
		int payload;

		/**
		 * The ship number.
		 */
		int ship;

		@Override
		public int compareTo(Position o) {
			return this.payload - o.payload;
		}
	}

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The Command-line parameters list.
	 */
	public static void main(String[] args) {
		int Q;
		scan = new Scanner(System.in);
		Q = scan.nextInt();

		for (int i = 0; i < Q; i++) {
			function();
		}

		scan.close();
	}

	/**
	 * This function deals with one test case.
	 */
	private static void function() {
		input();
		compute();
		if (maxPower == -1) {
			System.out.println("Not Exist");
			return;
		}

		System.out.println(maxPower);

		if (result) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	/**
	 * This function deals with the data inputting in one test case.
	 */
	private static void input() {
		N = scan.nextInt();
		M = scan.nextInt();
		T = scan.nextInt();
		S = scan.nextInt();

		planes = new ArrayList<>(T);
		planesAir = new ArrayList<>(T);
		planesShip = new ArrayList<>(T);

		positions = new ArrayList<>(N * M);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Position p = new Position();
				p.ship = i;
				p.payload = scan.nextInt();
				positions.add(p);
			}
		}

		for (int i = 0; i < T; i++) {
			Plane p = new Plane();
			p.B = scan.nextInt();
			planes.add(p);
		}

		for (int i = 0; i < T; i++) {
			Plane p = planes.get(i);
			p.C = scan.nextInt();
		}

		for (int i = 0; i < T; i++) {
			Plane p = planes.get(i);
			if (p.B != 0) {
				planesAir.add(p);
			} else if (p.C != 0) {
				planesShip.add(p);
			}
		}

		Collections.sort(planesAir);
		Collections.sort(planesShip);
		Collections.sort(positions);
	}

	/**
	 * This function compute the result of the problem.
	 */
	private static void compute() {
		int range = (int) Math.pow(2, N * M);
		maxPower = -1;
		int power1, power2;

		for (int x = 0; x < range; x++) {
			int airCnt = planesAir.size() - 1;
			int shipCnt = planesShip.size() - 1;
			int positionCnt = positions.size() - 1;
			power1 = power2 = 0;
			int t = 1;
			while (positionCnt >= 0) {
				if ((x & t) != 0 && airCnt >= 0) {
					power1 += planesAir.get(airCnt).B
							* positions.get(positionCnt).payload;
					airCnt--;
				} else if(shipCnt >= 0){
					power2 += planesShip.get(shipCnt).C
							* positions.get(positionCnt).payload;
					shipCnt--;
				}
				positionCnt--;
				t <<= 1;
			}

			if (power1 >= S) {
				if (maxPower == -1 || maxPower < power2) {
					maxPower = power2;
					result = check(x);
				} else if(maxPower == power2) {
					if(!result) {
						result = check(x);
					}
				}
			}
		}
	}

	/**
	 * Checks if every ship has at least one ship-attack plane.
	 * 
	 * @param maxMatrix
	 *            The current matrix.
	 * @return Return <code>true</code> if every ship has at least ship-attack
	 *         plane, or return <code>false</code>.
	 */
	private static boolean check(int maxMatrix) {
		boolean[] tags = new boolean[N];
		for (int i = 0; i < N; i++) {
			tags[i] = false;
		}
		int shipCnt = 0;
		
		for (int i = 0; shipCnt < planesShip.size() && i < N * M; i++) {
			if ((maxMatrix & (1 << i)) == 0) {
				tags[positions.get(N * M - 1 - i).ship] = true;
				shipCnt++;
			}
		}

		for (int i = 0; i < N; i++) {
			if (tags[i] == false) {
				return false;
			}
		}

		return true;
	}
}
