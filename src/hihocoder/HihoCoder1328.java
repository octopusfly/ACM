package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1328.
 * 
 * @version 2017-06-10
 * @author Zhang Yufei.
 */
public class HihoCoder1328 {
	/**
	 * Record the room information.
	 */
	private static class Room {
		/**
		 * The key to open this room (for locked room).
		 */
		int keyToOpen;

		/**
		 * The keys in this room.
		 */
		int keysInRoom;

		/**
		 * The coordinate of this room.
		 */
		int row, column;
	}

	private static class HeapNode {
		/**
		 * The room of this node.
		 */
		Room room;

		/**
		 * The key state.
		 */
		int key;

		/**
		 * The shortest distance to the destination.
		 */
		int distance;

		/**
		 * The index in heap.
		 */
		int index;
	}

	/**
	 * The heap used in dijkstra algorithm.
	 */
	private static class Heap {
		HeapNode[] nodes;
		int heapSize;

		public Heap(int capacity) {
			nodes = new HeapNode[capacity + 1];
			heapSize = 0;
		}

		/**
		 * Check if the current heap is empty.
		 * 
		 * @return If the heap is empty returns <code>true</code>, or returns
		 *         false.
		 */
		public boolean isEmpty() {
			return heapSize == 0;
		}

		/**
		 * Base function for operations in heap.
		 * 
		 * @param index
		 *            The current node to deal with.
		 */
		private void minHeapify(int index) {
			int min = nodes[index].distance;
			int minIndex = index;

			if (2 * index <= heapSize) {
				if (nodes[2 * index].distance < min) {
					min = nodes[2 * index].distance;
					minIndex = 2 * index;
				}
			}

			if (2 * index + 1 <= heapSize) {
				if (nodes[2 * index + 1].distance < min) {
					min = nodes[2 * index + 1].distance;
					minIndex = 2 * index + 1;
				}
			}

			if (minIndex != index) {
				HeapNode swap = nodes[index];
				nodes[index] = nodes[minIndex];
				nodes[minIndex] = swap;

				nodes[index].index = index;
				;
				nodes[minIndex].index = minIndex;

				minHeapify(minIndex);
			}
		}

		/**
		 * Add a new room into heap.
		 * 
		 * @param room
		 *            The room to add.
		 */
		public void addNode(HeapNode node) {
			heapSize++;
			nodes[heapSize] = node;
			nodes[heapSize].index = heapSize;

			int index = heapSize;
			while (index > 1) {
				if (nodes[index / 2].distance > nodes[index].distance) {
					HeapNode swap = nodes[index];
					nodes[index] = nodes[index / 2];
					nodes[index / 2] = swap;

					nodes[index].index = index;
					nodes[index / 2].index = index / 2;

					index /= 2;
				} else {
					break;
				}
			}
		}

		/**
		 * Remove the top element of this heap.
		 * 
		 * @return
		 */
		public HeapNode delTopNode() {
			HeapNode toDel = nodes[1];
			nodes[1] = nodes[heapSize];
			nodes[1].index = 1;
			nodes[heapSize] = null;

			heapSize--;
			if (heapSize > 0) {
				minHeapify(1);
			}

			return toDel;
		}

		/**
		 * Update the distance of the given room.
		 * 
		 * @param index
		 *            The index in heap of this room.
		 * @param newValue
		 *            The new distance value.
		 */
		public void updateNode(int index) {
			minHeapify(index);

			while (index > 1) {
				if (nodes[index / 2].distance > nodes[index].distance) {
					HeapNode swap = nodes[index];
					nodes[index] = nodes[index / 2];
					nodes[index / 2] = swap;

					nodes[index].index = index;
					nodes[index / 2].index = index / 2;

					index /= 2;
				} else {
					break;
				}
			}
		}
	}

	/**
	 * The input data;
	 */
	private static int N, M, K, a, b, c, d;

	/**
	 * All the rooms in the maze.
	 */
	private static Room[][] rooms;

	/**
	 * Each room in different key state.
	 */
	private static HeapNode[][][] nodes;

	/**
	 * The heap used in dijkstra algorithm.
	 */
	private static Heap heap;

	/**
	 * The number of different key state.
	 */
	private static int statesNum;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		input();

