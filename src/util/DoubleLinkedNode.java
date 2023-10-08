package util;

import model.Task;

public class DoubleLinkedNode<T> {
    
    private DoubleLinkedNode<T> next;
    private DoubleLinkedNode<T> prev;
    private T value;

    public DoubleLinkedNode(T value){
        this.value = value;
        next = null;
        prev = null;
    }

    public void addNewNode(DoubleLinkedNode<T> node) {
        if(this.next==null){
            node.setPrev(this);
            this.next = node;
        } else {
            node.getNext().addNewNode(node);
        }
    }

    public void removeNode(T value){
        DoubleLinkedNode<T> temp = this.getNext();
        DoubleLinkedNode<T> toRemove = new DoubleLinkedNode<T>(value);
        if(temp!=null){
            if(temp.getId()==toRemove.getId()){
                this.setNext(temp.getNext());
                if(this.getNext()!=null){
                    this.getNext().setPrev(this);
                }

                temp.setNext(null);
                temp.setPrev(null);
            } else {
                this.getNext().removeNode(value);
            }
        } 
        
    }
    
    public void modify(DoubleLinkedNode<T> task){
        
        DoubleLinkedNode<T> temp = this.getNext();
        if(temp!=null){
            if(temp.getId()==task.getId()){
                temp.setValue(task.getValue());
            } else {
                this.getNext().modify(task);
            }
        }
    }

    public DoubleLinkedNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleLinkedNode<T> next) {
        this.next = next;
    }

    public DoubleLinkedNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleLinkedNode<T> prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getTitle(){
        Task task = (Task) value;
        return task.getTitle();
    }
    
    public int getId(){
        Task task = (Task) value;
        return task.getId();
    }
    

    
}
