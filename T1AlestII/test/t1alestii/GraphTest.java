/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1alestii;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guilhermegirotto
 * @param <N>
 */
public class GraphTest<N> {
    
    public GraphTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Teste para adicionar um nodo
     */
    @Test
    public void testAddNode() {
        System.out.println("addNode");
        Graph instance = new Graph();
        assertEquals(instance.size(),0);
        instance.addNode(10);
        // Adiciona o nodo e testa para ver se o número de nodos aumentou em 1
        assertEquals(instance.size(),1);
    }

    /**
     * Teste para adicionar uma aresta
     * @throws t1alestii.GraphException
     */
    @Test
    public void testAddEdge() throws GraphException{
        System.out.println("addEdge");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addNode(11);
        instance.addEdge(10, 11, 12);
        
        // Pega os adjacentes ao 10 para ver se a aresta foi mesmo adicionada
        ArrayList<Integer> adjacentes = instance.getAdjacentesDados(10);
        // Garante que o 11 é adjacente ao 10
        assertTrue(adjacentes.contains(11));
    }
    
    /**
     * Teste para adicionar uma aresta sem nodo origem
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testAddEdgeSemNodoOrigem() throws GraphException{
        System.out.println("addEdge sem nodo origem");
        Graph instance = new Graph();
        instance.addNode(11);
        instance.addEdge(10, 11, 12);
        // Espera uma exceção, já que o nodo de origem não existe
    }
    
    /**
     * Teste para adicionar uma aresta sem nodo destino
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testAddEdgeSemNodoDestino() throws GraphException{
        System.out.println("addEdge sem nodo destino");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addEdge(10, 11, 12);
        // Espera uma exceção, já que o nodo de destino não existe
    }
    
    /**
     * Teste para remover um nodo
     * @throws t1alestii.GraphException
     */
    @Test
    public void testRemoveNode() throws GraphException {
        System.out.println("removeNode");
        Graph instance = new Graph();
        assertEquals(instance.size(),0);
        instance.addNode(10);
        assertEquals(instance.size(),1);
        instance.removeNode(10);
        assertEquals(instance.size(),0);
        // Garante que quando um nodo é removido o número de nodos de um grafo é decrementado
    }

    /**
     * Teste para remover um nodo inexistente
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testRemoveNodeInexistente() throws GraphException {
        System.out.println("removeNode inexistente");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.removeNode(15);
        assertEquals(instance.size(),1);
        //Garante que o nodo não foi removido e aguarda uma exceção, já que ele não existe
    }
    
    /**
     * Teste para remover uma aresta
     * @throws t1alestii.GraphException
     */
    @Test
    public void testRemoveEdge() throws GraphException {
        System.out.println("removeEdge");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addNode(11);
        
        ArrayList<N> adjacentes = instance.getAdjacentesDados(10);
        assertEquals(adjacentes.size(),0);
        // Garante que o nodo 10 não possui nenhum adjacente
        
        instance.addEdge(10, 11, 15);
        // Adiciona a aresta entre 10 e 11
        // Isto é assumido que funciona com base no teste anterior de adição de aresta
        
        adjacentes = instance.getAdjacentesDados(10);
        assertEquals(adjacentes.size(),1);
        // Pega novamente a lista de adjacentes, e garante que exsite uma nova aresta lá
        assertEquals(adjacentes.get(0),11);
        // Garante que este cara, de fato é o 11
        
        instance.removeEdge(10, 11, 15);
        // Remove a aresta
        
        adjacentes = instance.getAdjacentesDados(10);
        assertEquals(adjacentes.size(),0);
        // Confrima que o 10 passar a não possuir mais arestas
    }
    
