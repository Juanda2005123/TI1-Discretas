package util;

import exceptions.EmptyListException;

public class FifoLinkedList<T> {
    private DoubleLinkedNode<T> first;
    private DoubleLinkedNode<T> last;

    // The `public FifoLinkedList()` is a constructor for the `FifoLinkedList` class. It initializes
    // the `first` and `last` variables to `null`, indicating that the linked list is empty when it is
    // first created.
    public FifoLinkedList(){
        first = null;
        last = null;
    }

    /**
     * The enqueue function adds a new node with the given value to the front of the double linked
     * list.
     * 
     * @param value The value to be added to the queue.
     */
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
    
    /**
     * The `dequeue` function removes the first element from a double linked list in FIFO
     * (First-In-First-Out) order.
     */
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
    
    /**
     * The function "peek" returns the value of the last element in the list without removing it.
     * 
     * @return The method is returning the value of the last element in the list.
     */
    public T peek() throws EmptyListException{//Watch the last one, the first that entered
        if(isEmpty()){
            throw new EmptyListException("The list is empty");
        } 
        T value = last.getValue();
        return value;
    }
    
    /**
     * The function modifies the value of a node in a doubly linked list.
     * 
     * @param value The value that needs to be modified in the linked list.
     */
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
    
    /**
     * The remove function removes a node with a specific value from a doubly linked list.
     * 
     * @param value The value parameter represents the value of the node that the user wants to remove
     * from the double linked list.
     */
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

    /**
     * The function checks if both the first and last elements of a data structure are null, indicating
     * that the structure is empty.
     * 
     * @return The method is returning a boolean value, which indicates whether the linked list is
     * empty or not.
     */
    public boolean isEmpty(){
        return (first==null)&&(last==null);
    }
    






}
