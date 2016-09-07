package t1alestii;

import java.util.List;

public interface GraphTAD <N,A > extends IterableGraph <N>  {

 /**
  * Adiciona um novo nodo no grafo.
  */
 public abstract void addNode(N elem);

 /**
  * Adiciona uma nova aresta no grafo.
  */
 public abstract void addEdge(N orig, N dest, A val) throws GraphException;

 /**
  * Remove um nodo do grafo, junto com todas as arestas que incidem de e para o nodo.
  */
 public abstract void removeNode(N elem) throws GraphException;

 /**
  * Remove uma aresta do grafo.
  */
 public abstract void removeEdge(N orig, N dest, A val) throws GraphException;


 /**
  * Retorna uma lista de nodos, após fazer caminhamento em profundidade.
  */
 public abstract List < N > traversalDepth(N orig) throws GraphException;

 /**
  * Retorna uma lista de nodos, após fazer caminhamento em amplitude.
  */
 public abstract List < N > traversalWidth(N orig) throws GraphException;

 /**
  * Retorna um caminho qualquer entre dois nodos.
  */
 public abstract List < N > findPath(N orig, N dest) throws GraphException;

 /**
  * Retorna o n�mero de nodos de um grafo.
  */
 public abstract int size();

 /**
  * Retorna uma String com o conteúdo do grafo.
  */
 public abstract String toString();

}