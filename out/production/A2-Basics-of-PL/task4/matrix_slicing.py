import time
import numpy as np


def read_matrix(rows, cols):
    print("Enter elements of source matrix:")
    values = []
    for _ in range(rows):
        row = []
        for _ in range(cols):
            row.append(int(input()))
        values.append(row)
    return np.array(values, dtype=int)


def main():
    rows = int(input("Enter number of rows for source matrix: "))
    cols = int(input("Enter number of columns for source matrix: "))

    source = read_matrix(rows, cols)

    row_start = int(input("Enter starting row index: "))
    row_end = int(input("Enter ending row index (inclusive): "))
    col_start = int(input("Enter starting column index: "))
    col_end = int(input("Enter ending column index (inclusive): "))

    if (
        row_start < 0
        or row_end >= rows
        or row_start > row_end
        or col_start < 0
        or col_end >= cols
        or col_start > col_end
    ):
        print("Invalid slice indices provided.")
        return

    start = time.perf_counter()

    sliced = source[row_start : row_end + 1, col_start : col_end + 1]
    
    duration = (time.perf_counter() - start) * 1000

    print(f"Duration is {duration}")
    print("Sliced Result:")
    for row in sliced:
        print(" ".join(str(value) for value in row))


if __name__ == "__main__":
    main()