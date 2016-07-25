import java.util.TreeSet;

/**
 * 
 * @author Shubham Saxena
 * 
 */

public class EventQ {

	TreeSet<Event> eqT;

	EventQ() {
		this.eqT = new TreeSet<Event>();
	}

	void insert(Event e) {
		eqT.add(e);

	}

	Event extract() {
		if (eqT.isEmpty())
			return null;
		Event e;
		e = eqT.first();
		eqT.pollFirst();
		return e;
	}

	void delete(Event e) {
		eqT.remove(e);
	}

	/**
	 * checks if the segments have an associated even removes if yes.
	 * 
	 * @param s1
	 * @param s2
	 */
	void removeAssEvent(Segment s1, Segment s2) {
		if (s1 != null && s2 != null) {
			Event e = new Event("intersect", s1, s2, s1.intersect(s2));

			if (e.eventPt != null) {
				for (Event ev : eqT) {
					if (ev.equals(e)) {
						eqT.remove(ev);
					}
				}
			}
		}
	}

	/**
	 * adds an associated point if it exists between two segments
	 * 
	 * @param s1
	 * @param s2
	 */
	void addAssEvent(Segment s1, Segment s2) {
		if (s1 != null && s2 != null) {
			Event e = new Event("intersect", s1, s2, s1.intersect(s2));
			if (e.eventPt != null) {
				eqT.add(e);
			}
		}
	}

	
}
