import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class prehladavanie {

	public static void main(String[] args) {
		List<Vozidlo> zaciatokVozidla = new ArrayList<Vozidlo>();
		//Vytvaranie vozidiel do vstupneho stavu
		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 3, 'h');
		//Vozidlo zeleneVozidlo = new Vozidlo("Zelene", 3, 2, 4, 'v');
		//Vozidlo oranzoveVozidlo = new Vozidlo("Oranzove", 2, 1, 1, 'h');
		zaciatokVozidla.add(cerveneVozidlo);
		//zaciatokVozidla.add(zeleneVozidlo);
		//zaciatokVozidla.add(oranzoveVozidlo);
		
		Uzol pociatocnyStav = new Uzol(zaciatokVozidla, null, null);
		 
		pociatocnyStav.getPoleVozidiel().get(0).vypisDetailyVozidla();
		
		
		Queue<Uzol> rad = new LinkedList<>();
	    HashSet spracovany = new HashSet();
	    rad.add(pociatocnyStav);
	    spracovany.add(pociatocnyStav);
	    int o = 0;
	    
	    while(!rad.isEmpty()){
	        Uzol sucasnyUzol = rad.remove();
	        
	        //Zistenie ci je najdena cielova pozicia
	        
	        System.out.println("LOLO " + o++);
	        if(o > 1) break;
	        
	        if(sucasnyUzol.getPoleVozidiel().get(0).getSuradnicaX() == 5){
	        	System.out.println("Nasli sme cielovu poziciu");
	        	break;
	        }
	      
	        for(int i=0; i<sucasnyUzol.getPoleVozidiel().size(); i++) {
	        	
	        	int suradnicaX = sucasnyUzol.getPoleVozidiel().get(i).getSuradnicaX();
	            int suradnicaY = sucasnyUzol.getPoleVozidiel().get(i).getSuradnicaY();
	            int velkost = sucasnyUzol.getPoleVozidiel().get(i).getVelkost();
	            char smer = sucasnyUzol.getPoleVozidiel().get(i).getPosun();
	            boolean stop = false;
	            
	            if(smer == 'h') { //OPERATOR VPRAVO & VLAVO
	            	
	            	for(int j=1; j<5; j++) { //vytvaranie posunom vpravo
	            			if (stop) {
	            				stop = false;
	            				break;
	            			}
	            			
	            			for(int k = 0; k<sucasnyUzol.getPoleVozidiel().size(); k++) { 
	            				
	            					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom doprava
	            					if(sucasnyUzol.getPoleVozidiel().get(k).getSuradnicaX() != (suradnicaX+velkost+j-1)) { 
	            						if((suradnicaX+velkost+j-1) <= 6) { 
	            						Uzol novyStav = operaciaVpravo(sucasnyUzol,k,suradnicaX+velkost+j-1);
	            							if(!spracovany.contains(novyStav)){
	            								rad.add(novyStav);
	            							}
	            						}
	            					} else {
	            						stop = true;
	            						break;
	            					}
	            			}
	            	}
	            	stop = false;
	            	
	            	for(int j=1; j<5; j++) { //vytvaranie posunom vlavo	
	            		if (stop) {
	        				stop = false;
	        				break;
	        			}
	            			for(int k = 0; k<sucasnyUzol.getPoleVozidiel().size(); k++) { 
	            					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom dolava
	            					if(sucasnyUzol.getPoleVozidiel().get(k).getSuradnicaX() != (suradnicaX-j)) { 
	            						if((suradnicaX-j) >= 1) {
	            						//operaciaVlavo(sucasnyUzol,suradnicaX-j);
	                        			// vrati novo vytvoreny uzol 
	                        			// ten sa prida do queue
	            							Uzol novyStav = operaciaVpravo(sucasnyUzol,k,suradnicaX-j);
	            							if(!spracovany.contains(novyStav)){
	            								rad.add(novyStav);
	            							}
	            						} 
	            					} else {
	            						stop = true;
	            						break;
	            					}
	            			}
	            	}
	            	
	            } else { // OPERACIE DOLE & HORE
	            	for(int j=1; j<5; j++) { //vytvaranie posunom dole
	            		if (stop) {
	        				stop = false;
	        				break;
	        			}
	
	            			for(int k = 0; k<sucasnyUzol.getPoleVozidiel().size(); k++) { 
	            					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom dole
	            					if(sucasnyUzol.getPoleVozidiel().get(k).getSuradnicaY() != (suradnicaY+j+velkost-1)) { 
	            						if((suradnicaY+j) <= 6) {
	            						//operaciaDole(sucasnyUzol,suradnicaY+j+velkost-1);
	                        			// vrati novo vytvoreny uzol 
	                        			// ten sa prida do queue 
	            							Uzol novyStav = operaciaVpravo(sucasnyUzol,k,suradnicaY+j+velkost-1);
	            							if(!spracovany.contains(novyStav)){
	            								rad.add(novyStav);
	            							}
	            						}
	            					} else {
	            						stop = true;
	            						break;
	            					}
	            			}
	            		}
	            	}
	            stop = false;
	            	for(int j=1; j<5; j++) { //vytvaranie posunom hore
	            		if (stop) {
	        				stop = false;
	        				break;
	        			}
	            		
	            		
	            			for(int k = 0; k<sucasnyUzol.getPoleVozidiel().size(); k++) { 
	        					//prechadza zvysok celeho listu vozidiel, sleduje ci moze spravit krok smerom hore
	        					if(sucasnyUzol.getPoleVozidiel().get(k).getSuradnicaY() != (suradnicaY-j)) {
	        						if((suradnicaY-j) >= 1) {
	        						//operaciaHore(sucasnyUzol,suradnicaY+j);
	                    			// vrati novo vytvoreny uzol 
	                    			// ten sa prida do queue 
	        							Uzol novyStav = operaciaVpravo(sucasnyUzol,k,suradnicaY-j);
            							if(!spracovany.contains(novyStav)){
            								rad.add(novyStav);
            							}
	        						} 
	        					} else {
	        						stop = true;
	        						break;
	        					}
	            			}
	            	}
	        	}
	        spracovany.add(sucasnyUzol);	
		    }
        	
	}
	    
	    /*List<Vozidlo> pokusVozidla = new ArrayList<Vozidlo>();
	    cerveneVozidlo.setSuradnicaX(5);
	    pokusVozidla.add(cerveneVozidlo);
	    pokusVozidla.add(zeleneVozidlo);
	    pokusVozidla.add(oranzoveVozidlo);
	    
	    System.out.println(spracovany.contains(pociatocnyStav));
	    
	    Uzol pokusnyStav = new Uzol(pokusVozidla, pociatocnyStav , "VPRAVO");
	    System.out.println(spracovany.contains(pokusnyStav));
	    if(pokusnyStav.getPoleVozidiel().get(0).getSuradnicaX() == 5){
	    	System.out.println("Nasli sme cielovu poziciu");
	    	
	    }*/
	    
	    
	
	
	public static Uzol operaciaVpravo(Uzol stav, int indexVozidla, int posunNaPoziciu) {
		Uzol novyStav = new Uzol(stav.getPoleVozidiel(),stav,"Vpravo");

		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
		
		return novyStav;
	}
	
	public static Uzol operaciaVlavo(Uzol stav, int indexVozidla, int posunNaPoziciu) {
		Uzol novyStav = new Uzol(stav.getPoleVozidiel(),stav,"Vlavo");

		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaX(posunNaPoziciu);
		
		return novyStav;
	}
	
	public static Uzol operaciaDole(Uzol stav, int indexVozidla, int posunNaPoziciu) {
		Uzol novyStav = new Uzol(stav.getPoleVozidiel(),stav,"Dole");

		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
		
		return novyStav;
	}
	
	public static Uzol operaciaHole(Uzol stav, int indexVozidla, int posunNaPoziciu) {
		Uzol novyStav = new Uzol(stav.getPoleVozidiel(),stav,"Hore");

		novyStav.getPoleVozidiel().get(indexVozidla).setSuradnicaY(posunNaPoziciu);
		
		return novyStav;
	}
	
}
	/*public boolean BFS(Uzol pociatocnyStav){
		
	Queue<Uzol> queue = new LinkedList<>();
    HashSet explored = new HashSet();
    queue.add(pociatocnyStav);
    explored.add(pociatocnyStav);

    while(!queue.isEmpty()){
        Uzol current = queue.remove();
        if(current.equals(this.goalNode)) {
            System.out.println(explored);
            return true;
        }
        else{
            if(current.getChildren().isEmpty())
                return false;
            else
                queue.addAll(current.getChildren());
        }
        explored.add(current);
    }

    return false;
	}*/
	
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




