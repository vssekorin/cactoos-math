<img src="http://cf.jare.io/?u=http%3A%2F%2Fwww.yegor256.com%2Fimages%2Fbooks%2Felegant-objects%2Fcactus.svg" height="100px" />

[![Build Status](https://travis-ci.org/VsSekorin/cactoos-math.svg?branch=master)](https://travis-ci.org/VsSekorin/cactoos-math)

Addition to the [Cactoos](https://github.com/yegor256/cactoos) containing mathematical objects.

## Seq

Natural Number:
```java
new Seq(0, a -> a + 1)
```
Stream of random double greater than or equal to 6 and less than 16:
```java
new Seq(new Random(6, 16), a -> new Random(6, 16))
```
```java
new And(
    (Proc<Double>) System.out::println,
    new Mapped<Scalar<Double>, Double>(
        Scalar::value,
        new Limited<>(
            20,
            new Seq<>(new Random(0, 10), a -> new Random(0, 10))
        )
    )
).value();
```

## BiSeq

Fibonacci number:
```java
new BiSeq<>(0, 1, (fst, snd) -> fst + snd)
```

## How to contribute?

Just fork the repo and send us a pull request.

Make sure your branch builds without any warnings/issues:

```
mvn clean install -Pqulice
```

## MIT License

Copyright (c) 2017 Vseslav Sekorin

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

