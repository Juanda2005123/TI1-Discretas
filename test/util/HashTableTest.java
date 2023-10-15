/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.LocalDate;
import model.PriorityLevel;
import model.Task;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan David Quintero
 */
public class HashTableTest {
    
    
    @Test
    public void addRemoveTest(){
        
        HashTable hash = new HashTable();
        
        LocalDate now = LocalDate.now();
            
        Task newTask1 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
        Task newTask2 = new Task("DifferentTitle", "description", now, PriorityLevel.MEDIUM);
        Task newTask3 = new Task("AnotherOne", "description", now, PriorityLevel.MEDIUM);
        Task newTask4 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            
        newTask1.setId(0);
        newTask2.setId(1);
        newTask3.setId(2);
        newTask4.setId(3);
        
        hash.add(newTask1); //Same
        hash.add(newTask2);
        hash.add(newTask3);
        hash.add(newTask4); //Same
        
        hash.remove(newTask2);
        
        assertEquals(null, hash.search(newTask2));
        
        assertEquals(newTask3, hash.search(newTask3));
        
        
    }
    
    @Test
    public void modifyTest(){
        HashTable hash = new HashTable();
        
        LocalDate now = LocalDate.now();
            
        Task newTask1 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
        Task newTask2 = new Task("DifferentTitle", "description", now, PriorityLevel.MEDIUM);
        Task newTask3 = new Task("AnotherOne", "description", now, PriorityLevel.MEDIUM);
        Task newTask4 = new Task("Title", "description", now, PriorityLevel.MEDIUM);
            
        newTask1.setId(0);
        newTask2.setId(1);
        newTask3.setId(2);
        newTask4.setId(3);
        
        hash.add(newTask4);
        hash.add(newTask1);
        
        newTask1.setTitle("CAMBIO");
        hash.modify(newTask1);
        
        Task nodeTask = (Task) hash.search(newTask1);
        
        assertEquals("CAMBIO", nodeTask.getTitle());
    }
}
