public class Vector {
    private double[] m_vec;
    private int m_size;
    public Vector(double[] vector) {
        m_vec = vector;
        m_size = vector.length;
    }

    public double getEntry(int i) {
        if (i<m_size) return m_vec[i];
        System.err.println("Invalid entry");
        return 0;
    }

    public int getSize() {
        return m_size;
    }

    public void changeEntry(int i, double newEntry) {
        if (i<m_size) m_vec[i] = newEntry;
        else System.err.println("Invalid entry");
    }
    public double[] getVector() {
        return m_vec;
    }

    public String toString() {
        String output = "";
        for (double entry : m_vec) {
            output += "[ " + entry + " ]\n";
        }
        return output;
    }
}
