import java.util.Scanner;
import java.util.Stack;

/**
 * This is the ACM problem solving program for hihoCoder 1449
 * 
 * @version 2016-12-31
 * @author Zhang Yufei
 */
public class Main {
	/**
	 * The length of the input string.
	 */
	private static int length;

	/**
	 * The input string.
	 */
	private static char[] stringArray;

	/**
	 * Data used in SAM.
	 */
	private static int[] longest;
	private static int[] shortest;
	private static int[] suffixLink;
	private static int[] endpos;
	private static int[][] transfer;
	
	private static int stateCount;

	/**
	 * Used to search.
	 */
	private static Stack<Integer> stack;

	/**
	 * Used to record the result.
	 */
	private static int[] result;

	/**
	 * The main program.
	 * 
	 * @param arg
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		stringArray = scan.next().toCharArray();
		length = stringArray.length;
		scan.close();

		init();
		buildSAM();

		for (int i = stateCount; i >= 0; i--) {
			if (! stack.contains(i)) {
				search(i);
			}
		}
		if(2 * length + 10 < stack.size()) {
			System.out.println("0");
		}
		while (! stack.isEmpty()) {
			int state = stack.pop();
			result[longest[state]] = result[longest[state]] > endpos[state] ?
					result[longest[state]] : endpos[state];

			if (suffixLink[state] != -1) {
				endpos[suffixLink[state]] += endpos[state];
			}
		}

		for (int i = length - 1; i >= 0; i--) {
			result[i] = result[i] > result[i + 1] ? result[i]
					: result[i + 1];
		}

		print();
	}

	/**
	 * This function used to build SAM according to the input string.
	 */
	private static void buildSAM() {
		stateCount = -1;

		int init = (++stateCount);

		int pre = stateCount;

		for (int i = 0; i < length; i++) {
			int current = (++stateCount);
			longest[current] = i + 1;
			endpos[current] = 1;

			int j = pre;
			while (j >= 0) {
				if (transfer[j][stringArray[i] - 'a'] == -1) {
					transfer[j][stringArray[i] - 'a'] = current;
				} else {
					break;
				}
				j = suffixLink[j];
			}

			if (j == -1) {
				shortest[current] = 1;
				suffixLink[current] = init;
			} else {
				int x = transfer[j][stringArray[i] - 'a'];
				if (longest[j] + 1 == longest[x]) {
					shortest[current] = longest[x] + 1;
					suffixLink[current] = x;
				} else {
					int splitState = (++stateCount);

					copyTransfer(x, splitState);

					longest[splitState] = longest[j] + 1;
					shortest[splitState] = shortest[x];
					shortest[x] = longest[j] + 2;

					suffixLink[splitState] = suffixLink[x];
					suffixLink[x] = splitState;

					endpos[splitState] = 0;

					while (j >= 0) {
						if (transfer[j][stringArray[i] - 'a'] == x) {
							transfer[j][stringArray[i] - 'a'] = splitState;
						} else {
							break;
						}
						j = suffixLink[j];
					}

					shortest[current] = longest[splitState] + 1;
					suffixLink[current] = splitState;
				}
			}

			pre = current;
		}
	}

	/**
	 * Some initial operation.
	 */
	private static void init() {
		longest = new int[2 * length + 10];
		shortest = new int[2 * length + 10];
		suffixLink = new int[2 * length + 10];
		endpos = new int[2 * length + 10];
		transfer = new int[2 * length + 10][26];
		result = new int[length + 1];
		stack = new Stack<>();

		for (int i = 0; i < 2 * length + 10; i++) {
			longest[i] = shortest[i] = 0;
			suffixLink[i] = -1;
			endpos[i] = 0;
			for (int j = 0; j < 26; j++) {
				transfer[i][j] = -1;
			}
		}
		
		for(int i = 0; i <= length; i++) {
			result[i] = 0;
		}
	}

	/**
	 * Split a state node into a new nodes, copying the transfer function into
	 * new node.
	 * 
	 * @param org
	 *            The original state.
	 * @param split
	 *            The new state node.
	 */
	private static void copyTransfer(int org,
			int split) {
		for (int i = 0; i < 26; i++) {
			transfer[split][i] = transfer[org][i];
		}
	}

	/**
	 * Used to search the result (topo-sort).
	 * 
	 * @param index
	 *            The start node.
	 */
	private static void search(int index) {
		if (!stack.contains(index)) {
			if (suffixLink[index] != -1) {
				search(suffixLink[index]);
			}
			stack.push(index);
		}
	}

