#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/errno.h>

int main() {
	struct sockaddr_in serverAddr, clientAddr;
	socklen_t addrLen;
	char receiveBuf[21];
	int sockfd, len;

	if ( ( sockfd = socket( AF_INET, SOCK_DGRAM, 0 ) ) == -1 ) {
		perror( "create socket fail" );
		return -1;
	}

	memset( &serverAddr, sizeof(serverAddr), 0 );
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	serverAddr.sin_port = htons(3000);
	bind( sockfd, ( struct sockaddr *)&serverAddr, sizeof( serverAddr ) );

	while (1) {
		len = sizeof( clientAddr );
		addrLen = sizeof( clientAddr );

		recvfrom( sockfd, receiveBuf, sizeof( receiveBuf ), 0, (struct sockaddr *)&clientAddr, &addrLen );
	}

	return 0;
}