		heap = new Heap((int) (N * M * statesNum));

		for (int k = 0; k < statesNum; k++) {
			if (rooms[c][d].keyToOpen != 0
					&& (k & rooms[c][d].keyToOpen) != 0
					|| rooms[c][d].keyToOpen == 0) {
				nodes[c][d][k].distance = 0;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < statesNum; k++) {
					heap.addNode(nodes[i][j][k]);
				}
			}
		}

		dijkstra();

//		for (int k = 0; k < statesNum; k++) {
//			System.out.println("key: " + k);
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out
//							.print(nodes[i][j][k].distance
//									+ " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		int result = nodes[a][b][0].distance;
		if(result == Integer.MAX_VALUE) {
			result = -1;
		}
		System.out
				.println(result);
	}

	/**
	 * This function deal with the data inputting.
	 */
	private static void input() {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		K = scan.nextInt();
		a = scan.nextInt();
		b = scan.nextInt();
		c = scan.nextInt();
		d = scan.nextInt();

		statesNum = (int) Math.pow(2, K);

		rooms = new Room[N][M];
		nodes = new HeapNode[N][M][statesNum];

		for (int i = 0; i < N; i++) {
			char[] array = scan.next().toCharArray();
			for (int j = 0; j < M; j++) {
				rooms[i][j] = new Room();
				int keyToOpen = 0;
				if (array[j] == '#') {
					keyToOpen = 32;
				} else if (array[j] == '.') {
					keyToOpen = 0;
				} else {
					keyToOpen = 1 << (array[j] - 'A');
				}
				rooms[i][j].keyToOpen = keyToOpen;
				rooms[i][j].keysInRoom = 0;
				rooms[i][j].row = i;
				rooms[i][j].column = j;

				for (int k = 0; k < statesNum; k++) {
					nodes[i][j][k] = new HeapNode();
					nodes[i][j][k].room = rooms[i][j];
					nodes[i][j][k].distance = Integer.MAX_VALUE;
					nodes[i][j][k].index = -1;
					nodes[i][j][k].key = k;
				}
			}
		}

		int keyInRoom = 1;
		for (int i = 0; i < K; i++) {
			int row = scan.nextInt();
			int column = scan.nextInt();

			rooms[row][column].keysInRoom |= keyInRoom;

			keyInRoom <<= 1;
		}

		scan.close();
	}

	/**
	 * Compute the shortest path to the destination room for each room.
	 * 
	 * @param key
	 *            The current key.
	 */
	private static void dijkstra() {
		while (!heap.isEmpty()) {
			HeapNode node = heap.delTopNode();
			Room room = node.room;

			if (node.distance == Integer.MAX_VALUE) {
				continue;
			}

			int row = room.row;
			int column = room.column;
			if (room.keyToOpen == 0
					|| (room.keyToOpen & node.key) != 0) {
				if (row + 1 < N) {
					int key = node.key
							& (~rooms[row + 1][column].keysInRoom);
					if (nodes[row + 1][column][key].distance > node.distance + 1) {
						nodes[row + 1][column][key].distance = node.distance + 1;
						heap.updateNode(nodes[row + 1][column][key].index);
					}
				}

				if (row - 1 >= 0) {
					int key = node.key
							& (~rooms[row - 1][column].keysInRoom);
					if (nodes[row - 1][column][key].distance > node.distance + 1) {
						nodes[row - 1][column][key].distance = node.distance + 1;
						heap.updateNode(nodes[row - 1][column][key].index);
					}
				}

				if (column + 1 < M) {
					int key = node.key
							& (~rooms[row][column + 1].keysInRoom);
					if (nodes[row][column + 1][key].distance > node.distance + 1) {
						nodes[row][column + 1][key].distance = node.distance + 1;
						heap.updateNode(nodes[row][column + 1][key].index);
					}
				}

				if (column - 1 >= 0) {
					int key = node.key
							& (~rooms[row][column - 1].keysInRoom);
					if (nodes[row][column - 1][key].distance > node.distance + 1) {
						nodes[row][column - 1][key].distance = node.distance + 1;
						heap.updateNode(nodes[row][column - 1][key].index);
					}
				}
			}
		}
	}
}
