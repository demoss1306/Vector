package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import allatra.test.ArrayVector;
import allatra.test.ListVector;
import allatra.test.Vector;
import allatra.test.Vectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static junit.framework.Assert.assertEquals;


@RunWith(Parameterized.class)
public class ConstantTest
{
    private static final double EPSILON = 1e-7;

    public ConstantTest(Vector vector)
    {
        this.vector = vector;
        this.constant = Vectors.constant(vector);
    }

    @Parameters
    public static Collection<Object[]> parameters()
    {
        return Arrays.asList(new Object[][] {
                { new ArrayVector(new double[]{ 1,2,3,4,5 }) },
                { new ListVector( new double[]{ 1,2,3,4,5 }) }
        });
    }

    @Test
    public void testEquals()
    {
        assertEquals(vector.size(), constant.size());
            for (int i = 0; i < vector.size(); i++)
            assertEquals(vector.get(i), constant.get(i), EPSILON);
    }

    @Test
    public void testUpdate()
    {
        vector.set(1, vector.get(1) + 1);
        assertEquals(vector.get(1), constant.get(1), EPSILON);
    }

    @Test(expected = RuntimeException.class)
    public void testChange()
    {
        constant.set(1, constant.get(1) + 1);
    }

    @Test(expected = RuntimeException.class)
    public void testIteratorRemove()
    {
        Iterator<?> it = ((Iterable<?>) constant).iterator();
        it.next();
        it.remove();
    }

    private Vector vector;
    private Vector constant;
}
