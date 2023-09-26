package util;

import model.Class;
import exceptions.EmptyListException;

public class FifoLinkedList<T,V> {
    private DoubleLinkedNode<T,V> first;
    private DoubleLinkedNode<T,V> last;

    public FifoLinkedList(){
        first = null;
        last = null;
    }

    public void enqueue(Class<T,V> value){
        DoubleLinkedNode<T,V> node = new DoubleLinkedNode<>(value);
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
            DoubleLinkedNode<T,V> temp = last.getPrev();
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
