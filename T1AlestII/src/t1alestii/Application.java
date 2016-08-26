package t1alestii;
// TODO: Unificar NODOS
import java.util.Scanner;


public class Application {
    
    public static void main(String args[]){
        
        Graph grafo = new Graph();
        
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()){
            
            String line = in.next();
            
            if(line.matches("[0-9]+")) grafo.addNode(line);
            
            if(line.equals("aresta")) grafo.addEdge(in.next(), in.next(), in.next());
            
            if(line.equals("rnodo")) grafo.removeNode(in.next());
            
            if(line.equals("raresta")) grafo.removeEdge(in.next(),in.next(),in.next());
            
            if(line.equals("quit")) System.exit(0);
            
            System.out.println(grafo.toString());
            
        }
    }
}