    /**
     * Teste para remover uma aresta inexistente
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testRemoveEdgeInexistente() throws GraphException {
        System.out.println("removeEdge inexistente");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addNode(11);
        
        ArrayList<N> adjacentes = instance.getAdjacentesDados(10);
        assertEquals(adjacentes.size(),0);
        
        instance.removeEdge(10, 11, 15);
        
        adjacentes = instance.getAdjacentesDados(10);
        assertEquals(adjacentes.size(),0);
        // Garante que o número de adjacentes permaneceu o mesmo
        // Espera uma exceção, já que a aresta não existe
    }
    
    /**
     * Teste de caminhamento em profundidade
     * @throws t1alestii.GraphException
     */
    @Test
    public void testTraversalDepth() throws GraphException{
        System.out.println("traversalDepth");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> caminho = instance.traversalDepth(0);
        List<Integer> caminhoTesteDeMesa = new ArrayList<>();
        caminhoTesteDeMesa.add(0);
        caminhoTesteDeMesa.add(2);
        caminhoTesteDeMesa.add(1);
        caminhoTesteDeMesa.add(6);
        caminhoTesteDeMesa.add(3);
        caminhoTesteDeMesa.add(4);
        caminhoTesteDeMesa.add(5);
        assertEquals(caminho,caminhoTesteDeMesa);
        
        // Foi feito o teste de mesa para o caminhamento em profundidade para o grafo criado
        // Garante que o resultado do algorimo é o mesmo do teste de mesa
    }
    
    /**
     * Teste de caminhamento em profundidade com nodo de origem inexistente
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testTraversalDepthSemOrigem() throws GraphException{
        System.out.println("traversalDepth sem origem");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> caminho = instance.traversalDepth(15);
        assertEquals(caminho,null);
        // Não retorna nenhuma caminho já que o nodo de origem não existe
        // Aguarda uma exceção
    }
    /**
     * Teste do caminhamento em largura
     * @throws t1alestii.GraphException
     */
    @Test
    public void testTraversalWidth() throws GraphException {
        System.out.println("traversalWidth");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> caminho = instance.traversalWidth(0);
        List<Integer> caminhoTesteDeMesa = new ArrayList<>();
        caminhoTesteDeMesa.add(0);
        caminhoTesteDeMesa.add(2);
        caminhoTesteDeMesa.add(3);
        caminhoTesteDeMesa.add(1);
        caminhoTesteDeMesa.add(4);
        caminhoTesteDeMesa.add(5);
        caminhoTesteDeMesa.add(6);
        assertEquals(caminho,caminhoTesteDeMesa);
        
        // Foi feito o teste de mesa para o caminhamento em profundidade para o grafo criado
        // Garante que o resultado do algorimo é o mesmo do teste de mesa
    }
    
    /**
     * Teste do caminhamento em largura sem nodo de origem
     */
    @Test (expected=GraphException.class)
    public void testTraversalWidthSemOrigem() throws Exception {
        System.out.println("traversalWidth sem origem");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> caminho = instance.traversalDepth(15);
        assertEquals(caminho,null);
        
        // Não retorna nenhuma caminho já que o nodo de origem não existe
        // Aguarda uma exceção
        
    }
    /**
     * Teste do findpath
     * @throws t1alestii.GraphException
     */
    @Test
    public void testFindPath() throws GraphException{
        System.out.println("findPath");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> path = instance.findPath(0, 6);
        List<Integer> caminhoTesteDeMesa = new ArrayList<>();
        caminhoTesteDeMesa.add(0);
        caminhoTesteDeMesa.add(2);
        caminhoTesteDeMesa.add(1);
        caminhoTesteDeMesa.add(6);
        assertEquals(path,caminhoTesteDeMesa);
        
        // Foi feito o teste de mesa para o caminhamento em profundidade para o grafo criado
        // Garante que o resultado do algorimo é o mesmo do teste de mesa
    }
    
    /**
     * Teste do findpath quando não existe caminho
     * @throws t1alestii.GraphException
     */
    @Test
    public void testFindPathSemCaminho() throws GraphException{
        System.out.println("findPath sem caminho");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> path = instance.findPath(5, 0);
        assertEquals(path,null);
        
        // Não retorna nenhuma caminho já que não existe caminho entre os nodos
    }
    
