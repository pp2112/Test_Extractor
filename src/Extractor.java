import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Extractor {
	
	public Tripple<Integer,Pair<Double,Double>,Double> ExtractAndPopulate(Integer game_number) throws IOException {
		String address = "http://www.tennisexplorer.com/match-detail/?id="+game_number;
		URL url = new URL(address);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		List<String> results1 = final_matcher1(sb.toString());
		List<String> results2 = final_matcher2(sb.toString());
		if(results1.size() == 0 || results2.size() == 0){
			return null;
		}
		//System.out.println(results1.size() + " Number");		
		double player1_odds = extract_vals(results1.get(0));
		double player2_odds = extract_vals(results2.get(0));
		
		Double canBet = compute_bettable(player1_odds,player2_odds);
		Tripple<Integer,Pair<Double,Double>,Double> result = new Tripple<Integer,Pair<Double,Double>,Double>(game_number,new Pair<Double,Double>(player1_odds,player2_odds),canBet);
		return result;		
		
	}	
	
	public Double compute_bettable(double player1_odds, double player2_odds) {
		
		double player1_return = 0;
		double player2_return = 0;
		double res = 0;
		
		if(player1_odds >= player2_odds){
			player1_return = (player1_odds - 1)*100;
			player2_return = (player2_odds - 1)*(player1_return-33);
			res = player2_return-100;
		}else{
			player2_return = (player2_odds - 1)*100;
			player1_return = (player1_odds - 1)*(player2_return-33);
			res = player1_return-100;
			
		}		
		return (double) Math.round(res);
	
	}

	private Double extract_vals(String input_str){
		Pattern p1 = Pattern.compile("[0-9].[0-9][0-9]");
		Matcher m1 = p1.matcher(input_str);
		Double res = null;
		while(m1.find()){
			res = Double.parseDouble(m1.group());
		}
		return res;
	}
	
	private List<String> final_matcher1(String html_body) {
		List<String> result = new ArrayList<String>();		
		String s = "";
		
		Pattern p3 = Pattern.compile("<td class=\"k1 best-betrate\">[0-9].[0-9][0-9]</td>");
		Matcher m3 = p3.matcher(html_body);		
		while (m3.find()) {
			s = m3.group();
			result.add(s.replaceFirst("/product-reviews/",""));
		}
		
		return result;
	}
	
	private List<String> final_matcher2(String html_body) {
		List<String> result = new ArrayList<String>();
		String s = "";
		
		Pattern p3 = Pattern.compile("<td class=\"k2 best-betrate\">[0-9].[0-9][0-9]</td>");
		Matcher m3 = p3.matcher(html_body);		
		while (m3.find()) {
			s = m3.group();
			result.add(s.replaceFirst("/product-reviews/",""));
		}
		
		return result;
	}
	
	public List<Integer> getall_matches() throws IOException{		
		URL url = new URL("http://www.tennisexplorer.com/next/?year=2014&month=06&day=18");
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}		
		List<String> result = new ArrayList<String>();
		String s = "";		
		List<Integer> final_result = new ArrayList<Integer>();
		
		Pattern p3 = Pattern.compile("<a href=\"/match-detail/\\?id=[0-9][0-9][0-9][0-9][0-9][0-9][0-9]\" title=\"Click for match detail\">info</a>");
		Matcher m3 = p3.matcher(sb.toString());	
		
		while (m3.find()) {
			s = m3.group();
			result.add(s);
		}
		Pattern p4 = Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
		Matcher m4 = p4.matcher(result.toString());
		
		while(m4.find()){
			final_result.add(Integer.parseInt(m4.group()));
		}		
		//System.out.println(final_result);		
		return final_result;
	}

	
//359  
}