	/**
	 * Print the result;
	 */
	private static void print() {
		for (int i = 1; i <= length; i++) {
			System.out.println(result[i]);
		}
	}
}

// C++ code:
//#include<stdio.h>
//#include<string.h>
//
//const int max = 1000000;
//
//int length;
//
//char stringArray[max + 1];
//
//int longest[2 * max + 10];
//int shortest[2 * max + 10];
//int suffixLink[2 * max + 10];
//int endpos[2 * max + 10];
//char visited[2 * max + 10];
//int transfer[2 * max + 10][26];
//
//int stateCount = -1;
//
//int stack[2 * max + 10];
//int top = -1;
//
//int result[max + 1];
//
//void init() {
//	for (int i = 0; i < 2 * max + 10; i++) {
//		longest[i] = shortest[i] = 0;
//		suffixLink[i] = -1;
//		endpos[i] = 0;
//		visited[i] = 0;
//		for (int j = 0; j < 26; j++) {
//			transfer[i][j] = -1;
//		}
//	}
//	
//	for(int i = 0; i <= max; i++) {
//		result[i] = 0;
//	}
//}
//
//void copyTransfer(int org, int split) {
//	for (int i = 0; i < 26; i++) {
//		transfer[split][i] = transfer[org][i];
//	}
//}
//
///**
// * Used to search the result (topo-sort).
// * 
// * @param index
// *            The start node.
// */
//void search(int index) {
//	if (! visited[index]) {
//		if (suffixLink[index] != -1) {
//			search(suffixLink[index]);
//		}
//		stack[++top] = index;
//		visited[index] = 1;
//	}
//}
//
///**
// * Print the result;
// */
//void print() {
//	for (int i = 1; i <= length; i++) {
//		printf("%d\n", result[i]);
//	}
//}
//
//void buildSAM() {
//	stateCount = -1;
//
//	int init = (++stateCount);
//
//	int pre = stateCount;
//
//	for (int i = 0; i < length; i++) {
//		int current = (++stateCount);
//		longest[current] = i + 1;
//		endpos[current] = 1;
//
//		int j = pre;
//		while (j >= 0) {
//			if (transfer[j][stringArray[i] - 'a'] == -1) {
//				transfer[j][stringArray[i] - 'a'] = current;
//			} else {
//				break;
//			}
//			j = suffixLink[j];
//		}
//
//		if (j == -1) {
//			shortest[current] = 1;
//			suffixLink[current] = init;
//		} else {
//			int x = transfer[j][stringArray[i] - 'a'];
//			if (longest[j] + 1 == longest[x]) {
//				shortest[current] = longest[x] + 1;
//				suffixLink[current] = x;
//			} else {
//				int splitState = (++stateCount);
//
//				copyTransfer(x, splitState);
//
//				longest[splitState] = longest[j] + 1;
//				shortest[splitState] = shortest[x];
//				shortest[x] = longest[j] + 2;
//
//				suffixLink[splitState] = suffixLink[x];
//				suffixLink[x] = splitState;
//
//				endpos[splitState] = 0;
//
//				while (j >= 0) {
//					if (transfer[j][stringArray[i] - 'a'] == x) {
//						transfer[j][stringArray[i] - 'a'] = splitState;
//					} else {
//						break;
//					}
//					j = suffixLink[j];
//				}
//
//				shortest[current] = longest[splitState] + 1;
//				suffixLink[current] = splitState;
//			}
//		}
//
//		pre = current;
//	}
//}
//
//int main(void) {
//	scanf("%s", stringArray);
//	length = strlen(stringArray);
//
//	init();
//	buildSAM();
//
//	for (int i = stateCount; i >= 0; i--) {
//		if (!visited[i]) {
//			search(i);
//		}
//	}
//
//	while (top != -1) {
//		int state = stack[top--];
//		result[longest[state]] = result[longest[state]] > endpos[state] ?
//				result[longest[state]] : endpos[state];
//
//		if (suffixLink[state] != -1) {
//			endpos[suffixLink[state]] += endpos[state];
//		}
//	}
//
//	for (int i = length - 1; i >= 0; i--) {
//		result[i] = result[i] > result[i + 1] ? result[i]
//				: result[i + 1];
//	}
//
//	print();
//}
//
