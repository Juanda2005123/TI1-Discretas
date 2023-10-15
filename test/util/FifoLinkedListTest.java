/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import exceptions.EmptyListException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PriorityLevel;
import model.Task;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan David Quintero
 */
public class FifoLinkedListTest {
    
    @Test
    public void fifoEnqueueDequeue(){
        try {
            FifoLinkedList fifo = new FifoLinkedList();
            
            LocalDate now = LocalDate.now();
            
            Task newTask1 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask2 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask3 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask4 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            
            fifo.enqueue(newTask2);
            fifo.enqueue(newTask1);
            
            assertEquals(newTask2,fifo.peek());
            
            fifo.enqueue(newTask4);
            
            fifo.dequeue();
            
            assertEquals(newTask1,fifo.peek());
        } catch (EmptyListException ex) {
            Logger.getLogger(FifoLinkedListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void fifoModifyTest(){
        try {
            FifoLinkedList fifo = new FifoLinkedList();
            
            LocalDate now = LocalDate.now();
            
            Task newTask1 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask2 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask3 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask4 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            
            newTask1.setId(0);
            newTask2.setId(1);
            newTask3.setId(2);
            newTask4.setId(3);
            
            fifo.enqueue(newTask1);
            
            fifo.enqueue(newTask2);
            
            fifo.dequeue();
            
            assertEquals(newTask2,fifo.peek());
            
            fifo.enqueue(newTask3);
            fifo.enqueue(newTask4);
            
            newTask3.setTitle("Ya");
            
            fifo.modify(newTask3);
            
            fifo.dequeue();
            Task answer = (Task)fifo.peek();
            
            assertEquals(newTask3.getTitle(),answer.getTitle());
        } catch (EmptyListException ex) {
            Logger.getLogger(FifoLinkedListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void fifoRemoveATaskNotByFifoOrder(){
        try {
            FifoLinkedList fifo = new FifoLinkedList();
            
            LocalDate now = LocalDate.now();
            
            Task newTask1 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask2 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask3 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            Task newTask4 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            
            newTask1.setId(0);
            newTask2.setId(1);
            newTask3.setId(2);
            newTask4.setId(3);
            
            fifo.enqueue(newTask1);
            fifo.enqueue(newTask2);
            fifo.enqueue(newTask3);
            fifo.enqueue(newTask4);
            
            fifo.remove(newTask2);
            
            fifo.dequeue();
            
            assertEquals(newTask3,fifo.peek());
        } catch (EmptyListException ex) {
            Logger.getLogger(FifoLinkedListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