    /**
     * Teste do findpath sem nodo de origem
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testFindPathSemNodoOrigem() throws GraphException{
        System.out.println("findPath sem nodo de origem");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> path = instance.findPath(15, 5);
        assertEquals(path,null);
        
        // Não retorna nenhuma caminho já que o nodo de origem não existe
        // Aguarda uma exceção
    }
    
    /**
     * Teste do findpath sem nodo de destino
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testFindPathSemNodoDestino() throws GraphException{
        System.out.println("findPath sem nodo de destino");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> path = instance.findPath(0, 12);
        assertEquals(path,null);
        
        // Não retorna nenhuma caminho já que o nodo de destino não existe
        // Aguarda uma exceção
    }
    /**
     * Teste para verificar o tamanho do grafo
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Graph instance = new Graph();
        assertEquals(instance.size(),0);
        instance.addNode(1);
        assertEquals(instance.size(),1);
        
        // Garante que ao adicionar um nodo o numero de nodos do grafo aumenta
    }

    /**
     * Teste do iterador em largura
     * @throws t1alestii.GraphException
     */
    @Test
    public void testIteratorWidth() throws GraphException {
        System.out.println("iteratorWidth");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> caminho = instance.traversalWidth(0);
        Iterator iterador = instance.iteratorWidth(0);
        List<N> caminhoIterador = new ArrayList<>();
        while(iterador.hasNext())
            caminhoIterador.add((N)iterador.next());
        
        assertEquals(caminho,caminhoIterador);
        
        /*
            Como ja foi garantido que o caminhamento em largura funciona, neste teste o
            grafo começa iterando da mesma origem e vai até o fim. A cada nodo passado
            pelo iterador, este é adicionado em uma lista. No final, a lista que o caminhamento
            retorna e a que o iterador forma, são comparadas, e estas tem de ser identicas.
        */
    }
    
    /**
     * Teste do iterador em largura sem nodo origem
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testIteratorWidthSemOrigem() throws GraphException {
        System.out.println("iteratorWidth sem origem");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        Iterator iterador = instance.iteratorWidth(15);
        assertEquals(iterador,null);
        
        // Garante iterador nulo, já que o nodo de origem não existe
        // Espera uma exceção
    }
    
    /**
     * Teste de iterador em profundidade
     * @throws t1alestii.GraphException
     */
    @Test
    public void testIteratorDepth() throws GraphException {
        System.out.println("iteratorDepth");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        List<N> caminho = instance.traversalDepth(0);
        Iterator iterador = instance.iteratorDepth(0);
        List<N> caminhoIterador = new ArrayList<>();
        while(iterador.hasNext())
            caminhoIterador.add((N)iterador.next());
        
        assertEquals(caminho,caminhoIterador);
        
        /*
            Como ja foi garantido que o caminhamento em profundidade funciona, neste teste o
            grafo começa iterando da mesma origem e vai até o fim. A cada nodo passado
            pelo iterador, este é adicionado em uma lista. No final, a lista que o caminhamento
            retorna e a que o iterador forma, são comparadas, e estas tem de ser identicas.
        */
    }
    
