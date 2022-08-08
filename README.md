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

## Matrix

Identity matrix 6x6:

```java
new MatrixOf<>(
    row -> col -> row.equals(col) ? 1 : 0,
    6, 6
)
```

## How to contribute?

Just fork the repo and send us a pull request.

Make sure your branch builds without any warnings/issues:

```
mvn clean install -Pqulice
```

## Contributors

  - [@VsSekorin](https://github.com/VsSekorin) as Vseslav Sekorin ([Blog](http://vssekorin.com))
  - [@floreasorin](https://github.com/floreasorin)
  - [@alexandrustoica](https://github.com/alexandrustoica) as Alexandru Stoica

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
FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

