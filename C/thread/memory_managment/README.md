
Linux 執行記憶體管理
================

##資料的內部儲存##
在 Linux 中，可能出現以下兩種儲存格式：

 - Big Endian
	 將最高位元儲存在記憶體中的低位元處。
 - Little Endian
	將最低位元儲存在記憶體中的高位元處。

以儲存整數 0x12345678 的資料來看

```
int i = 0x12345678;
char p = (char *) (&i);
```
 
 - Big Endian
	```
	*(p) = 0x12;
	*(p + 1) = 0x34;
	...
	*(p + 3) = 0x78;
		
	```
 - Little Endian  
	```
	*(p) = 0x78;
	*(p + 1) = 0x56;
	...
	*(p + 3) = 0x12;
		
	```

##程式碼與記憶體##
下面範例示範了對程式碼的部分進行讀和寫，並試圖改變其內容後可發現，程式的內容是不可被修改的。
```
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
```

##資料段與緩衝段##

 - 初始化資料段(.data)
	通常也稱為資料段，它包含程式中明確指定的全域變數，例如：int max = 10(全域變數); static int max = 10; 初始化的資料段在編譯的時候就已經確定其大小，在程式執行的過程中都不會被改變。

 - 非初始化資料段(.bss)
	儲存在這個段中的資料，通常是沒有明確指定初值的全域變數和靜態變數。例如: int max(全域變數); static int count;

可以藉由將以下範例編譯成目的檔後， 觀察檔案大小，得知 bss 段對程式的目的檔的影響
```
#include <stdio.h>

int a[30];

int main() {
	printf("hello world\n");
	return 0;
}
```
	
## Stack ##
所有的自動變數、函式呼叫時所需要儲存的資訊(傳回位址、函式呼叫前個暫存器的值) 都會儲存在 Stack 上。每次呼叫函式時 Stack 都會隨著函數的呼叫而成長，隨著函式呼叫結束而死亡。以下示範回傳呼叫函式中區域變數的指標:
```
#include <stdio.h>
#include <string.h>

char* combine(char* str1, char* str2) {
	char str[1024];
	char *p = str;

	strcpy(str, str1);
	strcat(str, str2);

	return p;
}

int main() {
	char* p;

	p = combine("hello", "world");
	printf("%s\n", p);

	return 0;
}
```
p 變數在回呼函式被呼叫時，會被放進 stack ，而該函式結束時，此 stack 空間會被其他資料所覆蓋。


##Heap##
Heap 用於儲存使用者申請的記憶體空間，系統通常在 Heap 中進行動態記憶體分配。例如：

    int *p = (int *) malloc(sizeof(int));

Heap 的區塊位於資料段和 Stack 之間。而根據不同的處理器系結構，程式記憶體版面配置會有一定的差異，比如說， Little Endian 法的處理器，其 Stack 由高位指向低位址縮減。但是對於 Big Endian 法的處理器，情況正好和 Little Endian 法的機器相反。

不過因作業系統的記憶體分頁管理機制，所以在程式中所使用的是邏輯位址，也就是說，程式內部儲存版面配置也只是在虛擬記憶體上，並不是實際程式在實體記憶體中的分佈。

以下程式為觀察 Stack、Heap 和 資料段的儲存空間範例：
```
#include <stdio.h>

int data = 1; // 資料段的開始位址

int main() {
	int stack; // stack 的末位址

	printf("stack head: %x\n", &stack + 4);

	printf("data %x\n", &data + 4);

	printf("process space %u\n", (&stack + 4) - (&data + 4));
}
```

