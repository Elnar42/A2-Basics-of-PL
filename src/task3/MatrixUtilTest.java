package task3;

public class MatrixUtilTest {

    public static void main(String[] args) {
        testKnownProduct();
        testIdentity();
        testOneByOne();
        testZeros();
        testNegativeValues();
        System.out.println("All matrix multiplication tests passed");
    }

    private static void testKnownProduct() {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] b = {
                {7, 8},
                {9, 10},
                {11, 12}
        };
        int[][] expected = {
                {58, 64},
                {139, 154}
        };
        assertEqual(expected, MatrixUtil.multiply(a, b));
    }

    private static void testIdentity() {
        int[][] identity = {
                {1, 0},
                {0, 1}
        };
        int[][] value = {
                {5, 6},
                {7, 8}
        };
        assertEqual(value, MatrixUtil.multiply(identity, value));
        assertEqual(value, MatrixUtil.multiply(value, identity));
    }

    private static void testOneByOne() {
        int[][] a = {{4}};
        int[][] b = {{5}};
        assertEqual(new int[][]{{20}}, MatrixUtil.multiply(a, b));
    }

    private static void testZeros() {
        int[][] a = {
                {0, 0},
                {0, 0}
        };
        int[][] b = {
                {9, 8},
                {7, 6}
        };
        assertEqual(new int[][]{{0, 0}, {0, 0}}, MatrixUtil.multiply(a, b));
    }

    private static void testNegativeValues() {
        int[][] a = {
                {-1, 2},
                {3, -4}
        };
        int[][] b = {
                {5, -6},
                {-7, 8}
        };
        int[][] expected = {
                {-19, 22},
                {43, -50}
        };
        assertEqual(expected, MatrixUtil.multiply(a, b));
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
