/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import exceptions.EmptyListException;
import java.util.ArrayList;
import model.Task;
/**
 *
 * @author Juan David Quintero
 */
public class PriorityQueue {
    private ArrayList<DoubleLinkedNode<Task>> priority;
    private int size;
    
    public PriorityQueue(){
        priority = new ArrayList<DoubleLinkedNode<Task>>();
        size = -1;
    }
    
    public PriorityQueue(PriorityQueue other) {
        this.priority = new ArrayList<>(other.priority);
        this.size = other.size;
    }

    
    public Task getTask(int i){
        return priority.get(i).getValue();
    }
    
    public void setArraylist(ArrayList<DoubleLinkedNode<Task>> list){
        priority = list;
    }
    
    public ArrayList<DoubleLinkedNode<Task>> getArrayList(){
        return priority;
    }
    
    public int parent(int i){
      return (i - 1) / 2;
    }

    public int leftChild(int i){
      return ((2 * i) + 1);
    }

    public int rightChild(int i){
      return ((2 * i) + 2);
    }
    
    public void swap(int i, int j){
        DoubleLinkedNode<Task> temp = priority.get(i);
        priority.set(i, priority.get(j));
        priority.set(j, temp);
    }
    
    public void insert(Task value){
        DoubleLinkedNode<Task> newNode = new DoubleLinkedNode<Task>(value);
        
        priority.add(newNode);
        size++;
    }
    
    public void shiftUpPriority(int i){
        
        if(i!=0){
            DoubleLinkedNode<Task> parent = priority.get(parent(i));
            DoubleLinkedNode<Task> child = priority.get(i);
            while(parent!=null&&(i > 0 && (child.getValue().compareTo(parent.getValue()))>=0)){
                if((child.getValue().compareTo(parent.getValue()))==0){
                    if(child.getValue().compareToDeadLine(parent.getValue())>=0){
                        swap(parent(i),i);
                        i = parent(i);
                        if(i!=0){
                           parent = priority.get(parent(i));
                        } else {
                            parent=null;
                        }
                        
                        child = priority.get(i);
                    } else {
                        parent=null;
                    }
                } else {
                    swap(parent(i),i);
                    i = parent(i);
                    if(i!=0){
                       parent = priority.get(parent(i));
                    } else {
                        parent=null;
                    }
                    child = priority.get(i);
                }
            }   
            
            
        }
        
        
    }
    
    public void shiftUpDeadLine(int i){
        
        if(i!=0){
            DoubleLinkedNode<Task> parent = priority.get(parent(i));
            DoubleLinkedNode<Task> child = priority.get(i);

            while(parent!=null&&(i > 0 && (child.getValue().compareToDeadLine(parent.getValue()))<=0)){
                swap(parent(i),i);
                i = parent(i);

                parent = priority.get(parent(i));
                child = priority.get(i);
            }

        }
               
    }
    
    public void shiftDownPriority(int i){
        int maxIndex = i;
        
        int left = leftChild(i);
        int right = rightChild(i);
        
        Task leftTask = null;
        Task rightTask = null;
        
        if(left<=size){
            leftTask = priority.get(left).getValue();
        }
        if(right<=size){
            rightTask = priority.get(right).getValue();
        }
        
        
        if(leftTask!=null){
            if(left <= size && leftTask.compareTo(priority.get(maxIndex).getValue())==1){
                maxIndex = left;
            }
        }
        
        if(rightTask!=null){
           if(right <= size && rightTask.compareTo(priority.get(maxIndex).getValue())==1){
               maxIndex = right;
           }
        }
        
        if(i != maxIndex){
            swap(i,maxIndex);
            shiftDownPriority(maxIndex);
        }
    }
    
    public void shiftDownDeadLine(int i){
        int maxIndex = i;
        
        int left = leftChild(i);
        int right = rightChild(i);
        
        Task leftTask = null;
        Task rightTask = null;
        
        if(left<=size){
            leftTask = priority.get(left).getValue();
        }
        if(right<=size){
            rightTask = priority.get(right).getValue();
        }
        
       
        
        if(leftTask!=null){
            if(left <= size && leftTask.compareToDeadLine(priority.get(maxIndex).getValue())==1){
                maxIndex = left;
            }
        }
        
        if(rightTask!=null){
           if(right <= size && rightTask.compareToDeadLine(priority.get(maxIndex).getValue())==1){
               maxIndex = right;
           }
        }
        
        if(i != maxIndex){
            swap(i,maxIndex);
            shiftDownPriority(maxIndex);
        }
    }
    
    public Task extractMaxPriority() throws EmptyListException{
        if(size<0){
            throw new EmptyListException("The list is empty");
        }
        Task task = priority.get(0).getValue();
        priority.set(0, priority.get(size));
        priority.remove(size);
        size--;
        shiftDownPriority(0);
        return task;
    }
    
    public Task extractMaxDeadLine() throws EmptyListException{
        if(size<0){
            throw new EmptyListException("The list is empty");
        }
        Task task = priority.get(0).getValue();
        priority.set(0, priority.get(size));
        priority.remove(size);
        size--;
        shiftDownDeadLine(0);
        return task;
    }
    
    public void removePriority(Task task) throws EmptyListException{
        for (int i = 0; i < priority.size(); i++) {
            Task actual = priority.get(i).getValue();
            if(actual.getId()==task.getId()){
                removeShiftUpPriority(i);
                break;
            }
        }
        
    }
    
    private void removeShiftUpPriority(int i) throws EmptyListException{
        DoubleLinkedNode<Task> node = new DoubleLinkedNode<>(getMax());
        priority.set(i, node);
        shiftUpPriority(i);
        extractMaxPriority();
    }
    
    public void removeDeadLine(Task task) throws EmptyListException{
        for (int i = 0; i < priority.size(); i++) {
            Task actual = priority.get(i).getValue();
            if(actual.getId()==task.getId()){
                removeShiftUpDeadLine(i);
                break;
            }
        }
        
    }
    
    private void removeShiftUpDeadLine(int i) throws EmptyListException{
        DoubleLinkedNode<Task> node = new DoubleLinkedNode<>(getMax());
        priority.set(i, node);
        shiftUpDeadLine(i);
        extractMaxDeadLine();
    }
    
    public void modifyPriority(Task task) throws EmptyListException{
        removePriority(task);
        insert(task);
        shiftUpPriority(size);
        
    }
    
    public void modifyDeadLine(Task task) throws EmptyListException{
        
        removeDeadLine(task);
        insert(task);
        shiftUpDeadLine(size);
        
    }
    
    public Task getMax() throws EmptyListException{
        if(size<0){
            throw new EmptyListException("The list is empty");
        }
        return priority.get(0).getValue();
    }
    
    public int getSize(){
        return size;
    }
}
