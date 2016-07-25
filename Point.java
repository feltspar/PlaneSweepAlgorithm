/**
 * Point class to hold points
 * @author Shubham Saxena
 * 
 */
public class Point {
	double x;
	double y;

	Point() {
	}

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		String p = "(" + x + " " + y + ")";
		return p;
	}

	public boolean equals(Object s) {
		Point a = (Point) s;
		if (this.x ==a.x && this.y==a.y)
			return true;
		else
			return false;
	}
}
