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
    
    // The `public PriorityQueue()` constructor initializes a new instance of the `PriorityQueue`
    // class. It creates a new `ArrayList` called `priority` to store the tasks, and sets the initial
    // size of the queue to -1.
    public PriorityQueue(){
        priority = new ArrayList<DoubleLinkedNode<Task>>();
        size = -1;
    }
    
    // The `public PriorityQueue(PriorityQueue other)` constructor is creating a new instance of the
    // `PriorityQueue` class by copying the contents of another `PriorityQueue` object.
    public PriorityQueue(PriorityQueue other) {
        this.priority = new ArrayList<>(other.priority);
        this.size = other.size;
    }

    
   /**
    * The function returns the task at a specified index from a priority list.
    * 
    * @param i The parameter "i" is an integer representing the index of the task in the priority list.
    * @return The method is returning a Task object.
    */
    public Task getTask(int i){
        return priority.get(i).getValue();
    }
    /**
     * The function returns the index of the parent node given the index of a node in a binary tree.
     * 
     * @param i The parameter "i" represents the index of a node in a binary heap data structure.
     * @return The parent index of the given index.
     */
    
    public int parent(int i){
      return (i - 1) / 2;
    }

    /**
     * The function returns the index of the left child of a given node in a binary tree.
     * 
     * @param i The parameter "i" represents the index of a node in a binary tree.
     * @return The left child of the node at index i in a binary tree.
     */
    public int leftChild(int i){
      return ((2 * i) + 1);
    }

    /**
     * The function returns the index of the right child of a given node in a binary tree.
     * 
     * @param i The parameter "i" represents the index of a node in a binary tree.
     * @return The right child of the node at index i in a binary tree.
     */
    public int rightChild(int i){
      return ((2 * i) + 2);
    }
    
    /**
     * The swap function swaps two elements in a DoubleLinkedList.
     * 
     * @param i The index of the first element to be swapped.
     * @param j The parameter "j" is an integer representing the index of the second element to be
     * swapped.
     */
    public void swap(int i, int j){
        DoubleLinkedNode<Task> temp = priority.get(i);
        priority.set(i, priority.get(j));
        priority.set(j, temp);
    }
    
    /**
     * The insert function adds a new node with a given value to a priority queue.
     * 
     * @param value The parameter "value" is of type Task. It represents the task that needs to be
     * inserted into the data structure.
     */
    public void insert(Task value){
        DoubleLinkedNode<Task> newNode = new DoubleLinkedNode<Task>(value);
        
        priority.add(newNode);
        size++;
    }
    
    /**
     * The function shifts up the priority of a task in a priority queue.
     * 
     * @param i The parameter "i" represents the index of the element in the priority queue that needs
     * to be shifted up in priority.
     */
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
    
    /**
     * The function shifts a task up in priority based on its deadline.
     * 
     * @param i The parameter "i" represents the index of the task in the priority queue that needs to
     * be shifted up.
     */
    public void shiftUpDeadLine(int i){
        
        if(i!=0){
            DoubleLinkedNode<Task> parent = priority.get(parent(i));
            DoubleLinkedNode<Task> child = priority.get(i);

            while(parent!=null&&(i > 0 && (child.getValue().compareToDeadLine(parent.getValue()))<=0)){
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
    
    /**
     * The function shifts down the priority of a task at index i in a priority queue if necessary.
     * 
     * @param i The parameter `i` represents the index of the element in the priority queue that needs
     * to be shifted down to maintain the heap property.
     */
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
            if(leftTask.compareTo(priority.get(maxIndex).getValue())==1){
                maxIndex = left;
            }
        }
        
        if(rightTask!=null){
           if(rightTask.compareTo(priority.get(maxIndex).getValue())==1){
               maxIndex = right;
           }
        }
        
        if(i != maxIndex){
            swap(i,maxIndex);
            shiftDownPriority(maxIndex);
        }
    }
    
    /**
     * The function shifts down the element at index i in a priority queue based on the deadline of the
     * tasks.
     * 
     * @param i The parameter `i` represents the index of the element in the priority queue that needs
     * to be shifted down to maintain the heap property.
     */
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
            if(leftTask.compareToDeadLine(priority.get(maxIndex).getValue())==-1){
                maxIndex = left;
            }
        }
        
        if(rightTask!=null){
           if(rightTask.compareToDeadLine(priority.get(maxIndex).getValue())==-1){
               maxIndex = right;
           }
        }
        
        if(i != maxIndex){
            swap(i,maxIndex);
            shiftDownDeadLine(maxIndex);
        }
    }
    
    /**
     * The function extracts and returns the task with the highest priority from a list, while
     * maintaining the priority order.
     * 
     * @return The method is returning a Task object.
     */
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
    
   /**
    * The function extracts and returns the task with the maximum deadline from a priority list.
    * 
    * @return The method is returning a Task object.
    */
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
    
    /**
     * The function removes a task with a specific ID from a priority list.
     * 
     * @param task The parameter "task" is an object of type "Task" that represents the task to be
     * removed from the priority list.
     */
    public void removePriority(Task task) throws EmptyListException{
        for (int i = 0; i < priority.size(); i++) {
            Task actual = priority.get(i).getValue();
            if(actual.getId()==task.getId()){
                removeShiftUpPriority(i);
                break;
            }
        }
        
    }
    
    /**
     * The function removes the element at index i from the priority list, shifts the remaining
     * elements up to maintain the priority order, and extracts the maximum element from the priority
     * list.
     * 
     * @param i The parameter "i" represents the index of the priority list where the shift up
     * operation needs to be performed.
     */
    private void removeShiftUpPriority(int i) throws EmptyListException{
        DoubleLinkedNode<Task> node = new DoubleLinkedNode<>(getMax());
        priority.set(i, node);
        shiftUpPriority(i);
        extractMaxPriority();
    }
    
    /**
     * The function removes a task with a specific ID from a priority list.
     * 
     * @param task The parameter "task" is an object of type Task, which represents a task that needs
     * to be removed from the priority list.
     */
    public void removeDeadLine(Task task) throws EmptyListException{
        for (int i = 0; i < priority.size(); i++) {
            Task actual = priority.get(i).getValue();
            if(actual.getId()==task.getId()){
                removeShiftUpDeadLine(i);
                break;
            }
        }
        
    }
    
    /**
     * The function removes the task with the highest priority, shifts the remaining tasks up to
     * maintain the priority order, and extracts the new highest priority task.
     * 
     * @param i The parameter "i" represents the index of the element in the priority list that needs
     * to be removed and updated.
     */
    private void removeShiftUpDeadLine(int i) throws EmptyListException{
        DoubleLinkedNode<Task> node = new DoubleLinkedNode<>(getMax());
        priority.set(i, node);
        shiftUpDeadLine(i);
        extractMaxDeadLine();
    }
    
    /**
     * The function modifies the priority of a task by removing it from the list, inserting it back in,
     * and shifting up its priority.
     * 
     * @param task The task object that needs to be modified.
     */
    public void modifyPriority(Task task) throws EmptyListException{
        removePriority(task);
        insert(task);
        shiftUpPriority(size);
        
    }
    
    /**
     * The function modifies the deadline of a task by removing the old deadline, inserting the task
     * with the new deadline, and shifting up the deadlines of other tasks.
     * 
     * @param task The task object that needs to be modified.
     */
    public void modifyDeadLine(Task task) throws EmptyListException{
        
        removeDeadLine(task);
        insert(task);
        shiftUpDeadLine(size);
        
    }
    
    /**
     * The getMax() function returns the maximum value from a list of tasks.
     * 
     * @return The method is returning the value of the first element in the "priority" list.
     */
    public Task getMax() throws EmptyListException{
        if(size<0){
            throw new EmptyListException("The list is empty");
        }
        return priority.get(0).getValue();
    }
    
    /**
     * The getSize() function returns the size of an object.
     * 
     * @return The method is returning the value of the variable "size".
     */
    public int getSize(){
        return size;
    }
}
