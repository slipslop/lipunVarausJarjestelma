
//Luokka peruslippu:
//Sellainen lippu, jonka normaalisti ostat, esim. elokuviin mennessä. Ei siis esim. opiskelija, varusmies tai eläkeläisalennusta.


//Implementoidaan ILippu rajapintaa.
public class PerusLippu implements ILippu{

	String elokuvanNimi;
	String paivaMaara;
	String paikka;
	
	
	public PerusLippu(String elokuvanNimi, String paivaMaara, String paikka) {
		this.elokuvanNimi = elokuvanNimi;
		this.paivaMaara = paivaMaara;
		this.paikka = paikka;
	}
	
	//Getteris ja setterit.
	public String getNimi() {
		return elokuvanNimi;
		
	}


	
	public String getPaivamaara() {
		return paivaMaara;
	}


	public String getPaikka() {
		return paikka;
	}

	
	
	
}
