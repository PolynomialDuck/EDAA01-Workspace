package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class RealMountain extends Fractal {
	private HashMap<Side, Point> map = new HashMap<Side, Point>();
	static double dev;
	Point p1;
	Point p2;
	Point p3;
	
	public RealMountain(Point p1, Point p2, Point p3, double dev) {
		super();
		this.dev = dev;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	@Override
	public String getTitle() {
		return "Real Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(p1.getX(), p1.getY());
		fractalLine(turtle, order, p1, p2, p3, this.dev);
	}
	private void fractalLine(TurtleGraphics turtle, int order, Point b1, Point b2, Point b3, double dev) {
		if (order == 0) {
			turtle.moveTo(b1.getX(), b1.getY());
			turtle.forwardTo(b2.getX(), b2.getY());
			turtle.forwardTo(b3.getX(), b3.getY());
			turtle.forwardTo(b1.getX(), b1.getY());
			} else {
				Point b1ToB2 = getMid(b1,b2, dev);
				Point b1ToB3 = getMid(b1,b3, dev);
				Point b2ToB3 = getMid(b2,b3, dev);
				
				fractalLine(turtle, order-1, b1, b1ToB2, b1ToB3, (dev/2));
				fractalLine(turtle, order-1, b2, b1ToB2, b2ToB3, (dev/2));
				fractalLine(turtle, order-1, b3, b2ToB3, b1ToB3, (dev/2));
				fractalLine(turtle, order-1, b1ToB3, b1ToB2, b2ToB3, (dev/2));
				
			}
	}
	private Point getMid(Point p1, Point p2, double rand) {
		System.out.println(map.containsKey(new Side(p1,p2)));
		if(map.containsKey(new Side(p1,p2))) {
			Point t = map.remove(new Side(p1,p2));
			return t;
		}
		else {
			Point k = new Point( (p1.getX() + p2.getX() ) / (2), (p1.getY() + p2.getY() + (int)RandomUtilities.randFunc(rand)) / (2));
			map.put(new Side(p1, p2), k);
			return k;
		}
        }
}
