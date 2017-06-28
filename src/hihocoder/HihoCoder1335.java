package hihocoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is the ACM problem solving program for hihoCoder 1335.
 * Result: RE.
 * @version 2017-06-13
 * @author Zhang Yufei.
 */
public class HihoCoder1335 {
	private static class Type {
		Set<Person> set;
	}

	private static class Person implements
			Comparable<Person> {
		int id;
		String name;
		boolean visited;
		Type type;

		@Override
		public int compareTo(Person o) {
			if (o == null) {
				return 1;
			}
			return id - o.id;
		}
	}

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();

		Person[] persons = new Person[N];

		Map<String, Set<Person>> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			persons[i] = new Person();
			persons[i].id = i;
			persons[i].name = scan.next();
			persons[i].visited = false;
			persons[i].type = new Type();

			Set<Person> set = new TreeSet<>();
			int mailsCnt = scan.nextInt();

			for (int j = 0; j < mailsCnt; j++) {
				String mail = scan.next();
				if (map.containsKey(mail)) {
					Set<Person> s = map.get(mail);
					for (Person p : s) {
						set.addAll(p.type.set);
						break;
					}
					s.add(persons[i]);
				} else {
					Set<Person> s = new TreeSet<>();
					s.add(persons[i]);
					map.put(mail, s);
				}
			}

			for (Person p : set) {
				p.type = persons[i].type;
			}

			set.add(persons[i]);

			persons[i].type.set = set;
		}

		scan.close();

		for (int i = 0; i < N; i++) {
			if (!persons[i].visited) {
				for (Person p : persons[i].type.set) {
					p.visited = true;
					System.out.print(p.name + " ");
				}
				System.out.println();
			}
		}
	}
}

