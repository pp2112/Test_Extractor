import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		Extractor extract = new Extractor();
		Set<Tripple<Integer,Pair<Double,Double>,Double>> track_set = new HashSet<Tripple<Integer,Pair<Double,Double>,Double>>();
		
		System.out.println(extract.compute_bettable(6.12,1.28));
		
		while(true){
			List<Integer> matches = extract.getall_matches();
			//System.out.println(matches.size());
			for (Integer k : matches) {
				Tripple<Integer, Pair<Double, Double>, Double> result = extract.ExtractAndPopulate(k);
				if (result != null && result.getZ() >= 5) {
					track_set.add(result);
					/*Integer reference = result.getX();
					Pair<Double, Double> odds = result.getY();
					Double isBettable = result.getZ();

					System.out.println(reference);
					System.out.println(odds);
					System.out.println(isBettable);
					System.out.println();*/
				}
			}
			System.out.println();
			System.out.println(track_set);
			track_set.clear();
			Thread.sleep(60000);			
		}
	}

}
