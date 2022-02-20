#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>
#include <string.h>

#define N       (10)

static void error(unsigned int line, int c, bool* err)
{
	char	buf[3];

	if (c == '\n'){
		strcpy(buf, "\\n");
	} else {
		buf[0] = c;
		buf[1] = 0;
	}

	printf("line %u: error at %s\n", line, buf);

	*err = true;
}

static bool isOperator(int c)
{
return c == '+' || c == '-' || c == '/' || c == '*';
}

static bool checkError(int c, int d)
{
		return ((isOperator(c) && d < 2)) || (!(isOperator(c) || isdigit(c) || c == '\n' || c == ' '));
}

int main(void)
{
        int             stack[N];
		int 			i;
        int             c;
        int             d;
        int             x;
        bool            num;
        bool            err;
        unsigned        line;

        x = 0;
        i = 0;
				d = 0;
        line = 1;
        num = false;
        err = false;

        while ((c = getchar()) != EOF) {

                if (err) {

                        if (c == '\n') {
                                line += 1;
                                err = 0;
                                i = 0;
                                d = 0;
                        }
                        continue;
                } else if (isdigit(c)) {
                        x = x * 10 + c - '0';
                        num = true;
                        continue;
                } else if (num) {

                        if (i == N)
                                error(line, '0' + x%10, &err);
                        else {
                                stack[d++] = x;
                                num = false;
                                x = 0;
				i++;
                        }

		}

		if (checkError(c, d)){
			error(line, c, &err);
			continue;
		}

		switch (c) {
			case '+' :
				stack[d-2] += stack[d-1];
				d--;
				break;

			case '-' :
				stack[d-2] -= stack[d-1];
				d--;
				break;

			case '*' :
				stack[d-2] *= stack[d-1];
				d--;
				break;

			case '/' :
				if (stack[d-1] == 0) {
					error(line, c , &err);
					break;
				} else {
					stack[d-2] = stack[d-2] / stack[d-1];
					d--;
					break;
				}

			case '\n' :
				if (d != 1){
					printf("line %u: error at \\n\n", line);
				} else{
					printf("line %u: %d\n", line, stack[0]);
				}
					line++;
					d = 0;
					i = 0;
				break;

		}
	}
}
