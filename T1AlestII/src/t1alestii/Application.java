package t1alestii;
// TODO: Unificar NODOS
import java.util.Scanner;


public class Application {
    
    public static void main(String args[]) throws GraphException{
        
        Graph grafo = new Graph();
        
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()){
            
            String line = in.next();
            
            if(line.matches("[0-9]+")) grafo.addNode(line);
            
            if(line.equals("a")) grafo.addEdge(in.next(), in.next(), in.next());
            
            if(line.equals("rnodo")) grafo.removeNode(in.next());
            
            if(line.equals("raresta")) grafo.removeEdge(in.next(),in.next(),in.next());
            
            if(line.equals("size")) System.out.println(grafo.size());
            
            if(line.equals("findpath")) System.out.println(grafo.GenericArrayToString(grafo.findPath(in.next(),in.next())));
            
            if(line.equals("tw")) System.out.println(grafo.GenericArrayToString(grafo.traversalWidth(in.next())));
            
            if(line.equals("td")) System.out.println(grafo.GenericArrayToString(grafo.traversalDepth(in.next())));
            
            if(line.equals("it")) System.out.println(grafo.GenericIteratorToString(grafo.iteratorWidth(in.next())));
            
            if(line.equals("id")) System.out.println(grafo.GenericIteratorToString(grafo.iteratorDepth(in.next())));
            
            if(line.equals("quit")) System.exit(0);
            
            System.out.println(grafo.toString());
            
        }
    }
}
