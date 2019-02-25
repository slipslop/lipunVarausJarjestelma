import java.util.ArrayList;


//vipLippu on lippu, jolla saa esim. erikoispalveluja tai paremman istumapaikan jne, jne.



//implementoidaan ILippu rajapintaa.
public class VipLippu implements ILippu {

	String elokuvanNimi;
	String paivaMaara;
	String paikka;
	//ArrayList<VipLippu> vipLiput = new ArrayList<>();
	
	public VipLippu(String elokuvanNimi, String paivaMaara, String paikka) {
		
		this.elokuvanNimi = elokuvanNimi;
		this.paikka = paikka;
		this.paivaMaara = paivaMaara;
	}

	//Ja getterit ja setterit
	
	
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
