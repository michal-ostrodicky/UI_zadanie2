import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Uzol {
	private List<Vozidlo> poleVozidiel;
	private Uzol predchodza; 
	private String poslednePouzityOperator;
	
	public Uzol(List<Vozidlo> poleVozidiel, Uzol predchodza , String string ) {
		this.poleVozidiel = poleVozidiel;
		this.predchodza = predchodza;
		this.poslednePouzityOperator = string;
		
	}
	
	public Uzol(Uzol uzol) {
		this.poleVozidiel = uzol.poleVozidiel;
		this.predchodza = uzol.predchodza;
		this.poslednePouzityOperator = uzol.poslednePouzityOperator;
		
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
					poleVozidiel.get(i).getVelkost(),poleVozidiel.get(i).getPosun(),poleVozidiel.get(i).getFarba()};
			hashArray = hashArray + Arrays.hashCode(x);
		}
		return hashArray;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(poleVozidiel);
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
