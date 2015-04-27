package allatra.test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayVector extends AbstractVector implements Iterable {

    private double[] values;

    public ArrayVector(double[] elements) {
        values = elements;
    }

    public ArrayVector(int size) {
        this.values = new double[size];
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {

            int cursor = -1;

            @Override
            public boolean hasNext() {
                return (size() - 1) > cursor;
            }

            @Override
            public Object next() {
                if (!(hasNext())) throw new NoSuchElementException();
                else {
                    cursor++;
                    return get(cursor);
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public int hashCode() {
        final int constant = 7;
        int result = 7;
        for (int i = 0; i < size(); i++) {
            result = (result * constant) + (int) values[i];
        }
        return result;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if ((otherObject == null) || (getClass() != otherObject.getClass()) || ((ArrayVector) otherObject).size() != size())
            return false;
        ArrayVector otherVector = (ArrayVector) otherObject;
        for (int i = 0; i < otherVector.size(); i++) {
            if (otherVector.get(i) == this.get(i)) i++;
            else return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String vectorName = "";
        for (int i = 0; i < size(); i++)
            vectorName += get(i) + " ";
        return vectorName;
    }

    @Override
    public ArrayVector clone() throws CloneNotSupportedException {
        ArrayVector cloneVector = (ArrayVector) super.clone();
        cloneVector.values = new double[size()];
        for (int i = 0; i < size(); i++) {
            cloneVector.set(i, this.get(i));
        }
        return cloneVector;
    }

    public int size() {
        return values.length;
    }

    public double get(int index) {
        if (0 > index || size() <= index) throw new ArrayIndexOutOfBoundsException();
        return values[index];
    }

    public void set(int index, double value) {
        values[index] = value;
    }

}


