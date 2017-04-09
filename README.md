flatback
========

Crossword solver for site [www.scanword.ru](http://www.scanword.ru), based on helper site [loopy.ru](http://loopy.ru).

## Installation

Via git (or downloaded tarball):

```bash
$ git clone git@github.com:gevorg/Flatback.git
```

## Build using [Apache Maven](http://maven.apache.org/) and run

```bash
$ cd flatback
$ mvn package
$ java -jar target/flatback-1.0.0.jar

Please enter scanword.ru username: [your username]
Please enter scanword.ru password: [your password]
...
```

## Success looks like this

![Success](https://raw.github.com/gevorg/flatback/master/success.png)

## Requirements

 - **[Java](http://www.java.com/)** - Java is a general-purpose, concurrent, class-based, object-oriented computer programming language that is specifically designed to have as few implementation dependencies as possible.
 - **[Apache Maven](http://maven.apache.org/)** - Apache Maven is a software project management and comprehension tool.
 - **[Firefox](https://www.mozilla.org/firefox)** - Firefox Web browser.

## License

The MIT License (MIT)

Copyright (c) 2017 Gevorg Harutyunyan

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
