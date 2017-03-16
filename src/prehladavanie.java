import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class prehladavanie{
	
	
	public static void main(String[] args)  {
		List<Vozidlo> zaciatokVozidla = new ArrayList<Vozidlo>();
		long start;
		long koniec;
		long casTrvaniaDFS; 
		long casTrvaniaBFS;
		int pocetVozidiel;
		int suradnicaX; 
		int suradnicaY; 
		int velkost; 
		char smer;
		String smerString = new String();
		String farba = new String();
		
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		try {
			System.out.println("Napiste pocet vozidiel: ");
			pocetVozidiel = reader.nextInt();
		
			for(int i = 1; i<=pocetVozidiel; i++) {
				
				System.out.println("Farba vozidla " + i + ". vozidla: ");
				farba = reader.next();
				System.out.println("Velkost vozidla");
				velkost = reader.nextInt();
				System.out.println("X-ova suradnica: ");
				suradnicaX = reader.nextInt();
				System.out.println("Y-ova suradnica: ");
				suradnicaY = reader.nextInt();
				System.out.println("Smer vozidla");
				smerString = reader.next();
				smer = smerString.charAt(0);
				Vozidlo cerveneVozidlo = new Vozidlo(farba, velkost, suradnicaY, suradnicaX, smer);
				zaciatokVozidla.add(cerveneVozidlo);
				System.out.println("\n");
			}
		
		} catch(InputMismatchException e) {
			System.out.println("ZLY VSTUP");
			System.exit(1500);
			
		}
		reader.close();
		//Vytvaranie vozidiel do vstupneho stavu
		//Vozidlo blokoveVozidlo = new Vozidlo("Fialove",3 ,3 ,4 , 'h');
		
//		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
//		Vozidlo oranzoveVozidlo = new Vozidlo("Oranzove",2 ,1 ,1 , 'h');
//		Vozidlo zlteVozidlo = new Vozidlo("Zlte", 3, 2, 1, 'v');
//		Vozidlo fialoveVozidlo = new Vozidlo("Fialove",2 ,5 ,1 , 'v');
//		Vozidlo zeleneVozidlo = new Vozidlo("Zelene", 3, 2, 4, 'v');
//		Vozidlo svetlomodreVozidlo = new Vozidlo("Svetlomodre", 3, 6, 3, 'h');
//		Vozidlo siveVozidlo = new Vozidlo("Sive", 2, 5, 5, 'h');
//		Vozidlo tmavomodreVozidlo = new Vozidlo("Tmavomodre", 3, 1, 6, 'v');
		
		
//		1, 1, 0, 0, 0, 1, 
//		1, 0, 0, 1, 0, 1, 
//		1, 1, 1, 1, 0, 1, 
//		1, 0, 0, 1, 0, 0, 
//		1, 0, 0, 0, 1, 1, 
//		1, 0, 1, 1, 1, 0, 
		 
		//zaciatokVozidla.add(blokoveVozidlo);
		
//		zaciatokVozidla.add(cerveneVozidlo);
//		zaciatokVozidla.add(oranzoveVozidlo);
//		zaciatokVozidla.add(zlteVozidlo);
//		zaciatokVozidla.add(fialoveVozidlo);
//		zaciatokVozidla.add(zeleneVozidlo);
//		zaciatokVozidla.add(svetlomodreVozidlo);
//		zaciatokVozidla.add(siveVozidlo);
//		zaciatokVozidla.add(tmavomodreVozidlo);

		
		
//		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
//		Vozidlo blokoveVozidlo = new Vozidlo("Fialove",3, 2, 4, 'v');
//		Vozidlo oranzoveVozidlo = new Vozidlo("Oranzove",3, 2, 5, 'v');
//		Vozidlo zlteVozidlo = new Vozidlo("Zlte", 3, 2, 6, 'v');
//		Vozidlo fialoveVozidlo = new Vozidlo("Fialove",3 ,5 ,4 , 'h');
//		Vozidlo fialoveVozidlo2 = new Vozidlo("Fialove",3 ,6 ,4 , 'h');
//		Vozidlo zlteVozidlo2 = new Vozidlo("zlteVozidlo",3 ,5 ,1 , 'h');
//		Vozidlo zlteVozidlo3 = new Vozidlo("zlteVozidlo",3 ,6 ,1 , 'h');
//
//		zaciatokVozidla.add(cerveneVozidlo);
//		zaciatokVozidla.add(blokoveVozidlo);
//		zaciatokVozidla.add(oranzoveVozidlo);
//		zaciatokVozidla.add(zlteVozidlo);
//		zaciatokVozidla.add(fialoveVozidlo);
//		zaciatokVozidla.add(fialoveVozidlo2);
//		zaciatokVozidla.add(zlteVozidlo2);
//		zaciatokVozidla.add(zlteVozidlo3);
		
//		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
//		Vozidlo zeleneVozidlo = new Vozidlo("Zelene", 3, 2, 4, 'v');
//		Vozidlo svetlomodreVozidlo = new Vozidlo("Svetlomodre", 3, 6, 3, 'h');
//		
//		
//		zaciatokVozidla.add(cerveneVozidlo);
//		zaciatokVozidla.add(zeleneVozidlo);
//		zaciatokVozidla.add(svetlomodreVozidlo);
		System.out.println("Krizovatka pred presunom: ");
		vytvorPole(zaciatokVozidla);
		
		BFS hladanieDoSirky =  new BFS();	
		DFS hladanieDoHlby =  new DFS();
		
		start = System.currentTimeMillis();
		Uzol cielovy =  hladanieDoSirky.vypocetBFS(zaciatokVozidla);
		koniec = System.currentTimeMillis();
		casTrvaniaBFS = koniec - start;
		
		start = System.currentTimeMillis();
		hladanieDoHlby.vypocetDFS(zaciatokVozidla, cielovy);
		koniec = System.currentTimeMillis();
		casTrvaniaDFS = koniec - start;
		
		System.out.println("\nBFS cas vypoctu " + casTrvaniaBFS + " ms.");
		System.out.println("DFS cas vypoctu " + casTrvaniaDFS + " ms.");
		
		if (cielovy != null) {
			System.out.println("Krizovatka po presune: ");
			vytvorPole(cielovy);
		}
    	
	    	
	    	
	}
	
	public static void vytvorPole(Uzol uzol) {
		int mapa[] = new int[50];
		 
		 for(int i = 0; i<uzol.getPoleVozidiel().size(); i++) {
	
			 
			 int zapisPoctuJednotiekx = uzol.getPoleVozidiel().get(i).getVelkost();
			 if (uzol.getPoleVozidiel().get(i).getPosun() == 'h') {
	
		    			int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
		    			int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX;
	
		    			for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
		    				mapa[indexVPoli+j] = i+1;	
		    			}    			 
		    			
		    } else {		    	
		    	int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
				int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX;
	
				for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
					mapa[indexVPoli+6*j] = i+1;	
				}    		
		    	
		    	
		    }
		 }
		 String stringovaMapa = new String();
	    	for (int j = 0; j<36; j++) {
		    	 if ( j % 6 == 0) {
		    		 stringovaMapa = stringovaMapa +"\n";
		    	 }
		    	 stringovaMapa = stringovaMapa + mapa[j] + ", ";
		    	 
		     }
		    	System.out.println(stringovaMapa);
	}
	
	public static void vytvorPole(List<Vozidlo> zoznamVozidiel) {
		int mapa[] = new int[50];
		 
		 for(int i = 0; i<zoznamVozidiel.size(); i++) {
	
			 
			 int zapisPoctuJednotiekx = zoznamVozidiel.get(i).getVelkost();
			 if (zoznamVozidiel.get(i).getPosun() == 'h') {
	
		    			int suradnicaX = (zoznamVozidiel.get(i).getSuradnicaX()-1);
		    			int indexVPoli = (zoznamVozidiel.get(i).getSuradnicaY()-1)*6 + suradnicaX;
	
		    			for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
		    				mapa[indexVPoli+j] = i+1;	
		    			}    			 
		    			
		    } else {		    	
		    	int suradnicaX = (zoznamVozidiel.get(i).getSuradnicaX()-1);
				int indexVPoli = (zoznamVozidiel.get(i).getSuradnicaY()-1)*6 + suradnicaX;
	
				for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
					mapa[indexVPoli+6*j] = i+1;	
				}    		
		    	
		    	
		    }
		 }
		 String stringovaMapa = new String();
	    	for (int j = 0; j<36; j++) {
		    	 if ( j % 6 == 0) {
		    		 stringovaMapa = stringovaMapa +"\n";
		    	 }
		    	 stringovaMapa = stringovaMapa + mapa[j] + ", ";
		    	 
		     }
		    	System.out.println(stringovaMapa+ "\n");
	}
	
}


