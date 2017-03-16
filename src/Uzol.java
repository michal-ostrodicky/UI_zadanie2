import java.util.Arrays;
import java.util.List;

public class Uzol {
	private List<Vozidlo> poleVozidiel;
	private int hashStavu;
	private int hashPredchodcu;
	

	private StringBuilder poslednePouzityOperator;
	
	public Uzol(List<Vozidlo> poleVozidiel, int hashPredchodcu, int hashStavu  , StringBuilder builder ) {
		this.poleVozidiel = poleVozidiel;
		this.poslednePouzityOperator = builder;
		this.hashStavu = hashStavu;
		this.hashPredchodcu = hashPredchodcu;
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

	public int getHashStavu() {
		return hashStavu;
	}

	public void setHashStavu(int hashStavu) {
		this.hashStavu = hashStavu;
	}

	public int getHashPredchodcu() {
		return hashPredchodcu;
	}

	public void setHashPredchodcu(int hashPredchodcu) {
		this.hashPredchodcu = hashPredchodcu;
	}
	
	public int getHashCode() {
		int hashArray = 17;
		
		for(int i=0; i<poleVozidiel.size(); i++) {
			Object[] x = {poleVozidiel.get(i).getSuradnicaX(),poleVozidiel.get(i).getSuradnicaY(), 
					poleVozidiel.get(i).getVelkost(),poleVozidiel.get(i).getPosun(),poleVozidiel.get(i).getFarba()};
			hashArray = hashArray*31 + Arrays.hashCode(x)*i;
			
		}
		return hashArray;
	}
	
	
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
