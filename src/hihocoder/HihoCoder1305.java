package hihocoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1305.
 * 
 * @version 2017-04-25
 * @author Zhang Yufei.
 */
public class HihoCoder1305 {
	/**
	 * Range class, using linked list as store space.
	 */
	private static class Range {
		int A, B;
		
		public boolean isInRange(int data) {
			return data >= A && data <= B;
		}
	}

	/**
	 * The comparator for <code> Range </code> node.
	 */
	private static class RangeComparator implements
			Comparator<Range> {
		@Override
		public int compare(Range o1, Range o2) {
			if (o1.A <= o2.A) {
				return -1;
			} else if (o1.A == o2.A) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	/**
	 * Input data.
	 */
	private static int N, M;

	/**
	 * Range List
	 */
	private static Range[] listA, listB;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		// Input.
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		listA = new Range[N];
		listB = new Range[M];
		
		for(int i = 0; i < N; i++) {
			listA[i] = new Range();
			listA[i].A = scan.nextInt();
			listA[i].B = scan.nextInt();
		}
		
		for(int i = 0; i < M; i++) {
			listB[i] = new Range();
			listB[i].A = scan.nextInt();
			listB[i].B = scan.nextInt();
		}
		
		scan.close();
		
		// range adjust.
		RangeComparator comparator = new RangeComparator();
		Arrays.sort(listA, comparator);
		Arrays.sort(listB, comparator);
		
		int indexA = 0;
		for(int i = 1; i < N; i++) {
			if(listA[indexA].isInRange(listA[i].A)) {
				listA[indexA].B = listA[indexA].B > listA[i].B ? 
						listA[indexA].B : listA[i].B;
			} else {
				indexA++;
				listA[indexA].A = listA[i].A;
				listA[indexA].B = listA[i].B;
			}
		}
		N = indexA + 1;

		int indexB = 0;
		for(int i = 1; i < M; i++) {
			if(listB[indexB].isInRange(listB[i].A)) {
				listB[indexB].B = listB[indexB].B > listB[i].B ? 
						listB[indexB].B : listB[i].B;
			} else {
				indexB++;
				listB[indexB].A = listB[i].A;
				listB[indexB].B = listB[i].B;
			}
		}
		M = indexB + 1;
		
		// compute.
		indexA = indexB = 0;
		int sub = 0;
		while(indexA < N && indexB < M) {
			if(listB[indexB].isInRange(listA[indexA].A)
				&& listB[indexB].isInRange(listA[indexA].B)) {
				listA[indexA].B = listA[indexA].A;
				indexA++;
			} else if(listB[indexB].isInRange(listA[indexA].A)) {
				listA[indexA].A = listB[indexB].B;
				indexB++;
			} else if(listB[indexB].isInRange(listA[indexA].B)) {
				listA[indexA].B = listB[indexB].A;
				indexA++;
			} else if(listB[indexB].B < listA[indexA].A) {
				indexB++;
			} else if(listB[indexB].A > listA[indexA].B) {
				indexA++;
			} else {
				sub += listB[indexB].B -= listB[indexB].A;
				indexB++;
			}
		}
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			sum += listA[i].B - listA[i].A;
		}
		
		System.out.println(sum - sub);
	}
}
