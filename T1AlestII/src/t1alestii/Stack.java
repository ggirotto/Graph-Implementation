package t1alestii;


public class Stack<N> {

  // Referencia para  o primeiro elemento
  private Node top;

  /*
   Node
   A lista usa esta classe interna. Cada nodo contÃ©m
   uma informação e referÃªncia para o prÃ³ximo nodo.
  */
  public class Node {
    Node next;
    N data;

    Node( N newData ) {
      data = newData;
      next = null;
    }
    
  }

  public Stack() {
    top = null;
  }

  public void push( N data ) {
    Node p = new Node( data );
    p.next = top;
    top = p;
  }
  
  public Node pop(){
      Node aux = top;
      top = top.next;
      return aux;
  }
  
  public boolean isEmpty(){
      return top == null;
  }

  public void print( )  {
    System.out.println( "**********" );
    Node p = top;
    while ( p!= null ) {
      System.out.println( p.data );
      p = p.next;
    }
    System.out.println( "**********" );
  }

}
