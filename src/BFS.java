import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Trieda prehladavania do sirky
 * @author Michal Ostrodicky
 */
public class BFS extends Operator {

	private static long idAktualnehoStavu = 0;
	private StringBuilder konecnyVypis = new StringBuilder();
	private List<String> vypis = new ArrayList<String>();

	public BFS() {

	}
	/**
	 * Funkcia na hladanie do sirky
	 * @param zaciatokVozidla - Parameter obsahujuci zoznam vozidiel s ich zaciatocnymi poziciami 
	 * @return
	 */
	public Uzol vypocetBFS(List<Vozidlo> zaciatokVozidla) {
		
	Uzol pociatocnyStav = new Uzol(zaciatokVozidla,0,0,idAktualnehoStavu++,0, null);
	pociatocnyStav.setHashStavu(pociatocnyStav.getHashCode());
	
	// na ulozenie nespracovanych stavov pouzivam classu QUEUE(front) 
	Queue<Uzol> radNespracovanych = new LinkedList<>();
	// Hash mapa na ulozenie stavov, ktore som uz vytvoril(navstivil)
	//Druhy parameter je zoznam uzlov, ktore maju rovnaky hash kod vypocitany pre dane rozlozenie vozidiel v stave
	Map<Long, ArrayList<Uzol>> vytvoreneUzly = new HashMap<Long, ArrayList<Uzol>>();
	
	// pridanie pociatocneho stavu do frontu
    radNespracovanych.add(pociatocnyStav);
    // pridanie pociatocneho stavu do hashmapy
    ArrayList<Uzol> zoznamUzlovNaHash = new ArrayList<Uzol>();
    zoznamUzlovNaHash.add(pociatocnyStav);
    vytvoreneUzly.put(pociatocnyStav.getHashCode(), zoznamUzlovNaHash);
   
    // pocitadlo kolko stavov bolo vybratych a spracovanych vramci prehladavania
    int counter = 0;

    //cyklus, ktory postupne vybera z radu este nespracovane stavy
    while(!radNespracovanych.isEmpty()){
    	counter++;
    	// FIFO vyberie sa prvok, ktory tam bol najdlhsie
        Uzol sucasnyUzol = radNespracovanych.remove();
        int[] mapa = new int[50]; 
       
 	    vytvorPole(sucasnyUzol,mapa);
        //System.out.println("Aktualne pracujem s:" + sucasnyUzol.getHashStavu());
 	    
        //Zistenie ci je najdena cielova pozicia       
        if (porovnajCielovy(sucasnyUzol)){
        	//Ak sme nasli, je potrebne vypisat operatory, ktore sme pouzili
        	//vytvorPole(sucasnyUzol,mapa);
        	//sucasnyUzol.vypisVozidiel();
        	Uzol cielovy = sucasnyUzol;
        	
        	//System.out.println(sucasnyUzol.getPoslednePouzityOperator());
        	
        	//postupne sa prechadza spatne az k prvemu stavu a pri tejto ceste sa vypisuju pouzite operatory
        	if(sucasnyUzol.getPoslednePouzityOperator() != null) { 
        		vypis.add(sucasnyUzol.getPoslednePouzityOperator().toString());
        	}
     
        	while(sucasnyUzol.getIdPredchodcu() != 0) {      		

        		long hash = sucasnyUzol.getHashPredchodcu();
        		long idPredchodzu = sucasnyUzol.getIdPredchodcu();
        		zoznamUzlovNaHash = null;
        		zoznamUzlovNaHash = vytvoreneUzly.get(hash);

        		for(Uzol aktualnyUzolvZozname: zoznamUzlovNaHash) {
    	   	    	//vytvorPole(aktualnyUzolvZozname,mapa);	
        			if(aktualnyUzolvZozname.getIdStavu() == idPredchodzu) {
        				vypis.add(aktualnyUzolvZozname.getPoslednePouzityOperator().toString());
        				sucasnyUzol = aktualnyUzolvZozname;
        				break;
        			}
        		}
      
        	}
        	//Operatory su v opacnom poradi, tento cyklus ich obrati
        	for(int i = vypis.size(); i>0; i--) {
        		if (i == 1) {			
        			konecnyVypis.append(vypis.get(i-1).substring(0, (vypis.get(i-1).length()-1)));
        		} else if (i % 5 == 0){
        			konecnyVypis.append(vypis.get(i-1)+ "\n");
        		} else {
        			konecnyVypis.append(vypis.get(i-1));
        		}
        	}
        	
        	System.out.println("**** Postupnost operatorov(najdene hladanim do sirky): ****" );
        	System.out.println(konecnyVypis + "\n");
        	
        	
   	    	System.out.println("Pocet prejdenych stavov v prehladavani do sirky = " + counter);
        	return cielovy;
        	
        }
        
       
       
        //System.out.println("Aktualne pracujem s:");
        //sucasnyUzol.vypisVozidiel();

        //Pre kazde vozidlo skusam vykonat operaciu ak sa da
        for(int i=0; i<sucasnyUzol.getPoleVozidiel().size(); i++) { 
        	int suradnicaX = sucasnyUzol.getPoleVozidiel().get(i).getSuradnicaX();
            int suradnicaY = sucasnyUzol.getPoleVozidiel().get(i).getSuradnicaY();
            int velkost = sucasnyUzol.getPoleVozidiel().get(i).getVelkost();
            char smer = sucasnyUzol.getPoleVozidiel().get(i).getPosun();
            int indexVPoli = 0;
            int posunNaPoziciu = 0;
            
            if(smer == 'h') { //OPERATOR VPRAVO & VLAVO
            	if (velkost == 2) {
            		
            		indexVPoli = (suradnicaY-1)*6 + suradnicaX + velkost - 2;  //-1 kvoli velkosti vozidla
            		// -1 kvoli Xovej suradnici, pole mapy je zacina od indexu 0
            		for(int j=1; j<5; j++) { //vytvaranie posunom vpravo
    					//pre kazdy posun vyhodnocujem ci sa mozem na danu poziciu v mape dostat.
            			//ak sa tam nenachadza 1 a nie som mimo mapy mozem vykonat operator
    					posunNaPoziciu = suradnicaX+j;
    					if((mapa[indexVPoli+j] != 1) && ((posunNaPoziciu) < 6)) { 
    						//statickou metodou operaciaVpravo vygenerujem stav posunom vpravo
    						Uzol novyStav = operaciaVpravo(sucasnyUzol,i,posunNaPoziciu, j,idAktualnehoStavu++);
    						
    						//pridam novy stav do hash mapy, a tym si zapamatam, ze som uz dany stav vygeneroval
    						pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
    					} else { //ak sa nemoze pohnut tak cyklus for sa skonci
    						break;
    					}
            		}   
            		
            		indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
            		for(int j=1; j<5; j++) { //vytvaranie posunom vlavo	
            			posunNaPoziciu = suradnicaX-j;
            			//pre kazdy posun vyhodnocujem ci sa mozem na danu poziciu v mape dostat.
            			//ak sa tam nenachadza 1 a nie som mimo mapy mozem vykonat operator  			
        				if ((indexVPoli-j > 0) && (mapa[indexVPoli-j] != 1) && (posunNaPoziciu >= 1)) {
        					//statickou metodou operaciaVlavo vygenerujem stav posunom vlavo
							Uzol novyStav = operaciaVlavo(sucasnyUzol,i,posunNaPoziciu,j,idAktualnehoStavu++);
							
							//pridam novy stav do hash mapy, a tym si zapamatam, ze som uz dany stav vygeneroval
							pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);	
    					} else { //ak sa nemoze pohnut tak cyklus for sa skonci
    						break; 
    					}
            	}
            } else if (velkost == 3) {
            	indexVPoli = (suradnicaY-1)*6 + suradnicaX + velkost - 2; //-1 kvoli velkosti vozidla
        		// -1 kvoli Xovej suradnici, pole mapy je zacina od indexu 0
            	for(int j=1; j<4; j++) { //vytvaranie posunom vpravo
        			//ak sa tam nenachadza 1 a nie som mimo mapy mozem vykonat operator
            		posunNaPoziciu = suradnicaX+j;
					if((mapa[indexVPoli+j] != 1) && ((suradnicaX+j) < 5)) { 
						//statickou metodou operaciaVpravo vygenerujem stav posunom vpravo
						Uzol novyStav = operaciaVpravo(sucasnyUzol,i,posunNaPoziciu, j,idAktualnehoStavu++);
						
						//pridam novy stav do hash mapy, a tym si zapamatam, ze som uz dany stav vygeneroval	
						pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
					} else { //ak sa nemoze pohnut tak cyklus for sa skonci
						break;
					}
            	}   	
            	
            	indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
		    	for(int j=1; j<4; j++) { //vytvaranie posunom vlavo	
		    		//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom dolava
    				posunNaPoziciu = suradnicaX-j;
    				if ((mapa[indexVPoli-j] != 1) && ((posunNaPoziciu)) >= 1) {
    					//statickou metodou operaciaVlavo vygenerujem stav posunom vlavo
						Uzol novyStav = operaciaVlavo(sucasnyUzol,i,posunNaPoziciu,j,idAktualnehoStavu++);
						
						//pridam novy stav do hash mapy, a tym si zapamatam, ze som uz dany stav vygeneroval
						pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
    				} else { //ak sa nemoze pohnut tak cyklus for sa skonci
    					break;
    				}
		    	}
            }
            	
           
        	} // endIF horizontalne 
            else if (smer == 'v' ) { // OPERACIE  HORE & DOLE
            	if (velkost == 2) {
		            indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1 + 6; //pre Velkost vozidiel +6 co znamena posun o riadok nizsie v mape
		            
		            for(int j=1; j<5; j++) { //vytvaranie posunom dole
		            	posunNaPoziciu = suradnicaY+j;
	            		if ((mapa[indexVPoli+j*6] != 1) && ((suradnicaY+j)) < 6) {
    							Uzol novyStav = operaciaDole(sucasnyUzol,i,posunNaPoziciu,j,idAktualnehoStavu++);
    							pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
	        			} else { 
	        				break;
	        			}
	            	}
		            
		            
	            	indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
	            	for(int j=1; j<5; j++) { //vytvaranie posunom hore
	            		posunNaPoziciu = suradnicaY-j;
	            		if ((indexVPoli-j*6 >= 0) && (mapa[indexVPoli-j*6] != 1) && (posunNaPoziciu >= 1)) {
	            			Uzol novyStav = operaciaHore(sucasnyUzol,i,posunNaPoziciu,j,idAktualnehoStavu++);
	            			pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
	            		} else { 
            				break;
            			}
            		}
            
        	} // endif Velkost == 2
            	else if (velkost == 3) {
		            indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1 + 12; //pre Velkost 3 vozidiel  +12 co znamena posun o 2 riadky nizsie v mape
	            	for(int j=1; j<4; j++) { //vytvaranie posunom dole
	            		posunNaPoziciu = suradnicaY+j;
	            		if ((mapa[indexVPoli+j*6] != 1) && ((posunNaPoziciu)) < 5) {
							Uzol novyStav = operaciaDole(sucasnyUzol,i,posunNaPoziciu,j,idAktualnehoStavu++);
							pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
    					} else { 
    						break;
    					}
	            	}
	            	

        			indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
	            	for(int j=1; j<4; j++) { //vytvaranie posunom hore
	            		posunNaPoziciu = suradnicaY-j;
	            		if ((indexVPoli-j*6 >= 0) && (mapa[indexVPoli-j*6] != 1) && (posunNaPoziciu >= 1)) { //prva podmienka je kvoli tomu, aby nechodilo do zapornych indexov v poli
	            			Uzol novyStav = operaciaHore(sucasnyUzol,i,posunNaPoziciu,j,idAktualnehoStavu++);
	            			pridanieUzlaDoHashMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
	            		}
	            		else { 
            				break;
            			}
            		}
            	} // endif smer == 3
        	} // vertikalny pohyb 
            	
        } //endfor vykonat operator na kazde vozidlo
        
        
	    } //while is empty

