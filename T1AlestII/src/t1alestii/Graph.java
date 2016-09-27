package t1alestii;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private class IteratorDepth<N> implements Iterator<N> {

        // PILHA
        // ESTRUTURA DE MARCACAO
        private matrixNode current;
        private final HashSet<matrixNode> listaMarcados;
        private final Stack pilha;

        public IteratorDepth(matrixNode origem) {
            current = origem;
            listaMarcados = new HashSet<>();
            pilha = new Stack();
            listaMarcados.add(origem);
            pilha.push(origem);
        }

        @Override
        public boolean hasNext() {
            return !pilha.isEmpty();
        }

        @Override
        public N next() {

            N dado = (N) current.getDado();
            listaMarcados.add(current);
            current = findNextCurrent(current);
            return dado;

        }

        private matrixNode findNextCurrent(matrixNode current) {

            ArrayList<matrixNode> adjacentes = getAdjacentes(current.getDado());

            for (matrixNode n : adjacentes) {
                if (listaMarcados.contains(n) == false) {
                    pilha.push(current);
                    return n;
                }
            }
            if (!hasNext()) {
                return null;
            }

            return findNextCurrent((matrixNode) pilha.pop().data);
        }

    }

    private class IteratorWidth<N> implements Iterator<N> {

        // FILA
        // ESTRTUURA DE MARCACAO
        private matrixNode origem;
        private final HashSet<matrixNode> nodosMarcados;
        private final Queue fila;

        public IteratorWidth(matrixNode origem) {
            this.origem = origem;
            nodosMarcados = new HashSet<>();
            fila = new Queue();
            nodosMarcados.add(origem);
            fila.insert(origem);
        }

        @Override
        public boolean hasNext() {
            return !fila.isEmpty();
        }

        @Override
        public N next() {
            origem = (matrixNode) fila.get().nodo;

            ArrayList<matrixNode> adjacentes = getAdjacentes(origem.getDado());

            for (matrixNode n : adjacentes) {
                if (nodosMarcados.contains(n) == false) {
                    fila.insert(n);
                    nodosMarcados.add(n);
                }
            }

            return (N) origem.getDado();
        }

    }

    @Override
    public void addNode(N elem) {

        matrixNode p = new matrixNode(elem);
        matrizAdjacencias.addNodo(p);
        listaNodos.put(elem, p);

    }
    
    // Adicionar aresta valorada
    @Override
    public void addEdge(N orig, N dest, A val) throws GraphException {

        matrixNode nodoOrigem = listaNodos.get(orig);
        if (nodoOrigem == null) {
            throw new GraphException("O nodo de origem não existe no grafo");
        }

        matrixNode nodoDestino = listaNodos.get(dest);
        if (nodoDestino == null) {
            throw new GraphException("O nodo de destino não existe no grafo");
        }

        matrizAdjacencias.addEdge(nodoOrigem, nodoDestino, val);

    }
    
    // Adiciona aresta não-valorada
    public void addEdge(N orig, N dest) throws GraphException {

        matrixNode nodoOrigem = listaNodos.get(orig);
        if (nodoOrigem == null) {
            throw new GraphException("O nodo de origem não existe no grafo");
        }

        matrixNode nodoDestino = listaNodos.get(dest);
        if (nodoDestino == null) {
            throw new GraphException("O nodo de destino não existe no grafo");
        }

        matrizAdjacencias.addEdge(nodoOrigem, nodoDestino);

    }

    @Override
    public void removeNode(N elem) throws GraphException {

        matrixNode remove = listaNodos.remove(elem);
        if (remove == null) {
            throw new GraphException("Este nodo não existe no grafo!");
        }

        matrizAdjacencias.removeNodo(remove);

    }
    
    // Remove aresta valorada
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
    
    // Remove aresta não-valorada
    public void removeEdge(N orig, N dest) throws GraphException {

        matrixNode nodoOrigem = listaNodos.get(orig);
        matrixNode nodoDestino = listaNodos.get(dest);

        if (nodoOrigem == null) {
            throw new GraphException("O nodo de origem não existe no grafo");
        } else if (nodoDestino == null) {
            throw new GraphException("O nodo de destino não existe no grafo");
        }

        matrizAdjacencias.removeEdge(nodoOrigem, nodoDestino);

    }
    
    /*
        ALGORITMO DE DIJKSTRA
    Requer:
    - Todas aresta devem possuir somente um valor e este deve ser um natural
     */
    public ArrayList<String> dijkstra(N orig) throws GraphException {

        if (!listaNodos.containsKey(orig)) {
            throw new GraphException("O nodo de origem não existe no grafo");
        }

        LinkedHashMap<matrixNode, Double> hash = dijkstraAlgorithm(orig);

        ArrayList<String> resultadoDij = new ArrayList<>();

        for (Map.Entry<matrixNode, Double> entry : hash.entrySet()) {
            String aux = entry.getKey().getDado() + " - " + entry.getValue();
            resultadoDij.add(aux);
        }

        return resultadoDij;
    }

    private LinkedHashMap<matrixNode, Double> dijkstraAlgorithm(N orig) {

        LinkedHashMap<matrixNode, Double> D = new LinkedHashMap<>();
        D.put(listaNodos.get(orig), 0.0);
        for (matrixNode n : listaNodos.values()) {
            if (!n.equals(listaNodos.get(orig))) {
                D.put(n, Double.POSITIVE_INFINITY);
            }
        }

        PriorityQueue Q = new PriorityQueue();

        for (matrixNode n : listaNodos.values()) {
            Q.addNode(n, D.get(n));
        }

        matrixNode u;

        while (!Q.isEmpty()) {
            u = (matrixNode) Q.removeMin();

            // Pega os adjacentes a u
            int linhaNodo = matrizAdjacencias.getLinhaDoDado(u);
            ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);

            for (matrixNode z : adjacentes) {
                if (Q.contains(z)) {
                    if ((D.get(u) + getValorAresta(u, z)) < D.get(z)) {
                        D.put(z, D.get(u) + getValorAresta(u, z));
                        Q.updateKey(z, D.get(z));
                    }
                }
            }

        }

        return D;
    }

    private double edgeCost(matrixNode i, matrixNode j) {
        
        ArrayList<Object> valoresArestas = matrizAdjacencias.getEdge(i, j);
        if(valoresArestas == null) return Double.POSITIVE_INFINITY;
        if(valoresArestas.size() == 1 && Integer.parseInt(valoresArestas.get(0)+"") == -1) return Double.POSITIVE_INFINITY;
        return Double.parseDouble(valoresArestas.get(0).toString());
        
    }
    
    private double min(double a, double b){
        if (a>b) return b;
        return a;
    }
    
    /*
    Requer:
    - Os valores das arestas existentes devem ser numeros
    - Arestas com mais de um valor, será considerado o primeiro
    */
    public double[][] floydAlgorithm() {
        
        double D[][] = new double[listaNodos.size()][listaNodos.size()];
        
        int n = 0,m = 0;
        
        for(matrixNode a : listaNodos.values()){
            for(matrixNode b : listaNodos.values()){
                D[n][m] = edgeCost(a, b);
                m++;
            }
            m = 0;
            n++;
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
        // TODO -> PQ TA RETORNANDO 6??
        return D;
    }

    private double getValorAresta(matrixNode a, matrixNode b) {
        double aux = Double.parseDouble(matrizAdjacencias.getEdge(a, b).get(0) + "");
        return aux;
    }

    @Override
    public List<N> traversalDepth(N orig) throws GraphException {

        if (listaNodos.containsKey(orig) == false) {
            throw new GraphException("O nodo de origem não existe no grafo");
        }
        ArrayList<N> caminhoPercorrido = new ArrayList<>();
        HashSet<matrixNode> nodosMarcados = new HashSet<>();
        traversalDepth(orig, caminhoPercorrido, nodosMarcados);
        return caminhoPercorrido;

    }

    private List<N> traversalDepth(N orig, ArrayList<N> caminhoPercorrido, HashSet<matrixNode> nodosMarcados) {

        matrixNode arbitrary = listaNodos.get(orig);

        caminhoPercorrido.add(arbitrary.getDado());
        nodosMarcados.add(arbitrary);

        ArrayList<matrixNode> adjacentes = getAdjacentes(arbitrary.getDado());

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
        caminhoPercorrido.add(arbitrary.getDado());
        Q.insert(arbitrary);

        while (!Q.isEmpty()) {

            arbitrary = (matrixNode) Q.get().nodo;

            ArrayList<matrixNode> adjacentes = getAdjacentes(arbitrary.getDado());

            for (matrixNode n : adjacentes) {

                if (nodosVisitados.contains(n) == false) {
                    arbitrary = n;
                    if (arbitrary.getDado().equals(orig)) {
                        return caminhoPercorrido;
                    }
                    nodosVisitados.add(arbitrary);
                    Q.insert(arbitrary);
                    caminhoPercorrido.add(arbitrary.getDado());
                }
            }
        }

        return caminhoPercorrido;

    }

    @Override
    public List<N> findPath(N orig, N dest) throws GraphException {

        if (listaNodos.containsKey(orig) == false) {
            throw new GraphException("O nodo de origem não existe no grafo");
        }
        if (listaNodos.containsKey(dest) == false) {
            throw new GraphException("O nodo de destino não existe no grafo");
        }
        HashSet<matrixNode> nodosMarcados = new HashSet<>();
        ArrayList<N> caminhoPercorrido = new ArrayList<>();
        return findPath(orig, dest, nodosMarcados, caminhoPercorrido);

    }

    private List<N> findPath(N atual, N dest, HashSet<matrixNode> nodosMarcados, ArrayList<N> caminhoPercorrido) {

        if (atual.equals(dest)) {
            caminhoPercorrido.add(listaNodos.get(dest).getDado());
            return caminhoPercorrido;
        }

        matrixNode arbitrary = listaNodos.get(atual);

        nodosMarcados.add(arbitrary);
        caminhoPercorrido.add(arbitrary.getDado());

        ArrayList<matrixNode> adjacentes = getAdjacentes(arbitrary.getDado());

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

        if (listaNodos.containsKey(origem) == false) {
            throw new GraphException("O nodo de origem não existen no grafo");
        }
        return new IteratorWidth(listaNodos.get(origem));

    }

    @Override
    public Iterator<N> iteratorDepth(N origem) throws GraphException {

        if (listaNodos.containsKey(origem) == false) {
            throw new GraphException("O nodo de origem não existen no grafo");
        }
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
            ArrayList<matrixNode> adjacentes = getAdjacentes(n.getDado());
            for (matrixNode p : adjacentes) {
                resultado += "[" + p.getDado() + "-" + matrizAdjacencias.getDado(linhaNodo, matrizAdjacencias.getLinhaDoDado(p)) + "]";
            }

            resultado += ")";
        }
        return resultado;
    }

    // Pega os nodos adjacentes
    // Recebe o dado de um matrixNode como argumento
    private ArrayList<matrixNode> getAdjacentes(N dado) {

        matrixNode arbitrary = listaNodos.get(dado);
        int linhaNodo = matrizAdjacencias.getLinhaDoDado(arbitrary);
        ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
        return adjacentes;
    }

    // Retorna os dados dos ajacentes ao nodo 'dado'
    // Criado para facilitar os testes do JUnit visto que ele não tem acesso a classe matrixNode
    public ArrayList<N> getAdjacentesDados(N dado) {

        matrixNode arbitrary = listaNodos.get(dado);
        int linhaNodo = matrizAdjacencias.getLinhaDoDado(arbitrary);
        ArrayList<matrixNode> adjacentes = matrizAdjacencias.getAdjacentes(linhaNodo);
        ArrayList<N> adj = new ArrayList<>();
        for (matrixNode n : adjacentes) {
            adj.add(n.getDado());
        }
        return adj;
    }

}
