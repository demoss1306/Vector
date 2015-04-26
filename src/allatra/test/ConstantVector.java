package allatra.test;

/**
 * Created by 1 on 26.04.2015.
 */
public class ConstantVector implements Vector {

    private Vector vector;

    public ConstantVector(Vector vector) {

        this.vector = vector;
    }

    @Override
    public int size() {
        return vector.size();
    }

    @Override
    public double get(int index) {
        return vector.get(index);
    }

    @Override
    public void set(int index, double value) {
        throw new RuntimeException();
    }
}
