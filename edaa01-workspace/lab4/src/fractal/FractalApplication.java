package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		
		Point a = new Point(100,100);
		Point b = new Point(450,450);
		Point c = new Point(80, 400);
		
		fractals[0] = new Mountain(a, b, c, 25);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
