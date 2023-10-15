package util;

import exceptions.EmptyListException;

public class Stack<T> {

    private DoubleLinkedNode<T> top;
    private int size;

    // The `public Stack()` constructor is initializing a new instance of the `Stack` class. It sets
    // the `top` reference to `null`, indicating that the stack is empty, and sets the `size` variable
    // to 0, indicating that the stack has no elements.
    public Stack(){
        this.top = null;
        this.size = 0;
    }

    /**
     * The function checks if a data structure is empty by comparing its size to zero.
     * 
     * @return The method is returning a boolean value, which indicates whether the size of the object
     * is equal to 0.
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * The getSize() function returns the size of an object.
     * 
     * @return The method is returning the value of the variable "size".
     */
    public int getSize(){
        return size;
    }

    /**
     * The push function adds a new node to the top of a double linked list.
     * 
     * @param node The node parameter is the data that you want to push onto the stack. It can be of
     * any type, as the method is generic and can handle any data type.
     */
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

    /**
     * The function pops the top element from a stack and returns its value.
     * 
     * @return The method is returning the value of the top node that is being removed from the stack.
     */
    public T pop() throws EmptyListException{

        if(isEmpty()){

            throw new EmptyListException("The stack is empty");

        }

        T node = getTop().getValue();
        top = top.getNext();
        size--;

        return node;

    }

    /**
     * The function "peek" returns the value at the top of the stack without removing it, and throws an
     * exception if the stack is empty.
     * 
     * @return The method is returning the value of the top element in the stack.
     */
    public T peek() throws EmptyListException{

        if(isEmpty()){

            throw new EmptyListException("The stack is empty");

        }

        T ans = top.getValue();

        return ans;
    }

    /**
     * The function returns the top node of a doubly linked list.
     * 
     * @return The method is returning a DoubleLinkedNode object.
     */
    private DoubleLinkedNode<T> getTop(){
        return top;
    }

}
