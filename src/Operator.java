import java.util.ArrayList;
import java.util.List;

public class Operator {
	
	public static Uzol operaciaVpravo(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun,long idAktualnehoStavu) {
		List<Vozidlo> novoVytvorene = copyArrayList(stav);
		long hashPredchodzu = stav.getHashStavu(); 
		long idPredchodzu = stav.getIdStavu();
		
		StringBuilder builder = new StringBuilder();
		builder.append("VPRAVO(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posun);
		builder.append("), ");
		
		Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
		novyStav.setHashStavu(novyStav.getHashCode());
		
		return novyStav;
	}
	
	public static Uzol operaciaVlavo(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun,long idAktualnehoStavu) {
		
		List<Vozidlo> novoVytvorene = copyArrayList(stav);
		long hashPredchodzu = stav.getHashStavu(); 
		long idPredchodzu = stav.getIdStavu();
	
		
		StringBuilder builder = new StringBuilder();
		builder.append("VLAVO(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posun);
		builder.append("), ");
		
		Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
		novyStav.setHashStavu(novyStav.getHashCode());
		
		return novyStav;
	}
	
	public static Uzol operaciaDole(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun,long idAktualnehoStavu) {
		
		List<Vozidlo> novoVytvorene = copyArrayList(stav);
		long hashPredchodzu = stav.getHashStavu(); 
		long idPredchodzu = stav.getIdStavu();
	
		
		StringBuilder builder = new StringBuilder();
		builder.append("DOLE(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posun);
		builder.append("), ");
		
		Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
		novyStav.setHashStavu(novyStav.getHashCode());
		
		return novyStav;
	}
	
	public static Uzol operaciaHore(Uzol stav, int indexVozidla, int posunNaPoziciu, int posun,long idAktualnehoStavu) {
		
		List<Vozidlo> novoVytvorene = copyArrayList(stav);
		long hashPredchodzu = stav.getHashStavu(); 
		long idPredchodzu = stav.getIdStavu();
	
		
		StringBuilder builder = new StringBuilder();
		builder.append("HORE(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posun);
		builder.append("), ");
		
		Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
		novyStav.setHashStavu(novyStav.getHashCode());
		
		return novyStav;
	}
	
	//Metoda na kopirovanie arraylistu vozidiel, pri vytvarani noveho stavu
			public static List<Vozidlo> copyArrayList(Uzol stav) {
				int suradnicaX;
				int suradnicaY;
				int velkost;
				char smer;
				
				List<Vozidlo> novoVytvorene = new ArrayList<Vozidlo>();
			    for(int p = 0; p<stav.getPoleVozidiel().size();p++) {
			    	suradnicaX = stav.getPoleVozidiel().get(p).getSuradnicaX();
			    	suradnicaY = stav.getPoleVozidiel().get(p).getSuradnicaY();
			    	velkost = stav.getPoleVozidiel().get(p).getVelkost();
			    	smer = stav.getPoleVozidiel().get(p).getPosun();
			    	String farba = stav.getPoleVozidiel().get(p).getFarba();
			    	Vozidlo noveVozidlo = new Vozidlo(farba, velkost, suradnicaY, suradnicaX, smer);
			    	novoVytvorene.add(noveVozidlo);
			    }
				return novoVytvorene;
			
			}
	
	
}