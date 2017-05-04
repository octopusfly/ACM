package hihocoder;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1513.
 * 
 * @version 2017-04-25
 * @author Zhang Yufei.
 */
public class HihoCoder1513 {
	private static class Grade {
		int chinese;
		int math;
		int english;
		int physics;
		int chemistry;

		int id;
		BitSet mask;
		
		public Grade(int id) {
			this.id = id;
			mask = new BitSet(N);
			mask.set(0, N, true);
		}
		
	}
	
	/**
	 * The input data.
	 */
	private static int N;

	/**
	 * The grades list.
	 */
	private static Grade[] grades;

	/**
	 * The comparator for grade.
	 */
	private static Comparator<Grade> comparator1;
	private static Comparator<Grade> comparator2;
	private static Comparator<Grade> comparator3;
	private static Comparator<Grade> comparator4;
	private static Comparator<Grade> comparator5;
	private static Comparator<Grade> comparator6;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		// Input
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();

		grades = new Grade[N];

		for (int i = 0; i < N; i++) {
			grades[i] = new Grade(i);
			grades[i].chinese = scan.nextInt();
			grades[i].math = scan.nextInt();
			grades[i].english = scan.nextInt();
			grades[i].physics = scan.nextInt();
			grades[i].chemistry = scan.nextInt();
		}

		scan.close();
		
		// init comparators
		comparator1 = new Comparator<Grade>() {
			@Override
			public int compare(Grade o1, Grade o2) {
				if (o1.chinese > o2.chinese) {
					return 1;
				} else if(o1.chinese < o2.chinese) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		comparator2 = new Comparator<Grade>() {
			@Override
			public int compare(Grade o1, Grade o2) {
				if (o1.math > o2.math) {
					return 1;
				} else if(o1.math < o2.math) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		comparator3 = new Comparator<Grade>() {
			@Override
			public int compare(Grade o1, Grade o2) {
				if (o1.english > o2.english) {
					return 1;
				} else if(o1.english < o2.english) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		comparator4 = new Comparator<Grade>() {
			@Override
			public int compare(Grade o1, Grade o2) {
				if (o1.physics > o2.physics) {
					return 1;
				} else if(o1.physics < o2.physics) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		comparator5 = new Comparator<Grade>() {
			@Override
			public int compare(Grade o1, Grade o2) {
				if (o1.chemistry > o2.chemistry) {
					return 1;
				} else if(o1.chemistry < o2.chemistry) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		comparator6 = new Comparator<Grade>() {
			@Override
			public int compare(Grade o1, Grade o2) {
				if (o1.id > o2.id) {
					return 1;
				} else if(o1.id < o2.id) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		// compute
		BitSet mask = new BitSet(N);

		mask.clear();
		Arrays.sort(grades, comparator1);
		for(int i = 0; i < N; i++) {
			grades[i].mask.and(mask);
			mask.set(grades[i].id, true);
		}
		
		mask.clear();
		Arrays.sort(grades, comparator2);
		for(int i = 0; i < N; i++) {
			grades[i].mask.and(mask);
			mask.set(grades[i].id, true);
		}
		
		mask.clear();
		Arrays.sort(grades, comparator3);
		for(int i = 0; i < N; i++) {
			grades[i].mask.and(mask);
			mask.set(grades[i].id, true);
		}
		
		mask.clear();
		Arrays.sort(grades, comparator4);
		for(int i = 0; i < N; i++) {
			grades[i].mask.and(mask);
			mask.set(grades[i].id, true);
		}
		
		mask.clear();
		Arrays.sort(grades, comparator5);
		for(int i = 0; i < N; i++) {
			grades[i].mask.and(mask);
			mask.set(grades[i].id, true);
		}
		
		// print
		Arrays.sort(grades, comparator6);
		for(int i = 0; i < N; i++) {
			System.out.println(grades[i].mask.cardinality());
		}
	}
}
