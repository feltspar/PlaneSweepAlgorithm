/**
 * Event class that holds an event start of segment, end of segment or
 * intersection of point
 * 
 * @author Shubham Saxena
 * 
 */
public class Event implements Comparable<Event> {

	String type; // left right intersect
	Segment s1;
	Segment s2;
	Point eventPt;

	Event() {
		type = new String();
		s1 = new Segment();
		s2 = new Segment(); //null for start and end events. 
		eventPt = new Point(); // holds start point if start event , end pt if
								// end event, intersection pt if intersection
								// event
	}

	Event(String type, Segment s1, Segment s2, Point eventPt) {
		this.type = type;
		this.s1 = s1;
		this.s2 = s2;

		this.eventPt = eventPt;

		/*
		 * if (type.equals("start")) this.eventPt = s1.a; if
		 * (type.equals("end")) this.eventPt = s1.b; if
		 * (type.equals("intersect") && eventPt == null) { this.eventPt =
		 * s1.intersect(s2); }
		 */
	}

	public int compareTo(Event e) {
		if (this.eventPt != null && e != null && e.eventPt!= null) {
			if (this.eventPt.x < e.eventPt.x)
				return -1;
			else
				return 1;
		}
		return 0;
	}

	public String toString() {
		return ""+this.type+"  " + this.eventPt;
	}

	public boolean equals(Object e) {
		Event x = (Event) e;
		if (this.type.equals(x.type) && this.s1.equals(x.s1)
				&& this.s2.equals(x.s2)) // left right intersect

			return true;
		else
			return false;
	}
}
