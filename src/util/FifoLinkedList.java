package util;

import model.Task;

public class FifoLinkedList<T,V> {
    private NodeFifo<T,V> first;
    private NodeFifo<T,V> last;

    public FifoLinkedList(){
        first = null;
        last = null;
    }

    public void enqueue(Class<T,V> value){
        NodeFifo<T,V> node = new NodeFifo<>(value);
        if(isEmpty()){
            first = node;
            last = node;
        } else {
            node.setNext(first);
            first.setPrev(node);
            first = node;
        }
    }
    public void dequeue() throws EmptyListException{
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } else {
            NodeFifo<T,V> temp = last.getPrev();
            temp.setNext(null);
            last.setPrev(null);
            last = temp;
        }
    }

    public Class<T,V> peek() throws EmptyListException{
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } 
        Class<T,V> value = last.getValue();
        return value;
    }

    public boolean isEmpty(){
        return (first==null) ? true : false;
    }
    






}
