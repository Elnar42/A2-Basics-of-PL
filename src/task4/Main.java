package task4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows for source matrix: ");
        int rows = scanner.nextInt();

        System.out.print("Enter number of columns for source matrix: ");
        int cols = scanner.nextInt();

        int[][] source = new int[rows][cols];

        System.out.println("Enter elements of source matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                source[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter starting row index: ");
        int rowStart = scanner.nextInt();

        System.out.print("Enter ending row index (inclusive): ");
        int rowEnd = scanner.nextInt();

        System.out.print("Enter starting column index: ");
        int colStart = scanner.nextInt();

        System.out.print("Enter ending column index (inclusive): ");
        int colEnd = scanner.nextInt();

        if (rowStart < 0 || rowEnd >= rows || rowStart > rowEnd ||
                colStart < 0 || colEnd >= cols || colStart > colEnd) {
            System.out.println("Invalid slice indices provided.");
        } else {
            long startTime = System.nanoTime();

            int[][] sliced = MatrixUtil.sliceMatrix(source, rowStart, rowEnd, colStart, colEnd);

            long endTime = System.nanoTime();

            double duration = (endTime - startTime) / 1_000_000.0;

            System.out.println("Duration is " + duration + " ms");
            System.out.println("Sliced Result:");
            for (int[] row : sliced) {
                for (int value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        }
    }
}