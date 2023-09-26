package util;

import model.Class;
import exceptions.EmptyListException;

public class LifoLinkedList<T,V> {
    private DoubleLinkedNode<T,V> first;

    public LifoLinkedList(){
        first = null;
    }

    public void push(Class<T,V> value){
        DoubleLinkedNode<T,V> node = new DoubleLinkedNode<>(value);
        if(isEmpty()){
            first = node;
        } else {
            node.setNext(first);
            first.setPrev(node);
            first = node;
        }
    }

    public Class<T,V> pop() throws EmptyListException{
        
        Class<T,V> returnClass = peek();
        
        if(first.getNext()!=null){
            DoubleLinkedNode<T,V> temp = first.getNext();
            temp.setPrev(null);
            first.setNext(null);
            first = temp;
        } else {
            first = null;
        }

        return returnClass;
    }

    public Class<T,V> peek() throws EmptyListException{
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } 
        Class<T,V> value = first.getValue();
        return value;
    }

    public boolean isEmpty(){
        return (first==null) ? true : false;
    }
    






}
