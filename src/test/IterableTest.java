package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import allatra.test.ArrayVector;
import allatra.test.ListVector;
import allatra.test.Vector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class IterableTest
{
    private static final double EPSILON = 1e-7;
    private Vector vector;

    public IterableTest(Vector vector)
    {
        this.vector = vector;
    }

    @Parameters
    public static Collection<Object[]> parameters()
    {
        return Arrays.asList(new Object[][] {
                { new ArrayVector(new double[]{ 1,2,3,4,5 }) },
                { new ListVector(new double[]{ 1,2,3,4,5 }) }
        });
    }

    @Test
    public void testIterator()
    {
        int i = 0;
        for (double value : (Iterable<Double>) vector)
            assertEquals(vector.get(i++), value, EPSILON);
    }

    @Test
    public void testRemoveInvalid()
    {
        try {
            ((Iterable<Double>) vector).iterator().remove();
            fail("Can't remove element before it is returned");
        }
        catch (IllegalStateException e) {
            // ok
        }
        catch (UnsupportedOperationException e) {
            if (vector instanceof ListVector)
                fail("List must support removing");
        }
    }

    @Test
    public void testRemoveTwice()
    {
        if (vector instanceof ListVector) {
            try {
                Iterator<?> it = ((Iterable<?>) vector).iterator();
                it.next();
                it.next();
                it.remove();
                it.remove();
                fail("Can't remove element twice");
            }
            catch (IllegalStateException e) {
                // ok
            }
        }
    }

    @Test
    public void testRemove()
    {
        ListVector first = new ListVector(new double[]{ 1,2,3,4,5 });
        ListVector second = new ListVector(new double[]{ 1,2,3,4,5 });

        Iterator<Double> it = ((Iterable<Double>) first).iterator();
        it.next();
        it.remove();
        while (it.hasNext()) it.next();
        it.remove();

        second.remove(0);
        second.remove(second.size() - 1);

        assertEquals(first, second);
    }

    @Test(expected = NoSuchElementException.class)
    public void testAfterLast()
    {
        Iterator<Double> it = ((Iterable<Double>) vector).iterator();
        while (it.hasNext()) it.next();
        it.next();
    }
}

