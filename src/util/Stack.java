package util;

public class Stack {

    private DoubleLinkedNode top;
    private int size;

    public Stack(){
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void push(T node){

        if(top != null){

            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(node);
            DoubleLinkedNode<T> tempNode = new DoubleLinkedNode<T>(null);
            tempNode = top;
            newNode.setNext(tempNode);
            setTop(newNode);

        }else {

            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(node);
            setTop(newNode);

        }

    }

    public T pop(){
        if(isEmpty()){

            throw new IllegalStateException("The stack is empty");

        }

        T node = getTop();
        top = top.getNext();
        size--;

        return node;

    }

    private void setTop(DoubleLinkedNode<T> newTop){
        top = newTop;
    }
    private DoubleLinkedNode<T> getTop(){
        return top;
    }

}
