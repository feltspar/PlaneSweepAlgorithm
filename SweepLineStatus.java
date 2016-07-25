import java.util.TreeSet;

/**
 * 
 * @author Shubham
 * 
 */
public class SweepLineStatus {

	TreeSet<Segment> slsT;

	SweepLineStatus() {
		slsT = new TreeSet<Segment>();
	}

	void insert(Segment s) {
		slsT.add(s);
	}

	void delete(Segment s) {
		slsT.remove(s);
	}

	void swap(Segment a, Segment b) {
		Segment temp = new Segment(b.a,b.b);
		a.a.x = b.b.x;
		a.a.y = b.b.y;
		
		b.a.x = temp.a.x;
		b.b.y = temp.b.y;
		
	}

	Segment above(Segment s) {
		return slsT.higher(s);
	}

	Segment below(Segment s) {
		return slsT.lower(s);
	}

}
