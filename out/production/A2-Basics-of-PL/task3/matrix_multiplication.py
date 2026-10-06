import time

import numpy as np


def read_matrix(name, rows, cols):
    print(f"Enter elements of matrix {name}:")
    values = []
    for _ in range(rows):
        row = []
        for _ in range(cols):
            row.append(int(input()))
        values.append(row)
    return np.array(values, dtype=int)


def main():
    rows_a = int(input("Enter number of rows for matrix A: "))
    cols_a = int(input("Enter number of columns for matrix A: "))
    rows_b = int(input("Enter number of rows for matrix B: "))
    cols_b = int(input("Enter number of columns for matrix B: "))

    if cols_a != rows_b:
        print(
            "Matrix multiplication is not possible. "
            "Columns of A must equal rows of B."
        )
        return

    a = read_matrix("A", rows_a, cols_a)
    b = read_matrix("B", rows_b, cols_b)

    start = time.perf_counter()
    product = a @ b
    duration = (time.perf_counter() - start) * 1000

    print(f"Duration is {duration}")
    print("Result:")
    for row in product:
        print(" ".join(str(value) for value in row))


if __name__ == "__main__":
    main()
