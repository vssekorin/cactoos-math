[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/VsSekorin/cactoos-math)](http://www.rultor.com/p/VsSekorin/cactoos-math)

[![Build Status](https://travis-ci.org/VsSekorin/cactoos-math.svg?branch=master)](https://travis-ci.org/VsSekorin/cactoos-math)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/VsSekorin/cactoos-math/blob/master/LICENSE.txt)

Cactoos-Math is math extension of [Cactoos](https://github.com/yegor256/cactoos) ([my blog post about this](http://vssekorin.com/post/cactoos/)).

**Motivation**:

- Math classes is overkill for Cactoos, but it can be useful and interesting.
- It is generally accepted that FP, rather than OOP, is more suitable for math.
- Practice in OOP.

**Principles**.
These are the [design principles](http://www.elegantobjects.org#principles) behind Cactoos.

Java version required: 1.8+.

## Funcs

Please use a `Func<X, Func<Y, Z>>` instead of `BiFunc<X, Y, Z>` ([a little bit about that](https://github.com/google/guava/wiki/IdeaGraveyard#functionspredicates-for-n--2-inputs)).

```java
BiFunc<Integer, Long, Number> bifunc = (fst, snd) -> fst + snd;
Func<Integer, Func<Long, Number>> funcfunc = new BiFuncFunc<>(bifunc);
```

[Partial application](https://en.wikipedia.org/wiki/Partial_application):

```java
Func<Long, Long> func = new PartApply<>(bifunc, 2);
```
This is equivalent to:
```java
Func<Long, Long> func = new BiFuncFunc<>(bifunc).apply(2)
```

## Seq

Natural Number:
```java
new Seq(0, a -> a + 1)
```
Stream of random double greater than or equal to 6 and less than 16:
```java
new Seq(new Random(6, 16), a -> new Random(6, 16))
```

## BiSeq

Fibonacci number:
```java
new BiSeq<>(0, 1, (fst, snd) -> fst + snd)
```

Lucas number:
```java
new BiSeq<>(2, 1, fst -> snd -> snd + fst)
```

## Matrix

Identity matrix 6x6:

```java
new MatrixOf<>(
    row -> col -> row.equals(col) ? 1 : 0,
    6, 6
)
```

The product of two matrices:

```java
new MatrixProd<>(
    new MatrixOf<>(
        new Integer[][]{
            {1, 2},
            {3, 4},
        }
    ),
    new MatrixOf<>(
        new Long[][]{
            {9L, 8L},
            {7L, 6L},
        }
    ),
    a -> b -> a * b,
    a -> b -> a + b,
    0L
)
```

Determinant:

```java
new IntDet<>(
    new MatrixOf<>(
        new Integer[][]{
            {1, 2},
            {3, 4},
        }
    )
)
```

## Scalars

Random number in [2; 4]:
```java
new Random(() -> 2, () -> 4);
```

Factorial of 5:

```java
new ReduceLeft<>(
    new Limited<>(
        5,
        new Seq<>(1, a -> a + 1)
    ),
    fst -> snd -> fst * snd
);
```

Other scalars: `Abs`, `Cbrt`, `Constant`, `Cos`, `Exp`, `FoldLeft`, `FoldRight`, `ReduceLeft`, `ReduceRight`, `Round`, `Sin`, `Sqrt`, `Tan`.

## How to contribute?

Just fork the repo and send us a pull request.

Make sure your branch builds without any warnings/issues:

```
mvn clean install -Pqulice
```

## Contributors

  - [@VsSekorin](https://github.com/VsSekorin) as Vseslav Sekorin ([Blog](http://vssekorin.com))
  - [@daryaego](https://github.com/daryaego) as Darya Egorova
  - [@floreasorin](https://github.com/floreasorin)

## MIT License

Copyright (c) 2017-2019 Vseslav Sekorin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

