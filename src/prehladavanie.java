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
		
		Scanner vstup = new Scanner(System.in); 
		System.out.println("Zadajte nazov vstupu( napr. Vstup1)");
		String filename = vstup.next();
		
		File file = new File(filename+".txt");
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
		
		start = System.currentTimeMillis();
		hladanieDoHlby.vypocetDFS(zaciatokVozidla, cielovy);
		koniec = System.currentTimeMillis();
		casTrvaniaDFS = koniec - start;
		
		System.out.println("\nBFS cas vypoctu " + casTrvaniaBFS + " ms.");
		System.out.println("DFS cas vypoctu " + casTrvaniaDFS + " ms.");
		
		if (cielovy != null) {
			System.out.println("\nKrizovatka po presune: ");
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


