# Matrix multiplication


## Introduction

#### We are asked to implement matrix multiplication in both python(Numpy) and C-like language. The main requirement is to have not fixed values, but rather ask the values from user's input.

#### This task has two implementation of the same operation in both Java and Python. Java does not support matrix multiplication and we are required to do the things manually.

#### In Java : 

#### We have the class called `MatrixUtil` which contains the core logic for multiplying the matrix. I wrote it in that way so that other methods (add, subtract) etc, can later be added without changing the main logic.
####  It uses three nested loops over plain `int` arrays: for each result cell it walks the shared dimension and adds `a[i][k] * b[k][j]`. `Main` reads the matrices from the console, calls that method, and prints the product together with the elapsed time.

#### In Python :
#### The array version is `matrix_multiplication.py`. It is relatively shorter than writing in Java because the product is simple single expression `a @ b` on NumPy arrays


## Unit tests

#### `MatrixUtilTest` checks the multiplication method against products that were calculated by hand:

#### - `[[1, 2, 3], [4, 5, 6]]` times `[[7, 8], [9, 10], [11, 12]]` equals `[[58, 64], [139, 154]]`
#### - multiplying on either side by a 2×2 identity matrix leaves the other matrix unchanged
#### - a 1×1 product, `4 * 5 = 20`
#### - a zero matrix times any compatible matrix stays zero
#### - a product that includes negative entries, `[[-1, 2], [3, -4]]` times `[[5, -6], [-7, 8]]` equals `[[-19, 22], [43, -50]]` 

#### The run printed `All matrix multiplication tests passed`.

## Execution time

#### Both measurements time only the multiplication. Building the matrices and printing them are outside the timer.

#### On a 2×2 matrix the Java loop finishes in well under a microsecond. The NumPy call takes about 0.006 ms, roughly nine times longer, because the call still has to enter the library for a product that is only eight multiplications.

#### From 50×50 upward the library call is faster, and the gap grows with the matrix. At 500×500 the Java loop takes 585 ms and NumPy takes 306 ms, so the same integer product is about 1.9 times faster in NumPy. The gap stays in that range because both sides are multiplying 32-bit integers.