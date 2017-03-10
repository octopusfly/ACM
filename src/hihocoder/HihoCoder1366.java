package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1366
 * 
 * @version 2017-03-09
 * @author Zhang Yufei
 */
public class HihoCoder1366 {
	/**
	 * Input data.
	 */
	private static int N;

	/**
	 * The root node of trie.
	 */
	private static Node root;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();

		int count = 0;
		root = new Node();
		for (int i = 0; i < N; i++) {
			String string = scan.next();
			char[] array = string.toCharArray();

			if (search(array)) {
				count++;
			}

			insert(array);
		}
		
		System.out.println(count);
		
		scan.close();
	}

	/**
	 * Find the reverse word in trie.
	 * 
	 * @param array
	 *            The word to search.
	 * @return If there is a reverse words in the trie, returns
	 *         <code>true</code>, or <code>false</code>
	 */
	private static boolean search(char[] array) {
		Node n = root;
		for (int i = array.length - 1; i >= 0; i--) {
			char ch = array[i];
			Node next = n.childs[ch - 'a'];
			if (next == null) {
				return false;
			}
			n = next;
		}

		if (n.isWord) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Insert a new word into trie.
	 * 
	 * @param array
	 *            The word to insert.
	 */
	private static void insert(char[] array) {
		Node n = root;
		for(char ch : array) {
			Node next = n.childs[ch - 'a'];
			if(next == null) {
				next = new Node();
				n.childs[ch - 'a'] = next;
			}
			n = next;
		}
		
		n.isWord = true;
	}
}

/**
 * Record the node in trie.
 */
class Node {
	Node[] childs;
	boolean isWord;

	public Node() {
		childs = new Node[26];
		isWord = false;
	}
}
