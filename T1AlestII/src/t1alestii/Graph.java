package t1alestii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graph<N,A> implements GraphTAD<N,A> {
    
    public class matrixNode{
    
        private final N dado;
        
        matrixNode(N newDado){
            dado = newDado;
        }
        
        public N getDado() { return dado; }
        
    }
    
    Matrix matrizAdjacencias;
    HashMap<N,matrixNode> listaNodos;
    
    public Graph(){
        
        matrizAdjacencias = new Matrix();
        listaNodos = new HashMap<>();
        
    }
    
    @Override
    public void addNode(N elem) {
        
       matrixNode p = new matrixNode(elem);
       matrizAdjacencias.addNodo(p);
       listaNodos.put(elem, p);
       
    }

    @Override
    public void addEdge(N orig, N dest, A val) {
        
       matrixNode nodoOrigem = listaNodos.get(orig);
       matrixNode nodoDestino = listaNodos.get(dest);
       
       matrizAdjacencias.addEdge(nodoOrigem, nodoDestino, val);
       
    }

    @Override
    public void removeNode(N elem) {
        
        matrixNode remove = listaNodos.get(elem);
        if(remove==null) return;
        matrizAdjacencias.removeNodo(remove);
        listaNodos.remove(elem);
        
    }

    @Override
    public void removeEdge(N orig, N dest, A val) {
        
       matrixNode nodoOrigem = listaNodos.get(orig);
       matrixNode nodoDestino = listaNodos.get(dest);
       
       matrizAdjacencias.removeEdge(nodoOrigem, nodoDestino, val);
        
    }

    @Override
    public List<N> traversalDepth(N orig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<N> traversalWidth(N orig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<N> findPath(N orig, N dest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<N> iteratorWidth(N origem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<N> iteratorDepth(N origem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
           int linhaNodo = matrizAdjacencias.getLinhaDoDado(n);
           ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
           for(matrixNode p : adjacentes){
               resultado += "[" + p.getDado() + "-" + matrizAdjacencias.getDado(linhaNodo, matrizAdjacencias.getLinhaDoDado(p)) + "]";
           }
           resultado += ")";
       }
       return resultado;
   }
    
    
}
