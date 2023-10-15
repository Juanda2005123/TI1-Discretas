package util;


public class HashTable<T> {

    private DoubleLinkedNode<T>[] hashTable;

    public HashTable() {
        hashTable = new DoubleLinkedNode[23]; //A siempre ganar como el 23
    }

    public int hashFunction(int key){ //Title
        return key%23;
    }

    public void add(T newTask){
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(newTask);
        int key = hashFunction(node.getId());

        if(hashTable[key]==null){
            hashTable[key] = node;
        } else{
            hashTable[key].addNewNode(node);
        }
    }

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