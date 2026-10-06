# Experimentation of Python code

## Introduction

#### We are given the code block to investigate and explain it :

```python
tpl = (1, 2, 3)
__sizeof__()
lst = [1, 2, 3]
__sizeof__()
```

#### Python provides the `__sizeof__()` method to determine the amount of memory directly allocated for an object. It can be useful for investigating how different Python data structures are represented internally.

#### However, above code block have some syntax issues that needs to be first. `__sizeof__()` is not associated with any object. We need to call it via the object. So, the correct code block will be this, after fixing the syntax issues:

```python
tpl = (1, 2, 3)
print(tpl.__sizeof__())
lst = [1, 2, 3]
print(lst.__sizeof__())
```

#### First data type is **tuple** (tpl) which is immutable sequence. That means once it has been created, its size can not be changed later.

#### Second data type is **list** (lst) which also contains the same values. But its size can be increased/decreased as needed.

#### _Note: I deliberately added **print()** to show the values on console_

## Interpretation

#### When running the program, we will see the values as following: **48** and **72**  (_Note: these values might change depending on Python version or interpreter._)

#### We need to first understand internal structures of tuple and list and their differences to understand the variations on their sizes. 

#### 1) **Tuple** - A tuple is an **immutable** sequence. Once it has been created, its size cannot be changed.
#### 2) **List** - A list is **mutable**, which means that elements can be **added or removed** after the list has been created.

#### The main issue on the memory management is to predict the capacity that program will hold. For that reasons, different programming languages implemented some technologies, like garbage collectors in Java. When a program guarantees that the values will not be modified, it can logically use memory more efficiently and save some space.

#### In that sense, **tuples** will be more efficient. Python knows that the tuple will always contain three values. That is why, it does not need to maintain any additional threshold memory capacity for future elements. Since the tuples can not grow or shrink, its internal representation can remain fixed all the time.

#### A list is mutable, which means that elements can be added or removed after the list has been created. Python lists normally allocate _additional capacity_ beyond the number of elements currently stored. 

#### The additional capacity helps it to grow without reallocating memory for every append operations. That also means that for every append operation, python does not increase the size at the same level each time. 
#### Lets have a look at one quick example to understand it. I run the application of following :

```
lst = []

print(len(lst), lst.__sizeof__())

lst.append(1)
print(len(lst), lst.__sizeof__())

lst.append(2)
print(len(lst), lst.__sizeof__())

lst.append(3)
print(len(lst), lst.__sizeof__())

lst.append(4)
print(len(lst), lst.__sizeof__())

lst.append(5)
print(len(lst), lst.__sizeof__())

# 0 40
# 1 72
# 2 72
# 3 72
# 4 72
# 5 104
```

#### As we notice from the output, until the fifth element, Python kept the size 72 which shows that memory for elements 1, 2, 3, 4 are allocated from the additional capacity. Once, it has been used, the size again increased to 104. I am pretty sure that, next 3-4 elements will also show 104 as size.


#### I run the same experiment with tuple : 

```
tpl = ()

print(len(tpl), tpl.__sizeof__())

tpl = tpl + (1,)
print(len(tpl), tpl.__sizeof__())

tpl = tpl + (2,)
print(len(tpl), tpl.__sizeof__())

tpl = tpl + (3,)
print(len(tpl), tpl.__sizeof__())

tpl = tpl + (4,)
print(len(tpl), tpl.__sizeof__())

tpl = tpl + (5,)
print(len(tpl), tpl.__sizeof__())

# 0 24
# 1 32
# 2 40
# 3 48
# 4 56
# 5 64
```

#### As seen from the output, the values each time inceases the same amount when I copy the tuple and add one more element to create new tuple. That is also demonstration of fixed memory allocations on the tuples.

## Lesson Learned

#### From my perspective, the main lesson is that immutability can make memory management more predictable, while mutability provides flexibility at the cost of requiring a dynamic allocation strategy. That does not mean that tuple is better than list or one another. They are two different data structures that solves almost different problems.  

