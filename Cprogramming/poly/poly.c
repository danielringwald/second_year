#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#include "error.h"
#include "poly.h"

typedef struct term term;

struct term {

	int c;	 	//konstant
	int deg;	//Grad
	term* next;

};

struct poly_t {

	term* first;
	term* last;

};

void insert(term *t, poly_t *poly)
{
	if (poly->first == NULL){
		poly->first = t;
	} else {
		poly->last->next = t;
	}
	poly->last = t;
}

term* new_term()
{
	
	term* t = malloc(sizeof(term));
	t->c = 1;
	t->deg = 0;
	t->next = NULL;
	
	return t;
}

void insert_in_order(term *t, poly_t *poly)
{
	
	if(poly->first == NULL){
		poly->first = t;
		poly->last = t;
	} else {
		term *p = poly->first;
		term *q = NULL;
		while(p != NULL){

			if(p->deg == t->deg){
				p->c += t->c;
				free(t);
				return;
			} else if (p->deg < t->deg){
				q->next = t;
				t->next = p;
				return;
			}

			q = p;
			p = p->next;
		}
		poly->last->next = t;
		poly->last = t;	
	}
}

void print_term(int c, int deg, bool head)
{

		if(head){
			if (c < 0){
				printf("- ");
			}	
		} else {
			if (c < 0){
				printf(" - ");
			} else {
				printf(" + ");
			}
		}

		if(deg == 0){
			printf("%d", abs(c));
		} else if (deg == 1){
			if(c == 1){
				printf("x");
			} else {
				printf("%dx", abs(c));
			}
		} else {
			if(c == 1){
				printf("x^%d", deg);
			} else {
				printf("%dx^%d", abs(c), deg);
			}
		}

}

poly_t* new_poly_from_string(const char* p) 
{

	poly_t *poly;

	poly = malloc(sizeof(poly_t));
	poly->first = NULL;
	poly->last = NULL;

	int x = 0;
	bool num = false;
	bool exp = false;
	term *t;
	t = new_term();

	while(*p != 0){

		if(isdigit(*p)){
			x = x*10 + *p -'0';
			num = true;
		} else if (num){

			if (exp){
				t->deg = x;
				insert(t, poly);
				t = new_term();
				exp = false;
			} else {
				t->c *= x;
				if (*p != 'x'){
					t->deg = 0;
					insert(t, poly);
					t = new_term();
				} else if (*(++p) != '^'){
					t->deg = 1;
					insert(t, poly);
					t = new_term();
				} else {
					exp = true;
				}
			}

			num = false;
			x = 0;

		} else if(*p == 'x'){
			
			if (*(++p) == '^'){
				exp = true;
			} else {
				t->deg = 1;
				insert(t, poly);
				t = new_term();
			}
		} else if (*p == '-'){
			t->c *= -1;
		}

		p++;
	}

	if (x != 0){
		if (exp){
			t->deg = x;
		} else {
			t->c *= x;
		}
	insert(t, poly);
	} 
	else {
		free(t);
	}

	return poly;
}

poly_t* mul(poly_t* p, poly_t* q) 
{

	term *t = p->first;
	term *s = q->first;
	term *n;
	poly_t *res;

	res = malloc(sizeof(poly_t));
	res->first = NULL;
	res->last = NULL;

	while(t != NULL){
		while(s != NULL){

			n = new_term();
			n->c = t->c*s->c;
			n->deg = t->deg + s->deg;
			insert_in_order(n, res);

			s = s->next;
		}
		t = t->next;
		s = q->first;
	}

	return res;

}

void free_poly(poly_t* poly) 
{

	term *p;
	term *q;

	p = poly->first;
	free(poly);

	while(p != NULL){
		q = p->next;
		free(p);
		p = q;
	}
}

void print_poly(poly_t* poly) 
{
	bool head = true;
	term *t = poly->first;
	print_term(t->c, t->deg, head);
	head = false;
	t = t->next;

	while(t != NULL){
		print_term(t->c, t->deg, head);
		t = t->next;
	}

	putchar('\n');
}