#include <stdio.h>

int data = 1; // 資料段的開始位址

int main() {
	int stack; // stack 的末位址

	printf("stack head: %x\n", &stack + 4);

	printf("data %x\n", &data + 4);

	printf("process space %u\n", (&stack + 4) - (&data + 4));
}