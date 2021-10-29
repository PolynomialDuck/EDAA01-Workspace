package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.RealMountain;
import mountain.Point;
import otherFractals.Rectangle;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[4];
		fractals[0] = new Koch(300);
		fractals[1] = new Mountain(new Point(150,300) , new Point(300,150), new Point(450,300));
		fractals[2] = new RealMountain(new Point(150,300) , new Point(300,150), new Point(450,300), 50);
		fractals[3] = new Rectangle(new Point(200,400) , new Point(200,200), new Point(400,200), new Point(400, 400), 50);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
