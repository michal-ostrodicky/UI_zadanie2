import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Trieda v ktorej sa nachadza main a ktora sa spusta. Ziskava vstup od pouzivatela.
 * Tato trieda vola metody na prehladavanie do sirky a do hlbky. 
 * Jednotlive vysledky prehladne vypisuje do dvojrozmerneho pola.
 * @author Michal Ostrodicky
 *
 */
public class prehladavanie{
	
	public static void main(String[] args)  {
		List<Vozidlo> zaciatokVozidla = new ArrayList<Vozidlo>();
		long start;
		long koniec;
		long casTrvaniaDFS; 
		long casTrvaniaBFS;
		int suradnicaX = 0; 
		int suradnicaY = 0; 
		int pocetVozidiel = 0;
		int velkost = 0; 
		char smer = 0;
		int counter;
		String farba = new String();
		
		
		BufferedReader reader = null;
		//zadanie nazvu testovacieho suboru
		Scanner vstup = new Scanner(System.in); 
		System.out.println("Zadajte nazov vstupu( napr. vstup1)");
		String filename = vstup.next();
		
		
		//citanie testovaci udajov zo suboru
		File file = new File(filename.toLowerCase()+".txt");
		try {
			reader = new BufferedReader(
				    new InputStreamReader(
				        new FileInputStream(file),
				        Charset.forName("UTF-8")));
		} catch (FileNotFoundException e) {
			System.out.println("Neexitujuci subor na vstup! Program sa vypina...");
			System.exit(0);
		}
			String c;
			try {	
				while((c = reader.readLine()) != null) {
					pocetVozidiel++;
					String[] arr = c.split(" ");  
					counter = 0;
					try {
					 for ( String slovo : arr) {
						 
						 switch(counter) {
						 case 0: farba = slovo; 
						 		 break;
						 case 1: velkost = Integer.parseInt(slovo);
						 		 break;
						 case 2: suradnicaY = Integer.parseInt(slovo);
						 		 break;
						 case 3: suradnicaX = Integer.parseInt(slovo);
						 		 break;
						 case 4: smer = slovo.charAt(0);
						 		 break;
						 }
						 counter++;
						 
					  }	
					 System.out.println(pocetVozidiel + " je " + farba);
					 Vozidlo vozidlo = new Vozidlo(farba, velkost, suradnicaY, suradnicaX, smer);
					 zaciatokVozidla.add(vozidlo);
					} catch (NumberFormatException e) {
						System.out.println("Chyba vo vstupnom subore, zadali cislo v zlom formate! Program sa vypina...");
						System.exit(0);
					}
				  
				}
			} catch (IOException e) {
				System.out.println("Chyba vo vstupnom subore! Program sa vypina...");
				System.exit(0);
			}        
		
		System.out.println("\nKrizovatka pred presunom: ");
		vytvorPole(zaciatokVozidla);
		
		BFS hladanieDoSirky =  new BFS();	
		DFS hladanieDoHlby =  new DFS();
		
		start = System.currentTimeMillis();
		Uzol cielovy =  hladanieDoSirky.vypocetBFS(zaciatokVozidla);
		koniec = System.currentTimeMillis();
		casTrvaniaBFS = koniec - start;
		
		if (cielovy != null) {
			System.out.println("\nKrizovatka po presune BFS: ");
			vytvorPole(cielovy);
		}
		
		
		start = System.currentTimeMillis();
		cielovy = hladanieDoHlby.vypocetDFS(zaciatokVozidla, cielovy);
		koniec = System.currentTimeMillis();
		casTrvaniaDFS = koniec - start;
		
		if (cielovy != null) {
			System.out.println("\nKrizovatka po presune DFS: ");
			vytvorPole(cielovy);
		}
		
		System.out.println("\nBFS cas vypoctu " + casTrvaniaBFS + " ms.");
		System.out.println("DFS cas vypoctu " + casTrvaniaDFS + " ms.");
		
		
    	
	    	
	    	
	}
	/**
	 * Pomocna metoda na vytvorenie reprezentacie mapy. Kazde cislo vacsie ako 0 znaci nejake vozidlo.
	 * Vytvori jednorozmerne pole. Krizovatka ma rozlozenie 6x6 a v jednorozmernom poli je to tak, 
	 * ze prvych 6 indexov(0-5 v reprezentacii pola) patri prvemu riadku, dalsich 6 je od 6 po 11, atd. 
	 * @param uzol - uzol z ktoreho sa generuje mapa
	 */
	public static void vytvorPole(Uzol uzol) {
		int mapa[] = new int[50];
		 
		 for(int i = 0; i<uzol.getPoleVozidiel().size(); i++) {
			 int zapisPoctuJednotiekx = uzol.getPoleVozidiel().get(i).getVelkost();//podla velkosti pridavam 1 do mapy.
			 //pre velkost 2 pridavam dve jednotky, inak pridavam tri jednotky
			 if (uzol.getPoleVozidiel().get(i).getPosun() == 'h') {
	
		    			int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
		    			int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX; //pre kazde vozidlo zistim jeho index v poli a do mapy mapisem pocet jednotiek
	
		    			for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
		    				mapa[indexVPoli+j] = i+1;	
		    			}    			 
		    			
		    } else { // vertikalny smer 	    	
		    	int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
				int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX;
	
				for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
					mapa[indexVPoli+6*j] = i+1;	//vertikalne auta maju jednotky pod sebou, preto pridavam nasobky 6(sirka krizovatky)
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
	
	/**
	 * Pomocna metoda na vytvorenie reprezentacie mapy. Kazde cislo vacsie ako 0 znaci nejake vozidlo.
	 * Tato metoda vsak prijima ako parameter priamo zoznam vozidiel a nie stav. 
	 * @param zoznamVozidiel - 
	 */
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


