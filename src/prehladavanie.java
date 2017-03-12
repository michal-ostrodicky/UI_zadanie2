import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class prehladavanie {

	public static void main(String[] args) {
		List<Vozidlo> zaciatokVozidla = new ArrayList<Vozidlo>();
		//Vytvaranie vozidiel do vstupneho stavu
		Vozidlo cerveneVozidlo = new Vozidlo("Cervene", 2, 3, 2, 'h');
		Vozidlo zeleneVozidlo = new Vozidlo("Zelene", 3, 2, 4, 'v');
		Vozidlo oranzoveVozidlo = new Vozidlo("Oranzove", 2, 1, 1, 'h');
		zaciatokVozidla.add(cerveneVozidlo);
		zaciatokVozidla.add(zeleneVozidlo);
		zaciatokVozidla.add(oranzoveVozidlo);
		
		Uzol pociatocnyStav = new Uzol(zaciatokVozidla, null, null);

		
		pociatocnyStav.getPoleVozidiel().get(0).vypisDetailyVozidla();
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
	 } */

}


