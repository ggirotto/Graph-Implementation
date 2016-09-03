package t1alestii;

public class Queue<N> {

  // ReferÃªncia para  o primeiro elemento
  private Node first;

  /*
   Node
   A lista usa esta classe interna. Cada nodo contÃ©m
   uma informaÃ§Ã£o e referÃªncia para o prÃ³ximo nodo.
  */
  public class Node {
    Node next;
    N nodo;

    Node( N newData ) {
      nodo = newData;
      next = null;
    }
  }

  public Queue() {
    first = null;
  }

  public void insert( N data ) {
    
    Node p = new Node( data );
    if(first == null){
        first = p;
        return;
    }
    
    Node aux = first;
    while(aux.next != null)
        aux = aux.next;
    aux.next = p;
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
}