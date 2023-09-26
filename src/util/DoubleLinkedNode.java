package util;

import model.Class;

public class DoubleLinkedNode<T,V>{
    
    private Class<T,V> value;
    private DoubleLinkedNode<T,V> next;
    private DoubleLinkedNode<T,V> prev;

    public DoubleLinkedNode(Class<T,V> value){
        this.value = value;
        next = null;
        prev = null;
    }

    public Class<T, V> getValue() {
        return value;
    }

    public DoubleLinkedNode<T, V> getNext() {
        return next;
    }

    public void setNext(DoubleLinkedNode<T, V> next) {
        this.next = next;
    }

    public DoubleLinkedNode<T, V> getPrev() {
        return prev;
    }

    public void setPrev(DoubleLinkedNode<T, V> prev) {
        this.prev = prev;
    }

    

}