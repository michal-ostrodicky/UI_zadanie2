import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {

	static long idAktualnehoStavu = 0;

	public BFS(List<Vozidlo> zaciatokVozidla) {
		
	Uzol pociatocnyStav = new Uzol(zaciatokVozidla,0,0,idAktualnehoStavu++,0, null);
	pociatocnyStav.setHashStavu(pociatocnyStav.getHashCode());
	System.out.println("Hash prveho " + pociatocnyStav.getHashCode());

	
	Queue<Uzol> radNespracovanych = new LinkedList<>();
	Map<Long, ArrayList<Uzol>> vytvoreneUzly = new HashMap<Long, ArrayList<Uzol>>();
	
	
    radNespracovanych.add(pociatocnyStav);
    ArrayList<Uzol> zoznamUzlovNaHash = new ArrayList<Uzol>();
    zoznamUzlovNaHash.add(pociatocnyStav);
    vytvoreneUzly.put(pociatocnyStav.getHashCode(), zoznamUzlovNaHash);
    //System.out.println("Hash kod pre tento stav> " + pociatocnyStav.getHashCode());
    
    int o = 0;

    while(!radNespracovanych.isEmpty()){
    	o++;
 	    /*if (o == 4) {
 	    	break;
 	    }*/
    	System.out.println("Velkost queue je pred vyberom " + radNespracovanych.size()+ " " + o);
        Uzol sucasnyUzol = radNespracovanych.remove();
        int[] mapa = new int[50];
       
 	    vytvorPole(sucasnyUzol,mapa);
        //System.out.println("Aktualne pracujem s:" + sucasnyUzol.getHashStavu());
        //Zistenie ci je najdena cielova pozicia       
        if (porovnajCielovy(sucasnyUzol)){
        	vytvorPole(sucasnyUzol,mapa);
        	sucasnyUzol.vypisVozidiel();
        	
        	System.out.println("**** Postupnost operatorov: ****" );
        	System.out.println(sucasnyUzol.getHashStavu() + " "+ sucasnyUzol.getPoslednePouzityOperator());
       int q = 0;
        	while(sucasnyUzol.getIdPredchodcu() != 0) {      		
        		q++;
        		//vypis = vypis + sucasnyUzol.getPoslednePouzityOperator() + ", ";
        		long hash = sucasnyUzol.getHashPredchodcu();
        		long idPredchodzu = sucasnyUzol.getIdPredchodcu();
        		//System.out.println("Dalsi " + hash);
        		zoznamUzlovNaHash = null;
        		zoznamUzlovNaHash = vytvoreneUzly.get(hash);

        		for(Uzol aktualnyUzolvZozname: zoznamUzlovNaHash) {
    	   	    	//vytvorPole(aktualnyUzolvZozname,mapa);	
        			if(aktualnyUzolvZozname.getIdStavu() == idPredchodzu) {
        				System.out.println(aktualnyUzolvZozname.getHashStavu() + " "+ aktualnyUzolvZozname.getPoslednePouzityOperator());
        				sucasnyUzol = aktualnyUzolvZozname;
        				break;
        			}
        		}
      
        	}
        	//System.out.println(sucasnyUzol.getHashStavu() + " "+ sucasnyUzol.getPoslednePouzityOperator());
        	break;
        	
        	
        }
        
       
       
        //System.out.println("Aktualne pracujem s:");
        //sucasnyUzol.vypisVozidiel();


        for(int i=0; i<sucasnyUzol.getPoleVozidiel().size(); i++) { // pre kazde vozidlo v stave urob operaciu ak sa da
        	//System.out.println(o++);
        	int suradnicaX = sucasnyUzol.getPoleVozidiel().get(i).getSuradnicaX();
            int suradnicaY = sucasnyUzol.getPoleVozidiel().get(i).getSuradnicaY();
            int velkost = sucasnyUzol.getPoleVozidiel().get(i).getVelkost();
            char smer = sucasnyUzol.getPoleVozidiel().get(i).getPosun();
            int indexVPoli = 0;
          
            
            if(smer == 'h') { //OPERATOR VPRAVO & VLAVO
            	
            	if (velkost == 2) {
            		 //-1 kvoli velkosti, -1 kvoli Xovej suradnici pole od 0
            		indexVPoli = (suradnicaY-1)*6 + suradnicaX + velkost - 2;
            		for(int j=1; j<5; j++) { //vytvaranie posunom vpravo
            					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom doprava
            					if((mapa[indexVPoli+j] != 1) && ((suradnicaX+j) < 6)) { 
            						Uzol novyStav = operaciaVpravo(sucasnyUzol,i,suradnicaX+j, j);
            						pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
            					} else {
            						break;
            					}
            		}   
            		
            		indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
            		for(int j=1; j<5; j++) { //vytvaranie posunom vlavo	
            					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom dolava  			
            				if ((indexVPoli-j > 0) && (mapa[indexVPoli-j] != 1) && ((suradnicaX-j) >= 1)) {
            							Uzol novyStav = operaciaVlavo(sucasnyUzol,i,suradnicaX-j,j);
            							pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);

            							
            					} else {
            						break;
            					}
            	}
            } else if (velkost == 3) {
            	
            	indexVPoli = (suradnicaY-1)*6 + suradnicaX + velkost - 2;
            	for(int j=1; j<4; j++) { //vytvaranie posunom vpravo
					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom doprava
					if((mapa[indexVPoli+j] != 1) && ((suradnicaX+j) < 5)) { 
						Uzol novyStav = operaciaVpravo(sucasnyUzol,i,suradnicaX+j, j);
						pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
					} else {
						break;
					}
            	}   	
            	
            	indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
		    	for(int j=1; j<4; j++) { //vytvaranie posunom vlavo	
		    					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom dolava
		    				if ((mapa[indexVPoli-j] != 1) && ((suradnicaX-j)) >= 1) {
		    					
		    							Uzol novyStav = operaciaVlavo(sucasnyUzol,i,suradnicaX-j,j);
		    							pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
		    				} else {
		    					break;
		    				}
		    	}
            }
            	
           
        	} // endIF horizontalne 
            else if (smer == 'v' ) { // OPERACIE  HORE & DOLE
            	if (velkost == 2) {
		            indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1 + 6; //pre Velkost vozidiel +6 co znamena posun o riadok nizsie v mape
	            	for(int j=1; j<5; j++) { //vytvaranie posunom dole
	            		if ((mapa[indexVPoli+j*6] != 1) && ((suradnicaY+j)) < 6) {
        							Uzol novyStav = operaciaDole(sucasnyUzol,i,suradnicaY+j,j);
        							pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
	        					} else {
	        						break;
	        					}
	            	}
	            	indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
	            	for(int j=1; j<5; j++) { //vytvaranie posunom hore
	            		
	            		if ((indexVPoli-j*6 >= 0) && (mapa[indexVPoli-j*6] != 1) && ((suradnicaY-j)) >= 1) {
	            			Uzol novyStav = operaciaHore(sucasnyUzol,i,suradnicaY-j,j);
	            			pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
	            		}
	            		else {
            				break;
            			}
            		}
            
        	} // endif Velkost == 2
            	else if (velkost == 3) {
            		
		            	
		            indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1 + 12; //pre Velkost vozidiel +6 co znamena posun o riadok nizsie v mape
	            	for(int j=1; j<4; j++) { //vytvaranie posunom dole
	            		if ((mapa[indexVPoli+j*6] != 1) && ((suradnicaY+j)) < 5) {
        							Uzol novyStav = operaciaDole(sucasnyUzol,i,suradnicaY+j,j);
        							pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
	        					} else {
	        						break;
	        					}
	            	}
	            	

        			indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
	            	for(int j=1; j<4; j++) { //vytvaranie posunom hore
	            		if ((indexVPoli-j*6 >= 0) && (mapa[indexVPoli-j*6] != 1) && ((suradnicaY-j)) >= 1) { //prva podmienka je kvoli tomu, aby nechodilo do zapornych indexov v poli
	            			Uzol novyStav = operaciaHore(sucasnyUzol,i,suradnicaY-j,j);
	            			pridanieUzlaDoMapy(vytvoreneUzly, radNespracovanych, novyStav,sucasnyUzol,i);
	            		}
	            		else {
            				break;
            			}
            		}
            	} // endif smer == 3
        	} // vertikalny pohyb 
            	
        } //endfor vykonat operator na kazde vozidlo
        
        
	    } //while is empty
  //  System.out.println("***** Pocet " + maxKroky);
    //System.out.println(save);

    if(radNespracovanych.isEmpty()) {
    	System.out.println("*** Nenasiel som riesenie!! ***");
    	
    }
}

	//funkcia na pridanie uzla do mapy
	public static void pridanieUzlaDoMapy(Map<Long, ArrayList<Uzol>> vytvoreneUzly, Queue<Uzol> radNespracovanych, Uzol novyStav,Uzol sucasnyStav,int i) {
		   ArrayList<Uzol> tempList = null;
		   
		   long key = novyStav.getHashCode();
		   
		   if (vytvoreneUzly.containsKey(novyStav.getHashCode())) {
		      tempList = vytvoreneUzly.get(key);
		      
		      if(tempList == null) {
			         tempList = new ArrayList<Uzol>();
			  }
//		      System.out.println("Tu je co som vygeneroval");
//		      novyStav.vypisVozidiel();
		      boolean pridanieStavu = false;
		      
		      for (Uzol porovnavaci : tempList) {
		    	  if (!zistiTotoznost(porovnavaci,novyStav)) {
		    		  //ak nie su totozne
//				      System.out.println("Tu je co porovnavam");
//				      porovnavaci.vypisVozidiel();
				      pridanieStavu = true;		      
		    	} else {
//		    		System.out.println("Nasiel som totozne a koncim");
		    		pridanieStavu = false;
		    		break;
		    	} 
		      }	   
		      if (pridanieStavu) {
		    	  tempList.add(novyStav);  
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
			  radNespracovanych.add(novyStav);
//			  System.out.println("Pridal som novy stav autom " + sucasnyStav.getPoleVozidiel().get(i).getFarba() + " x=" 
//						+ novyStav.getPoleVozidiel().get(i).getSuradnicaX() + " y=" + novyStav.getPoleVozidiel().get(i).getSuradnicaY() 
//						+ " operacia: " + novyStav.getPoslednePouzityOperator() + " hashcode predchodzu=" + novyStav.getHashPredchodcu()
//						+ " ID predchodzu= " + novyStav.getIdPredchodcu() + " ID aktualne=" + novyStav.getIdStavu());
//			  System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + "\n");
			  //novyStav.vypisVozidiel();
		      tempList = new ArrayList<Uzol>();
		      tempList.add(novyStav);               
		   }
		   vytvoreneUzly.put(key,tempList);
		}


		//funkcia na porovnanie cieloveho stavu, ak nulte(cervene) auto dosiahne suradnicu x = 5, nasli sme konecny stav
		public static boolean porovnajCielovy(Uzol uzol) {
			if(uzol.getPoleVozidiel().get(0).getSuradnicaX() == 5){
		    	System.out.println("Nasli sme cielovu poziciu");
		    	return true;
		    }
			return false;		
		}
		
		public static Uzol operaciaVpravo(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun) {
			List<Vozidlo> novoVytvorene = copyArrayList(stav);
			long hashPredchodzu = stav.getHashStavu(); 
			long idPredchodzu = stav.getIdStavu();
			
			StringBuilder builder = new StringBuilder();
			builder.append("VPRAVO(");
			builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
			builder.append(", ");
			builder.append(posun);
			builder.append(")");
			
			Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
			novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
			novyStav.setHashStavu(novyStav.getHashCode());
			
			return novyStav;
		}
		
		public static Uzol operaciaVlavo(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun) {
			
			List<Vozidlo> novoVytvorene = copyArrayList(stav);
			long hashPredchodzu = stav.getHashStavu(); 
			long idPredchodzu = stav.getIdStavu();
		
			
			StringBuilder builder = new StringBuilder();
			builder.append("VLAVO(");
			builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
			builder.append(", ");
			builder.append(posun);
			builder.append(")");
			
			Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
			novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
			novyStav.setHashStavu(novyStav.getHashCode());
			
			return novyStav;
		}
		
		public static Uzol operaciaDole(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun) {
			
			List<Vozidlo> novoVytvorene = copyArrayList(stav);
			long hashPredchodzu = stav.getHashStavu(); 
			long idPredchodzu = stav.getIdStavu();
		
			
			StringBuilder builder = new StringBuilder();
			builder.append("DOLE(");
			builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
			builder.append(", ");
			builder.append(posun);
			builder.append(")");
			
			Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
			novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
			novyStav.setHashStavu(novyStav.getHashCode());
			
			return novyStav;
		}
		
		public static Uzol operaciaHore(Uzol stav, int indexVozidla, int posunNaPoziciu, int posun) {
			
			List<Vozidlo> novoVytvorene = copyArrayList(stav);
			long hashPredchodzu = stav.getHashStavu(); 
			long idPredchodzu = stav.getIdStavu();
		
			
			StringBuilder builder = new StringBuilder();
			builder.append("HORE(");
			builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
			builder.append(", ");
			builder.append(posun);
			builder.append(")");
			
			Uzol novyStav = new Uzol(novoVytvorene, hashPredchodzu,0,idAktualnehoStavu++,idPredchodzu, builder);
			novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
			novyStav.setHashStavu(novyStav.getHashCode());
			
			return novyStav;
		}
		
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
		
		//vytvori jednorozmerne pole, v ktorom reprezentujem pozicie aut v mape
		 public static void vytvorPole(Uzol uzol,int mapa[]) {
			
			 String stringovaMapa = new String();
			 
			 for(int i = 0; i<uzol.getPoleVozidiel().size(); i++) {
		
				 
				 int zapisPoctuJednotiekx = uzol.getPoleVozidiel().get(i).getVelkost();
				 if (uzol.getPoleVozidiel().get(i).getPosun() == 'h') {
		
			    			int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
			    			int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX;
		
			    			for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
			    				mapa[indexVPoli+j] = 1;	
			    			}    			 
			    			
			    } else {		    	
			    	int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
					int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX;
		
					for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
						mapa[indexVPoli+6*j] = 1;	
					}    		
			    	
			    	
			    }
			 }
			
//		     for (int j = 0; j<36; j++) {
//		    	 if ( j % 6 == 0) {
//		    		 stringovaMapa = stringovaMapa +"\n";
//		    	 }
//		    	 stringovaMapa = stringovaMapa + mapa[j] + ", ";
//		    	 
//		     }
//		    	System.out.println(stringovaMapa);
			 
		 }
	
}
