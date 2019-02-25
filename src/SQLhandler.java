import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//Tästä luokasta tuli melko pitkä, mutta lyhyesti, se hoitaa kaiken tietokantaan kirjoittamisen, lukemisen, poistamisen, tiedon keruun jne.

public class SQLhandler {

	
	
	
	//Tässä esitellään muuttujia, joilla on helppo tehdä SQL queryja. Eli jos esimerkiksi haluaa muuttaa
	//Tablen nimen, voi sen tehdä tässä, eikä silloin tarvitse muuttaa mitään muuta.
	
	private static final String DB_NAME_PERUS = "perusLiput.db";
	private static final String DB_NAME_VIP = "vipLiput.db";
	
	//Jokaisen käyttäjän täytyy erikseen muokata tätä muuttujaa, jotta ohjelma toimisi. Eli polkua täytyy muokata
																	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<< tätä osiota, DB_NAME_PERUS ja VIP voi jättää niinkuin ne on
	private static final String CONNECTION_STRING_PERUS = "jdbc:sqlite:C:\\Users\\vilit\\Desktop\\JavaWorkspace\\Varausjärjestelmä\\"+DB_NAME_PERUS;
	private static final String CONNECTION_STRING_VIP = "jdbc:sqlite:C:\\Users\\vilit\\Desktop\\JavaWorkspace\\Varausjärjestelmä\\"+DB_NAME_VIP;
	
	private static final String TABLE_PERUSLIPUT = "Perusliput";
	private static final String TABLE_VIPLIPUT = "Vipliput";
	
	private static final String COLUMN_NIMI = "Nimi";
	private static final String COLUMN_PVM = "Pvm";
	private static final String COLUMN_PAIKKA = "Paikka";
	private static final String COLUMN_VARATTU= "Varattu";
	
	
	
	
	
	
	//Tämä metodi luo tablet Perusliput ja Vipliput. Ja ainoastaan silloin, jos niitä ei vielä ole olemassa
	public void luoTable() throws SQLException{
		
		
		
		try 
		{
			//Tässä esitellään kaikille lipuille omat Connection muuttujat.
			Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
			
			//Sekä Statement muuttujat
			Statement statementPerus = connPerus.createStatement();
	
			
			
			statementPerus.execute("CREATE TABLE IF NOT EXISTS " + TABLE_PERUSLIPUT +
					" (" + COLUMN_NIMI + " text, " + COLUMN_PVM + " text, " + COLUMN_PAIKKA + 
					" text, "+ COLUMN_VARATTU + " text " +")");
			
			}	
			catch (SQLException e)
			{
				System.out.println("Something went wrong " + e.getMessage());
			}
		
		try 
		{
			//Tässä esitellään kaikille lipuille omat Connection muuttujat.
			
			Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
			//Sekä Statement muuttujat
			
			Statement statementVip = connVip.createStatement();
			
			
			statementVip.execute("CREATE TABLE IF NOT EXISTS " + TABLE_VIPLIPUT +
					" (" + COLUMN_NIMI + " text, " + COLUMN_PVM + " text, " + COLUMN_PAIKKA + 
					" text, "+ COLUMN_VARATTU + " text "+")");
			
			
			
		}	
		catch (SQLException e)
		{
			System.out.println("Something went wrong " + e.getMessage());
		}
			
	
		
	
	
	
}
	
//Tämän metodin tarkoituksena on tallettaa sille annettu lippu tietokantaan.
	//Tätä metodia kutsutaan LippuHandler luokasta, sillä liput talletetaan aivan ensiksi
	//Arraylistiin, josta aivan aluksi luetaan tiedot, jotka syötetään tämän metodin arvoiksi. (nimi,pvm,paikka)
	public void talletaPerusLippu(String nimi, String pvm, String paikka) throws SQLException {
		
		
		try {
			Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
		
		Statement statementPerus = connPerus.createStatement();
		
		
		statementPerus.execute("INSERT INTO " + TABLE_PERUSLIPUT + " (" + COLUMN_NIMI + ", " +
				COLUMN_PVM+ ", " + COLUMN_PAIKKA + ", " + COLUMN_VARATTU + " ) "+ "VALUES ('" + nimi + "' , '"+pvm+ "' , '" + paikka + "' , '" + "EI" + "')");
		
		System.out.println("Perusliput elokuvaan " + nimi +"  talletettu");
		}catch(SQLException e) {
			System.out.println("Jokin meni pieleen ( " + e.getMessage());
		}
		
		
		
	}
	
