/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import exceptions.EmptyListException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author User
 */
public class StackTest {
    
    @Test
    public void allTest(){
        try {
            Stack s = new Stack();
            
            int num1 = 1;
            int num2 = 2;
            int num3 = 3;
            int num4 = 4;
            int num5 = 5;
            
            s.push(num1);
            s.push(num2);
            s.push(num3);
            
            assertEquals(num3,s.peek());
            
            s.pop();
            s.pop();
            
            assertEquals(num1,s.pop());
            
            s.push(num4);
            
            assertEquals(num4,s.peek());
            
            s.push(num5);
            
            assertEquals(num5,s.pop());
            assertEquals(num4,s.pop());
        } catch (EmptyListException ex) {
            Logger.getLogger(StackTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
