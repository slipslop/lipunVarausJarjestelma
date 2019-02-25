import java.sql.SQLException;
import java.util.Scanner;


 




/*
 * T‰m‰n luokan tarkoitus on hoitaa ohjelman peruslogiikka. Eli hoitaa k‰ytt‰j‰n syˆtˆt.
 * Jos ohjelma ei syyst‰ taikka toisesta toimi, voit tarkistaa SQLhandler luokassa, onko CONNECTION_STRING muuttujat oikein.
 */

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		
		//T‰ss‰ initialisoidaan LippuHandlerista ja SQLhandlerista uudet oliot.
		LippuHandler lh = new LippuHandler();
		SQLhandler sqlHandler = new SQLhandler();
		
		
		//Hyvin perus while loop, jonka sis‰ll‰ switch-statementteja.
		while(true) {
			
			
			System.out.println("Mit‰ haluaisit tehd‰? ");
			
			
			printOhjeet();
			
			
			int valinta = sc.nextInt();
			sc.nextLine();
			
			switch(valinta) {
			case 1:
				System.out.println("Anna elokuvan nimi: ");
				String nimi = sc.nextLine();
				System.out.println("Anna p‰iv‰m‰‰r‰: ");
				String pvm = sc.nextLine();
				System.out.println("Anna paikan koodi: ");
				String paikka = sc.nextLine();
				
				PerusLippu pl = new PerusLippu(nimi, pvm, paikka);
				lh.lisaaPerusLippu(pl);
			break;
			
			case 2:
				System.out.println("Anna elokuvan nimi: ");
				nimi = sc.nextLine();
				System.out.println("Anna p‰iv‰m‰‰r‰: ");
				pvm = sc.nextLine();
				System.out.println("Anna paikan koodi: ");
				paikka = sc.nextLine();
				
				
				VipLippu vl = new VipLippu(nimi, pvm, paikka);
				lh.lisaaVipLippu(vl);
			break;
			
			case 3:
				System.out.println("Talletetaan j‰rjestelm‰‰n...");
				sqlHandler.luoTable();
				lh.kirjaaPerusLippu();
				lh.kirjaaVipLippu();
			break;
			
			case 4:
				System.out.println("Haluatko varata: ");
				System.out.println("1 : Peruslippuja ");
				System.out.println("2 : Viplippuja");
				int perusVaiVip = sc.nextInt();
				sc.nextLine();
				switch(perusVaiVip) {
				
				case 1:
					if(sqlHandler.tulostaVaraamattomatLiput("Perus")) {
						System.out.println("Kirjoita elokuvan nimi: ");
						nimi = sc.nextLine();
						System.out.println("Kirjota p‰iv‰m‰‰r‰: ");
						pvm = sc.nextLine();
						System.out.println("Kirjoita paikan koodi: ");
						paikka = sc.nextLine();
						sqlHandler.varaaPerusLippu(nimi, pvm, paikka);
						break;
					}else {
						break;
					}
					
				
					
					
			
				case 2:
					if(sqlHandler.tulostaVaraamattomatLiput("Vip")) {
						System.out.println("Kirjoita elokuvan nimi: ");
						nimi = sc.nextLine();
						System.out.println("Kirjota p‰iv‰m‰‰r‰: ");
						pvm = sc.nextLine();
						System.out.println("Kirjoita paikan koodi: ");
						paikka = sc.nextLine();
						sqlHandler.varaaVipLippu(nimi, pvm, paikka);
						break;
					}else {
						break;
					}
					
				}
				break;
		
			case 5:
				sqlHandler.tulostaVarattuLippu();
				break;
			
				
			case 6:
				System.out.println("Haluatko peruuttaa: ");
				System.out.println("1 : Peruslippun varauksen ");
				System.out.println("2 : Viplipun varauksen");
				perusVaiVip = sc.nextInt();
				sc.nextLine();
				switch(perusVaiVip) {
				
				case 1:
					//Jos tulostaVarattuLippu("Perus") = true;
					if(sqlHandler.tulostaVarattuLippu("Perus")) {
						System.out.println("Kirjoita elokuvan nimi: ");
						nimi = sc.nextLine();
						System.out.println("Kirjota p‰iv‰m‰‰r‰: ");
						pvm = sc.nextLine();
						System.out.println("Kirjoita paikan koodi: ");
						paikka = sc.nextLine();
					
					
						sqlHandler.poistaPeruslipunVaraus(nimi, pvm, paikka);
						break;
					}else {
						break;
					}
					
				case 2:
					//Jos tulostaVarattuLippu("Vip") = true;
					if(sqlHandler.tulostaVarattuLippu("Vip")) {
						System.out.println("Kirjoita elokuvan nimi: ");
					nimi = sc.nextLine();
					System.out.println("Kirjota p‰iv‰m‰‰r‰: ");
					pvm = sc.nextLine();
					System.out.println("Kirjoita paikan koodi: ");
					paikka = sc.nextLine();
				
				
					sqlHandler.poistaViplipunVaraus(nimi, pvm, paikka);
					break;
					}else {
						break;
					}
					
				}
				break;
			
				
				
			case 7:
				System.out.println("Haluatko: ");
				System.out.println("1. Poista kaikki perusliput");
				System.out.println("2. Poista kaikki vipliput");
				System.out.println("3. Poista tietty peruslippu");
				System.out.println("4. Poista tietty viplippu");
				perusVaiVip = sc.nextInt();
				sc.nextLine();
				switch(perusVaiVip) {
				case 1:
					sqlHandler.poistaKaikkiLiput("Perus");
					break;
				case 2:
					sqlHandler.poistaKaikkiLiput("Vip");
					break;
				case 3:
					if(sqlHandler.tulostaKaikkiPerusLiput()) {
						System.out.println("Kirjoita elokuvan nimi: ");
					nimi = sc.nextLine();
					System.out.println("Kirjoita p‰iv‰m‰‰r‰: ");
					pvm = sc.nextLine();
					System.out.println("Kirjoita paikan koodi: ");
					paikka = sc.nextLine();
					sqlHandler.poistaLippu("Perus", nimi, pvm, paikka);
					break;
					}else{
						break;
					}
					
				case 4:
					if(sqlHandler.tulostaKaikkiVipLiput()) {
						System.out.println("Kirjoita elokuvan nimi: ");
						nimi = sc.nextLine();
						System.out.println("Kirjoita p‰iv‰m‰‰r‰: ");
						pvm = sc.nextLine();
						System.out.println("Kirjoita paikan koodi: ");
						paikka = sc.nextLine();
						sqlHandler.poistaLippu("Vip", nimi, pvm, paikka);
						break;
					}else {
						break;
					}
					
				}
				break;
			//poistu while-loopista.
			case 8:
				System.exit(0);
				break;
		}
		
		}	
		
	}
	//T‰m‰n metodin tarkoitus on tulostaan ohjeet k‰ytt‰j‰lle.
	private static void printOhjeet() {
		System.out.println("1. Luo uusi peruslippu");
		System.out.println("2. Luo uusi viplippu");
		System.out.println("3. Tallenna liput j‰rjestelm‰‰n(Teht‰v‰ ennen kuin voi varata)");
		System.out.println("4. Varaa lippuja");
		System.out.println("5. Tulosta varatut liput");
		System.out.println("6. Poista varauksia");
		System.out.println("7. Poista lippuja");
		System.out.println("8. Poistua");
		
		
		
		
	}

}
