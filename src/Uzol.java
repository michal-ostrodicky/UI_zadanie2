import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Uzol {
	private List<Vozidlo> poleVozidiel;
	private Uzol predchodza; 
	private String poslednePouzityOperator;
	
	public Uzol(List<Vozidlo> poleVozidiel, Uzol predchodza , String poslednePouzityOperator ) {
		this.poleVozidiel = poleVozidiel;
		this.predchodza = predchodza;
		this.poslednePouzityOperator = poslednePouzityOperator;
		
	}
	
	public List<Vozidlo> getPoleVozidiel() {
		return poleVozidiel;
	}
	public void setPoleVozidiel(List<Vozidlo> poleVozidiel) {
		this.poleVozidiel = poleVozidiel;
	}
	public Uzol getPredchodza() {
		return predchodza;
	}
	public void setPredchodza(Uzol predchodza) {
		this.predchodza = predchodza;
	}
	public String getPoslednePouzityOperator() {
		return poslednePouzityOperator;
	}
	public void setPoslednePouzityOperator(String poslednePouzityOperator) {
		this.poslednePouzityOperator = poslednePouzityOperator;
	}
	
	public int getHashCode() {
		int hashArray = 0;
		
		for(int i=0; i<poleVozidiel.size(); i++) {
			Object[] x = {poleVozidiel.get(i).getSuradnicaX(),poleVozidiel.get(i).getSuradnicaY(), 
					poleVozidiel.get(i).getVelkost(),poleVozidiel.get(i).getPosun()};
			hashArray = hashArray + Arrays.hashCode(x)*(i+1);
		}
		return hashArray;
	}
}
