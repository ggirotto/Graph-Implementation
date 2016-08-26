package t1alestii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import t1alestii.Graph.matrixNode;

public class Matrix<N> {
    
    private Object [][] matrix;
    private int size;
    private final Map<matrixNode,Integer> posicaoMatrix;
    private int cont = 0;
    private ArrayList<Integer> nextContValues;
    
    public Matrix() {
        
        size = 10;
        this.matrix = new Object[size][size];
        posicaoMatrix = new HashMap<>();
        nextContValues = new ArrayList<>();
        
    }
    
    public Matrix(int newSize) {
        
        size = newSize;
        this.matrix = new Object[size][size];
        posicaoMatrix = new HashMap<>();
        nextContValues = new ArrayList<>();
        
    }
    
    public void addNodo(matrixNode o){
        
        if(cont>size){
            resize();
        }
        
        if(nextContValues.isEmpty()){
            posicaoMatrix.put(o, cont);
            cont++;
        }else
            posicaoMatrix.put(o, nextContValues.remove(0));
        
    }
    
    public void removeNodo(matrixNode node){
        
        int linhaNodoRemovido = posicaoMatrix.remove(node);
        nextContValues.add(linhaNodoRemovido);
        
        /*
            Remove os dados contidos na linha e na coluna do nodo na matrix
        */
        for(int i=0; i<matrix[linhaNodoRemovido].length; i++){
            matrix[linhaNodoRemovido][i] = null;
        }
        for(int j=0; j<matrix.length; j++){
            matrix[j][linhaNodoRemovido] = null;
        }
        
    }
    
    public void removeEdge(matrixNode orig, matrixNode dest, N aresta){
        
        int linha = posicaoMatrix.get(orig);
        int coluna = posicaoMatrix.get(dest);
        
        ArrayList<Object> o = (ArrayList<Object>)matrix[linha][coluna];
        /*
            Se só possui uma aresta, setar o campo para null sinalizando que não existem arestas
        */
        if(o.isEmpty())
            matrix[linha][coluna] = null;
        // Senão, somente remove o valor da aresta em questão
        o.remove(aresta);
        
    }
    
    public void addEdge(matrixNode dadoOrigem, matrixNode dadoDestino, N dadoAresta){
        
        ArrayList<Object> o = (ArrayList<Object>) matrix[posicaoMatrix.get(dadoOrigem)][posicaoMatrix.get(dadoDestino)];
        
        if(o != null){
            if(!o.contains(dadoAresta))
                o.add(dadoAresta);
        }
        else {
            o = new ArrayList<Object>();
            o.add(dadoAresta);
            matrix[posicaoMatrix.get(dadoOrigem)][posicaoMatrix.get(dadoDestino)] = o;
        }
    }
    
    private void resize(){
        
        size = size+((size*20)/100);
        
        Object [][] newMatrix = new Object[size][size];
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        
        matrix = newMatrix;
    }
    
    public Object getDado(int linha, int coluna) {
        
        if(linha>size || coluna>size) return -1;
        return matrix[linha][coluna];
        
    }
    
    public matrixNode getRepresentante(int coluna){
        
        for (Map.Entry<matrixNode, Integer> entry : posicaoMatrix.entrySet()) {
            if(entry.getValue() == coluna)
                return entry.getKey();
        }
        
        return null;
    }
    
    public ArrayList<matrixNode> getAdjacentes(int linha){
        
        ArrayList<matrixNode> adjacentes = new ArrayList<>();
        
        for(int i=0; i<matrix[linha].length;i++){
            if(matrix[linha][i] != null){
                for (Map.Entry<matrixNode, Integer> entry : posicaoMatrix.entrySet()) {
                    if(entry.getValue() == i){
                        adjacentes.add(entry.getKey());
                        break;
                    }
                }
            }
        }
        
        return adjacentes;
    }
    
    public int getLinhaDoDado(matrixNode dado){
        return posicaoMatrix.get(dado);
    }
    
    public int getNroColunasDaLinha(int linha){
        return matrix[linha].length;
    }
}
