/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
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
public class PriorityQueueTest {
    
    public PriorityQueueTest() {}

    
    @Test
    public void testInsertPriority(){
        
        LocalDate now = LocalDate.now();
        
        PriorityQueue p = new PriorityQueue();
        
        Task new1 = new Task("1", "description", now, PriorityLevel.NON);
        
        p.insert(new1);
        p.shiftUpPriority(p.getSize());
        
        assertEquals(new1, p.getMax());
        
        assertEquals(new1, p.extractMaxPriority());
    }
    
    @Test
    public void testInsertMoreCasesPriority(){
        
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
    }
    
    
    @Test
    public void testRemovePriority(){
        
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
    }
    
    @Test
    public void testAllDeadLine(){
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
    }
    
    
    @Test
    public void testModify(){
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
        
        
    }
    
    @Test
    public void testForShowTasks(){
        LocalDate now = LocalDate.now();
        
        PriorityQueue p = new PriorityQueue();
        
        Task new1 = new Task("Title", "Description", now, PriorityLevel.LOW);
        Task new2 = new Task("Title", "Description", now, PriorityLevel.HIGH);
        Task new3 = new Task("Title", "Description", now, PriorityLevel.IMMEDIATE);
        Task new4 = new Task("Title", "Description", now, PriorityLevel.NON);
        Task new5 = new Task("Title", "Description", now, PriorityLevel.MEDIUM);
        Task new6 = new Task("Title", "Description", now, PriorityLevel.LOW);
        p.insert(new1);
        p.shiftUpPriority(p.getSize());
        p.insert(new2);
        p.shiftUpPriority(p.getSize());
        p.insert(new3);
        p.shiftUpPriority(p.getSize());
        p.insert(new4);
        p.shiftUpPriority(p.getSize());
        p.insert(new5);
        p.shiftUpPriority(p.getSize());
        p.insert(new6);
        p.shiftUpPriority(p.getSize());
        
        for(int i = 0; i < 3; i++){
            PriorityQueue prueba = p;
            System.out.println("("+i+")"+prueba.getSize());
            for(int j = 0; j < prueba.getSize(); j++) {
                p.extractMaxPriority();
                System.out.println("PRUEBA DENTRO "+i+""+p.getSize());
            }
            p = prueba;
        }
        
        assertEquals(5,p.getSize());

    }
    
    
}
