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
        assertEquals(instance.size(),1);
    }

    /**
     * Teste para adicionar uma aresta
     */
    @Test
    public void testAddEdge() throws GraphException{
        System.out.println("addEdge");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addNode(11);
        instance.addEdge(10, 11, 12);
    }
    
    /**
     * Teste para adicionar uma aresta sem nodo origem
     */
    @Test (expected=GraphException.class)
    public void testAddEdgeSemNodoOrigem() throws GraphException{
        System.out.println("addEdge sem nodo origem");
        Graph instance = new Graph();
        instance.addNode(11);
        instance.addEdge(10, 11, 12);
    }
    
    /**
     * Teste para adicionar uma aresta sem nodo destino
     */
    @Test (expected=GraphException.class)
    public void testAddEdgeSemNodoDestino() throws GraphException{
        System.out.println("addEdge sem nodo destino");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addEdge(10, 11, 12);
    }
    
    /**
     * Teste para remover um nodo
     */
    @Test
    public void testRemoveNode() throws Exception {
        System.out.println("removeNode");
        Graph instance = new Graph();
        assertEquals(instance.size(),0);
        instance.addNode(10);
        assertEquals(instance.size(),1);
        instance.removeNode(10);
        assertEquals(instance.size(),0);
    }

    /**
     * Teste para remover um nodo inexistente
     */
    @Test (expected=GraphException.class)
    public void testRemoveNodeInexistente() throws Exception {
        System.out.println("removeNode inexistente");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.removeNode(15);
        assertEquals(instance.size(),1);
    }
    
    /**
     * Teste para remover uma aresta
     */
    @Test
    public void testRemoveEdge() throws GraphException {
        System.out.println("removeEdge");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addNode(11);
        
        ArrayList<N> adjacentes = instance.getAdjacentes(10);
        assertEquals(adjacentes.size(),0);
        
        instance.addEdge(10, 11, 15);
        
        adjacentes = instance.getAdjacentes(10);
        assertEquals(adjacentes.size(),1);
        
        instance.removeEdge(10, 11, 15);
        
        adjacentes = instance.getAdjacentes(10);
        assertEquals(adjacentes.size(),0);
    }
    
    /**
     * Teste para remover uma aresta inexistente
     */
    @Test (expected=GraphException.class)
    public void testRemoveEdgeInexistente() throws GraphException {
        System.out.println("removeEdge inexistente");
        Graph instance = new Graph();
        instance.addNode(10);
        instance.addNode(11);
        
        ArrayList<N> adjacentes = instance.getAdjacentes(10);
        assertEquals(adjacentes.size(),0);
        
        instance.removeEdge(10, 11, 15);
        
        adjacentes = instance.getAdjacentes(10);
        assertEquals(adjacentes.size(),0);
    }
    
    /**
     * Teste de caminhamento em profundidade
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
        assertEquals(caminho.size(),instance.size());
    }
    
    /**
     * Teste de caminhamento em profundidade com nodo de origem inexistente
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
        assertEquals(caminho.size(),0);
    }
    /**
     * Teste do caminhamento em largura
     */
    @Test
    public void testTraversalWidth() throws Exception {
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
        List<N> caminho = instance.traversalDepth(0);
        assertEquals(caminho.size(),instance.size());
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
        assertEquals(caminho.size(),0);
    }
    /**
     * Teste do findpath
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
        assertTrue(path.size()>0);
    }
    
    /**
     * Teste do findpath quando não existe caminho
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
        assertTrue(path == null);
    }
    
    /**
     * Teste do findpath sem nodo de origem
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
        assertTrue(path.isEmpty());
    }
    
    /**
     * Teste do findpath sem nodo de destino
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
        assertTrue(path.isEmpty());
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
    }

    /**
     * Teste do iterador em largura
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
        for(int i=0; i<caminho.size(); i++)
            assertEquals(caminho.get(i),caminhoIterador.get(i));
    }
    
    /**
     * Teste do iterador em largura sem nodo origem
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
    }
    
    /**
     * Teste de iterador em profundidade
     */
    @Test
    public void testIteratorDepth() throws Exception {
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
        for(int i=0; i<caminho.size(); i++)
            assertEquals(caminho.get(i),caminhoIterador.get(i));
    }
    
    /**
     * Teste do iterador em profundidade sem nodo origem
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
    }
    
}
