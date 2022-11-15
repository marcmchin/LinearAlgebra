import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner m_scan = new Scanner(System.in);

        while (true) {
            System.out.println("\nM A T R I X   C A L C U L A T O R");
            System.out.println("B Y :   M A R C U S\n");
            System.out.println("Enter 1 for augmented matrix row reduction");
            System.out.println("Enter 2 for Markov chain steady state calculator");
            System.out.println("Enter 3 for Markov chain n-step calculator");
            System.out.println("Enter 4 for matrix multiplication");
            System.out.println("Enter 5 for matrix-vector multiplication");
            System.out.println("Enter 0 to close program");

            String selection = m_scan.nextLine();
            if (selection.equals("1")) {
                augmentedMatrixRowReduction();
            } else if (selection.equals("2")) {
                markovChainCalculator();
            } else if (selection.equals("3")) {
                nStepCalculator();
            } else if (selection.equals("4")) {
                matrixMultiplication();
            } else if (selection.equals("5")) {
                matrixVectorMultiplication();
            } else if (selection.equals("0")) {
                System.out.println("\nThanks for using my calculator! :)\n");
                break;
            }
        }
    }

    public static void augmentedMatrixRowReduction() {
        Scanner m_scan0 = new Scanner(System.in);
        System.out.println("\nAugmented Matrix Row Reduction\n");
        System.out.println("This calculator takes in a nxn+1 augmented matrix and performs row reduction.");
        System.out.print("Enter the number of rows: ");
        int rows = m_scan0.nextInt();
        int columns = rows+1;
        double[][] augarray = new double[rows][columns];
        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=columns; j++) {
                System.out.println("Enter entry of row " + i + " column " + j);
                augarray[i-1][j-1] = m_scan0.nextDouble();
            }
        }
        AugmentedMatrix aug = new AugmentedMatrix(augarray);
        System.out.println("\nThe row reduced form is:");
        System.out.println(Operations.rowReducedEchelonForm(aug.getSquareMatrix(), aug.getVector()));
        continuePrompt();
    }
    public static void markovChainCalculator() {
        Scanner m_scan1 = new Scanner(System.in);
        System.out.println("\nMarkov Chain Steady State Calculator\n");
        System.out.println("This calculator takes in a nxn stochastic matrix and computes the steady state.");
        System.out.print("Enter the dimension of the stochastic matrix: ");
        int dim = m_scan1.nextInt();
        double[][] stocarray = new double[dim][dim];
        for (int i=1; i<=dim; i++) {
            for (int j=1; j<=dim; j++) {
                System.out.println("Enter entry of row " + i + " column " + j);
                stocarray[i-1][j-1] = m_scan1.nextDouble();
            }
        }
        MarkovChain markov = new MarkovChain(new StochasticMatrix(new SquareMatrix(stocarray)));
        System.out.println("\nThe steady state is:");
        System.out.println(markov.steadyState());
        continuePrompt();
    }
    public static void nStepCalculator() {
        Scanner m_scan2 = new Scanner(System.in);
        System.out.println("\nMarkov Chain N-Step Calculator\n");
        System.out.println("This calculator takes in a nxn stochastic matrix, a nx1 probability vector, a step value n, and computes the state after n-steps.");
        System.out.println("Enter the dimension of the stochastic matrix");
        int dim = m_scan2.nextInt();
        double[][] stocarray = new double[dim][dim];
        for (int i=1; i<=dim; i++) {
            for (int j=1; j<=dim; j++) {
                System.out.println("Enter entry of row " + i + " column " + j);
                stocarray[i-1][j-1] = m_scan2.nextDouble();
            }
        }
        System.out.println("Now enter the initial state vector.");
        double[] initvec = new double[dim];
        for (int i=1; i<=dim; i++) {
                System.out.println("Enter entry " + i);
                initvec[i-1] = m_scan2.nextDouble();
        }
        System.out.println("Now enter the amount of cycles (n-steps).");
        int n = m_scan2.nextInt();
        MarkovChain markov = new MarkovChain(new StochasticMatrix(new SquareMatrix(stocarray)), new ProbabilityVector(new Vector(initvec)));
        System.out.println("\nThe resulting probability vector is:");
        System.out.println(markov.nStepCycle(n));
        continuePrompt();
    }
    public static void matrixMultiplication() {
        Scanner m_scan3 = new Scanner(System.in);
        System.out.println("\nMatrix Multiplication Calculator\n");
        System.out.println("This calculator takes in two nxn square matricies and multiplies them.");
        System.out.print("Enter the dimension of the square matricies: ");
        int dim = m_scan3.nextInt();
        double[][] matrix1 = new double[dim][dim];
        for (int i=1; i<=dim; i++) {
            for (int j=1; j<=dim; j++) {
                System.out.println("Enter entry of row " + i + " column " + j + " of matrix 1");
                matrix1[i-1][j-1] = m_scan3.nextDouble();
            }
        }
        double[][] matrix2 = new double[dim][dim];
        for (int i=1; i<=dim; i++) {
            for (int j=1; j<=dim; j++) {
                System.out.println("Enter entry of row " + i + " column " + j + " of matrix 2");
                matrix2[i-1][j-1] = m_scan3.nextDouble();
            }
        }
        System.out.println("\nThe resulting matrix is:");
        System.out.println(Operations.multiplyMatricies(new SquareMatrix(matrix1), new SquareMatrix(matrix2)));
        continuePrompt();
    }
    public static void matrixVectorMultiplication() {
        Scanner m_scan4 = new Scanner(System.in);
        System.out.println("\nMatrix-Vector Multiplication Calculator\n");
        System.out.println("This calculator takes in a nxn square matrix and a nx1 vector and multiplies them.");
        System.out.print("Enter the dimension of the matrix and vector: ");
        int dim = m_scan4.nextInt();
        double[][] matrix = new double[dim][dim];
        for (int i=1; i<=dim; i++) {
            for (int j=1; j<=dim; j++) {
                System.out.println("Enter entry of row " + i + " column " + j + " of the matrix");
                matrix[i-1][j-1] = m_scan4.nextDouble();
            }
        }
        double[] vector = new double[dim];
        for (int i=1; i<=dim; i++) {
            System.out.println("Enter entry " + i + " of the vector");
            vector[i-1] = m_scan4.nextDouble();
        }
        System.out.println("\nThe resulting vector is:");
        System.out.println(Operations.multiplyMatrixByVector(new SquareMatrix(matrix), new Vector(vector)));
        continuePrompt();
    }
    public static void continuePrompt() {
        Scanner m_scan5 = new Scanner(System.in);
        while (true) {
            System.out.println("\nWould you like to continue? (Y/N)");
            String answer = m_scan5.nextLine();
            if (answer.toLowerCase().equals("y")) break;
            else if (answer.toLowerCase().equals("n")) {
                System.out.println("\nThanks for using my calculator! :)\n");
                System.exit(0);
            }
        }
    }

}