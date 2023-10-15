package util;

import exceptions.EmptyListException;

public class Stack<T> {

    private DoubleLinkedNode<T> top;
    private int size;

    public Stack(){
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public void push(T node){

        if(top != null){

            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(node);
            DoubleLinkedNode<T> tempNode = top;
            
            newNode.setNext(tempNode);
            top = newNode;
            size++;

        }else {

            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(node);
            top = newNode;
            size++;
        }

    }

    public T pop() throws EmptyListException{

        if(isEmpty()){

            throw new EmptyListException("The stack is empty");

        }

        T node = getTop().getValue();
        top = top.getNext();
        size--;

        return node;

    }

    public T peek() throws EmptyListException{

        if(isEmpty()){

            throw new EmptyListException("The stack is empty");

        }

        T ans = top.getValue();

        return ans;
    }

    private DoubleLinkedNode<T> getTop(){
        return top;
    }

}
