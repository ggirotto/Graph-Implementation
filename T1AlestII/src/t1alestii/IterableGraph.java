package t1alestii;

import java.util.Iterator;

public interface IterableGraph<N> {

public Iterator<N> iteratorWidth(N origem) throws GraphException;
public Iterator<N> iteratorDepth(N origem) throws GraphException;

}