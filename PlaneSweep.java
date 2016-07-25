import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class implements the Plane Sweep Algorithm to calculate the intersection
 * points of line segments
 * 
 * @author Shubham Saxena
 * 
 */
public class PlaneSweep {

	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {

		// input for segments
		File input = new File(args[0]);
		Scanner sc = new Scanner(input);

		PlaneSweep ps = new PlaneSweep();
		int n = Integer.parseInt(sc.next());
		Segment[] in = new Segment[n]; // input segment array
		System.out.println(n);

		for (int i = 0; i < n; i++) {
			in[i] = new Segment(Integer.parseInt(sc.next()),
					Integer.parseInt(sc.next()), Integer.parseInt(sc
					.next()), Integer.parseInt(sc.next()));
			System.out.println(in[i]);
		}

		
		ArrayList<Point> out = ps.intersections(in);

		System.out.println("number of intersections: " + out.size());
		/*
		 * for (int i = 0; i < out.size(); i++) { System.out.println(out); }
		 */
		// printing to file
		PrintWriter writer = new PrintWriter("outputPlaneSweep.txt", "UTF-8");
		writer.println(out.size());

		for (Point p : out) {
			writer.println(p.toString());
		}

		writer.close();
		sc.close();
	}

	/**
	 * Calculates the intersection of all segments
	 * 
	 * @param in
	 * @return
	 */
	ArrayList<Point> intersections(Segment[] in) {
		ArrayList<Point> out = new ArrayList<Point>();

		EventQ eq = new EventQ();

		for (int i = 0; i < in.length; i++) {
			Event eS = new Event("start", in[i], null, in[i].a);
			Event eE = new Event("end", in[i], null, in[i].b);
			
			eq.insert(eS);
			eq.insert(eE);
		}

		SweepLineStatus sls = new SweepLineStatus();
		System.out.println(eq.eqT.size());
		System.out.println(eq.eqT);
		while (!eq.eqT.isEmpty()) {
			System.out.println(eq.eqT.size());
			Event e = eq.extract();
			if (e != null) {
				if (e.type.equals("start")) {
					System.out.println("a");
					sls.insert(e.s1); // put segment in sweepline Status
					Segment abv = sls.above(e.s1);
					Segment below = sls.below(e.s1);

					eq.removeAssEvent(abv, below); // remove event e
					if (abv != null) {
						if (e.s1.intersect(abv) != null) {
							System.out.println("1s");
							eq.insert(new Event("intersect", e.s1, abv, e.s1
									.intersect(abv)));
						}
					}
					if (below != null) {
						if (e.s1.intersect(below) != null) {
							System.out.println("2s");
							eq.insert(new Event("intersect", e.s1, below, e.s1
									.intersect(abv)));
						}
					}
				}

				else if (e.type.equals("end")) {
					System.out.println("b");
					Segment abv = sls.above(e.s1);
					Segment below = sls.below(e.s1);
					sls.delete(e.s1);
					eq.addAssEvent(abv, below);
				}

				else if (e.type.equals("intersect")) {
					System.out.println("c");
					out.add(e.eventPt);
					out.add(e.s1.intersect(sls.below(e.s1)));
					System.out.println("here"
							+ e.s1.intersect(sls.below(e.s1)));
					eq.eqT.remove(e);
					//sls.swap(e.s1, e.s2);
				}
			}
			System.out.println("event: "+eq.eqT);
			System.out.println();
			System.out.println("status: "+sls.slsT);
			System.out.println("inter: "+out);
		}

		return out;
	}
}
