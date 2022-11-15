public class MarkovChain {
    private ProbabilityVector m_q;
    private StochasticMatrix m_p;
    private int m_size;
    
    public MarkovChain (StochasticMatrix p, ProbabilityVector q) {
        if (q.getSize() == p.getSize()) {
            m_q = q;
            m_p = p;
            m_size = p.getSize();
        } else {
            System.err.println("Sizes do not match");
        }
    }

    public MarkovChain (StochasticMatrix p) {
        this(p, Operations.getDefaultProbabilityVector(p.getSize()));
    }

    public ProbabilityVector nStepCycle(int n) {
        SquareMatrix tempMatrix = m_p.getClassMatrix();
        for (int i=0; i<n; i++) {
            tempMatrix = Operations.multiplyMatricies(tempMatrix, tempMatrix);
        }
        return new ProbabilityVector(Operations.multiplyMatrixByVector(tempMatrix, m_q.getClassVector()));
    }

    public ProbabilityVector steadyState() {
        // Uses equation Pq=q (solve for eigenvector with eigenvalue 1)
        // Pq-q = 0
        // (P-I)q = 0
        // Let A = P-I
        SquareMatrix A = Operations.subtractMatricies(m_p.getClassMatrix(), Operations.getIdentity(m_size));
        // Create zero vector
        Vector zero = Operations.getZeroVector(m_size);
        // Row reduce into augmented matrix
        AugmentedMatrix aug = Operations.rowReducedEchelonForm(A, zero);
        // Checks for rows of 0s
        // If there is row of 0s, replace with row of 1s
        // This is because q1+q2+q3... = 1 because its a probability vector
        for (int i=0; i<m_size; i++) {
            boolean isZeroRow = true;
            for (int e = 0; e<m_size+1; e++) {
                if (aug.getEntry(i, e) != 0) {
                    isZeroRow = false;
                    break;
                }
            }
            if (isZeroRow) {
                for (int e2 = 0; e2< m_size+1; e2++) {
                    aug.changeEntry(i, e2, 1);
                }
            }
        }
        // Row reduce again to find solution
        aug = Operations.rowReducedEchelonForm(aug.getSquareMatrix(), aug.getVector());
        boolean linIndep = true;

        // Check for linear dependence
        for (int i=0; i<m_size; i++) {
            int zeroCount = 0;
            for (int e=0; e<m_size; e++) {
                if (aug.getEntry(i, e) == 0) zeroCount++;
            }
            if (zeroCount != m_size-1) {
                linIndep = false;
                break;
            }
        }
        // Only return resulting vector if there is a single steady state (linearly independent)
        if (linIndep) return new ProbabilityVector(aug.getVector());
        System.err.println("No solution");
        return null;
    }
}