	//Tämä metodi on käytännössä sama kuin talletaPerusLippu();
	public void talletaVipLippu(String nimi, String pvm, String paikka) throws SQLException {
		try {
			Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
		
		Statement statementVip = connVip.createStatement();
		
		
		statementVip.execute("INSERT INTO " + TABLE_VIPLIPUT + " (" + COLUMN_NIMI + ", " +
				COLUMN_PVM+ ", " + COLUMN_PAIKKA + ", " + COLUMN_VARATTU + " ) "+ "VALUES ('" + nimi + "' , '"+pvm+ "' , '" + paikka + "' , '" + "EI" + "')");
		
		System.out.println("Vipliput elokuvaan " + nimi + " talletettu ");
		
		}catch(SQLException e) {
			System.out.println("Jotain meni pieleen! " + e.getMessage());
		}
		
	}
	
	//Tämä metodi yksinkertaisesti muuttaa sille annetun lipun "Varattu" sarakkeen arvon 'kyllä'
	public void varaaPerusLippu(String nimi, String pvm, String paikka) throws SQLException {
		
		try {
			
		Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
		
		Statement statementPerus = connPerus.createStatement();
		//UPDATE Perusliput SET varattu = 'KYLLÄ' WHERE nimi = 'nimi' AND pvm = 'pvm' AND paikka = 'paikka'
		statementPerus.execute("UPDATE " + TABLE_PERUSLIPUT + " SET " + COLUMN_VARATTU + "= 'KYLLÄ' WHERE "+
											COLUMN_NIMI + "= '" + nimi+ "' AND "+ COLUMN_PVM + "= '" + pvm +"' AND " + COLUMN_PAIKKA + "= '" + paikka +"'");
		
		
		System.out.println("Varattu " + nimi);
		}catch(SQLException e) {
			System.out.println("Jokin meni pieleen! " +e.getMessage());
		}
		
		
		
	}
	
	//Tämä metodi yksinkertaisesti muuttaa sille annetun lipun "Varattu" sarakkeen arvon 'kyllä'
	public void varaaVipLippu(String nimi, String pvm, String paikka) throws SQLException {
		
		try {
			
		Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
		
		Statement statementVip = connVip.createStatement();
		
		statementVip.execute("UPDATE " + TABLE_VIPLIPUT + " SET " + COLUMN_VARATTU + "= 'KYLLÄ' WHERE "+
				COLUMN_NIMI + "= '" + nimi+ "' AND "+ COLUMN_PVM + "= '" + pvm +"' AND " + COLUMN_PAIKKA + "= '" + paikka +"'");
		
		
		System.out.println("Varattu " + nimi);
		}catch(SQLException e) {
			System.out.println("Jokin meni pieleen! ");
		}
		
		
		
	}
	
