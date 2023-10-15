/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
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
public class PriorityQueueTest {

    
    @Test
    public void testInsertPriority(){
        
        try {
            LocalDate now = LocalDate.now();
            
            PriorityQueue p = new PriorityQueue();
            
            Task new1 = new Task("1", "description", now, PriorityLevel.NON);
            
            p.insert(new1);
            p.shiftUpPriority(p.getSize());
            
            assertEquals(new1, p.getMax());
            
            assertEquals(new1, p.extractMaxPriority());
        } catch (EmptyListException ex) {
            Logger.getLogger(PriorityQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testInsertMoreCasesPriority(){
        
        try {
            LocalDate now = LocalDate.now();
            
            PriorityQueue p = new PriorityQueue();
            
            Task new1 = new Task("1", "description", now, PriorityLevel.NON);
            
            p.insert(new1);
            p.shiftUpPriority(p.getSize());
            
            Task new2 = new Task("2", "description", now, PriorityLevel.MEDIUM);
            
            p.insert(new2);
            p.shiftUpPriority(p.getSize());
            
            Task new3 = new Task("3", "description", now, PriorityLevel.IMMEDIATE);
            
            p.insert(new3);
            p.shiftUpPriority(p.getSize());
            
            Task new4 = new Task("4", "description", now, PriorityLevel.MEDIUM);
            
            p.insert(new4);
            p.shiftUpPriority(p.getSize());
            
            assertEquals(new3, p.extractMaxPriority());
            
            assertEquals(new4, p.extractMaxPriority());
        } catch (EmptyListException ex) {
            Logger.getLogger(PriorityQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testRemovePriority(){
        
        try {
            LocalDate now = LocalDate.now();
            
            PriorityQueue p = new PriorityQueue();
            
            Task new1 = new Task("1", "description", now, PriorityLevel.IMMEDIATE);
            
            p.insert(new1);
            p.shiftUpPriority(p.getSize());
            
            Task new2 = new Task("2", "description", now, PriorityLevel.MEDIUM);
            
            p.insert(new2);
            p.shiftUpPriority(p.getSize());
            
            Task new3 = new Task("3", "description", now, PriorityLevel.IMMEDIATE);
            
            p.insert(new3);
            p.shiftUpPriority(p.getSize());
            
            assertEquals(new3,p.getMax());
            
            p.removePriority(new3);
            
            assertEquals(new1,p.extractMaxPriority());
            
            assertEquals(new2,p.extractMaxPriority());
        } catch (EmptyListException ex) {
            Logger.getLogger(PriorityQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testAllDeadLine(){
        try {
            LocalDate now = LocalDate.now();
            
            PriorityQueue p = new PriorityQueue();
            
            Task new1 = new Task("1", "description", now.plusDays(1), PriorityLevel.IMMEDIATE);
            
            p.insert(new1);
            p.shiftUpDeadLine(p.getSize());
            
            Task new2 = new Task("2", "description", now, PriorityLevel.MEDIUM);
            
            p.insert(new2);
            p.shiftUpDeadLine(p.getSize());
            
            Task new3 = new Task("3", "description", now.plusDays(1), PriorityLevel.IMMEDIATE);
            
            p.insert(new3);
            p.shiftUpDeadLine(p.getSize());
            
            assertEquals(new2,p.getMax());
            
            Task new4 = new Task("4", "description", now.plusDays(-11), PriorityLevel.IMMEDIATE);
            
            p.insert(new4);
            p.shiftUpDeadLine(p.getSize());
            
            assertEquals(new4,p.extractMaxDeadLine());
            
            p.removeDeadLine(new2);
            
            assertEquals(new3,p.getMax());
        } catch (EmptyListException ex) {
            Logger.getLogger(PriorityQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testModify(){
        try {
            LocalDate now = LocalDate.now();
            
            PriorityQueue p = new PriorityQueue();
            
            
            Task new2 = new Task("2", "description", now, PriorityLevel.NON);
            new2.setId(2);
            p.insert(new2);
            p.shiftUpPriority(p.getSize());
            
            Task new3 = new Task("3", "description", now, PriorityLevel.LOW);
            new3.setId(3);
            p.insert(new3);
            p.shiftUpPriority(p.getSize());
            
            assertEquals(new3,p.getMax());
            
            new2.setPriority(PriorityLevel.HIGH);
            
            p.modifyPriority(new2);
            
            assertEquals(new2.getId(),p.extractMaxPriority().getId());
        } catch (EmptyListException ex) {
            Logger.getLogger(PriorityQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Test
    public void testForDeadLineOrder(){
        
        try {
            LocalDate now = LocalDate.now();
            
            PriorityQueue p = new PriorityQueue();
            
            Task new1 = new Task("new1", "Description", now, PriorityLevel.LOW); //111111111
            Task new2 = new Task("new2", "Description", now.plusDays(3), PriorityLevel.HIGH); //333333333
            Task new3 = new Task("new3", "Description", now.plusDays(2), PriorityLevel.IMMEDIATE); //22222
            Task new4 = new Task("new4", "Description", now.plusDays(10), PriorityLevel.NON);//666666666666
            Task new5 = new Task("new5", "Description", now.plusDays(10), PriorityLevel.MEDIUM); //55555555555
            Task new6 = new Task("new6", "Description", now.plusDays(5), PriorityLevel.LOW); //444444444
            p.insert(new1);
            p.shiftUpDeadLine(p.getSize());
            p.insert(new2);
            p.shiftUpDeadLine(p.getSize());
            p.insert(new3);
            p.shiftUpDeadLine(p.getSize());
            p.insert(new4);
            p.shiftUpDeadLine(p.getSize());
            p.insert(new5);
            p.shiftUpDeadLine(p.getSize());
            p.insert(new6);
            p.shiftUpDeadLine(p.getSize());
            
            assertEquals(new1, p.getMax());
            
            assertEquals(new1,p.extractMaxDeadLine());////new1
            
            assertEquals(new3.getTitle(),p.extractMaxDeadLine().getTitle());////new3
            
            assertEquals(new2.getTitle(), p.extractMaxDeadLine().getTitle());
        } catch (EmptyListException ex) {
            Logger.getLogger(PriorityQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
