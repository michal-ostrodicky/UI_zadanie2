import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

public class prehladavanie{
	
	public static void main(String[] args)  {
		List<Vozidlo> zaciatokVozidla = new ArrayList<Vozidlo>();
		//Vytvaranie vozidiel do vstupneho stavu
		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
		Vozidlo oranzoveVozidlo = new Vozidlo("Oranzove",2 ,1 ,1 , 'h');
		Vozidlo zlteVozidlo = new Vozidlo("Zlte", 3, 2, 1, 'v');
		Vozidlo fialoveVozidlo = new Vozidlo("Fialove",2 ,5 ,1 , 'v');
		Vozidlo zeleneVozidlo = new Vozidlo("Zelene", 3, 2, 4, 'v');
		
		Vozidlo svetlomodreVozidlo = new Vozidlo("Svetlomodre", 3, 6, 3, 'h');
		Vozidlo siveVozidlo = new Vozidlo("Sive", 2, 5, 5, 'h');
		Vozidlo tmavomodreVozidlo = new Vozidlo("Tmavomodre", 3, 1, 6, 'v');
		
		
		
		zaciatokVozidla.add(cerveneVozidlo);
		zaciatokVozidla.add(oranzoveVozidlo);
		zaciatokVozidla.add(zlteVozidlo);
		zaciatokVozidla.add(fialoveVozidlo);
		zaciatokVozidla.add(zeleneVozidlo);
		zaciatokVozidla.add(svetlomodreVozidlo);
		zaciatokVozidla.add(siveVozidlo);
		zaciatokVozidla.add(tmavomodreVozidlo);
		
		
		
		Uzol pociatocnyStav = new Uzol(zaciatokVozidla,null,"LOLO");
		 
		
		
		
		//pociatocnyStav.getPoleVozidiel().get(0).vypisDetailyVozidla();
		
		
		Queue<Uzol> radNespracovanych = new LinkedList<>();
	    HashMap<Integer, Uzol> vytvoreneUzly = new HashMap<Integer, Uzol>();
	    radNespracovanych.add(pociatocnyStav);
	    vytvoreneUzly.put(pociatocnyStav.getHashCode(), pociatocnyStav);
	    //System.out.println("Hash kod pre tento stav> " + pociatocnyStav.getHashCode());
	    
	    int o = 0;
	    while(!radNespracovanych.isEmpty()){
	    	
	 	    
	    	System.out.println("Velkost queue je pred vyberom " + radNespracovanych.size());
	        Uzol sucasnyUzol = radNespracovanych.remove();
	        int[] mapa = new int[50];
	 	    vytvorPole(sucasnyUzol,mapa);
	        //System.out.println(sucasnyUzol.getPoslednePouzityOperator());
	        //Zistenie ci je najdena cielova pozicia       
	        if (porovnajCielovy(sucasnyUzol)){
	        	sucasnyUzol.vypisVozidiel();
	        	
	        	System.out.println("Postupnost operatorov: ");
	        	while(sucasnyUzol.getPredchodza() != null) {      		
	        		System.out.println(sucasnyUzol.getPoslednePouzityOperator());
	        		sucasnyUzol = sucasnyUzol.getPredchodza();
	        	}
	        		
	        	break;
	        	
	        }
	        //System.out.println("Zmena stavu LOLO ------ " + o++);
	       
	        System.out.println("Aktualne pracujem s:");
	        sucasnyUzol.vypisVozidiel();


	        for(int i=0; i<sucasnyUzol.getPoleVozidiel().size(); i++) { // pre kazde vozidlo v stave urob operaciu ak sa da
	        	System.out.println(o++);
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
	            							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
	            								//System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");
	            								//novyStav.getPoleVozidiel().get(0).vypisDetailyVozidla();
	            							} else {
	            								radNespracovanych.add(novyStav);
	            								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
	            								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
	            								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
	            								novyStav.vypisVozidiel();
	            								
	            							}

	            					} else {
	            						break;
	            					}
	            		}   
	            		
