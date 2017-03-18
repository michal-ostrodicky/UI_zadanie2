import java.util.Arrays;
import java.util.List;

/**
 * Struktura uzla. Ma atribut poleVozidiel, ktory vyjadruje aktualny stav(rozlozenie) aut v krizovatke.
 * Atributy idStavu a idPredchodcu vyjadruju jedinecne ID pre kazdy vytvoreny stav. Pri kazdom vytvoreni uzla 
 * sa hodnota ID inkrementuje. Tento atribut zabezpecuje jednoznacne retazenie stavov pri hladani(vypisani)
 * najkratsej postupnosti operatorov k cielovemu stavu.
 * Atribut hashStavu a hashPredchodcu predstavuje vypocitany hash z atributu poleVozidiel. Podla neho viem ziskat stav z hash mapy.
 * Hash je pre viacere stavy rovnake, preto to nie je jedinecny identifikator pre spatnu cestu. 
 * PoslednePouzityOperator - String v ktorom sa nachadza posledne pouzity operator pomocou, ktoreho som sa dostal do toho stavu.
 * @author Michal Ostrodicky
 *
 */
public class Uzol {
	private List<Vozidlo> poleVozidiel;
	private long hashStavu;
	private long hashPredchodcu;
	private long idStavu;
	private long idPredchodcu;
	private StringBuilder poslednePouzityOperator;
	
	public Uzol(List<Vozidlo> poleVozidiel, long hashPredchodcu, long hashStavu, long idStavu, long idPredchodcu, StringBuilder builder ) {
		this.poleVozidiel = poleVozidiel;
		this.poslednePouzityOperator = builder;
		this.hashStavu = hashStavu;
		this.hashPredchodcu = hashPredchodcu;
		this.idStavu = idStavu;
		this.idPredchodcu = idPredchodcu;
	}
	
	public List<Vozidlo> getPoleVozidiel() {
		return poleVozidiel;
	}
	public void setPoleVozidiel(List<Vozidlo> poleVozidiel) {
		this.poleVozidiel = poleVozidiel;
	}
	public StringBuilder getPoslednePouzityOperator() {
		return poslednePouzityOperator;
	}
	public void setPoslednePouzityOperator(StringBuilder poslednePouzityOperator) {
		this.poslednePouzityOperator = poslednePouzityOperator;
	}

	public long getHashStavu() {
		return hashStavu;
	}

	public void setHashStavu(long hashStavu) {
		this.hashStavu = hashStavu;
	}

	public long getHashPredchodcu() {
		return hashPredchodcu;
	}

	public void setHashPredchodcu(long hashPredchodcu) {
		this.hashPredchodcu = hashPredchodcu;
	}
	
	public long getIdStavu() {
		return idStavu;
	}

	public void setIdStavu(long idStavu) {
		this.idStavu = idStavu;
	}
	
	
	public long getIdPredchodcu() {
		return idPredchodcu;
	}

	public void setIdPredchodcu(long idPredchodcu) {
		this.idPredchodcu = idPredchodcu;
	}
	
	/**
	 * Vlastna metoda na vytvorenie hashu pre stav. 
	 * Do hashu sa zapocitavaju vsetky atributy pola vozidiel. Pri znizenie kolizii pouzivam prvocisla 17 a 31 pri vypocte
	 * @return
	 */
	public long getHashCode() {
		long hashArray = 17;
		
		for(int i=0; i<poleVozidiel.size(); i++) {
			Object[] x = {poleVozidiel.get(i).getSuradnicaX(),poleVozidiel.get(i).getSuradnicaY(), 
					poleVozidiel.get(i).getVelkost(),poleVozidiel.get(i).getPosun(),poleVozidiel.get(i).getFarba()};
			hashArray = hashArray*31 + Arrays.hashCode(x)*i;
			
		}
		return hashArray;
	}
	

	/**
	 * Pomocna metoda na vypis stavu, presne rozlozenie vozidiel, ich pozicie.
	 * 
	 */
	public void vypisVozidiel() {
		for(int i=0; i<poleVozidiel.size(); i++) {
			System.out.println("farba = " + poleVozidiel.get(i).getFarba());
			System.out.println("suradnice x= " + poleVozidiel.get(i).getSuradnicaX() + ", y= " + poleVozidiel.get(i).getSuradnicaY());
			System.out.println("velkost " + poleVozidiel.get(i).getVelkost());
			System.out.println("posun = " + poleVozidiel.get(i).getPosun() + "\n");
		}
		System.out.println("---------");
	}

}
