package hihocoder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1306.
 * 
 * @version 2017-04-25
 * @author Zhang Yufei.
 */
public class HihoCoder1306 {
	/**
	 * Record the information of each stock;
	 */
	private static class Stock {
		public static final int MAX = 1;
		public static final int MIN = 2;

		int minIndex, maxIndex;
		int price;
		int timestamp;

		public void setIndex(int index, int tag) {
			if (tag == MAX) {
				maxIndex = index;
			} else if (tag == MIN) {
				minIndex = index;
			}
		}
	}

	/**
	 * The heap used in this problem.
	 */
	private static class StockHeap {
		List<Stock> heap;
		Comparator<Stock> comparator;
		int tag;

		public StockHeap(Comparator<Stock> comparator,
				int tag) {
			this.comparator = comparator;
			this.tag = tag;
			heap = new ArrayList<>();
		}

		private void minHeapify(int index) {
			Stock min = heap.get(index);
			int minIndex = index;

			if (2 * index + 1 < heap.size()) {
				if (comparator.compare(min,
						heap.get(2 * index + 1)) > 0) {
					min = heap.get(2 * index + 1);
					minIndex = 2 * index + 1;
				}
			}

			if (2 * index + 2 < heap.size()) {
				if (comparator.compare(min,
						heap.get(2 * index + 2)) > 0) {
					min = heap.get(2 * index + 2);
					minIndex = 2 * index + 2;
				}
			}

			if (minIndex != index) {
				Stock tmp = heap.get(index);
				heap.set(index, heap.get(minIndex));
				heap.set(minIndex, tmp);

				heap.get(index).setIndex(index, tag);
				heap.get(minIndex).setIndex(minIndex, tag);

				minHeapify(minIndex);
			}
		}

		public void addHeap(Stock stock) {
			heap.add(stock);
			stock.setIndex(heap.size() - 1, tag);

			int index = heap.size() - 1;
			while (index > 0) {
				int parent = (index - 1) / 2;

				if (comparator.compare(heap.get(parent),
						heap.get(index)) <= 0) {
					break;
				}

				Stock tmp = heap.get(index);
				heap.set(index, heap.get(parent));
				heap.set(parent, tmp);

				heap.get(index).setIndex(index, tag);
				heap.get(parent).setIndex(parent, tag);

				index = parent;
			}
		}

		public void deleteHeap(int index) {
			heap.set(index, heap.get(heap.size() - 1));
			heap.remove(heap.size() - 1);
			if(index < heap.size()) {
				heap.get(index).setIndex(index, tag);
				minHeapify(index);
			}
		}

		public Stock getTop() {
			return heap.get(0);
		}
	}
	
	private static class StockQueue {
		private static class QueueNode {
			Stock stock;
			QueueNode next;
		}
		
		private QueueNode front;
		private QueueNode rear;
		
		public StockQueue() {
			front = new QueueNode();
			rear = front;
		}
		
		public void addQueue(Stock stock) {
			QueueNode node = new QueueNode();
			node.stock = stock;
			
			rear.next = node;
			rear = node;
		}
		
		public void deleteQueue() {
			QueueNode toDelete = front.next;
			front.next = toDelete.next;
		}
		
		public Stock getFront() {
			if(front.next == null) {
				return null;
			}
			return front.next.stock;
		}
		
		public Stock getRear() {
			if(front == rear) {
				return null;
			}
			
			return rear.stock;
		}
	}

	/**
	 * Input data.
	 */
	private static int N;

	/**
	 * Stock list.
	 */
	private static Map<Integer, Stock> stockMap;

	/**
	 * Stock heap.
	 */
	private static StockHeap maxHeap, minHeap;
	
	/**
	 * Stock queue.
	 */
	private static StockQueue queue;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();

		stockMap = new HashMap<>();

		maxHeap = new StockHeap(new Comparator<Stock>() {
			@Override
			public int compare(Stock o1, Stock o2) {
				if (o1.price > o2.price) {
					return -1;
				} else if (o1.price < o2.price) {
					return 1;
				} else {
					return 0;
				}
			}
		}, Stock.MAX);

		minHeap = new StockHeap(new Comparator<Stock>() {
			@Override
			public int compare(Stock o1, Stock o2) {
				if (o1.price > o2.price) {
					return 1;
				} else if (o1.price < o2.price) {
					return -1;
				} else {
					return 0;
				}
			}
		}, Stock.MIN);

		queue = new StockQueue();
		
		for (int i = 0; i < N; i++) {
			String operation = scan.next();
			if (operation.equals("P")) {
				int timestamp = scan.nextInt();

				Stock stock = new Stock();
				stock.price = scan.nextInt();
				stock.timestamp = timestamp;

				stockMap.put(timestamp, stock);

				maxHeap.addHeap(stock);
				minHeap.addHeap(stock);
				
				queue.addQueue(stock);
			} else if (operation.equals("R")) {
				int timestamp = scan.nextInt();
				while(queue.getFront().timestamp <= timestamp) {
					Stock stock = queue.getFront();
					minHeap.deleteHeap(stock.minIndex);
					maxHeap.deleteHeap(stock.maxIndex);
					queue.deleteQueue();
				}
			} else if (operation.equals("Q")) {
				System.out.println(maxHeap.getTop().price
						+ " " + minHeap.getTop().price
						+ " " + queue.getRear().price);
			} else {
				System.out.println("Error!");
			}
		}
		scan.close();
	}
}
