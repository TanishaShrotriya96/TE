//Name    : Sneha Bandi
//Roll No : 3403
//About   : UNIX System Calls 

#include<unistd.h>
#include<stdio.h>
int glob =6;
int main(int argc,char**argv)
{


int var ;	//automatic variable on stack
char ans;
int choice;
pid_t pid;
int ret_val,ret_code;
var = 88;	//We don't flush stdio.h
char *args[] = {"cal","12","2011",NULL}; //for execv case 5 and 7
char *ptr = "NEW=/home/tanishashrotriya/Documents/SPOS/Assign12";
int ret;
do{
printf("\n*************** MENU ***************\n");
printf("\n\t1. fork\n\t2. vfork\n\t3. wait\n\t4. execl\n\t5. execv\n\t6. execlp\n\t7. execvp\n\t8. getenv & putenv");
printf("\nCHOICE : ");
scanf("%d",&choice);

	//Switch Case
	switch(choice)
	{
		case 1://FORK
				printf("before fork\n");
				if((pid=fork())<0)
				{
					printf("\n\tfork error !");
				}
				else if(pid ==0)
				{
					glob++;
					var++;
				}
				else
				{
					sleep(2);
				}
				printf("\npid = %d\tglob = %d\tvar = %d\n",getpid(),glob,var);
				printf("\n\tvfork terminates !\n");
				exit(0);
		break;	
		case 2://VFORK

				printf("before vfork\n");
				if((pid=vfork())<0)
				{
					printf("\n\tvfork error !");
				}
				else if(pid ==0)
				{
					glob++;				//child
					var++;				//modify parent's variables
					printf("\n\tchild terminates !\n");
					exit(0);			//child terminates
				}
				else
				{
					sleep(2);
				}
			
				//parent continues here
				printf("\npid = %d\tglob = %d\tvar = %d\n",getpid(),glob,var);
				printf("\n\tvfork terminates !");
				exit(0);
		break;	
		case 3://Wait

				printf("before vfork\n");
				if((pid=fork())<0)
				{
					printf("\nChild process %x ",getpid());
					exit(10);
				}
				ret_val = wait(&ret_code);
				printf("\nWait ret_val : %x\tret_code : %x",ret_val,ret_code);
				printf("\n\tvfork terminates !\n");
				exit(0);
		break;
		case 4:
			execl("/usr/bin/cal","cal","03","2018",(char*)0);
			printf("\n\tReached here !");	
			break;
		case 5:
			execv("usr/bin/cal",args);
			printf("\n\tReached here !");	
			break;	
		case 6 :
			execlp("cal","cal","04","2018",(char*)0);
			break;
		case 7:
					execvp("usr/bin/cal",args);
					printf("\n\tReached here !");	
			break;	
		case 8:
			printf("\n\tBefore getenv : %s\tputenv : %d",getenv("NEW"),putenv());
			ret = putenv(ptr);
			printf("\n\tAfter getenv : %s\tputenv : %d",getenv("NEW"),ret);
			break;
		default:
			printf("\nEnter valid choice !");
	}
	printf("\n=========================================================");
	printf("\nDo you want to continue (y/n) ?\nAnswer : ");
	getchar();
	scanf("%c",&ans);
	printf("=========================================================");
}while(ans=='y'||ans=='Y');

}

/*
*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 1
before fork

pid = 3648	glob = 7	var = 89

pid = 3647	glob = 6	var = 88
[ccoew@localhost SystemCall_assignment7]$ ./a.out

*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 2
before vfork

pid = 3655	glob = 7	var = 89
[ccoew@localhost SystemCall_assignment7]$ ./a.out

*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 3
before vfork

Wait ret_val : ffffffff	ret_code : 400d53
Wait ret_val : e50	ret_code : 0[ccoew@localhost SystemCall_assignment7]$ ./a.out

*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 4
     March 2018     
Su Mo Tu We Th Fr Sa
             1  2  3 
 4  5  6  7  8  9 10 
11 12 13 14 15 16 17 
18 19 20 21 22 23 24 
25 26 27 28 29 30 31 
                     
[ccoew@localhost SystemCall_assignment7]$ ./a.out

*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 5

	Reached here !
=========================================================
Do you want to continue (y/n) ?
Answer : y
=========================================================
*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 6           
     April 2018     
Su Mo Tu We Th Fr Sa
 1  2  3  4  5  6  7 
 8  9 10 11 12 13 14 
15 16 17 18 19 20 21 
22 23 24 25 26 27 28 
29 30                
                     
[ccoew@localhost SystemCall_assignment7]$ ./a.out

*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 7

	Reached here !
=========================================================
Do you want to continue (y/n) ?
Answer : y
=========================================================
*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 8

	Before getenv : 0	putenv : 0
	After getenv : 0	putenv : 0
=========================================================
Do you want to continue (y/n) ?
Answer : y
=========================================================
*************** MENU ***************

	1. fork
	2. vfork
	3. wait
	4. execl
	5. execv
	6. execlp
	7. execvp
	8. getenv & putenv
CHOICE : 9

Enter valid choice !
=========================================================
Do you want to continue (y/n) ?
Answer : n
=========================================================
*/
