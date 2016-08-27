package t1alestii;

public class GraphException extends Exception {
    
    public GraphException(String message){
        super(message);
    }
    
    public GraphException(String message,Exception e){
        super(message,e);
    }
}
