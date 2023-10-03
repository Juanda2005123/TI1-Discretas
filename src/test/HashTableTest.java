package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.HashTable;

public class HashTableTest {
    

    @Test
    public void hashFunctionStringIsWorking(){
        var h = new HashTable();
        String prueba = "Hoy";
        int resultadoFuncion = h.hashFunction(prueba);
        int resultado = 72+111+121;
        assertEquals(resultado%23, resultadoFuncion);

    } 

}
