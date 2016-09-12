package t1alestii;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Application {
    
    public static void main(String args[]) throws GraphException{
        
        Graph grafo = new Graph();
        
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()){
            
            String line = in.next();
            
            // Adiciona um nodo
            if(line.equals("add")) grafo.addNode(in.next());
            
            // Adiciona uma aresta
            if(line.equals("aresta")) grafo.addEdge(in.next(), in.next(), in.next());
            
            // Remove um Nodo
            if(line.equals("rnodo")) grafo.removeNode(in.next());
            
            // Remove uma aresta
            if(line.equals("raresta")) grafo.removeEdge(in.next(),in.next(),in.next());
            
            // Número de nodos no grafo
            if(line.equals("size")) System.out.println(grafo.size());
            
            // Encontra um caminho entre dois nodos
            if(line.equals("findpath")) System.out.println(grafo.GenericArrayToString(grafo.findPath(in.next(),in.next())));
            
            // Caminhamento em largura
            if(line.equals("tw")) System.out.println(grafo.GenericArrayToString(grafo.traversalWidth(in.next())));
            
            // Caminhamento em profundidade
            if(line.equals("td")) System.out.println(grafo.GenericArrayToString(grafo.traversalDepth(in.next())));
            
            // Devolve o iterador do caminhamento em largura
            if(line.equals("itw")) imprimeIterador(grafo.iteratorWidth(in.next()));
            
            // Devolve o iterador do caminhamento em profundidade
            if(line.equals("itd")) imprimeIterador(grafo.iteratorDepth(in.next()));
            
            // Algoritmo de Dijkstra (Bonus)
            if(line.equals("dijkstra")) imprimeDijkstra(grafo.dijkstra(in.next()));
            
            // Encerra
            if(line.equals("quit")) System.exit(0);
            
            /*
                Imprime o grafo no formato:
                Nodo1([Adj1-[valoresArestas][Adj2 - [valoresArestas]])Nodo2...
            */
            System.out.println(grafo.toString());
            
        }
    }
    
    // Método para imprimir um iterador (vai até o fim)
    public static void imprimeIterador(Iterator iterador){
        
        while(iterador.hasNext()){
            System.out.print(iterador.next() + " ");
        }
        System.out.println();
    }
    
    // Método para imprimir o resultado do algoritmo de Dijkstra
    public static void imprimeDijkstra(ArrayList<String> result){
        
        for(String s : result)
            System.out.println(s);
    }
}
