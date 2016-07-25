import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This class implements a brute force method for calculating intersection points.
 * @author Shubham Saxena
 * 
 */

public class BruteForce {

	public static void main(String[] args) throws IOException {

		File input = new File(args[0]);
		Scanner sc = new Scanner(input);
		BruteForce bf = new BruteForce();
		int n = Integer.parseInt(sc.next());
		Segment[] in = new Segment[n];

		System.out.println(n);
		//taking input
		for (int i = 0; i < n; i++) {
			in[i] = new Segment(new Point(Integer.parseInt(sc.next()),
					Integer.parseInt(sc.next())), new Point(Integer.parseInt(sc
					.next()), Integer.parseInt(sc.next())));
			System.out.println(in[i]);
		}

		HashSet<Point> out = new HashSet<Point>(); //so that duplicates don't get added.

		for (int i = 0; i < in.length; i++) {
			for (int j = i + 1; j < in.length - 1; j++) {
				if (in[i].intersect( in[j]) != null) {
					out.add(in[i].intersect( in[j]));
				}
			}
		}

		//printing to file
		PrintWriter writer = new PrintWriter("outputBF.txt", "UTF-8");
		writer.println(out.size());

		for (Point p : out) {
			writer.println(p.toString());
		}
		System.out.println(out);
		writer.close();
		sc.close();

	}

	
	
}
