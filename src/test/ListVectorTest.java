package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import allatra.test.ListVector;
import org.junit.Test;
import test.VectorTest;

// ---> change to your package <---
//import ListVector;

public class ListVectorTest extends VectorTest
{
    @Override
    protected ListVector vector(int size)
    {
        return new ListVector(size);
    }

    @Override
    protected ListVector vector(double... elements)
    {
        return new ListVector(elements);
    }

    // tests ----------------------------------------------------------

    @Test
    public void testInsert()
    {
        ListVector vector = vector(1,2,3,4,5);
        vector.insert(0, 42);
        assertArrayEquals(new double[] {42,1,2,3,4,5}, elementsOf(vector), EPSILON);
        vector.insert(3, 11);
        assertArrayEquals(new double[] {42,1,2,11,3,4,5}, elementsOf(vector), EPSILON);
        vector.insert(7, 13);
        assertArrayEquals(new double[] {42,1,2,11,3,4,5,13}, elementsOf(vector), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertOutOfBoundsBefore()
    {
        vector(10).insert(-1,42);
    }

    @Test(expected = RuntimeException.class)
    public void testInsertOutOfBoundsAfter()
    {
        vector(10).insert(11,42);
    }

    @Test
    public void testRemove()
    {
        ListVector vector = vector(1,2,3,4,5,6);
        assertEquals(4, vector.remove(3), EPSILON);
        assertArrayEquals(new double[] {1,2,3,5,6}, elementsOf(vector), EPSILON);
        assertEquals(1, vector.remove(0), EPSILON);
        assertArrayEquals(new double[] {2,3,5,6}, elementsOf(vector), EPSILON);
        assertEquals(6, vector.remove(3), EPSILON);
        assertArrayEquals(new double[] {2,3,5}, elementsOf(vector), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveOutOfBoundsBefore()
    {
        vector(10).remove(-1);
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveOutOfBoundsAfter()
    {
        vector(10).remove(10);
    }
}




