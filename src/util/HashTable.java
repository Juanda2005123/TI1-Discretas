package util;

import java.util.ArrayList;
import model.Task;

public class HashTable {

    private ArrayList<Node> hashTable;

    public HashTable() {
        hashTable = new ArrayList<>(23);
    }

    public int hashFunction(String key){ //Title
        int answer = 0;
        char[] chars = key.toCharArray();
        for(int i = 0; i < chars.length; i++){
            answer += chars[i];
        }
        answer %= hashTable.size();

        return answer;
    }

    public void add(Task newTask){
        Node node = new Node(newTask);
        int key = hashFunction(newTask.getTitle());

        if(hashTable.get(key)==null){
            hashTable.set(key, node);
        } else{
            hashTable.get(key).addNewNode(node);
        }
    }

    public void remove(Task value) {
        int key = hashFunction(value.getTitle());

        Node temp = hashTable.get(key);

        if(temp!=null){
            if(temp.getValue()==value){
                hashTable.set(key, temp.getNext());
            } else {
                temp.removeNode(value);
            }
        }
    }   



}