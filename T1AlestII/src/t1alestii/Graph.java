package t1alestii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Graph<N,A,E> implements GraphTAD<N,A> {

    public class matrixNode {

        private final N dado;

        matrixNode(N newDado) {
            dado = newDado;
        }

        public N getDado() {
            return dado;
        }

    }
    
    private int iteratorIndex;
    private final Matrix matrizAdjacencias;
    private final HashMap<N, matrixNode> listaNodos;

    public Graph() {

        matrizAdjacencias = new Matrix();
        listaNodos = new HashMap<>();
        iteratorIndex = 0;

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
        if (remove == null) {
            return;
        }
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
        
        ArrayList<N> caminhoPercorrido = new ArrayList<>();
       HashSet<matrixNode> nodosMarcados = new HashSet<>();
       traversalDepth(orig,caminhoPercorrido, nodosMarcados);
       return caminhoPercorrido;
       
    }
    
    private List<N> traversalDepth(N orig, ArrayList<N> caminhoPercorrido, HashSet<matrixNode> nodosMarcados){
        
       matrixNode arbitrary = listaNodos.get(orig);
       
       caminhoPercorrido.add(arbitrary.dado);
       nodosMarcados.add(arbitrary);
             
       int linhaNodo = matrizAdjacencias.getLinhaDoDado(arbitrary);
       ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
       
       for(matrixNode n : adjacentes){
           if(nodosMarcados.contains(n) == false)
               traversalDepth((N)n.getDado(), caminhoPercorrido,nodosMarcados);
       }
       
       return caminhoPercorrido;
       
    }

    @Override
    public List<N> traversalWidth(N orig) {
        
        matrixNode arbitrary = listaNodos.get(orig);
       
       if(arbitrary == null) return null;
       
       HashSet<matrixNode> nodosVisitados = new HashSet<>();
       ArrayList<N> caminhoPercorrido = new ArrayList<>();
       Queue Q = new Queue();
       
       nodosVisitados.add(arbitrary);
       caminhoPercorrido.add(arbitrary.dado);
       Q.insert(arbitrary);
       
       while(!Q.isEmpty()){
           
           arbitrary = (matrixNode)Q.get().dado;
           
           int linhaNodo = matrizAdjacencias.getLinhaDoDado(arbitrary);
           
           ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
           
           for(matrixNode n : adjacentes){
               
               if(nodosVisitados.contains(n) == false){
                   arbitrary = n;
                   if(arbitrary.getDado().equals(orig))
                       return caminhoPercorrido;
                   nodosVisitados.add(arbitrary);
                   Q.insert(arbitrary);
                   caminhoPercorrido.add(arbitrary.dado);
               }
           }
       }
       
       return caminhoPercorrido;
       
    }

    @Override
    public List<N> findPath(N orig, N dest) {

        HashSet<matrixNode> nodosMarcados = new HashSet<>();
        ArrayList<N> caminhoPercorrido = new ArrayList<>();
        return findPath(orig, dest, nodosMarcados, caminhoPercorrido);

    }

    private List<N> findPath(N atual, N dest, HashSet<matrixNode> nodosMarcados, ArrayList<N> caminhoPercorrido) {

        if (atual.equals(dest)) {
            caminhoPercorrido.add(listaNodos.get(dest).dado);
            return caminhoPercorrido;
        }
        matrixNode arbitrary = listaNodos.get(atual);

        nodosMarcados.add(arbitrary);
        caminhoPercorrido.add(arbitrary.dado);

        int linhaNodo = matrizAdjacencias.getLinhaDoDado(arbitrary);
        ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
        
        List<N> resultado = null;
        for (matrixNode n : adjacentes) {
            if (nodosMarcados.contains(n) == false) {
                resultado = findPath((N) n.getDado(), dest, nodosMarcados, caminhoPercorrido);
            }
            if(resultado != null) return resultado;
            caminhoPercorrido.clear();
            caminhoPercorrido.add(listaNodos.get(atual).dado);
        }

        return null;
    }

    @Override
    public int size() {
        return listaNodos.size();
    }

    @Override
    public Iterator<N> iteratorWidth(N origem) {
        
        return traversalWidth(origem).iterator();
        
    }

    @Override
    public Iterator<N> iteratorDepth(N origem) {
        
        return traversalDepth(origem).iterator();
        
    }

    public String GenericArrayToString(List<N> l) {

        if (l == null) {
            return null;
        }

        String resultado = "";
        for (N dado : l) {
            resultado += dado + " ";
        }
        return resultado;
    }

    @Override
    public String toString() {

        String resultado = "";
        for (matrixNode n : listaNodos.values()) {

            resultado += n.getDado() + "(";
            int linhaNodo = matrizAdjacencias.getLinhaDoDado(n);
            ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
            for (matrixNode p : adjacentes) {
                resultado += "[" + p.getDado() + "-" + matrizAdjacencias.getDado(linhaNodo, matrizAdjacencias.getLinhaDoDado(p)) + "]";
            }
            resultado += ")";
        }
        return resultado;
    }

}
