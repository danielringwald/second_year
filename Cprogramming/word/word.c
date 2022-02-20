#include<stdio.h>
#include<stdbool.h>
#include<stdlib.h>
#include<ctype.h>

#define N (100)







int main(void)
{
	unsigned long int size;
	unsigned long int ctr;
	unsigned long int n;
	int c;

	n = N;
	size = 0;
	ctr = 0;
	char *p = malloc(N);
	char *word = malloc(0);

	while ((c = getchar()) != EOF)
	{

		if (ctr == n){
			n *= 2;
			p = realloc(p, n);
		}

		if (c == ' ' || c == '\n'){
			if (ctr > size){	
				size = ctr;

				free(word);					//Rensar förra ordet
				word = realloc(p, size);	//Nytt längre ord sparat i
											//p läggs över till word
				n = N;
				p = malloc(n);				//Reset n

			}
			ctr = 0; 	//reset after a word
		} else {
			
			if (isalpha(c)) {
		 		p[ctr] = c;
				ctr++;
		 	} else {
		 		
		 		while(!(c == ' ' || c == '\n')){
		 			c = getchar();
		 		}
		 		ctr = 0;

		 	}
		}
	}

	printf("%lu characters in longest word: %s\n", size, word);

	free(word);
	free(p);
	ctr = 0;
	
	return 0;
}
