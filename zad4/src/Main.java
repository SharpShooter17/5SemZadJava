/*****************************************************************************************************************************************
 * Aplikacja obslugujaca bank czasu. Klient zglasza (i wycofuje) uslugi jakie moze wykonac i ich terminy, 
 * moze tez zarzadac wyswietlenia wszystkich dostepnych uslug w banku i zarezerwowac sobie wybrana usluge. 
 * Serwer rozsyla komunikaty o nowych, zarezerwowanych, niewykorzystanych i wycofanych uslugach i terminach do wszystkich klientow. 
 * Nalezy zadbac o odpowiednia synchronizacje dostepu do zasobow i ich aktualizacje.
 * 
 * Klient mo�e doda� us�ug�.
 * Klient mo�e zarezerwowa� us�ug�.
 * W�a�ciciel mo�e usun�� us�ug�.
 * Klient mo�e anulowa� rezerwacj�.
 * Serwer wy�wietla dost�pne us�ugi
 * Serwer wy�wietla wolne us�ugi
 *****************************************************************************************************************************************/

public class Main {
	public static void main(String args[]){
		new Server().run();
		return;
	}
}
