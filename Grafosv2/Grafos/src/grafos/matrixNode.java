package grafos;

public class matrixNode<N>{
    
        private final N dado;
        
        matrixNode(N newDado){
            dado = newDado;
        }
        
        public N getDado() { return dado; }
        
}