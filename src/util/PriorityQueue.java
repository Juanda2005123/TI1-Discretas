/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
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
        
        DoubleLinkedNode<Task> parent = priority.get(parent(i));
        DoubleLinkedNode<Task> child = priority.get(i);

        while((i > 0 && (child.getValue().compareTo(parent))>=0)&&parent!=null){
            if((child.getValue().compareTo(parent))==0){
                if(child.getValue().compareToDeadLine(parent)==1){
                    swap(parent(i),i);
                    i = parent(i);
                    
                    parent = priority.get(parent(i));
                    child = priority.get(i);
                } else {
                    break;
                }
            } else {
                swap(parent(i),i);
                i = parent(i);

                parent = priority.get(parent(i));
                child = priority.get(i);
            }
        }
        
    }
    
    public void shiftUpDeadLine(int i){
        DoubleLinkedNode<Task> parent = priority.get(parent(i));
        DoubleLinkedNode<Task> child = priority.get(i);
        
        while((i > 0 && (child.getValue().compareToDeadLine(parent))>=0)&&parent!=null){
            swap(parent(i),i);
            i = parent(i);

            parent = priority.get(parent(i));
            child = priority.get(i);
        }
       
    }
    
    public void shiftDownPriority(int i){
        int maxIndex = i;
        
        int left = leftChild(i);
        int right = rightChild(i);
        Task leftTask = priority.get(left).getValue();
        Task rightTask = priority.get(right).getValue();
        
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
        Task leftTask = priority.get(left).getValue();
        Task rightTask = priority.get(right).getValue();
        
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
    
    public Task extractMaxPriority(){
        Task task = priority.get(0).getValue();
        priority.set(0, priority.get(size));
        priority.set(size, null);
        size -= 1;
        shiftDownPriority(0);
        return task;
    }
    
    public Task extractMaxDeadLine(){
        Task task = priority.get(0).getValue();
        priority.set(0, priority.get(size));
        priority.set(size, null);
        size -= 1;
        shiftDownDeadLine(0);
        return task;
    }
    
    //FALTA UN ELIMINAR
    //https://www.geeksforgeeks.org/priority-queue-using-binary-heap/
    public void remove(int i){
        
    }
}
