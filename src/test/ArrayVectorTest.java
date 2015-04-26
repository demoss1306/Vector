package test;

//---> change to your package <---
//import Vector;
//import ArrayVector;

import allatra.test.AbstractVector;
import allatra.test.ArrayVector;
import allatra.test.Vector;
import test.VectorTest;

public class ArrayVectorTest extends VectorTest
{
    @Override
    protected AbstractVector vector(int size)
    {
        return new ArrayVector(size);
    }

    @Override
    protected AbstractVector vector(double... elements)
    {
        return new ArrayVector(elements);
    }
}
