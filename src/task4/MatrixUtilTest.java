package task4;

public class MatrixUtilTest {

    public static void main(String[] args) {
        testStandardSlice();
        testSingleElement();
        testFullMatrix();
        testRowVector();
        System.out.println("All matrix slicing tests passed");
    }

    private static void testStandardSlice() {
        int[][] source = {
                {0, 10, 20, 30, 40},
                {50, 60, 70, 80, 90},
                {100, 110, 120, 130, 140},
                {150, 160, 170, 180, 190},
                {200, 210, 220, 230, 240}
        };
        int[][] expected = {
                {60, 70, 80},
                {110, 120, 130},
                {160, 170, 180}
        };
        assertEqual(expected, MatrixUtil.sliceMatrix(source, 1, 3, 1, 3));
    }

    private static void testSingleElement() {
        int[][] source = {
                {1, 2},
                {3, 4}
        };
        int[][] expected = {
                {4}
        };
        assertEqual(expected, MatrixUtil.sliceMatrix(source, 1, 1, 1, 1));
    }

    private static void testFullMatrix() {
        int[][] source = {
                {10, 20},
                {30, 40}
        };
        int[][] expected = {
                {10, 20},
                {30, 40}
        };
        assertEqual(expected, MatrixUtil.sliceMatrix(source, 0, 1, 0, 1));
    }

    private static void testRowVector() {
        int[][] source = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expected = {
                {4, 5, 6}
        };
        assertEqual(expected, MatrixUtil.sliceMatrix(source, 1, 1, 0, 2));
    }

    private static void assertEqual(int[][] expected, int[][] actual) {
        if (actual.length != expected.length) {
            throw new AssertionError(
                    "row count " + actual.length + ", expected " + expected.length);
        }
        for (int i = 0; i < expected.length; i++) {
            if (actual[i].length != expected[i].length) {
                throw new AssertionError(
                        "column count " + actual[i].length
                                + ", expected " + expected[i].length);
            }
            for (int j = 0; j < expected[i].length; j++) {
                if (actual[i][j] != expected[i][j]) {
                    throw new AssertionError(
                            "at [" + i + "][" + j + "] got " + actual[i][j]
                                    + ", expected " + expected[i][j]);
                }
            }
        }
    }
}