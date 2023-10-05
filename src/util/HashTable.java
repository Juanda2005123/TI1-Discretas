package util;

public class HashTable<T> {

    private DoubleLinkedNode<T>[] hashTable;

    public HashTable() {
        hashTable = new DoubleLinkedNode[23]; //A siempre ganar como el 23
    }

    public int hashFunction(String key){ //Title
        int answer = 0;
        char[] chars = key.toCharArray();
        for(int i = 0; i < chars.length; i++){
            answer += chars[i];
        }
        answer %= 23;

        return answer;
    }

    public void add(T newTask){
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(newTask);
        int key = hashFunction(node.getTitle());

        if(hashTable[key]==null){
            hashTable[key] = node;
        } else{
            hashTable[key].addNewNode(node);
        }
    }

    public void remove(T value) {
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(value);

        int key = hashFunction(node.getTitle());

        DoubleLinkedNode<T> temp = hashTable[key];

        if(temp!=null){
            if(temp.getValue()==value){
                hashTable[key] = temp.getNext();
            } else {
                temp.removeNode(value);
            }
        }
    }   
    
    public void modify(T task){
        DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(task);
        int key = hashFunction(node.getTitle());
        
        DoubleLinkedNode<T> temp = hashTable[key];
        
        if(temp!=null){
            if(temp.getValue()==task){
                temp.setValue(task);
                hashTable[key] = temp;
            } else {
                temp.modify(task);
            }
        }
    }



}