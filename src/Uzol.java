import java.util.List;

public class Uzol {
	private List<Vozidlo> poleVozidiel;
	private List<Uzol> predchodza; 
	//private Uzol predchodza; 
	private String poslednePouzityOperator;
	
	public Uzol(List<Vozidlo> poleVozidiel, List<Uzol> predchodza, String poslednePouzityOperator) {
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
	public List<Uzol> getPredchodza() {
		return predchodza;
	}
	public void setPredchodza(List<Uzol> predchodza) {
		this.predchodza = predchodza;
	}
	public String getPoslednePouzityOperator() {
		return poslednePouzityOperator;
	}
	public void setPoslednePouzityOperator(String poslednePouzityOperator) {
		this.poslednePouzityOperator = poslednePouzityOperator;
	}
}
