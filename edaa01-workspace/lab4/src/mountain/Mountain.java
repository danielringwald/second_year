package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	Point a;
	Point b;
	Point c;
	double dev;
	Map<Side, Point> map = new HashMap<Side, Point>();

	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		// TODO Auto-generated method stub

		fractalTriangle(turtle, dev, order, a, b, c);
	}

	private void fractalTriangle(TurtleGraphics turtle, double dev, int order, Point p1, Point p2, Point p3) {
		// TODO

		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {
			
			Point d = null;
			Point e = null;
			Point f = null;

			if(!map.containsKey(new Side(p1, p2))) {
				d = new Point((p1.getX() + p2.getX())/2, (p1.getY() + p2.getY())/2 + (int) RandomUtilities.randFunc(dev));
				map.put(new Side(p1, p2), d);
			} else {
				Side s = new Side(p1, p2);
				d = map.get(s);
				map.remove(s);
			}
			
			if(!map.containsKey(new Side(p2, p3))) {
				e = new Point((p2.getX() + p3.getX())/2, (p2.getY() + p3.getY())/2 + (int) RandomUtilities.randFunc(dev));
				map.put(new Side(p2, p3), e);
			} else {
				Side s = new Side(p2, p3);
				e = map.get(s);
				map.remove(s);
			}
			
			if(!map.containsKey(new Side(p3, p1))) {
				f = new Point((p3.getX() + p1.getX())/2, (p3.getY() + p1.getY())/2 + (int) RandomUtilities.randFunc(dev));
				map.put(new Side(p3, p1), f);
			} else {
				Side s = new Side(p3, p1);
				f = map.get(s);
				map.remove(s);
			}
			
			fractalTriangle(turtle, dev/2, order - 1, p1, d, f);
			fractalTriangle(turtle, dev/2, order - 1, e, d, f);
			fractalTriangle(turtle, dev/2, order - 1, e, f, p3);
			fractalTriangle(turtle, dev/2, order - 1, d, p2, e);
		}
	}

}
