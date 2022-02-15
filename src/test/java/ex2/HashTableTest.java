package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {

    @Test
    void count() {
        HashTable hashTable = new HashTable();
        Assertions.assertEquals(0, hashTable.count());
    }

    @Test
    void size() {
        HashTable hashTable = new HashTable();
        Assertions.assertEquals(16, hashTable.size());
    }

    @Test
    void put() {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "Perro"); //colisiona y vuida
        hashTable.put("17", "Dalmata"); //colisiona porque el maximo es 16 entonces en 17 vuelve a 1
        hashTable.put("9", "Cocodrilo"); //no colisiona y no vuida
        Assertions.assertEquals("Perro", hashTable.get("1"));
        Assertions.assertEquals("\n bucket[1] = [1, Perro]", hashTable.toString());
    }

    @Test
    void get() {
        HashTable hashTable = new HashTable();
        Assertions.assertNull(hashTable.get("1"));
        Assertions.assertNull(hashTable.get("0"));
        Assertions.assertEquals("", hashTable.toString());
    }

    @Test
    void drop() {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "Perro");
        hashTable.drop("1");
        Assertions.assertNull(hashTable.get("1"));
    }

    @Test
    void testToString() {
    }
}