	//Tämä metodi poistaa peruslipun varauksen. Tätä metodia kutsutaan Main luokasta, josta saa argumentit.
public void poistaPeruslipunVaraus(String nimi, String pvm, String paikka) throws SQLException {
		
		try {
			
		Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
		
		Statement statementPerus = connPerus.createStatement();
		
		statementPerus.execute("UPDATE " + TABLE_PERUSLIPUT + " SET " + COLUMN_VARATTU + "= 'EI' WHERE "+
											COLUMN_NIMI + "= '" + nimi+ "' AND "+ COLUMN_PVM + "= '" + pvm +"' AND " + COLUMN_PAIKKA + "= '" + paikka +"'");
		
		
		System.out.println("Varaus poistettu " + nimi);
		}catch(SQLException e) {
			System.out.println("Jokin meni pieleen! " +e.getMessage());
		}
		
		
		
	}


//Tämä metodi poistaa viplipun varauksen
public void poistaViplipunVaraus(String nimi, String pvm, String paikka) throws SQLException {
	
	try {
		
	Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
	
	Statement statementVip = connVip.createStatement();
	//Eli käytännössä muutetaan COLUMN_VARATTU : 'EI'.
	statementVip.execute("UPDATE " + TABLE_VIPLIPUT + " SET " + COLUMN_VARATTU + "= 'EI' WHERE "+
			COLUMN_NIMI + "= '" + nimi+ "' AND "+ COLUMN_PVM + "= '" + pvm +"' AND " + COLUMN_PAIKKA + "= '" + paikka +"'");
	
	
	System.out.println("Varaus poistettu " + nimi);
	}catch(SQLException e) {
		System.out.println("Jokin meni pieleen! ");
	}
	
	
	
}


//Tämä funktio palauttaa boolean arvon, riippuen siitä, onko tietyssä Tablessa yhtään merkintää, jossa COLUMN_VARATTU = 'KYLLÄ'.
//jos ei ole, palauttaa false;
public boolean tulostaVarattuLippu(String lippuTyyppi) throws SQLException {
	
	if(lippuTyyppi == "Perus") {
		try {
			Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
			Connection connPerusCount = DriverManager.getConnection(CONNECTION_STRING_PERUS);
			
			Statement statementPerus = connPerus.createStatement();
			Statement statementPerusCount = connPerusCount.createStatement();
																//SELECT * FROM Perusliput WHERE varattu = 'KYLLÄ';
			ResultSet resultsPerus = statementPerus.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT + " WHERE " + COLUMN_VARATTU + " = 'KYLLÄ'");
			ResultSet resultsPerusCount = statementPerusCount.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT + " WHERE " + COLUMN_VARATTU + " = 'KYLLÄ'");
			//Allaolevaa algoritmia käytetään tunnistamaan, onko kyseisessä Tablessa yhtään merkintää, jolla on COLUMN_VARATTU = 'KYLLÄ'.
			//Joudutaan luomaan Connectionille, Statementille sekä ResultSetille kahdet arvot, joista toista käytetään tässä. Muuten tämä ei toimisi.
			int i = -1;
			while(resultsPerusCount.next()) {
				i++;
			}
			//System.out.println(i); //debug tarkoitukseen
			//i:n arvo on -1 alussa, ja jos Tablesta löytyy yhtäkään tulosta, (eli i >= 0) on olemassa lippuja, joista voidaan poistaa varaus.
			if(i >= 0) {
				System.out.println("NÄISTÄ LIPUISTA VOI POISTAA VARAUKSEN:\n ");
				System.out.println("Elokuva:  Pvm:     Paikka:");
				while(resultsPerus.next()) {
				System.out.println(resultsPerus.getString(COLUMN_NIMI) + "    " + resultsPerus.getString(COLUMN_PVM) + "   " +
														resultsPerus.getString(COLUMN_PAIKKA));
			}
				System.out.println();
				resultsPerus.close();
				return true;
			}else {
				//Jos ei löydy varattuja lippuja, joista poistaa varaus, tulostetaan tämä:
				System.out.println("EI VARATTUJA LIPPUJA");
			}
			
		}catch(SQLException e) {
			System.out.println("Jotain meni vikaan! " + e.getMessage());
		}
		
	}
	
	//alla oleva on käytännössä sama, kuin yllä oleva. Ainoastaan tutkitaan Vipliput tablea.
	if(lippuTyyppi == "Vip") {
	
		try {
			Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
			Connection connVipCount = DriverManager.getConnection(CONNECTION_STRING_VIP);
		
			Statement statementVip = connVip.createStatement();
			Statement statementVipCount = connVipCount.createStatement();
		
			 
			ResultSet resultsVip = statementVip.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT + " WHERE " + COLUMN_VARATTU +" = 'KYLLÄ'");
			ResultSet resultsVipCount = statementVipCount.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT + " WHERE " + COLUMN_VARATTU +" = 'KYLLÄ'");
			
			int i = -1;
			while(resultsVipCount.next()) {
				i++;
			}
			//System.out.println("iin arvo" + i ); //debug tarkoitukseen.
			if(i >= 0) {
				System.out.println("NÄISTÄ LIPUISTA VOI POISTAA VARAUKSEN :\n ");
				System.out.println("Elokuva:  Pvm:     Paikka:");
				while(resultsVip.next()) {
				System.out.println(resultsVip.getString(COLUMN_NIMI) + "    " + resultsVip.getString(COLUMN_PVM) + "  " +
														resultsVip.getString(COLUMN_PAIKKA));
			}
				System.out.println();
				resultsVip.close();
				return true;
		}else {
			System.out.println("EI VARATTUJA LIPPUJA");
		}
		
	}catch(SQLException e) {
		System.out.println("Jotain meni vikaan! " + e.getMessage());
	}
		}
		
	return false;


}


