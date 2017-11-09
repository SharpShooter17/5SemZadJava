/*****************************************************************************************************************************************
 * Aplikacja obslugujaca bank czasu. Klient zglasza (i wycofuje) uslugi jakie moze wykonac i ich terminy, 
 * moze tez zarzadac wyswietlenia wszystkich dostepnych uslug w banku i zarezerwowac sobie wybrana usluge. 
 * Serwer rozsyla komunikaty o nowych, zarezerwowanych, niewykorzystanych i wycofanych uslugach i terminach do wszystkich klientow. 
 * Nalezy zadbac o odpowiednia synchronizacje dostepu do zasobow i ich aktualizacje.
 * 
 * Klient mo¿e dodaæ us³ugê.
 * Klient mo¿e zarezerwowaæ us³ugê.
 * W³aœciciel mo¿e usun¹æ us³ugê.
 * Klient mo¿e anulowaæ rezerwacjê.
 * Serwer wyœwietla dostêpne us³ugi
 * Serwer wyœwietla wolne us³ugi
 *****************************************************************************************************************************************/

public class Main {
	public static void main(String args[]){
		new Server().run();
		return;
	}
}
