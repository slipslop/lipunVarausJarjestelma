import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


//T‰t‰ luokkaa k‰ytet‰‰n p‰‰asiassa uusien lippujen tallettamisessa ArrayListiin.



public class LippuHandler {

	//Jokaiselle lipputyypille oma ArrayList, joka hyv‰ksyy ainoastaan tietyn tyyppisi‰ lippuja.
	
	ArrayList<VipLippu> vipLiput = new ArrayList<>();
	ArrayList<PerusLippu> perusLiput = new ArrayList<>();
	
	SQLhandler sqlHandler = new SQLhandler();
	
	
	//Lis‰‰ peruslipun listaan
		public void lisaaPerusLippu(PerusLippu pl) {
		
			String pvm = pl.getPaivamaara();
			String paikka = pl.getPaikka();
			String nimi = pl.getNimi();
			
			int count = 0;
				
			//1. ota nykyinen lippu
			//2. vertaa sit‰ jokaiseen jo ArrayListiin tallennettuul lippuun
			//3. jos lˆytyy identtinen, heivaa nykyinen menem‰‰n, jos ei niin lis‰‰ listaan.
			//4. toista for profit
			
			//Eli t‰h‰n on luotu algoritmi, jolla voidaan todeta, onko lippu uniikki, eli onko joko tai sen nimi,istumapaikka ja pvm uniikkeja.
			
			
			for(int i = 0; i < perusLiput.size();i++) {
				PerusLippu tl = perusLiput.get(i);
				if(tl.getNimi().contains(nimi) && tl.getPaikka().contains(paikka) && tl.getPaivamaara().contains(pvm)){
					//Eli jos lˆytyy identtinen, lis‰t‰‰n laskuriin 1
					count++;
				}
				
			}
			
			
			if(count > 0) {
				System.out.println("Peruslippua ei voitu lis‰t‰!");
				//Ei lis‰t‰ jos lˆytyy samoja
			}else {
				//Muutoin lis‰t‰‰n
				perusLiput.add(pl);
				System.out.println("Lis‰tty peruslippu");
			}
			
			
			
			
			
			}
		
	
	//Lis‰‰ viplipun listaan.
	public void lisaaVipLippu(VipLippu vl) {
		

		String pvm = vl.getPaivamaara();
		String paikka = vl.getPaikka();
		String nimi = vl.getNimi();
		
		int count = 0;
			
		//1. ota nykyinen lippu
		//2. vertaa sit‰ jokaiseen jo ArrayListiin tallennettuul lippuun
		//3. jos lˆytyy identtinen, heivaa nykyinen menem‰‰n, jos ei niin lis‰‰ listaan.
		//4. toista for profit
		
		//Eli t‰h‰n on luotu algoritmi, jolla voidaan todeta, onko lippu uniikki, eli onko joko tai sen nimi,istumapaikka ja pvm uniikkeja.
		
		
		for(int i = 0; i < vipLiput.size();i++) {
			VipLippu tl = vipLiput.get(i);
			if(tl.getNimi().contains(nimi) && tl.getPaikka().contains(paikka) && tl.getPaivamaara().contains(pvm)){
				//Eli jos lˆytyy identtinen, lis‰t‰‰n laskuriin 1
				count++;
			}
			
		}
		
		
		if(count > 0) {
			System.out.println("Viplippua ei voitu lis‰t‰!");
			//Ei lis‰t‰ jos lˆytyy samoja
		}else {
			//Muutoin lis‰t‰‰n
			vipLiput.add(vl);
			System.out.println("Lis‰tty uusi viplippu");
		}
		
		
	}
	
	//T‰m‰ metodi k‰y jokaisen lipun l‰pi, joka on listassa. Se tallettaa muuttujiin nimi, pvm ja paikka tietyt arvot, jonka j‰lkeen se kutsuu metodia
	//talletaPerusLippu(), niill‰ arvoilla. T‰m‰n j‰lkeen liput on telletettu tietokantaan.
	
	public void kirjaaPerusLippu() throws SQLException {
		
		for(int i = 0; i < perusLiput.size();i++) {
			PerusLippu l = perusLiput.get(i);
			
			String nimi = l.getNimi();
			String pvm = l.getPaivamaara();
			String paikka = l.getPaikka();
			
			sqlHandler.talletaPerusLippu(nimi, pvm, paikka);
			
		}
		
	}
	
	//k‰yt‰nnˆss‰ sama kuin kirjaaPerusLippu();
	public void kirjaaVipLippu() throws SQLException {
		
		for(int i = 0; i < vipLiput.size();i++) {
			VipLippu l = vipLiput.get(i);
			
			String nimi = l.getNimi();
			String pvm = l.getPaivamaara();
			String paikka = l.getPaikka();
			
			sqlHandler.talletaVipLippu(nimi, pvm, paikka);
			
		}
		
	}
	

	
	
	
	
	
	
	
}
