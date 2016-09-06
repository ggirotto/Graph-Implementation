package t1alestii;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class Graph<N, A, E> implements GraphTAD<N, A> {
    
    public class matrixNode {

        private final N dado;

        matrixNode(N newDado) {
            dado = newDado;
        }

        public N getDado() {
            return dado;
        }

    }
    
    private final Matrix matrizAdjacencias;
    private final LinkedHashMap<N, matrixNode> listaNodos;

    public Graph() {

        matrizAdjacencias = new Matrix();
        listaNodos = new LinkedHashMap<>();

    }
    
    private class IteratorDepth<N> implements Iterator<N>{
        
        private N current;
        
        public IteratorDepth(N origem){
            current = origem;
        }
        
        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public N next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private class IteratorWidth<N> implements Iterator<N>{
        
        private final List caminho;
        private int index;
        
        public IteratorWidth(matrixNode origem) throws GraphException{
            caminho = traversalWidth(origem.getDado());
            index = 0;
        }
        
        @Override
        public boolean hasNext() {
            return index != caminho.size();
        }

        @Override
        public N next() {
            N dado = (N) caminho.get(index);
            index++;
            return dado;
        }
        
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
    public void removeNode(N elem) throws GraphException {

        matrixNode remove = listaNodos.remove(elem);
        if (remove == null) {
            throw new GraphException("Este nodo não existe no grafo!");
        }

        matrizAdjacencias.removeNodo(remove);

    }

    @Override
    public void removeEdge(N orig, N dest, A val) throws GraphException {

        matrixNode nodoOrigem = listaNodos.get(orig);
        matrixNode nodoDestino = listaNodos.get(dest);

        if (nodoOrigem == null) {
            throw new GraphException("O nodo de origem não existe no grafo");
        } else if (nodoDestino == null) {
            throw new GraphException("O nodo de destino não existe no grafo");
        }

        matrizAdjacencias.removeEdge(nodoOrigem, nodoDestino, val);

    }

    @Override
    public List<N> traversalDepth(N orig) {

        ArrayList<N> caminhoPercorrido = new ArrayList<>();
        HashSet<matrixNode> nodosMarcados = new HashSet<>();
        traversalDepth(orig, caminhoPercorrido, nodosMarcados);
        return caminhoPercorrido;

    }

    private List<N> traversalDepth(N orig, ArrayList<N> caminhoPercorrido, HashSet<matrixNode> nodosMarcados) {

        matrixNode arbitrary = listaNodos.get(orig);

        caminhoPercorrido.add(arbitrary.dado);
        nodosMarcados.add(arbitrary);

        int linhaNodo = matrizAdjacencias.getLinhaDoDado(arbitrary);
        ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);

        for (matrixNode n : adjacentes) {
            if (nodosMarcados.contains(n) == false) {
                traversalDepth((N) n.getDado(), caminhoPercorrido, nodosMarcados);
            }
        }

        return caminhoPercorrido;

    }

    @Override
    public List<N> traversalWidth(N orig) throws GraphException {

        matrixNode arbitrary = listaNodos.get(orig);

        if (arbitrary == null) {
            throw new GraphException("O nodo de origem não existe no grafo");
        }

        HashSet<matrixNode> nodosVisitados = new HashSet<>();
        ArrayList<N> caminhoPercorrido = new ArrayList<>();
        Queue Q = new Queue();

        nodosVisitados.add(arbitrary);
        caminhoPercorrido.add(arbitrary.dado);
        Q.insert(arbitrary);

        while (!Q.isEmpty()) {

            arbitrary = (matrixNode) Q.get().nodo;

            int linhaNodo = matrizAdjacencias.getLinhaDoDado(arbitrary);

            ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);

            for (matrixNode n : adjacentes) {

                if (nodosVisitados.contains(n) == false) {
                    arbitrary = n;
                    if (arbitrary.getDado().equals(orig)) {
                        return caminhoPercorrido;
                    }
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
            if (resultado != null) {
                return resultado;
            }
        }
        caminhoPercorrido.remove(atual);
        return null;
    }

    @Override
    public int size() {
        return listaNodos.size();
    }

    @Override
    public Iterator<N> iteratorWidth(N origem) throws GraphException {

        return new IteratorWidth(listaNodos.get(origem));

    }

    @Override
    public Iterator<N> iteratorDepth(N origem) throws GraphException {

        return new IteratorDepth(listaNodos.get(origem));

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
