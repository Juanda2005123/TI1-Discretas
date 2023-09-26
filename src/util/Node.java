package util;

import model.Task;

public class Node {
    
    private Node next;
    private Node prev;
    private Task value;

    public Node(Task value){
        this.value = value;
        next = null;
        prev = null;
    }

    public void addNewNode(Node node) {
        if(this.next==null){
            node.setPrev(this);
            this.next = node;
        } else {
            node.getNext().addNewNode(node);
        }
    }

    public void removeNode(Task value){
        Node temp = this.getNext();
        if(temp!=null){
            if(temp.getValue()==value){
                this.setNext(temp.getNext());
                this.getNext().setPrev(this);

                temp.setNext(null);
                temp.setPrev(null);
            } else {
                this.getNext().removeNode(value);
            }
        } 
        
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Task getValue() {
        return value;
    }

    public void setValue(Task value) {
        this.value = value;
    }

    
}
