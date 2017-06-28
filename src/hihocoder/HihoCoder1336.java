package hihocoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1336.
 * Result:TLE
 * @version 2017-06-15
 * @author Zhang Yufei.
 */
public class HihoCoder1336 {
	/**
	 * Record the update operation.
	 */
	private static class UpdateRecord {
		int x, y;
		int value;
	}

	/**
	 * The node of each layer, recording the sum value and the range.
	 */
	private static class Node {
		int value;
		Map<String, UpdateRecord> records;
	}

	/**
	 * Using multi-layer to record the SUM value of different range. The top
	 * layer contains only one element, and the i-th layer will record the sum
	 * value of different range in (i+1)-th layer.
	 */
	private static class Layer {
		int N;
		Node[][] matrix;
	}

	/**
	 * The constant for MOD.
	 */
	private static final int MOD = 1000000007;

	/**
	 * Input data, the size of matrix and the number of operation.
	 */
	private static int N, M;

	/**
	 * The layers, the bottom layer is the original matrix.
	 */
	private static Layer[] layers;

	/**
	 * The number of layer.
	 */
	private static int layerCnt;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();

		init();

		for (int i = 0; i < M; i++) {
			String opera = scan.next();
			if (opera.equals("Add")) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				int value = scan.nextInt();
				set(x, y, value);
			} else if (opera.equals("Sum")) {
				int x1 = scan.nextInt();
				int y1 = scan.nextInt();
				int x2 = scan.nextInt();
				int y2 = scan.nextInt();
				System.out.println(query(x1, y1, x2, y2,
						layerCnt - 1, 0, 0));
			}
		}

		scan.close();
	}

	private static void init() {
		layerCnt = (int) (Math.ceil(Math.log(N)
				/ Math.log(2)) + 1);
		layers = new Layer[layerCnt];

		layers[0] = new Layer();
		layers[0].N = N;
		layers[0].matrix = new Node[layers[0].N][layers[0].N];

		for (int i = 1; i < layerCnt; i++) {
			layers[i] = new Layer();
			layers[i].N = (int) Math
					.ceil((double) layers[i - 1].N / 2.0);
			layers[i].matrix = new Node[layers[i].N][layers[i].N];
		}
	}

	private static void set(int x, int y, int value) {
		initNode(layerCnt - 1, 0, 0);

		UpdateRecord record = new UpdateRecord();
		record.x = x;
		record.y = y;
		record.value = value;

		addRecord(layers[layerCnt - 1].matrix[0][0], record);
	}

	private static int query(int x1, int y1, int x2,
			int y2, int layer, int row, int column) {
		Node node = layers[layer].matrix[row][column];

		int startRow = (int) (row * Math.pow(2, layer));
		int startColumn = (int) (column * Math
				.pow(2, layer));
		int endRow = (int) (startRow + Math.pow(2,
				layer) - 1);
		int endColumn = (int) (startColumn + Math.pow(2,
				layer) - 1);

		if (startRow > x2 || endRow < x1
				|| startColumn > y2 || endColumn < y1) {
			return 0;
		} else if (node == null) {
			return 0;
		} else {
			if (!node.records.isEmpty()) {
				for (UpdateRecord record : node.records
						.values()) {
					long addValue = (long) node.value
							+ record.value;
					if (addValue < 0) {
						addValue += MOD;
					}
					addValue %= MOD;
					node.value = (int) addValue;
					if (layer > 0) {
						int r = (int) (record.x / Math.pow(
								2, layer - 1));
						int c = (int) (record.y / Math.pow(
								2, layer - 1));
						initNode(layer - 1, r, c);
						addRecord(
								layers[layer - 1].matrix[r][c],
								record);
					}
				}

				node.records.clear();
			}

			if (startRow >= x1 && endRow <= x2
					&& startColumn >= y1 && endColumn <= y2) {
				return node.value;
			} else {
				long result = 0;
				for (int r = row * 2; r <= row * 2 + 1
						&& r < layers[layer - 1].N; r++) {
					for (int c = column * 2; c <= column * 2 + 1
							&& c < layers[layer - 1].N; c++) {
						result += query(x1, y1, x2, y2,
								layer - 1, r, c);
						if (result < 0) {
							result += MOD;
						}
						result %= MOD;
					}
				}

				return (int) result;
			}
		}
	}

	private static void initNode(int layer, int row,
			int column) {
		if (layers[layer].matrix[row][column] == null) {
			layers[layer].matrix[row][column] = new Node();
			layers[layer].matrix[row][column].records = new HashMap<>();
			layers[layer].matrix[row][column].value = 0;
		}
	}

	private static void addRecord(Node node,
			UpdateRecord record) {
		int x = record.x;
		int y = record.y;
		int value = record.value;
		if (node.records.containsKey(x + "#" + y)) {
			UpdateRecord r = node.records.get(x + "#" + y);
			long addValue = (long) r.value + value;
			if (addValue < 0) {
				addValue += MOD;
			}
			addValue %= MOD;
			r.value = (int) addValue;
		} else {
			if (record.value < 0) {
				record.value += MOD;
			}
			record.value %= MOD;
			node.records.put(x + "#" + y, record);
		}
	}
}

