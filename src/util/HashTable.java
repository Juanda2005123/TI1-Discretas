package util;

import model.Task;

public class HashTable {

    private Node[] hashTable;

    public HashTable() {
        hashTable = new Node[23]; //A siempre ganar como el 23
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

    public void add(Task newTask){
        Node node = new Node(newTask);
        int key = hashFunction(newTask.getTitle());

        if(hashTable[key]==null){
            hashTable[key] = node;
        } else{
            hashTable[key].addNewNode(node);
        }
    }

    public void remove(Task value) {
        int key = hashFunction(value.getTitle());

        Node temp = hashTable[key];

        if(temp!=null){
            if(temp.getValue()==value){
                hashTable[key] = temp.getNext();
            } else {
                temp.removeNode(value);
            }
        }
    }   



}