package mountain;

public class Side{
	private Point p1;
	private Point p2;
	
	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1() {
		return p1;
	}
	public Point getP2() {
		return p2;
	}
	@Override
	public int hashCode() {
	return p1.hashCode() + p2.hashCode();
	}
	@Override
	public boolean equals(Object s){
		if(s instanceof Side) {
		return ( (((Side)s).getP1().equals(p1) && ((Side)s).getP2().equals(p2)) || ((((Side)s).getP1().equals(p2) && ((Side)s).getP2().equals(p1))));
		}
		else {
			return false;
		}
	}
}
