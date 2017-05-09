package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1289.
 * 
 * @version 2017-05-07
 * @author Zhang Yufei.
 */
public class HihoCoder1289 {
	/**
	 * Mark the rule.
	 */
	private static class Rule {
		boolean isAllowed;
		int time;
	};

	/**
	 * The node used in trie.
	 */
	private static class Node {
		Rule rule;
		Node[] nodes;

		Node() {
			rule = null;
			nodes = new Node[2];
		}
	}

	/**
	 * Save the trie structure.
	 */
	private static Node trie;

	/**
	 * The input data.
	 */
	private static int N, M;

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameter list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();

		// Input rules and construct the trie.
		trie = new Node();

		for (int i = 0; i < N; i++) {
			String rule = scan.next();
			String address = scan.next();
			addTrie(rule, address, i);
		}

		// Search.
		for (int i = 0; i < M; i++) {
			String address = scan.next();
			if (search(address)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

		scan.close();
	}

	/**
	 * add a new rule into trie.
	 * 
	 * @param rule
	 *            Allow or deny.
	 * @param address
	 *            The address in this rule.
	 * @param time
	 *            The time when this rule is added.
	 */
	private static void addTrie(String rule,
			String address, int time) {
		Rule r = new Rule();
		r.time = time;

		if (rule.charAt(0) == 'a') {
			r.isAllowed = true;
		} else {
			r.isAllowed = false;
		}

		char[] netId = getNetId(address).toCharArray();

		Node cur = trie;
		for (int i = 0; i < netId.length; i++) {
			int index = 0;
			if (netId[i] == '0') {
				index = 0;
			} else {
				index = 1;
			}

			if (cur.nodes[index] == null) {
				cur.nodes[index] = new Node();
			}

			cur = cur.nodes[index];
		}
		if(cur.rule == null) {
			cur.rule = r;
		}
	}

	/**
	 * Compute the net id in binary.
	 * 
	 * @param address
	 *            The address to compute.
	 * @return A string representing the net id of this address in binary.
	 */
	private static String getNetId(String address) {
		String[] array = address.split("[/|.]");
		int[] ip = new int[4];

		ip[0] = Integer.parseInt(array[0]);
		ip[1] = Integer.parseInt(array[1]);
		ip[2] = Integer.parseInt(array[2]);
		ip[3] = Integer.parseInt(array[3]);

		int mask = 32;
		if (array.length == 5) {
			mask = Integer.parseInt(array[4]);
		}

		StringBuilder builder = new StringBuilder();
		if (mask > 0) {
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int x = ip[i];
				int s = 128;
				for (int j = 0; j < 8; j++) {
					builder.append(x / s);
					x %= s;
					s /= 2;
					cnt++;
					if (cnt == mask) {
						break;
					}
				}

				if (cnt == mask) {
					break;
				}
			}
		}

		return builder.toString();
	}

	/**
	 * Search the trie to find if the given address is allowed.
	 * 
	 * @param address
	 *            The address to search.
	 * @return If the address is allowed, return <code>true</code>, or return
	 *         <code>false</code>.
	 */
	private static boolean search(String address) {
		char[] array = getNetId(address).toCharArray();

		Node cur = trie;

		boolean result = true;
		int time = Integer.MAX_VALUE;

		for (int i = 0; i < array.length; i++) {
			if (cur.rule != null) {
				if (cur.rule.time < time) {
					result = cur.rule.isAllowed;
					time = cur.rule.time;
				}
			}
			int index = 0;
			if (array[i] == '0') {
				index = 0;
			} else {
				index = 1;
			}

			if (cur.nodes[index] == null) {
				break;
			}
			cur = cur.nodes[index];
		}
		
		if(cur != null && cur.rule != null) {
			if (cur.rule.time < time) {
				result = cur.rule.isAllowed;
				time = cur.rule.time;
			}	
		}
		return result;
	}
}

