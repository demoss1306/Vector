package allatra.test;

public abstract class AbstractVector implements Vector, Cloneable {

    public static void sort(Vector vector) {
        double tempValue;
        for (int i = 0; i < vector.size() - 1; i++)
            for (int j = 0; j < vector.size() - i - 1; j++)
                if (vector.get(j) > vector.get(j + 1)) {
                    tempValue = vector.get(j);
                    vector.set(j, vector.get(j + 1));
                    vector.set(j + 1, tempValue);
                }
    }

    public abstract int size();

    public abstract double get(int index);

    public abstract void set(int index, double value);

    public int indexOf(double value) {
        for (int i = 0; i < size(); i++) {
            if (get(i) == value) return i;
        }
        return -1;
    }

    public void add(Vector vector) {
        if (this.size() != vector.size()) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < size(); i++)
            set(i, this.get(i) + vector.get(i));
    }

    public void subtract(Vector vector) {
        if (this.size() != vector.size()) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < size(); i++)
            set(i, this.get(i) - vector.get(i));
    }

    public void multiply(Vector vector) {
        if (this.size() != vector.size()) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < size(); i++)
            set(i, this.get(i) * vector.get(i));
    }

    public double average() {
        double sum = 0;
        for (int i = 0; i < size(); i++)
            sum += get(i);
        return sum / size();
    }

    public double minimum() {
        if (size() == 0) throw new ArrayIndexOutOfBoundsException();
        double minValue = get(0);
        for (int i = 1; i < size(); i++)
            if (minValue > get(i)) minValue = get(i);
        return minValue;
    }

    public double maximum() {
        if (size() == 0) throw new ArrayIndexOutOfBoundsException();
        double maxValue = get(0);
        for (int i = 1; i < size(); i++)
            if (maxValue < get(i)) maxValue = get(i);
        return maxValue;
    }

}

