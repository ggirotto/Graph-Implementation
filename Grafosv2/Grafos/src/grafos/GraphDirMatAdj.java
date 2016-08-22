package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphDirMatAdj<N> {
    
    Matrix matrizAdjacencias;
    HashMap<N,matrixNode> listaNodos;
    
    public GraphDirMatAdj(){
        
        matrizAdjacencias = new Matrix();
        listaNodos = new HashMap<>();
        
    }
    
    public void addNode(N dado){
        
       matrixNode p = new matrixNode(dado);
       matrizAdjacencias.addNodo(p);
       listaNodos.put(dado, p);
       
   }
   
   public void addEdge(N dadoOrigem, N dadoDestino){
       
       matrixNode nodoOrigem = listaNodos.get(dadoOrigem);
       matrixNode nodoDestino = listaNodos.get(dadoDestino);
       
       matrizAdjacencias.add(nodoOrigem, nodoDestino);
       
   }
   
   public String breadFirst( N first ){
       
       matrixNode arbitrary = listaNodos.get(first);
       
       //if(arbitrary == null) return null;
       
       HashMap<matrixNode,Boolean> nodosVisitados = new HashMap<>();
       ArrayList<matrixNode> caminhoPercorrido = new ArrayList<>();
       Queue Q = new Queue();
       
       nodosVisitados.put(arbitrary,Boolean.TRUE);
       caminhoPercorrido.add(arbitrary);
       Q.insert(arbitrary);
       
       while(!Q.isEmpty()){
           
           arbitrary = Q.get().dado;
           
           int linhaNodo = matrizAdjacencias.getLinha(arbitrary);
           
           ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
           
           for(matrixNode n : adjacentes){
               
               if(nodosVisitados.get(n) == null || nodosVisitados.get(n) == false){
                   arbitrary = n;
                   if(arbitrary.getDado().equals(first))
                       return ArrayToString(caminhoPercorrido);
                   nodosVisitados.put(arbitrary,true);
                   Q.insert(arbitrary);
                   caminhoPercorrido.add(arbitrary);
               }
           }
       }
       
       return ArrayToString(caminhoPercorrido);
       
   }
   
   public String depthFirst(N first){

       ArrayList<matrixNode> caminhoPercorrido = new ArrayList<>();
       HashMap<matrixNode,Boolean> nodosMarcados = new HashMap<>();
       depthFirst(first,caminhoPercorrido, nodosMarcados);
       return ArrayToString(caminhoPercorrido);
       
   }
   
   private void depthFirst(N first, List<matrixNode> caminhoPercorrido, HashMap<matrixNode,Boolean> nodosMarcados){
       if(caminhoPercorrido.size()>0 && caminhoPercorrido.get(0).getDado().equals(first)) return;
       matrixNode arbitrary = listaNodos.get(first);
       
       caminhoPercorrido.add(arbitrary);
       nodosMarcados.put(arbitrary, Boolean.TRUE);
             
       int linhaNodo = matrizAdjacencias.getLinha(arbitrary);
       ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
       
       for(matrixNode n : adjacentes){
           if(nodosMarcados.get(n) == null || nodosMarcados.get(n) == false)
               depthFirst((N)n.getDado(), caminhoPercorrido,nodosMarcados);
       }
       
   }
   
   private String ArrayToString(List<matrixNode> l){
       
       String resultado = "";
       for(matrixNode n : l){
           resultado += n.getDado() + " ";
       }
       return resultado;
   }
   
   @Override
   public String toString(){
       
       String resultado = "";
       for(matrixNode n : listaNodos.values()){
           
           resultado += n.getDado() + "(";
           int linhaNodo = matrizAdjacencias.getLinha(n);
           ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
           for(matrixNode p : adjacentes){
               resultado += "[" + p.getDado() + "]";
           }
           resultado += ")";
       }
       return resultado;
   }
   
}
