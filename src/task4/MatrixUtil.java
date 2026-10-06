package task4;

public class MatrixUtil {

    public static int[][] sliceMatrix(int[][] source, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        int sliceRows = rowEnd - rowStart + 1;
        int sliceCols = columnEnd - columnStart + 1;

        int[][] slice = new int[sliceRows][sliceCols];

        for (int i = 0; i < sliceRows; i++) {
            for (int j = 0; j < sliceCols; j++) {
                slice[i][j] = source[rowStart + i][columnStart + j];
            }
        }
        return slice;
    }
}
