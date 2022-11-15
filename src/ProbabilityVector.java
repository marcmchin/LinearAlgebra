public class ProbabilityVector {
    Vector m_probvec;
    public ProbabilityVector(Vector vector) {
        double count = 0;
        for (int i=0; i<vector.getSize(); i++) {
            count += vector.getEntry(i);
        }
        if (count != 1.0) {
            System.out.println("Not a probability vector (this error may be due to rounding)");
        } 
        // Still sets it as there are often double rounding errors so it doesnt add up to 1
        m_probvec = vector;
    }

    public double getEntry(int i) {
        return m_probvec.getEntry(i);
    }

    public int getSize() {
        return m_probvec.getSize();
    }

    public void changeEntry(int i, double newEntry) {
        m_probvec.changeEntry(i, newEntry);
    }
    public double[] getVector() {
        return m_probvec.getVector();
    }
    public Vector getClassVector() {
        return m_probvec;
    }
    public String toString() {
        return m_probvec.toString();
    }
}