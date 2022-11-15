public class AugmentedMatrix {

    private double[][] m_matrix;
    private int m_rowsize;
    private int m_colsize;

    public AugmentedMatrix(double[][] matrix) {
        // Only accepts nxn+1 augmented matricies
        if (matrix.length+1 != matrix[0].length) {
            System.out.println("Not a valid augmented matrix");
        } else {
            m_matrix = matrix;
            m_rowsize = matrix.length;
            m_colsize = matrix[0].length;
        }
    }

    public double getEntry (int row, int col) {
        if (row < m_rowsize && col < m_colsize) 
        return m_matrix[row][col];
        System.err.println("Invalid entry");
        return 0;
    }

    public double[] getRow(int row) {
        if (row < m_rowsize) return m_matrix[row];
        System.out.println("Invalid row");
        return null;
    }
    public SquareMatrix getSquareMatrix() {
        double[][] outputMatrix = new double[m_rowsize][m_rowsize];
        for (int i=0; i< m_rowsize; i++) {
            for (int j=0; j<m_rowsize; j++) {
                outputMatrix[i][j] = m_matrix[i][j];
            }
        }
        return new SquareMatrix(outputMatrix);
    }

    public Vector getVector() {
        double[] outputVector = new double[m_rowsize];
        for (int i=0; i<m_rowsize; i++) {
            outputVector[i] = m_matrix[i][m_rowsize];
        }
        return new Vector(outputVector);
    }

    public double[] getColumn(int col) {
        if (col < m_matrix[0].length) {
            double[] column = new double[m_rowsize];
            for (int i=0; i<m_rowsize; i++) {
                column[i] = m_matrix[i][col];
            }
            return column;
        }
        System.out.println("Invalid column");
        return null;
    }

    public int getRowSize() {
        return m_rowsize;
    }

    public int getColSize() {
        return m_colsize;
    }

    public void changeEntry(int row, int col, double newEntry) {
        if (row < m_rowsize && col < m_colsize) 
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
