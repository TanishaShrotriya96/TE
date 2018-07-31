// Client side C/C++ program to demonstrate Socket programming
// gcc s.c -lm -o s

#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#define PORT 8080

//for file permissions and properties
#include <unistd.h>
#include <fcntl.h>

void files(int sock);
void calculator(int sock);
void trig(int sock);

int main()
{
    struct sockaddr_in serv_addr;
    int sock = 0, valread;    
    
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        printf("\n Socket creation error \n");
        return -1;
    }
  
   // memset(&serv_addr, '0', sizeof(serv_addr));
  
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    serv_addr.sin_addr.s_addr = INADDR_ANY;
 
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
    {
        printf("\nConnection Failed \n");
        return -1;
    }

   char *hello = "Hello from client";
   char buffer[1024] = {0};
   int choice;
   while(choice != 5) {
    
      printf("\nChoose Operation : \n1.Say Hello\n2.Send file \n3.Calculator\n4.Trigonometry\n5.Exit");
      scanf("%d",&choice);
      int converted_number = htonl(choice);

      // Write the number to the opened socket
      write(sock, &converted_number, sizeof(converted_number));
      
      switch(choice) {
        
           case 1 : send(sock , hello , strlen(hello) , 0 );
                    printf("\nHello message sent\n");
    		    valread = read( sock , buffer, 1024);
		    printf("%s\n",buffer );
                    break;
           case 2 : files(sock);
                    break;
           case 3 : calculator(sock);
                    break;
           case 4 : trig(sock);
                    break;
           case 5 : break;
      }
   }
   return 0;
}

void files(int sock) {

    char buf[1024] = {0};
    int filefd;
    int read_return;

    //open file 
    
    printf("Enter file name" );
    scanf("%s",buf);  
    send(sock , buf ,1024,0 );
  
    filefd = open(buf,O_WRONLY | O_CREAT | O_TRUNC,S_IRUSR | S_IWUSR);
    //O_WRONLY = write only /;
    //O_CREAT = create if not existing else open as it is
    // O_TRUNC = clear if exists hence overwrite                    
    //S_IRUSR= read priviledges to owner of file
    //S_IWUSR=write priviledges to owner of file
    if (filefd == -1) {
    	perror("open");
   	exit(EXIT_FAILURE);
    }
    
    read_return = read(sock, buf,1024);
    if (read_return == -1) {
        perror("read");
        exit(EXIT_FAILURE);
    }
    if (write(filefd, buf, read_return) == -1) {
        perror("write");
        exit(EXIT_FAILURE);
    }
    else { 
        printf("\nFile received\n");
        system("/home/tanishashrotriya/Documents/root/4socket/client/script.sh");
    }
 
    close(filefd);
    
}

void calculator(int sock) {

   int choice,num1,num2,res,converted_number;
   char buff[1024]={0};
   while(choice != 5) {

      printf("\nChoose Operation : \n1.Add\n2.Subtract\n3.Multiply\n4.Divide\n5.Exit to main");
      scanf("%d",&choice);
      converted_number = htonl(choice);
      // Write the number to the opened socket
      write(sock, &converted_number, sizeof(converted_number));
      
      if(choice !=5) {
	      read(sock,buff,1024); 
	      printf("%s\n",buff);	
	      scanf("%d",&num1);		    
	      converted_number = htonl(num1);
	      write(sock, &converted_number, sizeof(converted_number));
	    
	      read(sock,buff,1024); 
	      printf("%s\n",buff);	
	      scanf("%d",&num2);		    
	      converted_number = htonl(num2);
	      write(sock, &converted_number, sizeof(converted_number));
	      
	      read(sock,&res,sizeof(res));
	      converted_number=ntohl(res); 
	      printf("The result is : %d\n",converted_number);
	}
     }
}


void trig(int sock) {

   int choice=0,num1,num2,res,converted_number;
   double n1,n2;
   char buff[1024]={0};

   while(choice != 5) {

      printf("\nChoose Operation : \n1.Cosine\n2.Sine\n3.Tan\n4.Cot\n5.Exit to main");
      scanf("%d",&choice);
      converted_number = htonl(choice);
      // Write the number to the opened socket
      write(sock, &converted_number, sizeof(converted_number));
      
      if(choice !=5) {
	      read(sock,buff,1024); 
              printf("%s\n",buff);
	      scanf("%lf",&n1);
	      //convert to int to send to server. so 25.5 will be sent as 2550
	      num1=n1*100;		    
	      converted_number = htonl(num1);
	      write(sock, &converted_number, sizeof(converted_number));

	      read(sock,&res,sizeof(res));
	      converted_number=ntohl(res); 
	      //read result is an int so convert to double and get back original value by /100
	      double fin = converted_number;
	      fin=fin/100;
	      printf("The result is : %lf\n",fin);
      }
      

   }


}
