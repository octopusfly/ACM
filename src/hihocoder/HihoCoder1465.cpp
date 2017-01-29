/****************************************************/
/* File        : HihoCoder????                      */
/* Author      : Zhang Yufei                        */
/* Date        : 2017-01-07                         */
/* Description : HihoCoder ACM program. (submit:g++)*/
/****************************************************/

#include<stdio.h>
#include<string.h>

// The max length of input string.
const int MAX = 100000;

// The max length of the sum of comparing strings.
const int MAX_C = 100000;

/*
 * Define the state in SAM.
 * Fields:
 *		@visited: Mark if the state has been visited.
 *		@count: The number of the substrings in this state.
 *		@suffixLink: The suffix link of this state.
 *		@transfer: The transfer function.
 *		@maxlen: The maximum length of substring in this state.
 *		@minLen: The minimum length of substring in this state.
 */
typedef struct node {
	int visited;
	int count;
	int suffixLink;
	int transfer[26];
	int maxlen;
	int minlen;
} state;

// Define the SAM.
state sam[2 * MAX + 10];

// State count.
int stateCount;

// Input data.
char S[MAX + 1];
char str[MAX_C * 2 + 1];
int N;
int SLen;
int strLen;

// Define stack for topo-sort.
int stack[2 * MAX + 1];
int top = -1;

// The tag used in getAnswer;
int tag;

/*
 * This function do some initialing works before input.
 * Parameters:
 *		None.
 * Returns:
 *		None.
 */
void init(void) {
	for(int i = 0; i < 2 * MAX + 10; i++) {
		state *s = &sam[i];
		s->visited = 0;
		s->count = 0;
		s->suffixLink = -1;
		s->maxlen = s->minlen = 0;
		for(int i = 0; i < 26; i++) {
			s->transfer[i] = -1;
		}
	}	
	
	stateCount = -1;
}

/*
 * Build the SAM according to the input string.
 * Parameters:
 *		None.
 * Returns:
 *		None.
 */
void buildSAM(void) {
	SLen = strlen(S);
	
	int initState = (++stateCount);	
	int pre = initState;
	
	for(int i = 0; i < SLen; i++) {
		stateCount++;
		int current = stateCount;
		sam[current].count = 1;
		sam[current].maxlen = i + 1;
		
		int c = pre;
		while(c != -1 && sam[c].transfer[S[i] - 'a'] == -1) {
			sam[c].transfer[S[i] - 'a'] = current;
			c = sam[c].suffixLink;
		}	
		
		if(c == -1) {										// case 1.
			sam[current].minlen = 1;
			sam[current].suffixLink = initState;
		} else {
			int x = sam[c].transfer[S[i] - 'a'];
			if(sam[c].maxlen + 1 == sam[x].maxlen) { 		// case 2.
				sam[current].minlen = sam[x].maxlen + 1;
				sam[current].suffixLink = x;
			} else {										// case 3.
				int splitState = (++stateCount);
				sam[splitState].count = 0;
				sam[splitState].suffixLink = sam[x].suffixLink;
				sam[splitState].maxlen = sam[c].maxlen + 1;
				sam[splitState].minlen = sam[x].minlen;
				
				for(int j = 0; j < 26; j++) {
					sam[splitState].transfer[j] = sam[x].transfer[j];
				}
				
				sam[x].minlen = sam[splitState].maxlen + 1;
				sam[x].suffixLink = splitState;
				
				while(c != -1 && sam[c].transfer[S[i] - 'a'] == x) {
					sam[c].transfer[S[i] - 'a'] = splitState;
					c = sam[c].suffixLink;
				}
				
				sam[current].suffixLink = splitState;
				sam[current].minlen = sam[splitState].maxlen + 1;
			}
		}
		pre = current;
	}
}

/*
 * Depth First Search in SAM.
 * Parameters:
 *		@index: The current state to search.
 * Returns:
 *		None.
 */
void dfs(int index) {
	if(sam[index].visited == 0) {
		if(sam[index].suffixLink != -1) {
				dfs(sam[index].suffixLink);
		}
		
		sam[index].visited = 1;
		top++;
		stack[top] = index;
	}
}

/*
 * Compute the value @count in each state of the SAM by topo-sort.
 * Parameters:
 *		None.
 * Returns:
 *		None.
 */
void getCount(void) {
	for(int i = 0; i <= stateCount; i++) {
		if(sam[i].visited == 0) {
			dfs(i);
		}
	}
	
	while(top != -1) {
		int s = stack[top];
		top--;
		
		if(sam[s].suffixLink != -1) {
			sam[sam[s].suffixLink].count += sam[s].count;
		}
	}	
}

/*
 * Compute the answer according to the input @str.
 * Parameters:
 *		None.
 * Returns:
 *		The answer.
 */
long long getAnswer(void) {
	int u = 0;
	int l = 0;
	
	long long answer = 0;
	for(int i = 0; i < 2 * strLen - 1; i++) {
		while(u != -1 && sam[u].transfer[str[i] - 'a'] == -1) {
			u = sam[u].suffixLink;
			l = sam[u].maxlen;
		}
		
		if(u == -1) {
			u = 0;
			l = 0;
		} else {
			l++;
			u = sam[u].transfer[str[i] - 'a'];
			if(l >= strLen) {
				while(sam[u].suffixLink != -1 && 
					sam[sam[u].suffixLink].maxlen >= strLen) {
					u = sam[u].suffixLink;
					l = sam[u].maxlen;
				}
				
				if(sam[u].visited != tag) {
					answer += sam[u].count;
					sam[u].visited = tag;
				}
			}
		}
	}
	
	return answer;
}

/*
 * The main program.
 * Parameters:
 *		None.
 * Returns:
 *		If the program finished successfully, returns 0.
 */
int main(void) {
	init();
	
	scanf("%s", S);	
	buildSAM();
	getCount();
	
	scanf("%d", &N);
	
	tag = 2;
	for(int i = 0; i < N; i++) {
		scanf("%s", str);
		strLen = strlen(str);
		strncpy(str + strLen, str, strLen);
		str[2 * strLen - 1] = '\0';
		
		printf("%lld\n", getAnswer());
		tag++;
	}
	
	return 0;
}
