#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

typedef struct node1 {
	int isAllowed;
	int time;
} rule;

typedef struct node2 {
	struct node2* nodes[2];
	rule* r;
} trieNode;

int N, M;
trieNode *trie;

trieNode* initTrieNode() {
	trieNode *n = (trieNode*) malloc(sizeof(trieNode));
	n->nodes[0] = n->nodes[1] = NULL;
	n->r = NULL;
	
	return n; 
}

char* getBinary(char* address) {
	int ip[5];
	ip[4] = 32;
	
	int index = 0;
	int length = strlen(address);
	int num = 0;
	for(int i = 0; i < length; i++) {
		if(address[i] == '.' || address[i] == '/') {
			ip[index] = num;
			index++;
			num = 0;
		} else if(isdigit(address[i])){
			num *= 10;
			num += address[i] - '0';
		}
	}
	
	ip[index] = num;
	char *result = (char*) malloc(sizeof(char) * 33);
	int rIndex = 0;
	int mask = ip[4];
	if(mask > 0) {
		for(int i = 0; i < 4; i++) {
			int s = 128;
			int x = ip[i];
			for(int i = 0; i < 8; i++) {
				result[rIndex++] = '0' + x / s;
				x %= s;
				s /= 2;	
				if(rIndex == mask) {
					break;
				}
			}
			
			if(rIndex == mask) {
				break;
			}
		}
	}
	result[rIndex] = '\0';
	return result;	
}

void addTrie(char *command, char *address, int time) {
	rule *r = (rule*) malloc(sizeof(rule));
	if(command[0] == 'a') {
		r->isAllowed = 1;
	} else {
		r->isAllowed = 0;
	}
	r->time = time;		
	
	trieNode *cur = trie;
	
	char *binary = getBinary(address);
	int length = strlen(binary);
	
	for(int i = 0; i < length; i++) {
		int index;
		if(binary[i] == '1') {
			index = 1;
		} else {
			index = 0;
		}
		
		if(cur->nodes[index] == NULL) {
			cur->nodes[index] = initTrieNode();
		}
		cur = cur->nodes[index];
	}
	
	if(cur->r == NULL) {
		cur->r = r;
	} else {
		free(r);
	}
}

int search(char *address) {
	char* binary = getBinary(address);
	int length = strlen(binary);
	
	trieNode *cur = trie;
	
	int result = 1;
	int time = -1;
	for(int i = 0; i < length; i++) {
		if(cur->r != NULL) {
			if(time == -1 || cur->r->time < time) {
				time = cur->r->time;
				result = cur->r->isAllowed;
			}
		}
		int index;
		if(binary[i] == '1') {
			index = 1;
		} else {
			index = 0;
		}
		
		if(cur->nodes[index] == NULL) {
			break;
		}
		
		cur = cur->nodes[index];
	}
	
	if(cur->r != NULL) {
		if(time == -1 || cur->r->time < time) {
			time = cur->r->time;
			result = cur->r->isAllowed;
		}
	}
	
	return result;
}

int main(void) {
	scanf("%d %d", &N, &M);
	
	trie = initTrieNode();
	
	for(int i = 0; i < N; i++) {
		char command[10], address[30];
		scanf("%s %s", command, address);
		
		addTrie(command, address, i);
	}
	
	for(int i = 0; i < M; i++) {
		char address[20];
		scanf("%s", address);
		
		if(search(address)) {
			printf("YES\n");
		} else {
			printf("NO\n");
		}
	}	
	
	return 0;
}
