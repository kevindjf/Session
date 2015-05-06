Session
=======

[![Build Status](https://travis-ci.org/kevindjf/Session.svg)](https://travis-ci.org/kevindjf/Session)


Session is a easy to use Persistance Library which use SharedPreference to save datas

#Download

```groovy
compile ('com.github.kevindjf:session:1.0.0@aar'){
    transitive = true
}
```

#Usage
```java
 Session<Book> bookSession = new Session<>(this,Book.class);
 Book book = new Book();
 book.setName("Harry Potter");
 book.setNumber(10);
 bookSession.add(book);
```
Community
--------

Looking for contributors, feel free to fork !

Dependencies
--------

- GSON from Google : [https://github.com/google/gson][gson]

Credits
-------

Author: Kévin De Jesus Ferreira

<a href="https://plus.google.com/+DeJesusFerreiraKévin">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/DeJesusFKvin">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/profile/view?id=297859826">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


License
--------

    Copyright 2015 kevindjf, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
[gson]: https://github.com/google/gson
