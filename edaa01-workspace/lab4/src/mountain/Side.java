package mountain;

public class Side {
	private Point start;
	private Point end;
	private Point m;

	public Side(Point p1, Point p2) {
		start = p1;
		end = p2;
	}

	public Point getM() {
		return new Point((start.getX() + end.getX())/2, (start.getY() + end.getY())/2);
	}
	
	@Override
	public int hashCode() {
		return start.hashCode() + end.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Side) {
			Side s = (Side) (o);
			if (start == null & end == null) {
				return m.equals(s.m);
			} else {
				return (start.equals(s.start) && end.equals(s.end) || (end.equals(s.start) && start.equals(s.end)));
			}
		}
		return false;
	}

}
