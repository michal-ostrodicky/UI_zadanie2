import java.util.List;

public class Uzol {
	private List<Vozidlo> poleVozidiel;
	private Uzol predchodza; 
	private String poslednePouzityOperator;
	
	public Uzol(List<Vozidlo> poleVozidiel, Uzol predchodza, String poslednePouzityOperator) {
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
}
