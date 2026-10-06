# Matrix multiplication


## Introduction

#### We are asked to implement matrix multiplication in both python(Numpy) and C-like language. The main requirement is to have not fixed values, but rather ask the values from user's input.

#### This task has two implementation of the same operation in both Java and Python. Java does not support matrix multiplication and we are required to do the things manually.

#### In Java : 

#### We have the class called `MatrixUtil` which contains the core logic for multiplying the matrix. I wrote it in that way so that other methods (add, subtract) etc, can later be added without changing the main logic.
####  It uses three nested loops over plain `int` arrays: for each result cell it walks the shared dimension and adds `a[i][k] * b[k][j]`. `Main` reads the matrices from the console, calls that method, and prints the product together with the elapsed time.

Task 3 has two implementations of the same operation.

The C-like version is the Java method `MatrixUtil.multiply`. It uses three nested loops over plain `int` arrays: for each result cell it walks the shared dimension and adds `a[i][k] * b[k][j]`. `Main` reads the matrices from the console, calls that method, and prints the product together with the elapsed time.

The array version is `matrix_multiplication.py`. After the same console input, the product is the single expression `a @ b` on NumPy arrays.

## Unit tests

`MatrixUtilTest` checks the C-like method against products that were calculated by hand:

- `[[1, 2, 3], [4, 5, 6]]` times `[[7, 8], [9, 10], [11, 12]]` equals `[[58, 64], [139, 154]]`
- multiplying on either side by a 2×2 identity matrix leaves the other matrix unchanged
- a 1×1 product, `4 * 5 = 20`
- a row vector times a column vector, `[1, 2, 3] · [4, 5, 6] = 32`
- a zero matrix times any compatible matrix stays zero
- a product that includes negative entries, `[[-1, 2], [3, -4]]` times `[[5, -6], [-7, 8]]` equals `[[-19, 22], [43, -50]]`

Run them with:

```powershell
& "$env:USERPROFILE\.jdks\dragonwell-ex-21.0.12\bin\javac.exe" -d out src\task3\MatrixUtil.java src\task3\MatrixUtilTest.java
& "$env:USERPROFILE\.jdks\dragonwell-ex-21.0.12\bin\java.exe" -cp out task3.MatrixUtilTest
```

The run printed `All matrix multiplication tests passed`.

## Code size

Sizes below are the source files as stored, including input and output. The compiled size is the `MatrixUtil.class` file produced by `javac`.

| Piece | Lines | Non-blank lines | Bytes |
| --- | ---: | ---: | ---: |
| `MatrixUtil.java` (the C-like multiply) | 22 | 19 | 590 |
| `MatrixUtil.class` |  |  | 490 |
| `Main.java` (console driver) | 70 | 49 | 2030 |
| Java program, both files | 92 | 68 | 2620 |
| `matrix_multiplication.py` | 44 | 33 | 1157 |

The loop itself is the part that differs. In Java that is the three `for` loops inside `multiply`, about nine lines that name every index and every product term. In Python that work is the one statement `product = a @ b`. The rest of both programs is the same kind of code: read four dimensions, read each element, reject a mismatched pair of dimensions, then print the result.

The shorter Python file still depends on NumPy. The `@` operator calls a library routine; that routine is not part of this repository, so the 1157 bytes are only the code written for the task.

## Execution time

Both measurements time only the multiplication. Building the matrices and printing them are outside the clock. Each matrix is square. Cell `(i, j)` is `(i * 31 + j * 17 + seed) % 10`, with seed 1 for the left matrix and seed 2 for the right one, so both languages multiply the same integers. Java uses `int`. NumPy uses `int32`. A few calls run first and are discarded. The table is the average of the calls that follow: 20000 calls for 2×2, 200 for 50×50, 20 for 100×100, 3 for 250×250, and 2 for 500×500.

On a 2×2 matrix the Java loop finishes in well under a microsecond. The NumPy call takes about 0.006 ms, roughly nine times longer, because the call still has to enter the library for a product that is only eight multiplications.

From 50×50 upward the library call is faster, and the gap grows with the matrix. At 500×500 the C-like loop takes 585 ms and NumPy takes 306 ms, so the same integer product is about 1.9 times faster in NumPy. The gap stays in that range because both sides are multiplying 32-bit integers. NumPy’s largest speedups come from its floating-point kernels; this task stays on integers so the result matches the Java `int` product.

The number of arithmetic steps in the C-like loop is `n³` for an n×n product: 8 for n = 2, and 125 million for n = 500. The measured Java time follows that growth. From n = 250 to n = 500 the side length doubles and the time rises from 54.9 ms to 585 ms, a factor of about 10.7, close to the factor of 8 expected from doubling `n³`. The extra factor is the cost of moving three large arrays through memory for every result cell.
