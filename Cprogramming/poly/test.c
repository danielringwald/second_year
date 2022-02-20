#include <stdio.h>
#include "poly.h"

int main(void)
{

	poly_t* poly = new_poly_from_string("5x^3 + 12x^1");
	poly_t* poly1 = new_poly_from_string("2x^5 + 5x^2 + 3x + 4");
	poly_t* mult = mul(poly, poly1);
	print_poly(mult);
	return(0);

}