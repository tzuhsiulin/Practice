#include <stdio.h>
#include <stdlib.h>

void f1(void) {
	printf("the first exit handler\n");
}

void f2(void) {
	printf("this secod exit handler\n");
}

int main(void) {
	if (atexit(f1) == -1) {
		perror("fail to set exit handler");
		exit(1);
	}

	if (atexit(f1) == -1) {
		perror("fail to set exit handler");
		exit(1);
	}

	if (atexit(f2) == -1) {
		perror("fail to set exit handler");
		exit(1);
	}

	return 0;
}