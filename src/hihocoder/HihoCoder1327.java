package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1327.
 * 
 * @version 2017-05-31
 * @author Zhang Yufei.
 */
public class HihoCoder1327 {
	/**
	 * Record appearance number of every letter in given string. These nodes
	 * will be organized in a heap.
	 */
	private static class Node {
		char ch;
		int count;

		/**
		 * The index of this node in heap.
		 */
		int index;

		public Node(char ch) {
			this.ch = ch;
			count = 0;
			index = 0;
		}
	}

	private static class Heap {
		private Node[] heap;
		int heapSize;

		public Heap() {
			heap = new Node[27];
			heapSize = 0;
		}

		private void maxHeapify(int index) {
			int max = heap[index].count;
			int maxIndex = index;

			if (2 * index <= heapSize
					&& max < heap[2 * index].count) {
				max = heap[2 * index].count;
				maxIndex = 2 * index;
			}

			if (2 * index + 1 <= heapSize
					&& max < heap[2 * index + 1].count) {
				max = heap[2 * index + 1].count;
				maxIndex = 2 * index + 1;
			}

			if (maxIndex != index) {
				Node tmp = heap[index];
				heap[index] = heap[maxIndex];
				heap[maxIndex] = tmp;
				heap[index].index = index;
				heap[maxIndex].index = maxIndex;

				maxHeapify(maxIndex);
			}
		}

		public void addHeap(Node toAdd) {
			heapSize++;
			heap[heapSize] = toAdd;
			heap[heapSize].index = heapSize;

			int index = heapSize;
			while (index > 1) {
				if (heap[index / 2].count < heap[index].count) {
					Node tmp = heap[index];
					heap[index] = heap[index / 2];
					heap[index / 2] = tmp;
					heap[index].index = index;
					heap[index / 2].index = index / 2;

					index /= 2;
				} else {
					break;
				}
			}
		}

		public void updateHeap(int index) {
			maxHeapify(index);
			while (index > 1) {
				if (heap[index / 2].count < heap[index].count) {
					Node tmp = heap[index];
					heap[index] = heap[index / 2];
					heap[index / 2] = tmp;
					heap[index].index = index;
					heap[index / 2].index = index / 2;

					index /= 2;
				} else {
					break;
				}
			}
		}

		public Node getTop() {
			return heap[1];
		}
	}

	/**
	 * Record node for every letter 'a'-'z'.
	 */
	private static Node[] nodes;

	/**
	 * Input data;
	 */
	private static String S;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		S = scan.next();
		scan.close();

		char[] array = S.toCharArray();

		nodes = new Node[26];
		for (int i = 0; i < 26; i++) {
			nodes[i] = new Node((char) ('a' + i));
		}

		for (int i = 0; i < array.length; i++) {
			nodes[array[i] - 'a'].count++;
		}

		Heap heap = new Heap();
		for (int i = 0; i < 26; i++) {
			if (nodes[i].count > 0) {
				heap.addHeap(nodes[i]);
			}
		}

		char pre = 0;
		int length = array.length;

		if (heap.getTop().count > (length + 1) / 2) {
			System.out.println("INVALID");
			return;
		}

		StringBuilder builder = new StringBuilder();
		while (true) {
			boolean result = false;
			for (int i = 0; i < 26; i++) {
				if (nodes[i].ch == pre) {
					continue;
				}
				if (nodes[i].count > 0) {
					nodes[i].count--;
					heap.updateHeap(nodes[i].index);
					length--;

					if (heap.heapSize == 0
							|| heap.getTop().count <= (length + 1) / 2) {
						result = true;
						builder.append(nodes[i].ch);
						pre = nodes[i].ch;
						break;
					} else {
						nodes[i].count++;
						length++;
						heap.updateHeap(nodes[i].index);
					}
				}
			}

			if (!result) {
				if (heap.getTop().count > 0) {
					builder = new StringBuilder("INVALID");
				}
				break;
			}
		}

		System.out.println(builder);
	}
}
