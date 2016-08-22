package grafos;
// TODO: Unificar NODOS
import java.util.Scanner;


public class App {
    
    public static void main(String args[]){
        
        GraphDirLstAdj grafo = new GraphDirLstAdj();
        
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()){
            
            String line = in.next();
            
            if(line.matches("[0-9]+")) grafo.addNode(line);
            
            if(line.equals("a")) grafo.addEdge(in.next(), in.next());
            
            if(line.equals("bf")) System.out.println(grafo.breadFirst(in.next()));
            
            if(line.equals("df")) System.out.println(grafo.depthFirst(in.next()));
            
            if(line.equals("quit")) System.exit(0);
            
            System.out.println(grafo.toString());
            
        }
    }
}
