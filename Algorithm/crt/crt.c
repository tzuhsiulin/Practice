
/* CRT �p��  */
/* Author: Star Lee */
/* Date: 2009/12/20 */
/*
#include <stdio.h>
#include <stdlib.h>

#define MAX 5

int gcd(int a,int b)
{
 //swap
 if(a < b)
 {
     a = a+b;
     b = a-b;
     a = a-b;
 }

 if(b == 1)
     return b;
 else
     return gcd(b,(a%b));
}

int Get_M(int m[],int t)
{
 int i,j,r;
 int M = 1;

 for(i=0;i<t-1;i++)
 {
     for(j=i+1;j<t;j++)
     {
         r = gcd(m[i],m[j]);
         if(r!=1)
         {
             return M;
         }
     }
 }

 for(i=0;i<t;i++)
 {
     M*=m[i];
 }
 return M;
}

int Get_y(int M,int m)
{
 int y;
 for(y=1;y<m;y++)
 {
     if((( M / m * y )% m) == 1 )
         return y;
 }
 //return y;
}

int main (void)
{
 int T,i;
 int m[MAX],v[MAX],y[MAX];
 int M,X;

 for(i=0;i<MAX;i++)
 {
     m[i]=0;
     v[i]=0;
     y[i]=0;
 }

 do
 {
     printf("�@���X�պ⦡�]�̦h5�ա^�H");
     scanf("%d,",&T);

     if((T > MAX)||(T < 0))
     {
         printf(" %d �W�X���{���p��d��L�k�p�� !! ",T);
     }
     else
     {
         //�D��
         printf("EX�G�@���� 3 �ӳ̫�� 2 �ӡA�п�J 3,2 \n");
         for(i=0;i<T;i++)
         {
             printf("�п�� %2d �խȡG",(i+1));
             scanf("%d,%d",&m[i],&v[i]);
             if((m[i]==0)||(v[i]==0))
             {
                 printf("�� %2d �խȿ�J���~!!\n�G",(i+1));
                ("�榡�G�@���� 3 �ӳ̫�� 2 �ӡA�п�J 3,2 \n");
                 i--;
             }
         }

         //�C�X�D��
         printf("\n�z�n�p�⪺�D�ءG\n");
         for(i=0;i<T;i++)
         {
             printf(" X mod %d = %d \n",m[i],v[i]);
         }
         printf("\n �D X���ȡH\n");

         printf("\n M���ȡG");
         M = Get_M(m,T);

         if(M == 1)
         {
             printf("m[i]���ȡG");
             for(i=0;i<T;i++)
             {
                 printf(" %d",m[i]);
             }
             printf("�S�� ���� ���ŦX CRT �������n�D !! \n");
         }
         else
         {
             printf(" M = %d \n",M);

             printf("\n yi���ȡG\n");
             for(i=0;i<T;i++)
             {
                 y[i] = Get_y(M,m[i]);
                 printf(" y%d = %d \n",(i+1),y[i]);
             }

             X = 0;

             printf("\n �N�J�����G\n");
             printf(" X = ");
             for(i=0;i<T;i++)
             {
                 printf(" (M/m[%d])*v%d*y%d ",i,i,i);
                 if(i<T-1)
                     printf("+");
                 else
                     printf("\n");
             }

             printf("   = ");
             for(i=0;i<T;i++)
             {
                 printf(" (%d/%d)*%d*%d ",M,m[i],v[i],y[i]);
                 X += v[i]*(M/m[i])*y[i];

                 if(i<T-1)
                     printf("+");
                 else
                     printf("\n");
             }
             X = X % M;
             printf("   = %2d ������",X);
         }
     }
 }while(0);
 system("pause");

 return 0;
}
*/
