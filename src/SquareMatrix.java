public class SquareMatrix {

    private double[][] m_matrix;
    private int m_size;

    public SquareMatrix(double[][] matrix) {
        // Only accepts nxn square matricies
        if (matrix.length != matrix[0].length) {
            System.out.println("Not a valid square matrix");
        } else {
            m_matrix = matrix;
            m_size = matrix.length;
        }
    }

    public double getEntry (int row, int col) {
        if (row < m_size && col < m_size) 
        return m_matrix[row][col];
        System.err.println("Invalid entry");
        return 0;
    }

    public double[] getRow(int row) {
        if (row < m_size) return m_matrix[row];
        System.out.println("Invalid row");
        return null;
    }

    public double[] getColumn(int col) {
        if (col < m_size) {
            double[] column = new double[m_size];
            for (int i=0; i<m_size; i++) {
                column[i] = m_matrix[i][col];
            }
            return column;
        }
        System.out.println("Invalid column");
        return null;
    }

    public int getSize() {
        return m_size;
    }

    public void changeEntry(int row, int col, double newEntry) {
        if (row < m_size && col < m_size) 
        m_matrix[row][col] = newEntry;
    }

    public double[][] getMatrix() {
        return m_matrix;
    }

    public String toString() {
        String output = "";
        for (double[] row : m_matrix) {
            output += "[ ";
            for (double col: row) {
                output += col + " ";
            }
            output += "]\n";
        }
        return output;
    }
}
