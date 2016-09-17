網路程式設計
================

Linux 環境下使用通訊端進行處理程序之間的通訊。透過通訊介面，其他處理程序的位置對於應用程式來講是透明的。通訊端代表通訊的端點，也就是說，兩個端點應該各有一個通訊端才可以。

## 位元組序##
每一台主機由於體系結構的不同，所採用的資料儲存方式也不相同。在網路環境中，處理程序之間的通訊是要跨越主機的，這時候就會出現位元組序不統一的問題。

為了解決這個問題，網路通訊協定提供一種位元組序，當跨越主機的兩個處理程序進行通訊時，先將需要傳輸的資料轉換成網路位元組序，接收資料後，再將其轉換為本機的位元組序。

Linux 環境底下使用四個函數進行位元組之間的轉換，其函數如下：
```
#include <arpa/inet.h>

uint32_t htonl(uint32_t hostint32); // 轉成網路32位元組序
uint16_t htons(uint16_t hostint16); // 轉成網路16位元組序

uint32_t ntohl(uint32_t netint32); // 轉成電腦32位元組序
uint16_t ntohs(uint16_t nettint16); // 轉成電腦16位元組序
```

##地址格式##
在 IPv4 中，是用 32 位元的整數所組成，且每 8 位元都會用 . 隔開。而 IPv6 則是使用 128 位元的整數所組成。在 Linux 中，是使用 in_addr 結構表示一個 IP 位址，該結構定義如下：
```
#include <netinet/in.h>

struct in_addr {
	in_addr_t s_addr; // in_addr_t 被定義為無符號整數
}
```

當設定好 IP 時，還會需要一個 16 位元組的通訊 port 。當這些都確定好以後，就可以開始通訊了。

Linux 除了 IP 和 Port 以外，還有一些額外的資訊，其結構定義如下：
```
#include <netinet/in.h>

struct socketaddr_in {
	sa_family_t sin_family; // 16 bytes的地址組
	in_port_t sin_port; // 16 bytes of Port
	struct in_addr sin_addr; // 32 bytes of IP 
	unsigned char sin_zero[8]; // 填充區，8 bytes 都填 0
}
```

sin_family 參數表示使用的位址組，他會根據通訊端使用的場合不同，會使用不同的位址組。而 sin_zero 則是保證 socketaddr_in 結構剛好為 16 位元組。這樣可以使 socketaddr_in 和 socketadd 位址結構任意切換。 socketaddr 結構的定義如下：
```
#include <netinet/in.h>

struct socketaddr {
	sa_family sa_family; // 16 bytes of address
	char sa_data[14];	// 14 bytes 的擴充區
}
```