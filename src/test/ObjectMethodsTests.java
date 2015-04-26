package test;

import allatra.test.ArrayVector;
import allatra.test.ListVector;
import allatra.test.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class ObjectMethodsTests {
    protected Object[] objects;

    @BeforeClass
    public static void testToString() {
        System.out.println("ArrayVector: " + new ArrayVector(new double[]{1, 2, 3, 4, 5}));
        System.out.println("ListVector: " + new ListVector(new double[]{1, 2, 3, 4, 5}));
    }

    @Before
    public void createObjects() {
        final double[][] elements = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 0},
                {1, 2, 3},
                {}
        };
        objects = new Object[elements.length * 6];
        int pos = 0;
        for (double[] els : elements) {
            objects[pos++] = new ArrayVector(els);
            objects[pos++] = new ArrayVector(els);
            objects[pos++] = new ArrayVector(els);
            objects[pos++] = new ListVector(els);
            objects[pos++] = new ListVector(els);
            objects[pos++] = new ListVector(els);
        }
    }

    @Test
    public void equalsReflexive() {
        for (Object x : objects)
            assertEquals(x, x);
    }

    @Test
    public void equalsSymmetric() {
        for (Object x : objects)
            for (Object y : objects)
                assertTrue(x + " ~ " + y, x.equals(y) == y.equals(x));
    }

    @Test
    public void equalsTransitive() {
        for (Object x : objects)
            for (Object y : objects)
                for (Object z : objects)
                    if (x.equals(y) && y.equals(z))
                        assertTrue(x + " ~ " + y + " ~ " + z, x.equals(z));
    }

    @Test
    public void equalsToNull() {
        for (Object x : objects)
            assertFalse(x.equals(null));
    }

    @Test
    public void testHashCode() {
        for (Object x : objects)
            for (Object y : objects)
                if (x.equals(y))
                    assertTrue(x + " ~ " + y, x.hashCode() == y.hashCode());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        for (Object x : objects) {
            Vector clone = (Vector) (x instanceof ArrayVector
                    ? ((ArrayVector) x).clone()
                    : ((ListVector) x).clone());

            assertEquals(x, clone);
            if (clone.size() > 0) {
                clone.set(0, clone.get(0) + 1);
                assertFalse(clone + " isn't separate from " + x, x.equals(clone));
            }
        }
    }
}





