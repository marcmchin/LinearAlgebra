public class StochasticMatrix {
    SquareMatrix m_stocmatrix;

    public StochasticMatrix(SquareMatrix matrix) {
        for (int j=0; j<matrix.getSize(); j++) {
            double count = 0;
            for (int i=0; i<matrix.getSize(); i++) {
                count += matrix.getEntry(i, j);
            }
            if (count != 1) {
                System.out.println("Not a valid stochastic matrix (this error may be due to rounding)");
                // Still sets it as there are often double rounding errors so it doesnt add up to 1
                break;
            }
        }
        m_stocmatrix = matrix;
    }
    public double getEntry(int i, int j) {
        return m_stocmatrix.getEntry(i, j);
    }
    public double[] getRow(int row) {
        return m_stocmatrix.getRow(row);
    }
    public double[] getColumn(int col) {
        return m_stocmatrix.getColumn(col);
    }
    public int getSize() {
        return m_stocmatrix.getSize();
    }
    public void changeEntry(int row, int col, double newEntry) {
        m_stocmatrix.changeEntry(row, col, newEntry);
    }
    public double[][] getMatrix() {
        return m_stocmatrix.getMatrix();
    }
    public SquareMatrix getClassMatrix() {
        return m_stocmatrix;
    }
    public String toString() {
        return m_stocmatrix.toString();
    }
}
