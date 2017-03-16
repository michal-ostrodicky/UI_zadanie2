import java.util.ArrayList;
import java.util.List;


public class prehladavanie{
	

	public static void main(String[] args)  {
		List<Vozidlo> zaciatokVozidla = new ArrayList<Vozidlo>();
		//Vytvaranie vozidiel do vstupneho stavu
		/*Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
		Vozidlo blokoveVozidlo = new Vozidlo("Fialove",3 ,3 ,4 , 'h');
		Vozidlo oranzoveVozidlo = new Vozidlo("Oranzove",2 ,1 ,1 , 'h');
		Vozidlo zlteVozidlo = new Vozidlo("Zlte", 3, 2, 1, 'v');
		Vozidlo fialoveVozidlo = new Vozidlo("Fialove",2 ,5 ,1 , 'v');
		Vozidlo zeleneVozidlo = new Vozidlo("Zelene", 3, 2, 4, 'v');
		Vozidlo svetlomodreVozidlo = new Vozidlo("Svetlomodre", 3, 6, 3, 'h');
		Vozidlo siveVozidlo = new Vozidlo("Sive", 2, 5, 5, 'h');
		Vozidlo tmavomodreVozidlo = new Vozidlo("Tmavomodre", 3, 1, 6, 'v');
		
		
//		1, 1, 0, 0, 0, 1, 
//		1, 0, 0, 1, 0, 1, 
//		1, 1, 1, 1, 0, 1, 
//		1, 0, 0, 1, 0, 0, 
//		1, 0, 0, 0, 1, 1, 
//		1, 0, 1, 1, 1, 0, 
		 
		
		zaciatokVozidla.add(cerveneVozidlo);
		//zaciatokVozidla.add(blokoveVozidlo);
//		zaciatokVozidla.add(oranzoveVozidlo);
//		zaciatokVozidla.add(zlteVozidlo);
//		zaciatokVozidla.add(fialoveVozidlo);
		zaciatokVozidla.add(zeleneVozidlo);
		zaciatokVozidla.add(svetlomodreVozidlo);
//		zaciatokVozidla.add(siveVozidlo);
//		zaciatokVozidla.add(tmavomodreVozidlo);

		*/
		/*
		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
		Vozidlo blokoveVozidlo = new Vozidlo("Fialove",3, 2, 4, 'v');
		Vozidlo oranzoveVozidlo = new Vozidlo("Oranzove",3, 2, 5, 'v');
		Vozidlo zlteVozidlo = new Vozidlo("Zlte", 3, 2, 6, 'v');
		
		Vozidlo fialoveVozidlo = new Vozidlo("Fialove",3 ,5 ,4 , 'h');
		Vozidlo fialoveVozidlo2 = new Vozidlo("Fialove",3 ,6 ,4 , 'h');
		Vozidlo zlteVozidlo2 = new Vozidlo("zlteVozidlo",3 ,5 ,1 , 'h');
		Vozidlo zlteVozidlo3 = new Vozidlo("zlteVozidlo",3 ,6 ,1 , 'h');

		zaciatokVozidla.add(cerveneVozidlo);
		zaciatokVozidla.add(blokoveVozidlo);
		zaciatokVozidla.add(oranzoveVozidlo);
		zaciatokVozidla.add(zlteVozidlo);
		zaciatokVozidla.add(fialoveVozidlo);
		zaciatokVozidla.add(fialoveVozidlo2);
		zaciatokVozidla.add(zlteVozidlo2);
		zaciatokVozidla.add(zlteVozidlo3);*/
		
		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
		Vozidlo zeleneVozidlo = new Vozidlo("Zelene", 3, 2, 4, 'v');
		Vozidlo svetlomodreVozidlo = new Vozidlo("Svetlomodre", 3, 6, 3, 'h');
		
		
//		1, 1, 0, 0, 0, 1, 
//		1, 0, 0, 1, 0, 1, 
//		1, 1, 1, 1, 0, 1, 
//		1, 0, 0, 1, 0, 0, 
//		1, 0, 0, 0, 1, 1, 
//		1, 0, 1, 1, 1, 0, 
		 
		
		zaciatokVozidla.add(cerveneVozidlo);
		//zaciatokVozidla.add(blokoveVozidlo);
//		zaciatokVozidla.add(oranzoveVozidlo);
//		zaciatokVozidla.add(zlteVozidlo);
//		zaciatokVozidla.add(fialoveVozidlo);
		zaciatokVozidla.add(zeleneVozidlo);
		zaciatokVozidla.add(svetlomodreVozidlo);
//		zaciatokVozidla.add(siveVozidlo);
//		zaciatokVozidla.add(tmavomodreVozidlo);
		
		
		//new BFS(zaciatokVozidla);
		int[] mapa = new DFS(zaciatokVozidla);
	}
	
}
	