	            		indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
	            		for(int j=1; j<5; j++) { //vytvaranie posunom vlavo	
	            					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom dolava
	            			
	            				if ((indexVPoli-j > 0) && (mapa[indexVPoli-j] != 1) && ((suradnicaX-j) >= 1)) {
	            							Uzol novyStav = operaciaVlavo(sucasnyUzol,i,suradnicaX-j,j);
	            							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
	            								//System.out.println("Tu som3");
	            								/*if (o == 7 ) {
	            									
	            									System.out.println("LOL");
	            									novyStav.vypisVozidiel();
	            								}
	            								//	*/
	            								//novyStav.vypisVozidiel();
	            								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");
	            							} else {
	            								radNespracovanych.add(novyStav);
	            								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
	            								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
	            								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
	            								novyStav.vypisVozidiel();
	            							}
	            					} else {
	            						break;
	            					}
	            	}
	            } else if (velkost == 3) {
	            	
	            	indexVPoli = (suradnicaY-1)*6 + suradnicaX + velkost - 2;
	            	for(int j=1; j<4; j++) { //vytvaranie posunom vpravo
    					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom doprava
    					if((mapa[indexVPoli+j] != 1) && ((suradnicaX+j) <= 5)) { // namiesto 5 je tu 6
    						Uzol novyStav = operaciaVpravo(sucasnyUzol,i,suradnicaX+j, j);
    							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
    								//System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");

    							} else {
    								radNespracovanych.add(novyStav);
    								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
    								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
    								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
    								novyStav.vypisVozidiel();
    							}
    					} else {
    						break;
    					}
	            	}   	
	            	
	            	indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
			    	for(int j=1; j<4; j++) { //vytvaranie posunom vlavo	
			    					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom dolava
			    				if ((mapa[indexVPoli-j] != 1) && ((suradnicaX-j)) >= 1) {
			    							Uzol novyStav = operaciaVlavo(sucasnyUzol,i,suradnicaX-j,j);
			    							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
			    								//System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");
			    							} else {
			    								radNespracovanych.add(novyStav);
			    								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
			    								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
			    								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
			    								novyStav.vypisVozidiel();
			    							}
			    				} else {
			    					break;
			    				}
			    	}
	            }
	            	
	           
	        	} // endIF horizontalne 
	            else if (smer == 'v' ) { // OPERACIE  HORE & DOLE
	            	if (velkost == 2) {
	            			indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
			            	for(int j=1; j<5; j++) { //vytvaranie posunom hore
			            		
			            		if ((indexVPoli-j*6 > 0) && (mapa[indexVPoli-j*6] != 1) && ((suradnicaY-j)) >= 1) {
			            			Uzol novyStav = operaciaHore(sucasnyUzol,i,suradnicaY-j,j);
	    							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
	    								//System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");
	    							} else {
	    								radNespracovanych.add(novyStav);
	    								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
	    								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
	    								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
	    								novyStav.vypisVozidiel();			
	    							}
			            		}
			            		else {
		            				break;
		            			}
		            		}
		            
			            indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1 + 6; //pre Velkost vozidiel +6 co znamena posun o riadok nizsie v mape
		            	for(int j=1; j<5; j++) { //vytvaranie posunom hore
		            		if ((mapa[indexVPoli+j*6] != 1) && ((suradnicaY+j)) < 6) {
	        							Uzol novyStav = operaciaDole(sucasnyUzol,i,suradnicaY+j,j);
	        							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
		    								//System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");
		    							} else {
		    								radNespracovanych.add(novyStav);
		    								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
		    								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
		    								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
		    								novyStav.vypisVozidiel();			
		    							}
		        					} else {
		        						break;
		        					}
		            	}
            	} // endif Velkost == 2
	            	else if (velkost == 3) {
	            		
	            			indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1;
			            	for(int j=1; j<4; j++) { //vytvaranie posunom hore
			            		if ((indexVPoli-j*6 > 0) && (mapa[indexVPoli-j*6] != 1) && ((suradnicaY-j)) >= 1) { //prva podmienka je kvoli tomu, aby nechodilo do zapornych indexov v poli
			            			Uzol novyStav = operaciaHore(sucasnyUzol,i,suradnicaY-j,j);
	    							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
	    								//System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");
	    							} else {
	    								radNespracovanych.add(novyStav);
	    								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
	    								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
	    								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
	    								novyStav.vypisVozidiel();			
	    							}
			            		}
			            		else {
		            				break;
		            			}
		            		}
			            	
			            indexVPoli = (suradnicaY-1)*6 + suradnicaX - 1 + 12; //pre Velkost vozidiel +6 co znamena posun o riadok nizsie v mape
		            	for(int j=1; j<4; j++) { //vytvaranie posunom dole
		            		if ((mapa[indexVPoli+j*6] != 1) && ((suradnicaY+j)) < 6) {
	        							Uzol novyStav = operaciaDole(sucasnyUzol,i,suradnicaY+j,j);
	        							if(vytvoreneUzly.containsKey(novyStav.getHashCode())){
		    								
	        								if (o == 8 ) {
		    									System.out.println("shit");
		    									novyStav.vypisVozidiel();
		    									System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode() + " evidujeme!");
		    								}
		    							} else {
		    								radNespracovanych.add(novyStav);
		    								vytvoreneUzly.put(novyStav.getHashCode(), novyStav);
		    								System.out.println("Pridal som novy stav autom " + sucasnyUzol.getPoleVozidiel().get(i).getFarba());
		    								System.out.println("Hash kod pre tento stav> " + novyStav.getHashCode());
		    								novyStav.vypisVozidiel();			
		    							}
		        					} else {
		        						break;
		        					}
		            	}
	            	} // endif smer == 3
            	} // vertikalny pohyb 
	            	
	        } //endfor vykonat operator na kazde vozidlo
	        
	        
		    } //while is empty
	    /*if(radNespracovanych.isEmpty()) {
	    	System.out.println("Prazdny zasobnik ");
	    	
	    }*/
	  //vytvoreneUzly.put(sucasnyUzol.getHashCode(), sucasnyUzol);
	    
        
      /*  System.out.println("-----------------------");
        Uzol novyStav =  new Uzol(pokusVozidla, sucasnyUzol,"VPRAVO" + 5);
       
        novyStav.getPoleVozidiel().get(0).vypisDetailyVozidla();
        novyStav.getPoleVozidiel().get(1).vypisDetailyVozidla();
        
        sucasnyUzol.getPoleVozidiel().get(0).vypisDetailyVozidla();
        sucasnyUzol.getPoleVozidiel().get(1).vypisDetailyVozidla();
        
        System.out.println(novyStav.getPredchodza().getPoslednePouzityOperator());;

        
        System.out.println("-----------------------");
        List<Vozidlo> pokusVozidla2 = new ArrayList<Vozidlo>();
        for(int p = 0; p<sucasnyUzol.getPoleVozidiel().size();p++) {
        	int suradnicaX = sucasnyUzol.getPoleVozidiel().get(p).getSuradnicaX()+10000;   	
        	int suradnicaY = sucasnyUzol.getPoleVozidiel().get(p).getSuradnicaY();
        	int velkost = sucasnyUzol.getPoleVozidiel().get(p).getVelkost();
        	char smer = sucasnyUzol.getPoleVozidiel().get(p).getPosun();
        	String farba = sucasnyUzol.getPoleVozidiel().get(p).getFarba();
        	
        	Vozidlo cVozidlo = new Vozidlo(farba, velkost, suradnicaY, suradnicaX, smer);
        	pokusVozidla2.add(cVozidlo);

        }
        
        Uzol novyStav2 = new Uzol(pokusVozidla2, novyStav,"VPRAVO" + 3);
        novyStav.getPoleVozidiel().get(0).vypisDetailyVozidla();
        novyStav2.getPoleVozidiel().get(0).vypisDetailyVozidla();
      
        //novyStav2.getPredchodza().getPoleVozidiel().get(0).vypisDetailyVozidla();
        System.out.println(novyStav2.getPredchodza().getPoslednePouzityOperator());;
       // sucasnyUzol.getPoleVozidiel().get(0).vypisDetailyVozidla();
        	*/
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
		

		/*StringBuilder builder = new StringBuilder();
		builder.append("VPRAVO(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posun);
		builder.append(")");*/
		
		Uzol novyStav = new Uzol(novoVytvorene, stav, "VPRAVO " + stav.getPoleVozidiel().get(indexVozidla).getFarba() + " " + posun);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
		return novyStav;
	}
	
	public static Uzol operaciaVlavo(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun) {
		
		List<Vozidlo> novoVytvorene = copyArrayList(stav);
        
		/*StringBuilder builder = new StringBuilder();
		builder.append("VLAVO(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posunNaPoziciu);
		builder.append(")");*/
		
		Uzol novyStav = new Uzol(novoVytvorene, stav, "VLAVO " + stav.getPoleVozidiel().get(indexVozidla).getFarba() + " " + posun);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
		
		return novyStav;
	}
	
	public static Uzol operaciaDole(Uzol stav, int indexVozidla, int posunNaPoziciu,int posun) {
		
		List<Vozidlo> novoVytvorene = copyArrayList(stav);
      
		/*StringBuilder builder = new StringBuilder();
		builder.append("DOLE(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posunNaPoziciu);
		builder.append(")");*/
		
		Uzol novyStav = new Uzol(novoVytvorene, stav, "DOLE " + stav.getPoleVozidiel().get(indexVozidla).getFarba() + " " + posun);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
		
		return novyStav;
	}
	
	public static Uzol operaciaHore(Uzol stav, int indexVozidla, int posunNaPoziciu, int posun) {
		
		List<Vozidlo> novoVytvorene = copyArrayList(stav);
		
		/*StringBuilder builder = new StringBuilder();
		builder.append("VLAVO(");
		builder.append(stav.getPoleVozidiel().get(indexVozidla).getFarba());
		builder.append(", ");
		builder.append(posunNaPoziciu);
		builder.append(")");
		*/
		Uzol novyStav = new Uzol(novoVytvorene, stav,"HORE " + stav.getPoleVozidiel().get(indexVozidla).getFarba()+ " " + posun);
		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
		
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
	
	//vytvori jednorozmerne pole, v ktorom reprezentujem pozicie aut v mape
	 public static void vytvorPole(Uzol uzol,int mapa[]) {
		
		 String stringovaMapa = new String();
		 
		 for(int i = 0; i<uzol.getPoleVozidiel().size(); i++) {
			 	// 2
			 
			 int zapisPoctuJednotiekx = uzol.getPoleVozidiel().get(i).getVelkost();
			 if (uzol.getPoleVozidiel().get(i).getPosun() == 'h') {
				 
		    			// 1 3 a 3 3
		    			int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
		    			int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX;
		    			//indexVPoli[12]
		    			//indexVPoli[14]
		    			for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
		    				mapa[indexVPoli+j] = 1;	
		    			}    			 
		    		/*adjacency_matrix =	
		    		 *{ 
		    		 0,0,0,0,0,0,  
		    	     0,0,0,0,0,0,   
		    	     1,1,1,1,0,0,
		    	     0,0,0,0,0,0,    
		    	     0,0,0,0,0,0,    
		    	     0,0,0,0,0,0};   */	
		    			
		    } else {
	
	    		 /*{ 
	    		 0,0,0,0,0,1,  
	    	     1,0,0,0,0,1,   
	    	     1,0,0,0,0,1,
	    	     1,0,0,0,0,0,    
	    	     0,0,0,0,0,0,    
	    	     0,0,0,0,0,0};   */	
		    	
		    	int suradnicaX = (uzol.getPoleVozidiel().get(i).getSuradnicaX()-1);
    			int indexVPoli = (uzol.getPoleVozidiel().get(i).getSuradnicaY()-1)*6 + suradnicaX;
    			//indexVPoli[5]
    			//indexVPoli[6]
    			for(int j = 0; j<zapisPoctuJednotiekx; j++ ) {
    				mapa[indexVPoli+6*j] = 1;	
    			}    		
		    	
		    	
		    }
		 }
		
	     for (int j = 0; j<36; j++) {
	    	 if ( j % 6 == 0) {
	    		 stringovaMapa = stringovaMapa +"\n";
	    	 }
	    	 stringovaMapa = stringovaMapa + mapa[j] + ", ";
	    	 
	     }
	    	System.out.println(stringovaMapa);
		 
	 }
	
}
	
	
	/*public  void dfsUsingStack(int adjacency_matrix[][], Uzol uzol)  
	 {  
	  Stack<Uzol> stack=new  Stack<Uzol>();  
	  stack.add(uzol);  
	  node.visited=true;  
	  while (!stack.isEmpty())  
	  {  
	   Node element=stack.pop();  
	   System.out.print(element.data + "\t");  
	  
	   ArrayList<node> neighbours=findNeighbours(adjacency_matrix,element);  
	   for (int i = 0; i < neighbours.size(); i++) {  
	    Node n=neighbours.get(i);  
	    if(n!=null && !n.visited)  
	    {  
	     stack.add(n);  
	     n.visited=true;  
	  
	    }  
	   }  
	  }  
	 } } */




