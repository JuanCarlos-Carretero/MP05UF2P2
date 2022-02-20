package ex4;

import ex2.HashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void put() {
        HashTable hashTable = new ex2.HashTable();
        System.out.println("Colisiona " + hashTable.getCollisionsForKey("0", 4));
        System.out.println("Colisiona " + hashTable.getCollisionsForKey("1", 4));
        System.out.println("Colisiona " + hashTable.getCollisionsForKey("2", 4));
        System.out.println("Colisiona " + hashTable.getCollisionsForKey("9", 4));

        //2.1.1 Inserir no col·lisiona, una taula vuida
        hashTable.put("1", "Perro");
        assertEquals("\n bucket[1] = [1, Perro]", hashTable.toString());
        assertEquals(1, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.2 Inserir no col·lisiona, una taula no vuida
        hashTable.put("9", "Cocodrilo");
        assertEquals("\n" +
                " bucket[1] = [1, Perro]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(2, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.3 Inserir col·lisiona, una taula no vuida, col·locarà en 2a posició
        hashTable.put("12", "Dalmata");
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Dalmata]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(3, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.4 Inserir col·lisiona, una taula no vuida, col·locarà en 3a posició
        hashTable.put("23", "Turco Andaluz");
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Dalmata] -> [23, Turco Andaluz]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(4, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.5 Inserir ja existeix, no col·lisiona, una taula no vuida.
        hashTable.put("9", "Gato");
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Dalmata] -> [23, Turco Andaluz]\n" +
                " bucket[9] = [9, Gato]", hashTable.toString());
        assertEquals(4, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.6 Inserir ja existeix, si col·lisiona, 2a posició, una taula no vuida.
        hashTable.put("12", "Pastor Aleman");
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Pastor Aleman] -> [23, Turco Andaluz]\n" +
                " bucket[9] = [9, Gato]", hashTable.toString());
        assertEquals(4, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.7 Inserir ja existeix, si col·lisiona, 3a posició, una taula no vuida.
        hashTable.put("23", "Bulldog Frances");
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Pastor Aleman] -> [23, Bulldog Frances]\n" +
                " bucket[9] = [9, Gato]", hashTable.toString());
        assertEquals(4, hashTable.count());
        assertEquals(16, hashTable.size());
    }

    @Test
    void get() {
        HashTable hashTable = new ex2.HashTable();
        //2.1.8 Obtenir no col·lisiona, una taula vuida.
        assertNull(hashTable.get("0"));

        //2.1.9 Obtenir col·lisiona, una taula, 1a posició mateix bucket
        hashTable.put("1", "Perro");
        assertEquals("Perro", hashTable.get("1"));

        //2.1.10 Obtenir col·lisiona, una taula, 2a posició mateix bucket
        hashTable.put("12", "Dalmata");
        assertEquals("Dalmata", hashTable.get("12"));

        //2.1.11 Obtenir col·lisiona, una taula, 3a posició mateix bucket
        hashTable.put("23", "Turco Andaluz");
        assertEquals("Turco Andaluz", hashTable.get("23"));

        //2.1.12 Obtenir no existe, posicion buida
        Assertions.assertNull(hashTable.get("9"));

        //2.1.13 Obtenir no existe, posicio ocupada, altre no col·lisiona.
        hashTable.put("2", "Gato");
        assertNull(hashTable.get("13"));

        //2.1.14 Obtenir no existe, posicio ocupada, 3 altres col·lisiona.
        hashTable.put("13", "Persa");
        hashTable.put("24", "Esfinge");
        assertNull(hashTable.get("35"));
    }

    @Test
    void drop() {
        ex4.HashTable hashTable = new ex4.HashTable();
        hashTable.put("1", "Perro");
        hashTable.put("12", "Dalmata");
        hashTable.put("23", "Turco Andaluz");
        hashTable.put("9", "Cocodrilo");
        assertEquals(16, hashTable.size());

        //2.1.15 Esborrar no col·lisiona.
        hashTable.drop("9");
        assertNull(hashTable.get("9"));
        assertEquals("\n" +
                " bucket[1] = [1, Perro] -> [12, Dalmata] -> [23, Turco Andaluz]", hashTable.toString());
        assertEquals(3, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.16 Esborrar si col·lisiona, 1a posicio mateix bucket.
        hashTable.put("9", "Cocodrilo");
        hashTable.drop("1");
        assertNull(hashTable.get("1"));
        assertEquals("\n" +
                " bucket[1] = [12, Dalmata] -> [23, Turco Andaluz]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(3, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.17 Esborrar si col·lisiona, 2a posició mateix bucket.
        hashTable.put("1", "Perro");
        hashTable.drop("12");
        assertNull(hashTable.get("12"));
        assertEquals("\n" +
                " bucket[1] = [23, Turco Andaluz] -> [1, Perro]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(3, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.18 Esborrar si col·lisiona, 3a posició mateix bucket.
        hashTable.put("12", "Dalmata");
        hashTable.drop("12");
        assertNull(hashTable.get("12"));
        assertEquals("\n" +
                " bucket[1] = [23, Turco Andaluz] -> [1, Perro]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(3, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.19 Esborrar no existeix, posició buida.
        hashTable.put("12", "Dalmata");
        hashTable.drop("3");
        assertNull(hashTable.get("3"));
        assertEquals("\n" +
                " bucket[1] = [23, Turco Andaluz] -> [1, Perro] -> [12, Dalmata]\n" +
                " bucket[9] = [9, Cocodrilo]", hashTable.toString());
        assertEquals(4, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.20 Esborrar no existeix, posicio ocupada, no col·lisiona.
        hashTable.drop("70");
        assertNull(hashTable.get("70"));
        assertEquals(4, hashTable.count());
        assertEquals(16, hashTable.size());

        //2.1.21 Esborrar no existeix, posicio ocupada, 3 col·lisiona.
        hashTable.drop("34");
        assertNull(hashTable.get("34"));
        assertEquals(4, hashTable.count());
        assertEquals(16, hashTable.size());
    }

    @Test
    void count() {
    }

    @Test
    void size() {
    }

    @Test
    void testToString() {
    }
}