package mountain;

import fractal.*;

public class Mountain extends Fractal {
	Point p1;
	Point p2;
	Point p3;
	
	public Mountain(Point p1, Point p2, Point p3) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	@Override
	public String getTitle() {
		return "Triangel";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(p1.getX(), p1.getY());
		fractalLine(turtle, order, p1, p2, p3);
	}
	private void fractalLine(TurtleGraphics turtle, int order, Point b1, Point b2, Point b3) {
		if (order == 0) {
			turtle.moveTo(b1.getX(), b1.getY());
			turtle.forwardTo(b2.getX(), b2.getY());
			turtle.forwardTo(b3.getX(), b3.getY());
			turtle.forwardTo(b1.getX(), b1.getY());
			} else {
				fractalLine(turtle, order-1, b1, getMid(b1,b2), getMid(b1,b3));
				fractalLine(turtle, order-1, b2, getMid(b2,b1), getMid(b2,b3));
				fractalLine(turtle, order-1, b3, getMid(b3,b2), getMid(b3,b1));
			}
	}
	private Point getMid(Point p1, Point p2) {
        return new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2); 
        }
}