//Alla oleva funktio on käytännössä sama, kuin yllä oleva, tulostaVarattuLippu(String lippuTyyppi).
//Ainoa ero on, että tässä tarkastellaan ja tulostetaan sellaisia arvoja, joilla COLUMN_VARATTU = 'EI'.
public boolean tulostaVaraamattomatLiput(String lippuTyyppi) {
	
	
	if(lippuTyyppi == "Perus") {
		try {
			Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
			Connection connPerusCount = DriverManager.getConnection(CONNECTION_STRING_PERUS);
	
			Statement statementPerus = connPerus.createStatement();
			Statement statementPerusCount = connPerusCount.createStatement();
															//SELECT * FROM Perusliput WHERE varattu = 'KYLLÄ';
			ResultSet resultsPerus = statementPerus.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT + " WHERE " + COLUMN_VARATTU + " = 'EI'");
			ResultSet resultsPerusCount = statementPerusCount.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT + " WHERE " + COLUMN_VARATTU + " = 'EI'");
		int i = -1;
		while(resultsPerusCount.next()) {
			i++;
		}
		if(i >= 0) {
			
			System.out.println("NÄMÄ PERUSLIPUT VOI VARATA:\n ");
			System.out.println("Elokuva:  Pvm:     Paikka:");
			while(resultsPerus.next()) {
				System.out.println(resultsPerus.getString(COLUMN_NIMI) + "    " + resultsPerus.getString(COLUMN_PVM) + "   " +
														resultsPerus.getString(COLUMN_PAIKKA));
			}
			System.out.println();
			resultsPerus.close();
			return true;
		}else {
			System.out.println("EI VARATTAVIA LIPPUJA");
		}
		
	}catch(SQLException e) {
		System.out.println("Jotain meni vikaan! " + e.getMessage());
	}
	}


	
	if(lippuTyyppi == "Vip") {
		try {
			Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
			Connection connVipCount = DriverManager.getConnection(CONNECTION_STRING_VIP);
	
			Statement statementVip = connVip.createStatement();
			Statement statementVipCount = connVipCount.createStatement();
															
			ResultSet resultsVip = statementVip.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT + " WHERE " + COLUMN_VARATTU + " = 'EI'");
			ResultSet resultsVipCount = statementVipCount.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT + " WHERE " + COLUMN_VARATTU + " = 'EI'");
		int i = 0;
		while(resultsVipCount.next()) {
			i++;
		}
		if(i > 0) {
		
			
			System.out.println("NÄMÄ VIPLIPUT VOI VARATA:\n ");
			System.out.println("Elokuva:  Pvm:     Paikka:");
			while(resultsVip.next()) {
				System.out.println(resultsVip.getString(COLUMN_NIMI) + "    " + resultsVip.getString(COLUMN_PVM) + "   " +
														resultsVip.getString(COLUMN_PAIKKA));
			}
			System.out.println();
			resultsVip.close();
			return true;
		}else {
			System.out.println("EI VARATTAVIA LIPPUJA");
		}
		
	}catch(SQLException e) {
		System.out.println("Jotain meni vikaan! " + e.getMessage());
	}
	}
	return false;
		
		
	
	
}




