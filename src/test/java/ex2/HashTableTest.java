package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashTableTest {

    @Test
    void count() {
        HashTable hashTable = new HashTable();
        assertEquals(0, hashTable.count());
    }

    @Test
    void size() {
        HashTable hashTable = new HashTable();
        assertEquals(16, hashTable.size());
    }

    @Test
    void put() {
        HashTable hashTable = new HashTable();

        //2.1.1
        hashTable.put("1", "Perro"); //colisiona y vuida
        assertEquals("Perro", hashTable.get("1"));
        assertEquals("\n bucket[1] = [1, Perro]", hashTable.toString());
        assertEquals(1, hashTable.count());

        //2.1.2
        hashTable.put("9", "Cocodrilo"); //no colisiona y no vuida
        assertEquals("Perro", hashTable.get("1"));
        assertEquals("\n" +
                " bucket[1] = [1, Perro]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(2, hashTable.count());

        //2.1.3
        hashTable.put("12", "Dalmata");
        assertEquals("Dalmata", hashTable.get("12"));
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Dalmata]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(3, hashTable.count());

        //2.1.4
        hashTable.put("23","Turco Andaluz");
        assertEquals("Turco Andaluz", hashTable.get("23"));
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Dalmata] -> [23, Turco Andaluz]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(4, hashTable.count());

        //2.1.5
        hashTable.put("9", "Gato");
        assertEquals("Gato", hashTable.get("9"));
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Dalmata] -> [23, Turco Andaluz]\n" +
                " bucket[9] = [9, Gato]", hashTable.toString());
        assertEquals(4, hashTable.count());

        //2.1.6
        hashTable.put("12", "Pastor Aleman");
        assertEquals("Pastor Aleman", hashTable.get("12"));
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Pastor Aleman] -> [23, Turco Andaluz]\n" +
                " bucket[9] = [9, Gato]", hashTable.toString());
        assertEquals(4, hashTable.count());

        //2.1.7
        hashTable.put("23", "Bulldog Frances");
        assertEquals("Bulldog Frances", hashTable.get("23"));
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Pastor Aleman] -> [23, Bulldog Frances]\n" +
                " bucket[9] = [9, Gato]", hashTable.toString());
        assertEquals(4, hashTable.count());
    }

    @Test
    void get() {
        HashTable hashTable = new HashTable();
        Assertions.assertNull(hashTable.get("1"));
        Assertions.assertNull(hashTable.get("0"));
        assertEquals("", hashTable.toString());
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