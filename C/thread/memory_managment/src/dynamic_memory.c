#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void f1(char** p) {
	*p = (char *) malloc(sizeof(char) * 10);
	memset(*p, '0', 10);
}

int main() {
	char* p;

	f1(&p);
	printf("%s\n", p);
	
	return 0;
}