public void poistaKaikkiLiput(String lippuTyyppi) throws SQLException {
	if(lippuTyyppi == "Perus") {
		try {
		Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
				
		Statement statementPerus = connPerus.createStatement();
		
		statementPerus.execute("DROP TABLE '" + TABLE_PERUSLIPUT + "'");
		luoTable();
		System.out.println("Kaikki perusliput poistettu!");
			
		}catch(SQLException e) {
			System.out.println("Jokin meni pieleen! " + e.getMessage());
		}
		
	}
	
	if(lippuTyyppi == "Vip") {
		try {
			Connection connVip= DriverManager.getConnection(CONNECTION_STRING_VIP);
		
		Statement statementPerus = connVip.createStatement();
		
		statementPerus.execute("DROP TABLE '" + TABLE_VIPLIPUT + "'");
		luoTable();
		System.out.println("Kaikki vipliput poistettu!");
		}catch(SQLException e) {
			System.out.println("Jotain meni pieleen! " + e.getMessage());
		}
		
	}
}


//Tämän metodin avulla voidaan poistaa tietty lippu
public void poistaLippu(String lippuTyyppi, String nimi, String pvm, String paikka) throws SQLException {
	//peruslipun poistaminen.
	if(lippuTyyppi == "Perus") {
		try {
			//Tulosta eka kaikki perusliput
			Connection connPerus = DriverManager.getConnection(CONNECTION_STRING_PERUS);
		
			Statement statementPerus = connPerus.createStatement();
	
			statementPerus.execute("DELETE FROM " + TABLE_PERUSLIPUT + " WHERE " + 
					COLUMN_NIMI + "= '" + nimi+ "' AND "+ COLUMN_PVM + "= '" + pvm +"' AND " + COLUMN_PAIKKA + "= '" + paikka +"'");
	
			System.out.println("Poistettu lippu " + nimi);

		}catch(SQLException e) {
			System.out.println("Jotain meni pieleen "+ e.getMessage());
		}
	}
	
	//Vip lipun poistaminen
	if(lippuTyyppi == "Vip") {
		try {
			//Tulosta eka kaikki vipliput		
			
			Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
		
			Statement statementVip= connVip.createStatement();
	
			statementVip.execute("DELETE FROM " + TABLE_VIPLIPUT+ " WHERE " + 
					COLUMN_NIMI + "= '" + nimi+ "' AND "+ COLUMN_PVM + "= '" + pvm +"' AND " + COLUMN_PAIKKA + "= '" + paikka +"'");
	
			System.out.println("Poistettu lippu " + nimi);
	
			}catch(SQLException e) {
				System.out.println("Jotain meni pieleen "+ e.getMessage());
			}
			
	}
		

		
	
	

}


//Tätä funktiota käytetään tulostamaan kaikki perusliput
public boolean tulostaKaikkiPerusLiput() throws SQLException {
	
	try {
		Connection connPerusCount = DriverManager.getConnection(CONNECTION_STRING_PERUS);
		Connection connPerus= DriverManager.getConnection(CONNECTION_STRING_PERUS);
		
		
		Statement statementPerus = connPerus.createStatement();
		Statement statementPerusCount = connPerusCount.createStatement();
		ResultSet resultsPerus = statementPerus.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT);
		ResultSet resultsPerusCount = statementPerusCount.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT);
		
		
		int i = -1;
		while(resultsPerusCount.next()) {
			i++;
		}
		
		if(i >= 0) {
			
			System.out.println("KAIKKI PERUSLIPUT JOTKA VOI POISTAA");
			System.out.println("Elokuva:  Pvm:     Paikka:");
			while(resultsPerus.next()) {
				System.out.println(resultsPerus.getString(COLUMN_NIMI) + "    " + resultsPerus.getString(COLUMN_PVM) + "   " +
														resultsPerus.getString(COLUMN_PAIKKA));
			}
		return true;
		}else {
			System.out.println("EI LIPPUJA!");
		}
	}catch(SQLException e) {
		System.out.println("Jotain meni pieleen " + e.getMessage());
	}
	return false;
	
	
}


