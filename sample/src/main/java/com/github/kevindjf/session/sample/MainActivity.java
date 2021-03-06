package com.github.kevindjf.session.sample;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kevindjf.session.Session;

import com.github.kevindjf.session.sample.R;
import com.github.kevindjf.session.sample.model.Book;


public class MainActivity extends ActionBarActivity {

    Session<Book> bookSession;
    TextView name_book;
    TextView number_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Book book = bookSession.getByAttribute("name", "Harry Potter");

        name_book.setText(book.getName());
        number_book.setText(book.getNumber() + "");
    }

    private void init() {

        bookSession = new Session<>(this,Book.class);

        bookSession.clear();

        bookSession.add(new Book("Harry Potter",10))
                .add(new Book("Hunger Games ",5));

        name_book = (TextView) findViewById(R.id.name_book);
        number_book = (TextView) findViewById(R.id.number_book);
    }
}
