public class Operations {
    /**The dimensions methods are to check if the user has
    inputted an incorrect dimension and produce errors
    based on that**/

    public static boolean compareDimensionsMatrixVector(SquareMatrix m, Vector v) {
        return m.getSize() == v.getSize();
    }

    public static boolean compareDimensionsVectors(Vector v1, Vector v2) {
        return v1.getSize() == v2.getSize();
    }

    public static boolean compareDimensionsMatricies(SquareMatrix m1, SquareMatrix m2) {
        return m1.getSize() == m2.getSize();
    }

    public static double multiplyRowByColumn(double[] row, double[] col) {
        // This method is used for matrix and matrix-vector multiplication methods
        if (row.length == col.length) {
            double output = 0;
            for (int i=0; i<row.length; i++) {
                output += row[i] * col[i];
            }
            return output;
        }
        System.err.println("Dimensions do not match");
        return 0;
    }

    public static Vector multiplyVectorByScalar(Vector v, double s) {
        // Method works as name implies
        double[] outputVec = new double[v.getSize()];
        for (int i=0; i<v.getSize(); i++) {
            outputVec[i] = v.getEntry(i)*s;
        }
        return new Vector(outputVec);
    }

    public static SquareMatrix multiplyMatrixByScalar(SquareMatrix m, double s) {
        // Method works as name implies
        double[][] outputMatrix = new double[m.getSize()][m.getSize()];
        for (int i=0; i<m.getSize(); i++) {
            for (int j=0; j<m.getSize(); j++) {
                outputMatrix[i][j] = m.getEntry(i, j)*s;
            }
        }
        return new SquareMatrix(outputMatrix);
    }

    public static Vector multiplyMatrixByVector(SquareMatrix m, Vector v) {
        // The method takes advantage of the multiplyRowByColumn method and uses it for each vector entry
        if (compareDimensionsMatrixVector(m, v)) {
            double[] outputVec = new double[m.getSize()];
            for (int i=0; i<m.getSize(); i++) {
                outputVec[i] = multiplyRowByColumn(m.getRow(i), v.getVector());
            }
            return new Vector(outputVec);
        }
        System.err.println("Dimensions do not match");
        return null;
    }

    public static SquareMatrix multiplyMatricies(SquareMatrix m1, SquareMatrix m2) {
        // The method takes advantage of the multiplyRowByColumn method and uses it for each matrix row/column
        if (compareDimensionsMatricies(m1, m2)) {
            double[][] outputMatrix = new double[m1.getSize()][m1.getSize()];
            for (int i=0; i<m1.getSize(); i++) {
                for (int j=0; j<m1.getSize(); j++) {
                    outputMatrix[i][j] = multiplyRowByColumn(m1.getRow(i), m2.getColumn(j));
                }
            }
            return new SquareMatrix(outputMatrix);
        }
        System.err.println("Dimensions do not match");
        return null;
    }

    public static SquareMatrix addMatricies(SquareMatrix m1, SquareMatrix m2) {
        // Rounding is necessary due to double inaccuracy
        if (compareDimensionsMatricies(m1, m2)) {
            double[][] outputMatrix = new double[m1.getSize()][m1.getSize()];
            for (int i=0; i<m1.getSize(); i++) {
                for (int j=0; j<m1.getSize(); j++) {
                    outputMatrix[i][j] = Math.round(m1.getEntry(i, j) + m2.getEntry(i, j)*100000)/100000.0;
                }
            }
            return new SquareMatrix(outputMatrix);
        }
        System.err.println("Dimensions do not match");
        return null;
    }

    public static SquareMatrix subtractMatricies(SquareMatrix m1, SquareMatrix m2) {
        // Rounding is necessary due to double inaccuracy
        if (compareDimensionsMatricies(m1, m2)) {
            double[][] outputMatrix = new double[m1.getSize()][m1.getSize()];
            for (int i=0; i<m1.getSize(); i++) {
                for (int j=0; j<m1.getSize(); j++) {
                    outputMatrix[i][j] = Math.round((m1.getEntry(i, j) - m2.getEntry(i, j))*100000)/100000.0;
                }
            }
            return new SquareMatrix(outputMatrix);
        }
        System.err.println("Dimensions do not match");
        return null;
    }

    public static Vector addVectors(Vector v1, Vector v2) {
        // Rounding is necessary due to double inaccuracy
        if (compareDimensionsVectors(v1, v2)) {
            double[] outputVec = new double[v1.getSize()];
            for (int i=0; i<v1.getSize(); i++) {
                outputVec[i] = Math.round((v1.getEntry(i) + v2.getEntry(i))*100000)/100000.0;
            }
            return new Vector(outputVec);
        }
        System.err.println("Dimensions do not match");
        return null;
    }

