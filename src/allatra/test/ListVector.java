package allatra.test;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListVector extends AbstractVector implements Iterable {

    private Node firstNode;

    public ListVector(int size) {
        if (size == 0) {
            firstNode = null;
            return;
        }
        Node lastNode = firstNode = new Node();
        for (int i = 0; i < size - 1; i++) {
            lastNode.next = new Node();
            lastNode = lastNode.next;
        }
    }

    public ListVector(double[] elements) {
        Node lastNode = firstNode = new Node();
        for (int i = 0; i < elements.length; i++) {
            lastNode.value = elements[i];
            if (i < elements.length - 1) {
                lastNode.next = new Node();
                lastNode = lastNode.next;
            }
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {

            int cursor = -1;
            boolean beRemoved = false;

            @Override
            public boolean hasNext() {
                return (size() - 1) > cursor;
            }

            @Override
            public Object next() {
                if (!(hasNext())) throw new NoSuchElementException();
                else {
                    cursor++;
                    beRemoved = false;
                    return get(cursor);
                }
            }

            @Override
            public void remove() {
                if (beRemoved) throw new IllegalStateException();
                if ((cursor == -1) || (cursor >= size())) throw new IllegalStateException();
                else {
                    if (cursor == 0) {
                        firstNode = firstNode.next;
                        beRemoved = true;
                        return;
                    } else {
                        Node tempNode = firstNode;
                        for (int j = 0; j < cursor - 1; j++)
                            tempNode = tempNode.next;
                        tempNode.next = tempNode.next.next;
                        beRemoved = true;
                    }
                }
            }
        };
    }

    @Override
    public int hashCode() {
        final int constant = 13;
        int result = 13;
        for (int i = 0; i < size(); i++) {
            result = (result * constant) + (int) get(i);
        }
        return result;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if ((otherObject == null) || (getClass() != otherObject.getClass()) || ((ListVector) otherObject).size() != size())
            return false;
        ListVector otherVector = (ListVector) otherObject;
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
    public ListVector clone() throws CloneNotSupportedException {
        double[] values = new double[size()];
        for (int i = 0; i < size(); i++) {
            values[i] = this.get(i);
        }
        ListVector cloneVector = new ListVector(values);
        return cloneVector;
    }

    public int size() {
        Node tempNode = firstNode;
        for (int size = 0; ; size++)
            if (tempNode == null) return size;
            else tempNode = tempNode.next;
    }

    public double get(int index) {
        if (index < 0 || index > size()) throw new ArrayIndexOutOfBoundsException();
        Node tempNode = firstNode;
        for (int j = 0; j < index; j++)
            tempNode = tempNode.next;
        return tempNode.value;
    }

    public void set(int index, double value) {
        Node tempNode = firstNode;
        for (int j = 0; j < index; j++) {
            tempNode = tempNode.next;
        }
        tempNode.value = value;
    }

    public void insert(int index, double value) {
        if (index < 0 || index > size()) throw new ArrayIndexOutOfBoundsException();
        Node tempNode;
        if (index == 0) {
            tempNode = new Node();
            tempNode.value = value;
            tempNode.next = firstNode;
            firstNode = tempNode;
        } else {
            tempNode = firstNode;
            for (int j = 0; j < index - 1; j++)
                tempNode = tempNode.next;
            Node newNode = new Node();
            newNode.next = tempNode.next;
            tempNode.next = newNode;
            newNode.value = value;
        }
    }

    public double remove(int index) {
        if (index < 0 || index > size()) throw new ArrayIndexOutOfBoundsException();
        double value;
        if (index == 0) {
            value = firstNode.value;
            firstNode = firstNode.next;
            return value;
        } else {
            Node tempNode = firstNode;
            for (int j = 0; j < index - 1; j++)
                tempNode = tempNode.next;
            value = tempNode.next.value;
            tempNode.next = tempNode.next.next;
            return value;
        }
    }

    private class Node {
        private double value;
        private Node next;
    }
}
