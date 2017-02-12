package hihocoder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This is the ACM problem solving program for hihoCoder 1441.
 * 
 * @version 2016-12-16
 * @author Zhang Yufei
 */
public class HihoCoder1441 {
    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters list.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();

        // Construct the SAM.
        HashMap<String, Long> tags = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String s = str.substring(i, j + 1);
                if (tags.containsKey(s)) {
                    long value = tags.get(s);
                    value = value | (1L << j);
                    tags.put(s, value);
                } else {
                    tags.put(s, 1L << j);
                }
            }
        }

        HashMap<Long, State> sam = new HashMap<>();
        Set<String> substrings = tags.keySet();
        for (String s : substrings) {
            long value = tags.get(s);
            if (sam.containsKey(value)) {
                State state = sam.get(value);
                state.addString(s);
            } else {
                State state = new State();
                state.addString(s);
                sam.put(value, state);
            }
        }

       
        // Start search.
        int N = scan.nextInt();

        for (int i = 0; i < N; i++) {
            String search = scan.next();
            long value = tags.get(search);
            State state = sam.get(value);
            if (state != null) {
                System.out.print(state.shortest + " " + state.longest + " ");
                int cnt = 1;
                while (value > 0) {
                    if (value % 2 == 1) {
                        System.out.print(cnt + " ");
                    }
                    value /= 2;
                    cnt++;
                }
                
                System.out.println();
            }
        }
        
        scan.close();
    }
}

class State {
    HashSet<String> set;
    String shortest;
    String longest;

    State() {
        set = new HashSet<>();
    }

    void addString(String str) {
        set.add(str);
        if (shortest == null || shortest.length() > str.length()) {
            shortest = str;
        }
        if (longest == null || longest.length() < str.length()) {
            longest = str;
        }
    }
}