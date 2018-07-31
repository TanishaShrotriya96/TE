/* udpserver.c */ 

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int main()
{
        int sock;
        int addr_len, bytes_read;
        char recv_data[1024];
        struct sockaddr_in server_addr ;


        if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
            perror("Socket");
            exit(1);
        }

        server_addr.sin_family = AF_INET;
        server_addr.sin_port = htons(5000);
        server_addr.sin_addr.s_addr = INADDR_ANY;

        if (bind(sock,(struct sockaddr *)&server_addr,sizeof(struct sockaddr)) == -1)
        {
            perror("Bind");
            exit(1);
        }

	printf("\nUDPServer Waiting for client on port 5000");
        fflush(stdout);

//-------------------- Socket creation completed ----------------------------------------------------
	while (1)
	{
        bytes_read = recvfrom(sock,recv_data,1024,0,(struct sockaddr *)&server_addr,(socklen_t *)sizeof(struct sockaddr));
 	recv_data[bytes_read] = '\0';
        
        printf("File to be sent %s", recv_data);
        fflush(stdout);
        // recv_data=file name that client is asking for.
       
        int filefd;
        int read_return;
        char buffer[1024];
	   
        //open file 
  	filefd = open(recv_data, O_RDONLY);        

    	if (filefd == -1) {
			perror("open here");
			exit(EXIT_FAILURE);
    	}
		 //READ FILE INTO BUFFER
	    read_return = read(filefd, buffer,1024);
	    if (read_return == -1) {
		    perror("read");
		    exit(EXIT_FAILURE);
	    }

 	     //WRTIE TO CLIENT
		if ( sendto(sock,buffer,strlen(buffer), 0,(struct sockaddr *)&server_addr, sizeof(struct sockaddr))== -1) {
		    perror("write");
		    exit(EXIT_FAILURE);
	    }
	
	    else {
			printf("File Sent"); 
			fflush(stdout);
	    }

	    close(filefd);
    }

	return 0;
}

