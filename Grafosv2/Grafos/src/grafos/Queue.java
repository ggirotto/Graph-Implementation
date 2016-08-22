package grafos;

public class Queue<N> {

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
    matrixNode dado;

    Node( matrixNode newData ) {
      dado = newData;
      next = null;
    }
  }

  public Queue() {
    first = null;
    last = null;
  }

  public void insert( matrixNode data ) {
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