    	//ak sa program dostal sem, spravne riesenie sme nenasli, front je prazdny
    	System.out.println("*** Nenasiel som riesenie!! ***");
    	System.out.println("Pocet prejdenych stavov v prehladavani do sirky = " + counter);
    	return null;
	
}


	/**
	 * Metoda na pridanie uzla do hash mapy a radu nespracovanych uzlov
	 * @param vytvoreneUzly - zoznam uzlov, ktore maju rovnaky hash kod
	 * @param radNespracovanych - rad vytvorenych, ale este nespracovanych uzlov
	 * @param novyStav - novy vygenerovany stav z operatora 
	 * @param sucasnyStav - Stav z ktoreho som vygeneroval novy stav
	 * @param i - index vozidla, ktore sa operatorom posunulo v mape
	 */
	public static void pridanieUzlaDoHashMapy(Map<Long, ArrayList<Uzol>> vytvoreneUzly, Queue<Uzol> radNespracovanych, Uzol novyStav,Uzol sucasnyStav,int i) {
		   ArrayList<Uzol> tempList = null;
		   
		   long key = novyStav.getHashCode();
		   
		   
		   if (vytvoreneUzly.containsKey(novyStav.getHashCode())) {
		      tempList = vytvoreneUzly.get(key);
		      
		      if(tempList == null) { //kontrola keby nahodou po zoznam prazdny
			         tempList = new ArrayList<Uzol>();
			  }

		      boolean pridanieStavu = false;
		      
		      for (Uzol porovnavaci : tempList) { //pre kazdy uzol v zozname skontroluj ci som ho uz nevygeneroval
		    	  // tato kontrola nastava, pretoze niektore stavy mozu mat rovnaky hashcode
		    	  if (!zistiTotoznost(porovnavaci,novyStav)) {
		    		  //ak nie su totozne, pridanie stavu je povolene
//				      System.out.println("Tu je co porovnavam");
//				      porovnavaci.vypisVozidiel();
				      pridanieStavu = true;		      
		    	} else {
//		    		System.out.println("Nasiel som totozne a koncim");
		    		//ak su je uzol totozny(bude/bol uz navstiveny), tak pridanie stavu nie je povolene
		    		pridanieStavu = false;
		    		break;
		    	} 
		      }	   
		      if (pridanieStavu) {
		    	  tempList.add(novyStav);  //nasiel som unikatny stav, doteraz nevytvoreny, pridam ho do hash mapy a radu
		    	  radNespracovanych.add(novyStav); 
//		   
//				System.out.println("Pridal som novy stav autom " + sucasnyStav.getPoleVozidiel().get(i).getFarba() + " x=" 
//				+ novyStav.getPoleVozidiel().get(i).getSuradnicaX() + " y=" + novyStav.getPoleVozidiel().get(i).getSuradnicaY() 
//				+ " operacia: " + novyStav.getPoslednePouzityOperator() + " hashcode predchodzu=" + novyStav.getHashPredchodcu()
//				+ " ID predchodzu= " + novyStav.getIdPredchodcu() + " ID aktualne=" + novyStav.getIdStavu());
//				System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + "\n");  
		      } else {
//		    	  System.out.println("Pridanie stavu bolo FALSE\n");
		      }
		   } else {
			  radNespracovanych.add(novyStav); //moznost ak som vygeneroval stav, pre ktory nie je v hash mape
//			  System.out.println("Pridal som novy stav autom " + sucasnyStav.getPoleVozidiel().get(i).getFarba() + " x=" 
//						+ novyStav.getPoleVozidiel().get(i).getSuradnicaX() + " y=" + novyStav.getPoleVozidiel().get(i).getSuradnicaY() 
//						+ " operacia: " + novyStav.getPoslednePouzityOperator() + " hashcode predchodzu=" + novyStav.getHashPredchodcu()
//						+ " ID predchodzu= " + novyStav.getIdPredchodcu() + " ID aktualne=" + novyStav.getIdStavu());
//			  System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + "\n");
			  //novyStav.vypisVozidiel();
		      tempList = new ArrayList<Uzol>();
		      tempList.add(novyStav);               
		   }
		   vytvoreneUzly.put(key,tempList); // pridanie zoznamu uzlov (ktore maju rovnaky hash kod) do hash mapy
		}


	/**
	 * Metoda na porovnanie cieloveho stavu, ak nulte(cervene) auto dosiahne suradnicu x = 5, nasli sme konecny stav
	 * @param uzol - uzol, ktory sme prave vybrali z radu a ma byt porovnany s cielovou poziciou
	 * @return Ak som nasiel cielovu tak vrati true, inak false
	 */
		public static boolean porovnajCielovy(Uzol uzol) {
			if(uzol.getPoleVozidiel().get(0).getSuradnicaX() == 5){
		    	System.out.println("Nasli sme cielovu poziciu");
		    	return true;
		    }
			return false;		
		}
		

		/**
		 * Metoda porovnava dva stavy a ich konkretne rozlozenie aut ci su naozaj rovnake
		 * @param vHashMape - uzol nachadzajuci sa v hash mape
		 * @param novyStav - stav vytvoreny operatorom 
		 * @return ak je niektory z parametrov iny tak vrati false, nezhodu. Inak vrati true
		 */
		public static Boolean zistiTotoznost(Uzol vHashMape, Uzol novyStav){
			for(int i = 0; i< vHashMape.getPoleVozidiel().size(); i++) {
				if((vHashMape.getPoleVozidiel().get(i).getSuradnicaX() == novyStav.getPoleVozidiel().get(i).getSuradnicaX()
						&& vHashMape.getPoleVozidiel().get(i).getSuradnicaY() == novyStav.getPoleVozidiel().get(i).getSuradnicaY()
						&& vHashMape.getPoleVozidiel().get(i).getPosun() == novyStav.getPoleVozidiel().get(i).getPosun() 
						&& vHashMape.getPoleVozidiel().get(i).getFarba().equals(novyStav.getPoleVozidiel().get(i).getFarba()) 	
						)) 
				{ //ak su rovnake tak nic
				} else {
					return false;
				}
			}
			return true;
			
		}

		/**
		 * Vytvori jednorozmerne pole, v ktorom reprezentujem pozicie aut v mape. Krizovatka ma rozlozenie
		 * 6x6 a v jednorozmernom poli je to tak, ze prvych 6 indexov(0-5 v reprezentacii pola) patri prvemu riadku,
		 * dalsich 6 je od 6 po 11, atd. 
		 * @param uzol
		 * @param mapa - jednorozmerne pole reprezentujuce rozlozenie aut v krizovatke
		 */
		 public static void vytvorPole(Uzol uzol,int mapa[]) {
			 for(int i = 0; i<uzol.getPoleVozidiel().size(); i++) {	 
				 int zapisPoctuJednotiekx = uzol.getPoleVozidiel().get(i).getVelkost(); //podla velkosti pridavam 1 do mapy.
				 //pre velkost 2 pridavam dve jednotky, inak pridavam tri jednotky
				 if (uzol.getPoleVozidiel().get(i).getPosun() == 'h') { //horizontalny smer 
	    			int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
	    			int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX; //pre kazde vozidlo zistim jeho index v poli a do mapy mapisem pocet jednotiek

	    			for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
	    				mapa[indexVPoli+j] = 1;	
	    			}    			 
			    			
			    } else { // vertikalny smer 
			    	int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
					int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX; 
		
					for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
						mapa[indexVPoli+6*j] = 1; //vertikalne auta maju jednotky pod sebou, preto pridavam nasobky 6(sirka krizovatky)
					}    		
			    }
			 }
			
			 /*//pomocna funkcia na vykreslenie mapy v reprezentacii nul a jednotiek
			 String stringovaMapa = new String();
		     for (int j = 0; j<36; j++) {
		    	 if ( j % 6 == 0) {
		    		 stringovaMapa = stringovaMapa +"\n";
		    	 }
		    	 stringovaMapa = stringovaMapa + mapa[j] + ", ";
		    	 
		     }
		    	System.out.println(stringovaMapa);
			 */
		 }
		 
	
}

