package allatra.test;


public class Vectors {


    public static Vector constant(Vector vector) {
        return new ConstantVector(vector);
    }


    private static void add(Vector vector1, Vector vector2) {
        if (vector1.size() != vector2.size()) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < vector1.size(); i++)
            vector1.set(i, vector1.get(i) + vector2.get(i));
    }

    private static void subtract(Vector vector1, Vector vector2) {
        if (vector1.size() != vector2.size()) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < vector1.size(); i++)
            vector1.set(i, vector1.get(i) - vector2.get(i));
    }

    private static void multiply(Vector vector1, Vector vector2) {
        if (vector1.size() != vector2.size()) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < vector1.size(); i++)
            vector1.set(i, vector1.get(i) * vector2.get(i));
    }

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

    private static double average(Vector vector) {
        double sum = 0;
        for (int i = 0; i < vector.size(); i++)
            sum += vector.get(i);
        return sum / vector.size();
    }

    private static double minimum(Vector vector) {
        if (vector.size() == 0) throw new ArrayIndexOutOfBoundsException();
        double minValue = vector.get(0);
        for (int i = 1; i < vector.size(); i++)
            if (minValue > vector.get(i)) minValue = vector.get(i);
        return minValue;
    }

    private static double maximum(Vector vector) {
        if (vector.size() == 0) throw new ArrayIndexOutOfBoundsException();
        double maxValue = vector.get(0);
        for (int i = 1; i < vector.size(); i++)
            if (maxValue < vector.get(i)) maxValue = vector.get(i);
        return maxValue;
    }

}
