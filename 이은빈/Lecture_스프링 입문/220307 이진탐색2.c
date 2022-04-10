/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, 
C#, VB, Perl, Swift, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void selectionSort(int list[])
{
	int min, temp;
	for (int i = 0; i < 19; i++)
	{
		min = i;
		for (int j = i + 1; j < 20; j++)
			if (list[j] < list[min])
				min = j;
		
		temp = list[min];
		list[min] = list[i];
		list[i] = temp;
	}
}

int binarySearchRecur(int A[], int left, int right, int key) 
//ì¬ê· í¸ì¶ í¨ì
//ë(í¨ì)ë¥¼ ì§ì  ë¤ì í¸ì¶íë©´ì leftì right ê°ì ì¡°ì 
{
    int mid
    static int count = 0;
    //ì ì  ë³ì ì ì¸. ì¬ê· í¸ì¶í  ë countê° ëì ë¨
    //ì¬ê· í¸ì¶ììë ë°ë³µë¬¸ ìëì¤ê³  ì¡°ê±´ë¬¸ë§ ëì´
    
    if(left <= right)
    {
        count++;
        mid = (left + right) / 2;
        
        if (key == A[mid])
            return count;
        else if (key < A[mid]) //keyê° mid ë³´ë¤ ìì ê²½ì° ì¤ë¥¸ìª½ì ë³¼ íìê° ìì
            return binarySearchRecur(A, left, mid-1, key); 
            //ìí ê°ì í¨ìë¥¼ íµí´ ìì 
        else
            return binarySearchRecur(A, mid+1, right, key); 
            //íí ê°ì í¨ìë¥¼ íµí´ ìì 
    }
    return 0;
}

int main()
{
    int A[20], k;
    srand(time(NULL));
    for(int i = 0; i < 20; i++)
        A[i] = rand() % 100;
        
    selectionSort(A);    
    
    for(int i = 0; i < 20; i++)
        printf("%d ", A[i]);
    
    printf("\n");
    
    printf("Key : ");
    scanf("%d", &k);
    
    printf("%d\n", binarySearchIter(A, 0, 19, k));
    
    return 0;
}




