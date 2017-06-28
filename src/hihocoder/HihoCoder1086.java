package hihocoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1086.
 * (Microsoft interview problem)
 * 
 * @version 2017-06-27
 * @author Zhang Yufei.
 */
public class HihoCoder1086 {
	/**
	 * Record the pages' information.
	 */
	private static class Page {
		/**
		 * The index in heap.
		 */
		int index;

		int visitingTime;
		String url;
	}

	/**
	 * Define heap.
	 */
	private static class Heap {
		Page[] pages;
		int heapSize;

		Heap(int size) {
			pages = new Page[size + 1];
			heapSize = 0;
		}

		void minHeapify(int index) {
			int min = pages[index].visitingTime;
			int minIndex = index;

			if (index * 2 <= heapSize
					&& min > pages[index * 2].visitingTime) {
				min = pages[index * 2].visitingTime;
				minIndex = 2 * index;
			}

			if (index * 2 + 1 <= heapSize
					&& min > pages[index * 2 + 1].visitingTime) {
				min = pages[index * 2 + 1].visitingTime;
				minIndex = 2 * index + 1;
			}

			if (minIndex != index) {
				Page tmp = pages[index];
				pages[index] = pages[minIndex];
				pages[minIndex] = tmp;
				
				pages[index].index = index;
				pages[minIndex].index = minIndex;

				minHeapify(minIndex);
			}
		}

		void addHeap(Page page) {
			heapSize++;
			pages[heapSize] = page;
			pages[heapSize].index = heapSize;

			int index = heapSize;
			while (index > 1
					&& pages[index].visitingTime < pages[index / 2].visitingTime) {
				Page tmp = pages[index];
				pages[index] = pages[index / 2];
				pages[index / 2] = tmp;
				pages[index].index = index;
				pages[index / 2].index = index / 2;

				index /= 2;
			}
		}

		Page delHeap() {
			Page toDel = pages[1];
			pages[1] = pages[heapSize];
			pages[1].index = 1;
			heapSize--;

			minHeapify(1);

			return toDel;
		}

		void updateHeap(int index) {
			minHeapify(index);

			while (index > 1
					&& pages[index].visitingTime < pages[index / 2].visitingTime) {
				Page tmp = pages[index];
				pages[index] = pages[index / 2];
				pages[index / 2] = tmp;
				pages[index].index = index;
				pages[index / 2].index = index / 2;

				index /= 2;
			}
		}
	}

	/**
	 * Define the hash map.
	 */
	private static HashMap<String, Page> map;
	
	/**
	 * The input.
	 */
	private static Scanner scan;

	/**
	 * The output.
	 */
	private static PrintWriter writer;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		initIO();
		
		int N = scan.nextInt();
		int M = scan.nextInt();
		
		Heap heap = new Heap(M);
		map = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			String url = scan.next();
			if(map.containsKey(url)) {
				Page page = map.get(url);
				page.visitingTime = i;
				heap.updateHeap(page.index);
				
				writer.println("Cache");
			} else {
				if(heap.heapSize == M) {
					Page del = heap.delHeap();
					map.remove(del.url);
				}
				
				Page page = new Page();
				page.url = url;
				page.visitingTime = i;
				
				map.put(url, page);
				heap.addHeap(page);
				
				writer.println("Internet");
			}
		}
		
		scan.close();
		writer.close();
	}

	/**
	 * Initial the input and output.
	 */
	private static void initIO() {
		try {
			scan = new Scanner(System.in);
			// scan = new Scanner(new File(
			// "E:\\workspace\\ACM\\src\\data.txt"));
			// writer = new PrintWriter(new File(
			// "E:\\workspace\\ACM\\src\\test.txt"));
			writer = new PrintWriter(System.out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

