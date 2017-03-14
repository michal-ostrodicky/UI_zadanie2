import java.util.List;

public interface Operacie {

	public default Uzol operaciaVpravo(Uzol stav, List<Vozidlo> vozidla, int indexVozidla, int posunNaPoziciu,int posun) {
		return stav;}
	}
