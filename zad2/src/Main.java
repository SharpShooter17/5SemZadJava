import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Napisz program ³¹cz¹cy siê ze stron¹ podan¹ jako argument wywo³ania programu i wypisuj¹cy wszystkie znalezione w niej linki i adresy email.
 * Wykorzystuj¹c w tym celu wyra¿enia regularne (java.util.regex).
 * Oprócz tego program ma zapisaæ do pliku wszystkie parametry po³¹czenia, adres IP komputera na którym znajduje siê strona oraz nag³ówek strony (sekcja <head>)
 * */

public class Main {
	private static String help(){
		return new String("java Main http://www.example.com");
	}
	
	public static List<String> getSubstrings(String input, String pattern){
		List<String> result = new ArrayList<String>();
		Pattern p = Pattern.compile(pattern, Pattern.UNICODE_CASE | Pattern.DOTALL | Pattern.UNIX_LINES | Pattern.MULTILINE);
		Matcher match = null;
		int start = 0;
		while ( (match = p.matcher(input)).find(start) ){
			result.add(input.substring(match.start(), match.end()));
			start = match.end();
		}
		
		return result;
	}
	
	public static void printList(List<String> list){
		for (String string : list) {
			System.out.println(string);
		}
	}

	public static void main(String args[]){
		
		if (args.length != 1){
			System.out.printf(help());
			return;
		}
		
		URL url = null;
		
		try {
			url = new URL(args[0]);
		} catch (MalformedURLException e){
			System.out.printf(e.getMessage());
			return;
		}
		
		String content = new String();
		
		try {
			InputStream urlStream = url.openStream();
			
			char ch;
			
			while ( (byte)(ch = (char) urlStream.read()) != -1 ){
				content += ch;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> links = getSubstrings(content, "\\b(http|https|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
		List<String> emails = getSubstrings(content, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
		List<String> head = getSubstrings(content, "\\<head\\>.+\\</head\\>");

		printList(links);
		printList(emails);
		printList(head);
		
		try {
			PrintWriter print = new PrintWriter("output.txt");
			for (String string : links) {
				print.println(string);
			}
			for (String string : emails) {
				print.println(string);
			}
			print.println(InetAddress.getByName(url.getHost()).getHostAddress());
			if (!head.isEmpty()){
				print.println(head.get(0));
			}
			print.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return;
	}
}
