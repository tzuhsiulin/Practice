
/* CRT 計算  */
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
     printf("共有幾組算式（最多5組）？");
     scanf("%d,",&T);

     if((T > MAX)||(T < 0))
     {
         printf(" %d 超出本程式計算範圍無法計算 !! ",T);
     }
     else
     {
         //題目
         printf("EX：一次取 3 個最後剩 2 個，請輸入 3,2 \n");
         for(i=0;i<T;i++)
         {
             printf("請輸第 %2d 組值：",(i+1));
             scanf("%d,%d",&m[i],&v[i]);
             if((m[i]==0)||(v[i]==0))
             {
                 printf("第 %2d 組值輸入錯誤!!\n：",(i+1));
                ("格式：一次取 3 個最後剩 2 個，請輸入 3,2 \n");
                 i--;
             }
         }

         //列出題目
         printf("\n您要計算的題目：\n");
         for(i=0;i<T;i++)
         {
             printf(" X mod %d = %d \n",m[i],v[i]);
         }
         printf("\n 求 X的值？\n");

         printf("\n M的值：");
         M = Get_M(m,T);

         if(M == 1)
         {
             printf("m[i]的值：");
             for(i=0;i<T;i++)
             {
                 printf(" %d",m[i]);
             }
             printf("沒有 互質 不符合 CRT 公式的要求 !! \n");
         }
         else
         {
             printf(" M = %d \n",M);

             printf("\n yi的值：\n");
             for(i=0;i<T;i++)
             {
                 y[i] = Get_y(M,m[i]);
                 printf(" y%d = %d \n",(i+1),y[i]);
             }

             X = 0;

             printf("\n 代入公式：\n");
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
             printf("   = %2d ←答案",X);
         }
     }
 }while(0);
 system("pause");

 return 0;
}
*/
