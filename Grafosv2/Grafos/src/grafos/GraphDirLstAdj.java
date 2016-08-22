package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphDirLstAdj<N> {
    
   private class LstNode{
       ArrayList<LstNode> listaAdjacencias;
       N dado;
       
       public LstNode(N newDado){
           listaAdjacencias = new ArrayList<>();
           dado = newDado;
       }
       
   }
       
    private final HashMap<N,LstNode> listaNodos;
       
    public GraphDirLstAdj(){
        listaNodos = new HashMap<>();
    }
   
   public void addNode(N dado){
       listaNodos.put(dado, new LstNode(dado));
   }
   
   public boolean addEdge(N dadoOrigem, N dadoDestino){
       
       LstNode nodoOrigem = listaNodos.get(dadoOrigem);
       LstNode nodoDestino = listaNodos.get(dadoDestino);
       return nodoOrigem.listaAdjacencias.add(nodoDestino);
       
   }
   
   public String breadFirst( N first ){
       
       LstNode arbitrary = listaNodos.get(first);
       
       HashMap<LstNode,Boolean> nodosVisitados = new HashMap<>();
       ArrayList<LstNode> caminhoPercorrido = new ArrayList<>();
       Queue Q = new Queue();
       
       nodosVisitados.put(arbitrary,Boolean.TRUE);
       caminhoPercorrido.add(arbitrary);
       Q.insert(arbitrary);
       
       while(!Q.isEmpty()){
           
           arbitrary = Q.get().dado;
           
           for(LstNode n : arbitrary.listaAdjacencias){
               arbitrary = n;
                if(arbitrary.dado.equals(first))
                    return ArrayToString(caminhoPercorrido);
                if(nodosVisitados.get(n) == null){
                    nodosVisitados.put(arbitrary,true);
                    Q.insert(arbitrary);
                    caminhoPercorrido.add(arbitrary);
                }
           }
       }
       
       return ArrayToString(caminhoPercorrido);
       
   }
   
   public String depthFirst(N first){

       ArrayList<LstNode> caminhoPercorrido = new ArrayList<>();
       HashMap<LstNode,Boolean> nodosMarcados = new HashMap<>();
       depthFirst(first,caminhoPercorrido, nodosMarcados);
       return ArrayToString(caminhoPercorrido);
       
   }
   
   private void depthFirst(N first, ArrayList<LstNode> caminhoPercorrido, HashMap<LstNode,Boolean> nodosMarcados){
       if(caminhoPercorrido.size()>0 && caminhoPercorrido.get(0).dado.equals(first)) return;
       LstNode arbitrary = listaNodos.get(first);
       
       caminhoPercorrido.add(arbitrary);
       nodosMarcados.put(arbitrary, Boolean.TRUE);
       
       if(arbitrary.listaAdjacencias != null)
       for(LstNode n : arbitrary.listaAdjacencias)
            depthFirst((N)n.dado, caminhoPercorrido,nodosMarcados);
       
   }
   
   private String ArrayToString(ArrayList<LstNode> l){
       
       String resultado = "";
       for(LstNode n : l){
           resultado += n.dado + " ";
       }
       return resultado;
   }
   
   @Override
   public String toString(){
       
       String resultado = "";
       for(LstNode n : listaNodos.values()){
           resultado += n.dado + "(";
           for(LstNode a : n.listaAdjacencias){
            resultado += "[" + a.dado + "]";
           }
           resultado += ")";
       }
       return resultado;
   }

   private class Queue<N> {

  // ReferÃªncia para  o primeiro elemento
  private Node first;
  private Node last;

  /*
   Node
   A lista usa esta classe interna. Cada nodo contÃ©m
   uma informaÃ§Ã£o e referÃªncia para o prÃ³ximo nodo.
  */
  public class Node {
    Node next;
    LstNode dado;

    Node( LstNode newData ) {
      dado = newData;
      next = null;
    }
  }

  public Queue() {
    first = null;
    last = null;
  }

  public void insert( LstNode data ) {
    Node p = new Node( data );
    p.next = null;
    if(first == null){
        first = p;
        last = p;
        return;
    }
    last.next = p;
  }
  
  public void remove(){
      if(first == null) return;
      first = first.next;
  }
  
  public Node get(){
      if(first == null) return null;
      Node p = first;
      first = first.next;
      return p;
  }
  
  public boolean isEmpty(){
      return first == null;
  }

  public void print( )  {
    System.out.print( "[ " );
    Node p = first;
    while ( p!= null ) {
      System.out.print( p.dado + " " );
      p = p.next;
    }
    System.out.println( "]" );
  }
}
    
}
