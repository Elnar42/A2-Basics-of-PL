package task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows for matrix A: ");
        int rowsA = scanner.nextInt();

        System.out.print("Enter number of columns for matrix A: ");
        int colsA = scanner.nextInt();

        System.out.print("Enter number of rows for matrix B: ");
        int rowsB = scanner.nextInt();

        System.out.print("Enter number of columns for matrix B: ");
        int colsB = scanner.nextInt();

        if (colsA != rowsB) {
            System.out.println(
                    "Matrix multiplication is not possible. " +
                            "Columns of A must equal rows of B."
            );
        }else{

            int[][] a = new int[rowsA][colsA];
            int[][] b = new int[rowsB][colsB];

            System.out.println("Enter elements of matrix A:");

            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsA; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }

            System.out.println("Enter elements of matrix B:");

            for (int i = 0; i < rowsB; i++) {
                for (int j = 0; j < colsB; j++) {
                    b[i][j] = scanner.nextInt();
                }
            }

            long startTime = System.nanoTime();

            int[][] product = MatrixUtil.multiply(a, b);

            long endTime = System.nanoTime();

            double duration = (endTime - startTime) / 1_000_000.0;

            System.out.println("Duration is " + duration);


            System.out.println("Result:");

            for (int[] row : product) {
                for (int value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }

            scanner.close();
        }
    }
}