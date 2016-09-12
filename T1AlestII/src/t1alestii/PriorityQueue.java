package t1alestii;

public class PriorityQueue<N> {

    private class Node {

        N data;
        double chave;
        Node next;

        public Node(N data, double chave) {
            this.data = data;
            this.chave = chave;
            next = null;
        }

    }

    Node first;
    int size;

    public PriorityQueue() {
        first = null;
        size = 0;
    }

    public void addNode(N n, double chave) {
        Node p = new Node(n, chave);
        if(first == null){
            first = p;
            return;
        }
        
        Node aux = first;
        while (aux.next != null) {
            aux = aux.next;
        }
        aux.next = p;
        sortQueue();
        getSize();
    }

    public N getNode() {
        Node aux = first;
        first = first.next;
        getSize();
        return aux.data;
        
    }
    
    private void removeNode(Node nodo){
        if(first == nodo){
            first = first.next;
            return;
        }
        Node aux = first;
        while(aux.next != null){
            if(aux.next == nodo && aux.next.next != null){
                aux.next = aux.next.next;
                break;
            }
        }
        getSize();
    }

    public void sortQueue() {
        if(first == null) return;
        Node aux = first;
        Node aux2 = first;
        while (aux.next != null) {
            while (aux2.next != null) {
                aux2 = aux2.next;
                if (aux2.chave < aux.chave) {
                    Node aux3 = aux2;
                    aux2 = aux;
                    aux = aux3;
                }
            }
            aux = aux.next;
        }
    }
    
    public boolean isEmpty(){
        return first==null;
    }
    
    private void getSize(){
        size = 0;
        if(first == null) return;
        size++;
        Node aux = first;
        while(aux.next!=null){
            aux = aux.next;
            size++;
        }
    }
    
    public N removeMin(){
        Node aux = first;
        Node minKey = first;
        while(aux.next != null){
            if(aux.chave<minKey.chave) minKey = aux;
            aux = aux.next;
        }
        removeNode(minKey);
        sortQueue();
        return minKey.data;
    }
    
    public boolean contains(N n){
        if(first == null) return false;
        if(first.data.equals(n)) return true;
        Node aux = first;
        while(aux.next != null){
            if(aux.data.equals(n))
                return true;
            aux = aux.next;
        }
        return false;
    }
    
    public void updateKey(N n, double key){
        if(!contains(n)) return;
        if(first.data.equals(n)){
            first.chave = key;
            return;
        }
        Node aux = first;
        while(aux.next != null){
            if(aux.data.equals(n)){
                aux.chave = key;
                break;
            }
            aux = aux.next;
        }
    }

}
