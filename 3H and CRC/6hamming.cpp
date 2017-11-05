//ROLL NUMBER : 3476
#include<iostream>
 
using namespace std;
 
int main() {
    int data[11];
    int dataatrec[11],c,c1,c2,c3,c4,i;
 
    cout<<"Enter 7 bits of data one by one\n";
    //Data saved in reverse order
    cin>>data[0];
    cin>>data[1];
    cin>>data[2];
    cin>>data[4];
    cin>>data[5];
    cin>>data[6];
    cin>>data[8];
 
    //Calculation of even parity

    data[10]=data[0]^data[2]^data[4]^data[6]^data[8];
    data[9]=data[1]^data[2]^data[4]^data[5]^data[8];
    data[7]=data[4]^data[5]^data[6];
    data[3]=data[0]^data[1]^data[2];
 
    cout<<"\nEncoded data is\n";
    for(i=0;i<11;i++)
        cout<<data[i];
    
    cout<<"\n\nEnter received data bits one by one\n";
    for(i=0;i<11;i++)
        cin>>dataatrec[i];
     
    // once again ndata saved in reverse order  

    c1=dataatrec[10]^dataatrec[8]^dataatrec[6]^dataatrec[4]^dataatrec[2]^dataatrec[0];
    c2=dataatrec[9]^dataatrec[8]^dataatrec[5]^dataatrec[4]^dataatrec[2]^dataatrec[1];
    c3=dataatrec[7]^dataatrec[6]^dataatrec[5]^dataatrec[4];
    c4=dataatrec[3]^dataatrec[2]^dataatrec[1]^dataatrec[0];

    c=c4*8+c3*4+c2*2+c1 ;

    // to check placement of bits as _ _ _ _

   //                                8 4 2 1

   if(c==0) {
        cout<<"\nNo error while transmission of data\n";
    }
   
   else {
        cout<<"\nError on position d"<< c;
        
        cout<<"\nData sent : ";
        for(i=0;i<11;i++)
            cout<<data[i];
        
        cout<<"\nData received : ";
        for(i=0;i<11;i++)
            cout<<dataatrec[i];
        
        cout<<"\nCorrect message is: ";
        
        //if errorneous bit is 0 we complement it and vice versa
        if(dataatrec[11-c]==0)
            dataatrec[11-c]=1;
        else
             dataatrec[11-c]=0;
        for (i=0;i<11;i++) {
            cout<<dataatrec[i];
        }
    }
    
    return 0;
}
/*
Enter 7 bits of data one by one
1
0
0
1
1
0
1

Encoded data is
10011100111

Enter received data bits one by one
1
1
0
1
1
1
0
0
1
1
1

Error on position d10
Data sent : 10011100111
Data received : 11011100111
Correct message is: 10011100111
*/
