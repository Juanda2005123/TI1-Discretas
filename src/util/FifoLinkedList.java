package util;

import exceptions.EmptyListException;

public class FifoLinkedList<T,V> {
    private DoubleLinkedNode<T> first;
    private DoubleLinkedNode<T> last;

    public FifoLinkedList(){
        first = null;
        last = null;
    }

    public void enqueue(T value){ //Add
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(value);
        if(isEmpty()){
            first = node;
            last = node;
        } else {
            node.setNext(first);
            first.setPrev(node);
            first = node;
        }
    }
    
    public void dequeue() throws EmptyListException{ //Remove
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } else {
            DoubleLinkedNode<T> temp = last.getPrev();
            temp.setNext(null);
            last.setPrev(null);
            last = temp;
        }
    }
    
    public T peek() throws EmptyListException{//Watch the last one, the first that entered
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } 
        T value = last.getValue();
        return value;
    }

    public boolean isEmpty(){
        return (first==null);
    }
    






}
