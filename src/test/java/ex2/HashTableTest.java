package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {
    HashTable hashTable = new HashTable();
    @Test
    void count() {
        Assertions.assertEquals(0, hashTable.count());
        Assertions.assertEquals("", hashTable.toString());
    }

    @Test
    void size() {
        Assertions.assertEquals(16, hashTable.size());
        Assertions.assertEquals("", hashTable.toString());
    }

    @Test
    void put() {
        hashTable.put("1", "Cipote");
        Assertions.assertEquals("Cipote", hashTable.get("1"));
        Assertions.assertEquals("\n bucket[1] = [1, Cipote]", hashTable.toString());
    }

    @Test
    void get() {
        Assertions.assertNull(hashTable.get("1"));
        Assertions.assertNull(hashTable.get("0"));
        Assertions.assertEquals("", hashTable.toString());
    }

    @Test
    void drop() {
        hashTable.put("1", "Cipote");
        hashTable.drop("1");
        Assertions.assertNull(hashTable.get("1"));
    }

    @Test
    void testToString() {
    }
}