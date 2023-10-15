package util;

import exceptions.EmptyListException;

public class FifoLinkedList<T> {
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
    
    public void dequeue() throws EmptyListException{ //Remove by Fifo order
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } else {
            if(last==first){
                last=null;
                first=null;
            } else {
                DoubleLinkedNode<T> temp = last.getPrev();
                if(temp!=null){
                    temp.setNext(null);
                }
                last.setPrev(null);
                last = temp;
            }
            
            
        }
    }
    
    public T peek() throws EmptyListException{//Watch the last one, the first that entered
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } 
        T value = last.getValue();
        return value;
    }
    
    public void modify(T value){
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(value);
        if(!isEmpty()){
            if(first.getId()==node.getId()){
                first.setValue(value);
            } else if(last.getId()==node.getId()){
                last.setValue(value);
            } else {
                first.modify(node);
            }
        }
    }
    
    public void remove(T value) { //Remove if user deletes a task
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(value);
        if(!isEmpty()){
            if(first.getId()==node.getId()){
                first = first.getNext();
                if(first!=null){
                    first.getPrev().setNext(null);
                    first.setPrev(null);
                } else {
                    last = null;
                }
            } else if(last.getId()==node.getId()){
                last = last.getPrev();
                last.getNext().setPrev(null);
                last.setNext(null);
            } else {
                first.removeNode(value);
            }
        }
    }

    public boolean isEmpty(){
        return (first==null)&&(last==null);
    }
    






}
