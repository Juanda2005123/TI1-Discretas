package util;

import model.Task;

public class DoubleLinkedNode<T> {
    
    private DoubleLinkedNode<T> next;
    private DoubleLinkedNode<T> prev;
    private T value;

    // The `public DoubleLinkedNode(T value)` constructor is creating a new instance of the
    // `DoubleLinkedNode` class with the specified value. It sets the `value` field of the node to the
    // given value and initializes the `next` and `prev` fields to `null`.
    public DoubleLinkedNode(T value){
        this.value = value;
        next = null;
        prev = null;
    }

    /**
    * The addNewNode function adds a new node to a doubly linked list.
    * 
    * @param node The parameter `node` is an instance of the `DoubleLinkedNode` class.
    */
    public void addNewNode(DoubleLinkedNode<T> node) {
        if(this.next==null){
            node.setPrev(this);
            this.next = node;
        } else {
            node.getNext().addNewNode(node);
        }
    }

    /**
     * The removeNode function removes a node with a specific value from a doubly linked list.
     * 
     * @param value The value of the node that needs to be removed from the linked list.
     */
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
    
    /**
     * The modify function updates the value of a DoubleLinkedNode with a given ID.
     * 
     * @param task The parameter "task" is of type DoubleLinkedNode<T>, which represents a node in a
     * doubly linked list.
     */
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
    
    /**
     * The search function recursively searches for a specific node in a doubly linked list and returns
     * its value.
     * 
     * @param searchNode The searchNode parameter is of type DoubleLinkedNode<T> and represents the
     * node that we are searching for in the linked list.
     * @return The method is returning the value of the node that matches the search criteria, or null
     * if no matching node is found.
     */
    public T search(DoubleLinkedNode<T> searchNode){
        
        DoubleLinkedNode<T> temp = this.getNext();
        T returnV = null;
        if(temp!=null){
            if(temp.getId()==searchNode.getId()){
                returnV = temp.getValue();
            } else {
                returnV = this.getNext().search(searchNode);
            }
        }
        return returnV;
        
    }

    /**
     * The function returns the next node in a doubly linked list.
     * 
     * @return The method is returning a DoubleLinkedNode object.
     */
    public DoubleLinkedNode<T> getNext() {
        return next;
    }

    /**
     * The function sets the next node in a doubly linked list.
     * 
     * @param next The "next" parameter is of type DoubleLinkedNode<T>, which represents the next node
     * in a doubly linked list.
     */
    public void setNext(DoubleLinkedNode<T> next) {
        this.next = next;
    }

    /**
     * The function returns the previous node in a doubly linked list.
     * 
     * @return The method is returning a DoubleLinkedNode object.
     */
    public DoubleLinkedNode<T> getPrev() {
        return prev;
    }

    /**
     * The function sets the previous node of a doubly linked node.
     * 
     * @param prev The "prev" parameter is of type DoubleLinkedNode<T>, which represents the previous
     * node in a doubly linked list.
     */
    public void setPrev(DoubleLinkedNode<T> prev) {
        this.prev = prev;
    }

    /**
     * The getValue() function returns the value of a generic type T.
     * 
     * @return The method is returning a value of type T.
     */
    public T getValue() {
        return value;
    }

    /**
     * The function sets the value of a variable.
     * 
     * @param value The parameter "value" is of type T, which means it can be any type specified when
     * the method is called.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * The function returns the title of a task.
     * 
     * @return The method is returning the title of a task.
     */
    public String getTitle(){
        Task task = (Task) value;
        return task.getTitle();
    }
    
    /**
     * The function returns the ID of a task object.
     * 
     * @return The method is returning the ID of a Task object.
     */
    public int getId(){
        Task task = (Task) value;
        return task.getId();
    }
    

    
}
