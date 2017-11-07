// Server side C/C++ program to demonstrate Socket programming

#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#define PORT 8080

//Used for the file properties and permissions
#include <unistd.h>
#include <fcntl.h>
#include<math.h>

void files(int sock);
void calculator(int sock);
void trig(int sock);

int main()
{
    int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    char *hello = "Hello from server";
      
    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
     
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons( PORT );
      
    if (bind(server_fd, (struct sockaddr *)&address,sizeof(address))<0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }
    if (listen(server_fd, 3) < 0)
    {
        perror("listen");
        exit(EXIT_FAILURE);
    }
    if ((new_socket = accept(server_fd,(struct sockaddr*)&address,(socklen_t*)&addrlen))<0)
    {
        perror("accept");
        exit(EXIT_FAILURE);
    }

	int choice=0;
 
	while(choice != 5) {

		int return_status = read(new_socket, &choice, sizeof(choice));
		if (return_status > 0) {

		    printf("Choice of client is %d\n",ntohl(choice));
		}

		switch(ntohl(choice)) {

 		  case 1 : send(new_socket , hello , strlen(hello) , 0 );
               printf("\nHello message sent\n");
               valread = read( new_socket , buffer, 1024);
               printf("%s\n",buffer );
			         break;
      case 2 : files(new_socket);                       
               break;
      case 3 : calculator(new_socket);
               break;
      case 4 : trig(new_socket);
               break;
		  case 5 : exit(EXIT_SUCCESS);
               break;
                  }
	}
  return 0;
}

void files(int new_socket) {

    char *file_path = "output.tmp";
    char buf[1024]={0};
    char buffer[1024];
    int filefd;
    ssize_t read_return;

    read(new_socket,buf,1024);
    filefd = open(buf, O_RDONLY);
    if (filefd == -1) {
        perror("open here");
        exit(EXIT_FAILURE);
    }
    read_return = read(filefd, buffer,1024);
    if (read_return == -1) {
            perror("read");
            exit(EXIT_FAILURE);
    }
    if (write(new_socket, buffer, read_return) == -1) {
            perror("write");
            exit(EXIT_FAILURE);
     }
    else {
        printf("File Sent\n"); 
    }
    close(filefd);
    
}


void calculator(int new_socket) {

   int choice;
   char *mess=" ";
   int num1,num2,res;
   while(choice != 5) {
      int return_status = read(new_socket, &choice, sizeof(choice));
      choice =ntohl(choice);
      if(choice !=5) {
      mess = "Send number 1";
      send(new_socket , mess , strlen(mess) , 0 );
      read( new_socket ,&num1,sizeof(num1));

      mess = "Send number 2";
      send(new_socket , mess , strlen(mess) , 0 );     
      read(new_socket ,&num2,sizeof(num2)); 
      
      int n1= ntohl(num1); 
      int n2= ntohl(num2);    
      
      switch(choice) {
        
           case 1 : res = n1+n2;
                    break;
           case 2 : res =n1-n2;
                    break;
           case 3 : res=n1*n2;
                    break;
           case 4 : res=n1/n2;
                    break;  
      }
      int converted_number = htonl(res);
      printf("Sending value %d\n",res);
      // Write the number to the opened socket
      write(new_socket, &converted_number, sizeof(converted_number));
      } 
    }
}
void trig(int new_socket) {

   int choice=0;
   char *mess=" ";
   int num1,num2;
   double res;

   while(choice != 5) {
      int return_status = read(new_socket, &choice, sizeof(choice));
      choice = ntohl(choice);
      if(choice !=5) {
      mess = "Send angle in degrees";
      send(new_socket , mess , strlen(mess) , 0 );
      read(new_socket ,&num1,sizeof(num1));

      int n2= ntohl(num1); 
      
      double n1=n2*(3.14)/18000; //to get rid of the * 100 on client side
      
      //pi/10	
      switch(choice) {
        
           case 1 : res = cos(n1);
                    break;
           case 2 : res =sin(n1);
                    break;
           case 3 : res=tan(n1);
                    break;
           case 4 : res=n1;
                    break;  
           default: res=0;
                    break; 
      }
      int r = res*100;
      int converted_number = htonl(r);
      printf("Sending value %lf\n",res);
      // Write the number to the opened socket
      write(new_socket, &converted_number, sizeof(converted_number));
      }
    }

}

