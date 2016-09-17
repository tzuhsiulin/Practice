#include <stdio.h>

int f(int a, int b) {
	return a + b;
}

int main() {
	int (*p)(int, int);
	void *q;

	p = f;
	q = (void *)p; // get code
	printf("the code is : 0x%x", *((int *)q)); // get q pointer address

	*((int *) q) = 0x12345678;

	return 0;
}