    /**
     * Teste do iterador em profundidade sem nodo origem
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testIteratorDepthSemOrigem() throws GraphException {
        System.out.println("iteratorDepth sem origem");
        Graph instance = new Graph();
        instance.addNode(0);
        instance.addNode(1);
        instance.addNode(2);
        instance.addNode(3);
        instance.addNode(4);
        instance.addNode(5);
        instance.addNode(6);
        instance.addEdge(0, 2, 10);
        instance.addEdge(0, 3, 10);
        instance.addEdge(2, 3, 10);
        instance.addEdge(2, 1, 10);
        instance.addEdge(2, 4, 10);
        instance.addEdge(3, 4, 10);
        instance.addEdge(3, 5, 10);
        instance.addEdge(4, 1, 10);
        instance.addEdge(4, 6, 10);
        instance.addEdge(4, 5, 10);
        instance.addEdge(1, 6, 10);
        instance.addEdge(5, 6, 10);
        Iterator iterador = instance.iteratorDepth(15);
        
        assertEquals(iterador,null);
        
        // Garante iterador nulo, já que o nodo de origem não existe
        // Espera uma exceção
    }
    
    /**
     * Teste do algoritmo de dijkstra
     * @throws t1alestii.GraphException
     */
    @Test
    public void testeDijkstra() throws GraphException{
        System.out.println("Tesde do algoritmo de Dijkstra");
        Graph instance = new Graph();
        instance.addNode("A");
        instance.addNode("B");
        instance.addNode("C");
        instance.addNode("D");
        instance.addNode("E");
        instance.addNode("F");
        instance.addEdge("A", "B", 7);
        instance.addEdge("A", "C", 2);
        instance.addEdge("C", "E", 8);
        instance.addEdge("C", "D", 2);
        instance.addEdge("C", "B", 1);
        instance.addEdge("B", "D", 3);
        instance.addEdge("D", "E", 3);
        instance.addEdge("D", "F", 5);
        instance.addEdge("E", "F", 1);
        
        List<N> distanciaDijkstra = instance.dijkstra("A");
        
        List<String> caminhoTesteDeMesa = new ArrayList<>();

        caminhoTesteDeMesa.add("A - 0.0");
        caminhoTesteDeMesa.add("B - 3.0");
        caminhoTesteDeMesa.add("C - 2.0");
        caminhoTesteDeMesa.add("D - 4.0");
        caminhoTesteDeMesa.add("E - 7.0");
        caminhoTesteDeMesa.add("F - 8.0");
        
        assertEquals(distanciaDijkstra,caminhoTesteDeMesa);
        
        // Foi feito o teste de mesa para o resultado que o algoritmo de dijkstra
        // iria retornar com o input escolhido
        // Garante que o resultado do algorimo é o mesmo do teste de mesa
    }
    
    /**
     * Teste do algoritmo de dijkstra sem nodo origem
     * @throws t1alestii.GraphException
     */
    @Test (expected=GraphException.class)
    public void testeDijkstraSemOrigem() throws GraphException{
        System.out.println("Tesde do algoritmo de Dijkstra sem nodo origem");
        Graph instance = new Graph();
        instance.addNode("A");
        instance.addNode("B");
        instance.addNode("C");
        instance.addNode("D");
        instance.addNode("E");
        instance.addNode("F");
        instance.addEdge("A", "B", 7);
        instance.addEdge("A", "C", 2);
        instance.addEdge("C", "E", 8);
        instance.addEdge("C", "D", 2);
        instance.addEdge("C", "B", 1);
        instance.addEdge("B", "D", 3);
        instance.addEdge("D", "E", 3);
        instance.addEdge("D", "F", 5);
        instance.addEdge("E", "F", 1);
        
        List<N> distanciaDijkstra = instance.dijkstra("G");
        
        List<String> caminhoTesteDeMesa = new ArrayList<>();

        caminhoTesteDeMesa.add("A - 0.0");
        caminhoTesteDeMesa.add("B - 3.0");
        caminhoTesteDeMesa.add("C - 2.0");
        caminhoTesteDeMesa.add("D - 4.0");
        caminhoTesteDeMesa.add("E - 7.0");
        caminhoTesteDeMesa.add("F - 8.0");
        
        assertEquals(distanciaDijkstra,caminhoTesteDeMesa);
        
        // Foi feito o teste de mesa para o resultado que o algoritmo de dijkstra
        // iria retornar com o input escolhido
        // Garante que o resultado do algorimo é o mesmo do teste de mesa
    }
    
}
