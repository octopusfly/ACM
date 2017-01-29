package hihocoder;

import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1445.
 * 
 * @version 2016-12-24
 * @author Zhang Yufei
 * 
 * Submit: MLE. (AC using C++).
 */
public class HihoCoder1445 {
	private static int[] longest;
	private static int[] shortest;
	private static int[] suffixLink;
	private static int[][] transfer;
	
	private static int stateCount = -1;
	
	private static int length;
	
    /**
     * The main program.
     * 
     * @param args
     *            The command-line parameters list.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String string = scan.next();
        scan.close();

        char[] stringArray = string.toCharArray();
        
        length = stringArray.length;
        
        init();
        
        stateCount++;
        
        int pre = 0;
        long sum = 0;
        for (int i = 0; i < length; i++) {
        	stateCount++;
        	int current = stateCount;
        	longest[current] = i + 1;
        	
        	int j = pre;
        	while(j != -1) {
        		if(transfer[j][stringArray[i] - 'a'] == -1) {
        			transfer[j][stringArray[i] - 'a'] = stateCount;
        		} else {
        			break;
        		}
        		
        		j = suffixLink[j];
        	}
        	
        	if(j == -1) {
        		shortest[current] = 1;
        		suffixLink[current] = 0;
        	} else {
        		int x = transfer[j][stringArray[i] - 'a'];
        		if(longest[j] + 1 == longest[x]) {
        			shortest[current] = longest[x] + 1;
        			suffixLink[current] = x;
        		} else {
        			stateCount++;
        			int split = stateCount;
        			shortest[split] = shortest[x];
        			longest[split] = longest[j] + 1;
        			suffixLink[split] = suffixLink[x];
        			
        			for(int k = 0; k < 26; k++) {
        				transfer[split][k] = transfer[x][k];
        			}
        			
        			shortest[x] = longest[j] + 2;
        			suffixLink[x] = split;
        			
        			while(j != -1) {
        				if(transfer[j][stringArray[i] - 'a'] == x) {
        					transfer[j][stringArray[i] - 'a'] = split;
        				} else {
        					break;
        				}
        				
        				j = suffixLink[j];
        			}
        			
        			shortest[current] = longest[split] + 1;
        			suffixLink[current] = split;
        		}
        	}
        	
        	sum += longest[current] - shortest[current] + 1;
        	pre = current;
        }
        
        System.out.println(sum);
    }
    
    /**
     * Used to initial parameters.
     */
    private static void init() {
    	longest = new int[2 * length + 10];
    	shortest = new int[2 * length + 10];
    	suffixLink = new int[2 * length + 10];
    	transfer = new int[2 * length + 10][26];
    	for(int i = 0; i < 2 * length + 10; i++) {
    		shortest[i] = longest[i] = 0;
    		suffixLink[i] = -1;
    		for(int j = 0; j < 26; j++) {
    			transfer[i][j] = -1;
    		}
    	}
    }
}
