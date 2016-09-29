package t1alestii;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Application {
    
    public static void main(String args[]){
        
        Graph grafo = new Graph();
        
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()){
            
            String line = in.next();
            
            // Adiciona um nodo
            if(line.equals("add")) grafo.addNode(in.next());
            
            // Adiciona uma aresta valorada
            try{
                if(line.equals("addarestav")) grafo.addEdge(in.next(), in.next(), in.next());
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Adiciona uma aresta não valorada
            try{
                if(line.equals("addarestanv")) grafo.addEdge(in.next(), in.next());
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Remove um Nodo
            try{
                if(line.equals("rnodo")) grafo.removeNode(in.next());
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Remove uma aresta valorada
            try{
                if(line.equals("rarestav")) grafo.removeEdge(in.next(),in.next(),in.next());
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Remove uma aresta não-valorada
            try{
                if(line.equals("rarestanv")) grafo.removeEdge(in.next(),in.next());
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Número de nodos no grafo
            if(line.equals("size")) System.out.println(grafo.size());
            
            // Encontra um caminho entre dois nodos
            try{
                if(line.equals("findpath")) System.out.println(grafo.GenericArrayToString(grafo.findPath(in.next(),in.next())));
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Caminhamento em largura
            try{
                if(line.equals("tw")) System.out.println(grafo.GenericArrayToString(grafo.traversalWidth(in.next())));
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Caminhamento em profundidade
            try{
                if(line.equals("td")) System.out.println(grafo.GenericArrayToString(grafo.traversalDepth(in.next())));
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Devolve o iterador do caminhamento em largura
            try{
                if(line.equals("itw")) imprimeIterador(grafo.iteratorWidth(in.next()));
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Devolve o iterador do caminhamento em profundidade
            try{
                if(line.equals("itd")) imprimeIterador(grafo.iteratorDepth(in.next()));
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Algoritmo de Dijkstra
            try{
                if(line.equals("dijkstra")) imprimeDijkstra(grafo.dijkstra(in.next()));
            } catch (GraphException e){
                System.out.println("Exceção " + e + ", tenta novamente");
                continue;
            }
            
            // Algoritmo de Floyd
            if(line.equals("floyd")) imprimeFloyd(grafo.floydAlgorithm());
            
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
    private static void imprimeIterador(Iterator iterador){
        
        while(iterador.hasNext()){
            System.out.print(iterador.next() + " ");
        }
        System.out.println();
    }
    
    // Método para imprimir o resultado do algoritmo de Dijkstra
    private static void imprimeDijkstra(ArrayList<String> result){
        
        for(String s : result)
            System.out.println(s);
    }
    
    private static void imprimeFloyd(double [][] matrix){
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j] == Double.POSITIVE_INFINITY)
                    System.out.print(matrix[i][j] + "\t");
                else
                    System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println();
        }
        
    }
    
}
