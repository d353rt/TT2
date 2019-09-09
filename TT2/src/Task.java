import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//JSOUP is a HTML parser library for Java
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element; 

public class Task {
	private static final String DOMAIN_NAME_PATTERN = "(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z0-9][a-z0-9-]{0,61}[a-z0-9]";

	public static void main(String[] args) throws IOException {
			
		// URL input in the console
		// alternative:
		// String givenURL = "http://faz.net";
		String givenURL = EnterURL();
		
		//Output the computed results
		OutputResults(CountLinkedDomains(givenURL));
	}

	private static HashMap<String, Integer> CountLinkedDomains(String URL) throws IOException {
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		}
		catch (IllegalArgumentException e) {
			System.out.println("The given URL is not valid, maybe the protocol is missing?");
			System.exit(1);
		}
		catch (UnknownHostException e) {
			System.out.println("The given URL can not be reached");
			System.exit(2);
		}
		List <Element> link = doc.select("a");
		HashMap<String, Integer> DomainCountMap = new HashMap<String, Integer>();
		String absoluteHref = "";
		Pattern domain = Pattern.compile(DOMAIN_NAME_PATTERN);
		Matcher matcher = null;
		String matchString = "";
		
		for(Element el: link) {	
			absoluteHref = el.attr("abs:href");
			matcher = domain.matcher(absoluteHref);
            if (matcher.find())
			{ 
            	matchString = matcher.group(0);
            	if(DomainCountMap.containsKey(matchString)) {
            		DomainCountMap.put(matchString, DomainCountMap.get(matchString) + 1); 
            	}
            	else {
            		DomainCountMap.put(matchString, 1); 
            	}
			}
		}
		return DomainCountMap;
	}
	
	private static String EnterURL() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a URL: ");
		return br.readLine();
	}
	
	private static void OutputResults(HashMap<String, Integer> DomainCountMap){
		System.out.println("\n" + "Output: " );
		if (DomainCountMap.size() == 0) {
			System.out.println("No linked domains found");
		}
		else {
			for(@SuppressWarnings("rawtypes") Map.Entry entry : DomainCountMap.entrySet()){
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
		}
	}
}