    public static Vector subtractVectors(Vector v1, Vector v2) {
        // Rounding is necessary due to double inaccuracy
        if (compareDimensionsVectors(v1, v2)) {
            double[] outputVec = new double[v1.getSize()];
            for (int i=0; i<v1.getSize(); i++) {
                outputVec[i] = Math.round((v1.getEntry(i) - v2.getEntry(i))*100000)/100000.0;
            }
            return new Vector(outputVec);
        }
        System.err.println("Dimensions do not match");
        return null;
    }

    public static SquareMatrix getIdentity(int rank) {
        // Returns object SquareMatrix of the identity matrix of the given rank
        if (rank >= 1) {
            double[][] outputMatrix = new double[rank][rank];
            for (int i=0; i<rank; i++) {
                for (int j=0; j<rank; j++) {
                    if (i==j) outputMatrix[i][j] = 1.0;
                    else outputMatrix[i][j] = 0.0;
                }
            }
            return new SquareMatrix(outputMatrix);
        }
        System.err.println("Invalid rank");
        return null;
    }
    public static Vector getZeroVector(int rank) {
        // Returns object Vector of the zero vector of the given rank
        if (rank >= 1) {
            double[] outputVector = new double[rank];
            for (int i = 0; i<rank; i++) {
                outputVector[i] = 0;
            }
            return new Vector(outputVector);
        }
        System.err.println("Invalid rank");
        return null;
    }
    public static ProbabilityVector getDefaultProbabilityVector(int rank) {
        // Default probability vector is just zero vector with first entry 1
        // Used for default constructor in order to not produce error message (even though doing so isn't really an issue)
        Vector zero = getZeroVector(rank);
        zero.changeEntry(0, 1.0);
        return new ProbabilityVector(zero);
    }
    public static int getPivot(double[] row) {
        // Checks double array and returns index of first non-zero entry
        for (int i=0; i<row.length; i++) {
            if (row[i] != 0) return i;
        }
        return -1;
    }
    public static AugmentedMatrix rowReducedEchelonForm(SquareMatrix A, Vector b) {
        // Algorithm returns AugmentedMatrix object in row reduced echelon form
        if (A.getSize() == b.getSize()) {    
            double[][] m_A = A.getMatrix();
            double[] m_b = b.getVector();
            // Creates 2D double array of correct size
            double[][] augmentedMatrix = new double[A.getSize()][A.getSize()+1];

            // Adds SquareMatrix and Vector entries to the 2D array
            for (int i =0; i<A.getSize(); i++) {
                for (int j=0; j<A.getSize()+1; j++) {
                    if (j != A.getSize()) augmentedMatrix[i][j] = m_A[i][j];
                    else augmentedMatrix[i][j] = m_b[i];
                }
            }

            // Part 1: reducing downwards (reduced row)
            // For each row, the algorithm takes a factor of it (calculated by multiplier double)
            // and subtracts it from each row below it. This is how Gaussian elimination is done by hand
            double[][] reduceDown = augmentedMatrix;
            for (int i = 0; i<A.getSize(); i++) {
                int pivot = getPivot(reduceDown[i]);
                if (pivot != -1) {
                    for (int j = i+1; j<A.getSize(); j++) {
                        double multiplier = reduceDown[j][pivot]/reduceDown[i][pivot];
                        for (int e = 0; e<A.getSize()+1; e++) {
                            reduceDown[j][e] -= reduceDown[i][e] * multiplier;
                        }
                    }
                }
            }
            // Divides each row by its pivot so that each pivot is 1
            for (int i=0; i<A.getSize(); i++) {
                int pivot = getPivot(reduceDown[i]);
                if (pivot != -1) {
                    double multiplier = reduceDown[i][pivot];
                    for (int e=0; e<A.getSize()+1; e++) {
                        reduceDown[i][e] /= multiplier;
                    }
                }
            }

            // Part 2: reduce up (echelon form)
            // Performs same algorithm as reduce down but the other way around
            double[][] reduceUp = reduceDown;
            for (int i=A.getSize()-1; i>=0; i--) {
                int pivot = getPivot(reduceUp[i]);
                if (pivot != -1) {
                    for (int j=i-1; j>=0; j--) {
                        double multiplier = reduceUp[j][pivot]/reduceUp[i][pivot];
                        for (int e=0; e<A.getSize()+1; e++) {
                            reduceUp[j][e] -= reduceUp[i][e] * multiplier;
                        }
                    }
                }
            }
            // Returns augmented matrix
            return new AugmentedMatrix(reduceUp);
        }
        System.err.println("Dimensions do not match");
        return null;
    }
}