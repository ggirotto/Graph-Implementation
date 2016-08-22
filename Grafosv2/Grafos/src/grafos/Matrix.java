package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Matrix {
    
    private int [][] matrix;
    private int line;
    private int column;
    private final Map<matrixNode,Integer> posicaoMatrix;
    private int cont = 0;
    
    public Matrix() {
        
        line = 10;
        column = 10;
        this.matrix = new int[line][column];
        posicaoMatrix = new HashMap<>();
        
    }
    
    public Matrix(int newLine, int newColumn) {
        
        line = newLine;
        column = newColumn;
        this.matrix = new int[line][column];
        posicaoMatrix = new HashMap<>();
        
    }
    
    public void addNodo(matrixNode o){
        
        if(cont>line && cont>column){
            resize(cont,cont);
        }
        
        posicaoMatrix.put(o, cont);
        cont++;
    }
    
    public void add(matrixNode dadoOrigem, matrixNode dadoDestino){
        
        matrix[posicaoMatrix.get(dadoOrigem)][posicaoMatrix.get(dadoDestino)] = 1;
        
    }
    
    private void resize(int linha, int coluna){
        
        line = linha+((linha*20)/100);
        column = coluna+((coluna*20)/100);
        
        int [][] newMatrix = new int[line][column];
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        
        matrix = newMatrix;
    }
    
    public int getDado(int linha, int coluna) {
        
        if(linha>line || coluna>column) return -1;
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
            if(matrix[linha][i] == 1){
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
    
    public int getLinha(){
        return line;
    }
    
    public int getLinha(matrixNode dado){
        return posicaoMatrix.get(dado);
    }
    
    public int getColuna(){
        return column;
    }
    
    public int getNroColunas(int linha){
        return matrix[linha].length;
    }
}
