Session
=======

[![Build Status](https://travis-ci.org/kevindjf/Session.svg)](https://travis-ci.org/kevindjf/Session)


Session is a easy to use Persistance Library which save datas into SharedPreference.

**Session might not be used to save a huge models list, please use it only if you have to keep a small list**

**for example : save local favorites**

#Download

```groovy
compile ('com.github.kevindjf:session:1.0.0@aar'){
    transitive = true
}
```

#Simple usage

##Create a Session
```java
 Session<Book> bookSession = new Session<>(this,Book.class);
```

Will create or retrieve the last saved Book session

##Add Objects
```java
 bookSession.add(new Book("Harry Potter",10))
            .add(new Book("Hunger Games ",5));
```

##Retrieve saved objects
This book will be saved, and when you use bookSession.getAll(), **even after restarting the activity**
```java
bookSession.getAll();
//will return [Book{"Harry Potter",10},Book{"Hunger Games",5}]
```

##Clear the session
```java
bookSession.clear();
```

#Capabilities

##Create multiple sessions
To remove all session's objects
```java
//will use sharedpreferences key "Book"
Session<Book> bookSession = new Session<>(this,Book.class);

//will use sharedpreferences key "favorites"
Session<Book> bookSessionFavorites = new Session<>(this,Book.class,"favorites");
```

##Retrieve object by attribute
```java
bookSession.getByAttribute("name","Harry Potter");
//will return Book{"Harry Potter",10}
```

##Remove objects
```java
bookSession.removeAtIndex(1);

Book bookHarry = bookSession.getByAttribute("name","Harry Potter");
bookSession.remove(bookHarry);

bookSession.removeAll(bookList);
```

#Customisation

You can extend Session<> to add your own methods
```java
public class CustomBookSession extends Session<Book> {

    public CustomBookSession(Context context) {
        super(context, Book.class);
    }

    public CustomBookSession(Context context, String nomSession) {
        super(context, Book.class, nomSession);
    }

    //and add your custom methods
    public List<Book> getBooksLessThan(int number){
        List<Book> books = getAll();

        List<Book> returnList = new ArrayList<>();
        for (Book book : books){
            if(book.getNumber() < number)
                returnList.add(book);
        }

        return returnList;
    }
}
```

Use
```java
//will use sharedpreferences key "Book"
CustomBookSession session = new CustomBookSession(getApplicationContext());

//will use sharedpreferences key "favorites"
CustomBookSession sessionFavorites = new CustomBookSession(getApplicationContext(),"favorites");

session.getBooksLessThan(9);
//will return [Book{"Hunger Games",5}]
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
