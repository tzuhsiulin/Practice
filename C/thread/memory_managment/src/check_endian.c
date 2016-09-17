#include <stdio.h>

int isBigEndian() {
	int number = 0x12345678;
	char *p = (char *) &number;

	if (*p == 0x12) {
		return 1;
	}
	else {
		return 0;
	}
}

int main() {
	printf("%d\n", isBigEndian());
}