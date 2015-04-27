package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import allatra.test.AbstractVector;
import allatra.test.Vector;
import org.junit.Test;

//---> change to your package <---
//import Vector;

public abstract class VectorTest
{
    // ----------------------------------------------------------------

    protected abstract AbstractVector vector(int size);

    protected abstract AbstractVector vector(double ... elements);

    protected double[] elementsOf(AbstractVector vector)
    {
        double[] elements = new double[vector.size()];
        for (int i = 0; i < elements.length; i++)
            elements[i] = vector.get(i);
        return elements;
    }

    protected static final double EPSILON = 1e-5;

    // tests ----------------------------------------------------------

    @Test
    public void testConstructor1()
    {
        assertEquals(5, vector(5).size());
    }

    @Test
    public void testConstructor2()
    {
        double[] elements = {5,7,2,1,13,4};
        assertArrayEquals(elements, elementsOf(vector(elements)), EPSILON);
    }

    @Test
    public void testSet()
    {
        AbstractVector vector = vector(10);
        vector.set(0, 42);
        vector.set(5, 13);
        vector.set(9, 22);

        assertEquals(42, vector.get(0), EPSILON);
        assertEquals(13, vector.get(5), EPSILON);
        assertEquals(22, vector.get(9), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testGetOutOfRangeBefore()
    {
        vector(10).get(-1);
    }

    @Test(expected = RuntimeException.class)
    public void testGetOutOfRangeAfter()
    {
        vector(10).get(10);
    }

    @Test
    public void testMinimum()
    {
        assertEquals(5, vector(10,7,5,13,8,7).minimum(), EPSILON);
        assertEquals(5, vector(5,7,10,13,8,7).minimum(), EPSILON);
        assertEquals(5, vector(10,7,7,13,8,5).minimum(), EPSILON);
    }

    @Test
    public void testMaximum()
    {
        assertEquals(13, vector(10,7,5,13,8,7).maximum(), EPSILON);
        assertEquals(13, vector(13,7,10,5,8,7).maximum(), EPSILON);
        assertEquals(13, vector(10,7,7,5,8,13).maximum(), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testEmptyMinimum()
    {
        vector(0).minimum();
    }

    @Test(expected = RuntimeException.class)
    public void testEmptyMaximum()
    {
        vector(0).maximum();
    }

    @Test
    public void testSort()
    {
        double[] elements = {5,8,2,4,7,6,1,3,9};
        double[] sorted   = {1,2,3,4,5,6,7,8,9};
        AbstractVector vector = vector(elements);
        vector.sort(vector);//vector я добавил сам временно, чтобы срабатывали остальные тесты
        assertArrayEquals(sorted, elementsOf(vector), EPSILON);
    }

    @Test
    public void testIndexOf()
    {
        assertEquals(5, vector(0,1,2,3,4,42,6,7).indexOf(42));
        assertEquals(0, vector(42,1,2,3,4,5,6,7).indexOf(42));
        assertEquals(7, vector(0,1,2,3,4,5,6,42).indexOf(42));
        assertEquals(-1, vector(0,1,2,3,4,5,6,7).indexOf(42));
    }

    @Test
    public void testAverage()
    {
        assertEquals(45./9, vector(1,2,3,4,5,6,7,8,9).average(), EPSILON);
    }

    @Test
    public void testAdd()
    {
        AbstractVector vector = vector(1,2,3,4,5,6);
        vector.add(vector(2,3,4,5,6,7));
        assertArrayEquals(new double[] {3,5,7,9,11,13}, elementsOf(vector), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testAddMissized1()
    {
        vector(5).add(vector(3));
    }

    @Test(expected = RuntimeException.class)
    public void testAddMissized2()
    {
        vector(5).add(vector(7));
    }

    @Test
    public void testSubtract()
    {
        AbstractVector vector = vector(2,3,4,5,6,7);
        vector.subtract(vector(1,2,3,4,5,6));
        assertArrayEquals(new double[] {1,1,1,1,1,1}, elementsOf(vector), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testSubtractMissized1()
    {
        vector(5).subtract(vector(3));
    }

    @Test(expected = RuntimeException.class)
    public void testSubtractMissized2()
    {
        vector(5).subtract(vector(7));
    }

    @Test
    public void testMultiply()
    {
        AbstractVector vector = vector(1,2,3,4,5,6);
        vector.multiply(vector(2,3,4,5,6,7));
        assertArrayEquals(new double[] {2,6,12,20,30,42}, elementsOf(vector), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testMultMissized1()
    {
        vector(5).multiply(vector(3));
    }

    @Test(expected = RuntimeException.class)
    public void testMultMissized2()
    {
        vector(5).multiply(vector(7));
    }
}











