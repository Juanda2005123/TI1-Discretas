package util;


public class HashTable<T> {

    private DoubleLinkedNode<T>[] hashTable;

    // The `public HashTable()` constructor is initializing the `hashTable` array with a size of 23.
    // This means that the hash table will have 23 slots or buckets to store elements. The comment `//A
    // siempre ganar como el 23` suggests that the number 23 is being used as a constant value for the
    // size of the hash table.
    public HashTable() {
        hashTable = new DoubleLinkedNode[23]; //A siempre ganar como el 23
    }

    /**
     * The hashFunction takes an integer key and returns the remainder of key divided by 23.
     * 
     * @param key The key parameter is an integer value that represents the input value for the hash
     * function.
     * @return The remainder of the key divided by 23.
     */
    public int hashFunction(int key){ //Title
        return key%23;
    }

    /**
     * The add function adds a new task to a hash table using a hash function to determine the key.
     * 
     * @param newTask The new task that needs to be added to the hash table.
     */
    public void add(T newTask){
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(newTask);
        int key = hashFunction(node.getId());

        if(hashTable[key]==null){
            hashTable[key] = node;
        } else{
            hashTable[key].addNewNode(node);
        }
    }

    /**
     * The remove function removes a node with a specific value from a hash table.
     * 
     * @param value The value parameter represents the value of the node that needs to be removed from
     * the hash table.
     */
    public void remove(T value) {
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(value);

        int key = hashFunction(node.getId());

        DoubleLinkedNode<T> temp = hashTable[key];

        if(temp!=null){
            if(temp.getValue()==value){
                hashTable[key] = temp.getNext();
            } else {
                temp.removeNode(value);
            }
        }
    }   
    
    /**
     * The modify function updates the value of a node in a hash table if it exists, otherwise it does
     * nothing.
     * 
     * @param task1 The parameter `task1` is of type `T`, which means it can be any type specified when
     * the method is called. It represents the task that needs to be modified.
     */
    public void modify(T task1){
        
        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(task1);
        int key = hashFunction(newNode.getId());
        
        DoubleLinkedNode<T> temp = hashTable[key];
        
        if(temp!=null){
            
            if(temp.getId()==newNode.getId()){
                temp.setValue(task1);
                
                hashTable[key] = temp;
            } else {
                temp.modify(newNode);
            }
        }
    }
    
    /**
     * The search function takes a value as input, hashes it to find the key in the hash table, and
     * then searches for the value in the linked list at that key.
     * 
     * @param value The value that you want to search for in the hash table.
     * @return The method is returning the value of type T that matches the given search value.
     */
    public T search(T value){
        DoubleLinkedNode<T> searchNode = new DoubleLinkedNode<T>(value);
        int key = hashFunction(searchNode.getId());
        
        DoubleLinkedNode<T> temp = hashTable[key];
        
        T returnV = null;
        
        if(temp!=null){
            
            if(temp.getId()==searchNode.getId()){
                returnV =  temp.getValue();
            } else {
                returnV = temp.search(searchNode);
            }
            
        }
        return returnV;
    }



}