//Tätä funktiota käytetään tulostamaan kaikki Vipliput
public boolean tulostaKaikkiVipLiput() throws SQLException {
	
	try {
		Connection connVipCount = DriverManager.getConnection(CONNECTION_STRING_VIP);
		Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
		
		
		Statement statementVip = connVip.createStatement();
		Statement statementVipCount = connVipCount.createStatement();
		ResultSet resultsVip = statementVip.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT);
		ResultSet resultsVipCount = statementVipCount.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT);
		
		
		int i = -1;
		while(resultsVipCount.next()) {
			i++;
		}
		
		if(i >= 0) {
			
			System.out.println("KAIKKI PERUSLIPUT JOTKA VOI POISTAA");
			System.out.println("Elokuva:  Pvm:     Paikka:");
			while(resultsVip.next()) {
				System.out.println(resultsVip.getString(COLUMN_NIMI) + "    " + resultsVip.getString(COLUMN_PVM) + "   " +
														resultsVip.getString(COLUMN_PAIKKA));
			}
		return true;
		}else {
			System.out.println("EI LIPPUJA!");
		}
	}catch(SQLException e) {
		System.out.println("Jotain meni pieleen " + e.getMessage());
	}
	return false;
	
	
	
}



//Tämä metodi tulostaa kaikki liput jotka on varattu, eli ohjelmassa vaihtoehto 5.
public void tulostaVarattuLippu() throws SQLException {
	
	
	//Tulostaa varatut perusliput
	try {
		Connection connPerusCount = DriverManager.getConnection(CONNECTION_STRING_PERUS);
		Connection connPerus= DriverManager.getConnection(CONNECTION_STRING_PERUS);
		
		
		Statement statementPerus = connPerus.createStatement();
		Statement statementPerusCount = connPerusCount.createStatement();
		ResultSet resultsPerus = statementPerus.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT+ " WHERE " + COLUMN_VARATTU +" = 'KYLLÄ'");
		ResultSet resultsPerusCount = statementPerusCount.executeQuery("SELECT * FROM "+TABLE_PERUSLIPUT+ " WHERE " + COLUMN_VARATTU +" = 'KYLLÄ'");
		
		
		int i = -1;
		while(resultsPerusCount.next()) {
			i++;
		}
		
		if(i >= 0) {
			
			

			System.out.println("VARATUT VIPLIPUT :\n ");
			System.out.println("Elokuva:  Pvm:     Paikka:");
		while(resultsPerus.next()) {
			System.out.println(resultsPerus.getString(COLUMN_NIMI) + "    " + resultsPerus.getString(COLUMN_PVM) + "  " +
													resultsPerus.getString(COLUMN_PAIKKA));
			}
			}else {
				System.out.println("Ei varattuja peruslippuja!");
			}
		resultsPerus.close();
		System.out.println();
		}catch(SQLException e) {
			System.out.println("Jotain meni pieleen " + e.getMessage());
		}
		 
		
	
	
	
		


	//Tulostaa varatut vipliput
	try {
		Connection connVipCount = DriverManager.getConnection(CONNECTION_STRING_VIP);
		Connection connVip = DriverManager.getConnection(CONNECTION_STRING_VIP);
		
		
		Statement statementVip = connVip.createStatement();
		Statement statementVipCount = connVipCount.createStatement();
		 
		ResultSet resultsVip = statementVip.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT + " WHERE " + COLUMN_VARATTU +" = 'KYLLÄ'");
		 
		ResultSet resultsVipCount = statementVipCount.executeQuery("SELECT * FROM "+TABLE_VIPLIPUT + " WHERE " + COLUMN_VARATTU +" = 'KYLLÄ'");
		
		
		int i = -1;
		while(resultsVipCount.next()) {
			i++;
		}
		
		if(i >= 0) {
		
		System.out.println("VARATUT VIPLIPUT :\n ");
		System.out.println("Elokuva:  Pvm:     Paikka:");
	while(resultsVip.next()) {
		System.out.println(resultsVip.getString(COLUMN_NIMI) + "    " + resultsVip.getString(COLUMN_PVM) + "  " +
												resultsVip.getString(COLUMN_PAIKKA));
		}
		}else {
			System.out.println("Ei varattuja viplippuja!");
		}
	resultsVip.close();
	System.out.println();
	}catch(SQLException e) {
		System.out.println("Jotain meni pieleen " + e.getMessage());
	}
	 
	
	
}
	
	
	
	


}
