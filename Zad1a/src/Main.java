import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*Napisz program, który pobiera listê plików z lini poleceñ i wyœwietla liczbê wierszy ka¿dego z nich.
 * Program powinien utwo¿yæ jeden w¹tek dla ka¿dego z plików i u¿yæ tych w¹tków do zliczania liczby wierszy ka¿dego pliku jednoczeœnie.
 * Utwórz wersje programu, która odczytuje pliki nie jednoczeœnie a sekwencyjnie.
 * Porównaj wydajnoœæ wielow¹tkowego i jednow¹tkowego programu u¿ywaj¹c: System. currentTimeMillis do okreœlenie czasu wykonania.
 * Porównania dokonaj dla dówch trzech i piêciu plików.
 */

class LiczWatek extends Thread {
	File f;
	
	public LiczWatek(File f){
		this.f = f;
	}
	
	
	public void run(){
		try {
			System.out.println(f.getAbsolutePath() + ": " + Main.licz(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

public class Main {
	
	private static boolean fileExists(String path){
		File f = new File(path);
		if (f.exists() && !f.isDirectory())
			return true;
		else return false;
	}
	private static String help(){
		return new String( "java Main [drive:][path][filename]\n\n-help\tHelp" );
	}
	
	public static int licz(File f) throws FileNotFoundException{
		int result = 0;
		
		Scanner scaner = new Scanner(f);
		
		while (scaner.hasNextLine()){
			result++;
			scaner.nextLine();
		}
		
		scaner.close();
		
		return result;
	}
	
	private static void wyniki(List<String> files) throws FileNotFoundException{
		
		for (int i = 0; i < files.size(); i++){
			System.out.println(files.get(i) + ": " + licz( new File (files.get(i))));
		}
		
	}
	
	private static void wynikiWatki(List<String> files) throws FileNotFoundException, InterruptedException {
		List<LiczWatek> watki = new ArrayList<LiczWatek>();
		for (int i = 0; i < files.size(); i++){
			LiczWatek watek = new LiczWatek(new File (files.get(i)));
			watki.add(watek);
			watek.start();
		}
		
		for (LiczWatek liczWatek : watki) {
			liczWatek.join();
		}		
	}
	
	public static void main(String[] args) {
		
		if (args.length == 0){
			System.out.println(help());
			return;
		}
		
		if (args.length == 1){
			if (args[0].equals("-help")){
				System.out.println(help());
				return;
			}
		}
		
		List<String> pliki = new ArrayList<String>();
		for (int i = 0; i < args.length; i++){
			if (fileExists(args[i])){
				pliki.add(args[i]);
			}
			else {
				System.out.println("Niepoprawna œcie¿ka do pliku: " + args[i]);
				return;
			}
		}
		
		double time = System.currentTimeMillis();
		
		try {
			wyniki(pliki);
			//wynikiWatki(pliki);
		}	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		time = System.currentTimeMillis() - time;
		
		System.out.print("Czas wykonania programu: " + time + "ms");
		
		return;
	}
}
